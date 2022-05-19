import java.io.*;
import java.time.LocalDate;
import java.util.*;


import CasaInteligente.*;
import CasaInteligente.SmartDevices.SmartDevice;
import ComercializadoresEnergia.Fatura;

import static java.lang.System.out;

public class Controller implements Serializable {

    private Comunidade comunidade;
    private int idFatura;
    private LocalDate timeNow;


    /**
     * Construtor por omissão de Controller.
     */
    public Controller() {
        this.comunidade = new Comunidade();
        this.idFatura = 1;
        this.timeNow = LocalDate.now();
    }

    /**
     * Método que limpa a consola.
     * @throws IOException
     * @throws InterruptedException
     */
    public void cls() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    /**
     * Método que liga/desliga todos os SmartDevices de todas as Casas da Comunidade.
     * @param status Booleano que indica se o método liga ou desliga.
     */
    public void ligarDesligarComunidade(boolean status){
        for(CasaInteligente c: this.getComunidade().getCasas().values()){
            ligarDesligarCasa(status, c.getProprietario());
        }
    }

    /**
     * Método que liga/desliga todos os SmartDevices de uma determinada CasaInteligente.
     * @param status Booleano que indica se o método liga ou desliga.
     * @param prop Nome do proprietário da CasaInteligente.
     */
    public void ligarDesligarCasa(boolean status, String prop){
        for(SmartDevice s: this.getComunidade().getCasa(prop).getDevices().values()){
            if(!status) {
                s.turnOff();
            } else {
                s.turnOn();
            }
        }
    }

    /**
     * Método que diz qual a casa que mais gastou num certo período de tempo
     * @param periodo período de tempo
     * @return proprietário da casa que mais gastou durante o período passado como parâmetro
     */
    public Tuple casaQueMaisGastou(LocalDate periodo){
        float max = 0;
        String propMaisGastou = "";
        for(String prop: this.getComunidade().getCasas().keySet()){
            for(Fatura f: this.getComunidade().getCasa(prop).getFaturas()){
                if(f.getDataEmissao().equals(periodo) && f.getTotal() > max){
                    max = f.getTotal();
                    propMaisGastou = prop;
                }

            }
        }
        Tuple t = new Tuple(propMaisGastou, max);
        return t;
    }

    public Tuple comercializadorQueMaisFatura(){
        String comMaisFaturou = "";
        float max = 0;
        for(String com: this.getComunidade().getMercado().keySet()){
            float faturacao = this.getComunidade().getFornecedor(com).calculaFaturacao();
            if(faturacao > max){
                max = faturacao;
                comMaisFaturou = com;
            }
        }

        Tuple t = new Tuple(comMaisFaturou, max);
        return t;
    }

    public Map<String,List<Fatura>> listaFaturas(String fornecedor){
        return this.getComunidade().getFornecedor(fornecedor).getFaturas();
    }

    public List<Tuple> ordenaConsumidores(LocalDate periodo){
        List<Tuple> consumidores_ordenados = new ArrayList<>();

        for(String prop: this.getComunidade().getCasas().keySet()){
            float consumo = 0;
            for(Fatura f: this.getComunidade().getCasa(prop).getFaturas()){
                if(f.getDataEmissao().equals(periodo)){
                    consumo += f.getTotal();
                }
            }
            Tuple t = new Tuple(prop, consumo);
            consumidores_ordenados.add(t);
        }

        consumidores_ordenados.sort(new ConsumeComparator());
        return consumidores_ordenados;
    }




    public void saveProgramText(String textFile) throws IOException {
        SaveProgramText.saveTextMode(this.getComunidade(), textFile);
    }

    public void loadProgramText(String textFile) throws IOException{
        Parser.parse(this.getComunidade(), textFile);
    }


    public void saveProgramObjects(String objectFile) throws IOException {
        FileOutputStream fos =  new FileOutputStream(objectFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public void loadProgramObjects(String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Controller new_controlador = (Controller) ois.readObject();
        this.comunidade.setNomeDaComunidade(new_controlador.comunidade.getNomeDaComunidade());
        new_controlador.comunidade.getCasas().forEach((k,v) -> this.comunidade.setCasas(k,v));
        new_controlador.comunidade.getMercado().forEach((k,v) -> this.comunidade.setMercado(k,v));


        ois.close();
        fis.close();
    }

    public void printComunity(){
        out.println(this.comunidade);
    }


    public void simulacao(String timeSimul, String file) {
        LocalDate timeStart = this.timeNow;

        String[] timeSimulSplitted = timeSimul.split("/");
        Map<String, Float> consumos = new HashMap<>();  // consumo de cada casa associado ao valor do consumo total
        LocalDate timeEnd = LocalDate.of(Integer.parseInt(timeSimulSplitted[2]), Integer.parseInt(timeSimulSplitted[1]), Integer.parseInt(timeSimulSplitted[0]));

        if(file != null){
            int flag = 0;
            TreeMap<String, List<List<String>>> actions;
            actions = SimulParser.simulParser(file);
            LocalDate simulDate;
            for(String k: actions.keySet()) {
                String[] data = k.split("\\.");
                simulDate = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])); // data para a qual vamos saltar
                out.println(simulDate);
                // feita a simulação e guardados os consumos no mapa consumos
                for(String prop: this.comunidade.getCasas().keySet()) {  // iterar sobre cada casa
                    CasaInteligente casaAtual = this.comunidade.getCasa(prop);
                    //Map<String, Float> consumos_temp = new HashMap<>();     // consumo de cada dispositivo da casa
                    //consumos_temp.putAll(casaAtual.simula(this.timeNow, simulDate, comunidade.getFornecedor(casaAtual.getFornecedor())));
                    float valorSimul = casaAtual.simula(this.timeNow, simulDate, comunidade.getFornecedor(casaAtual.getFornecedor()));

                    if (consumos.containsKey(prop)) {
                        consumos.replace(prop, consumos.get(prop) + valorSimul); // criar um novo consumo para um novo dispositivo
                    } else {
                        consumos.put(prop, valorSimul);           // incrementar ao consumo que existia
                    }

                }
                this.timeNow = simulDate;

                for (List<String> l : actions.get(k)) {
                    switch (l.get(2)) {
                        case "turnOff" -> {

                            String proprietario = l.get(0);
                            String id = l.get(1);
                            this.comunidade.getCasa(proprietario).getDevice(id).turnOff();

                        }
                        case "turnOn" -> {
                            String proprietario = l.get(0);
                            String id = l.get(1);
                            this.comunidade.getCasa(proprietario).getDevice(id).turnOn();

                        }
                        case "mudar" -> {
                            String proprietario = l.get(0);
                            String novoFornecedor = l.get(1);
                            this.comunidade.getCasa(proprietario).setFornecedor(novoFornecedor);

                        }
                        case "mudarNumDisp" -> {
                            String proprietario = l.get(0);
                            int numDisps = Integer.parseInt(l.get(1));
                            this.comunidade.getFornecedor(proprietario).setNumeroDispositivos(numDisps);
                        }
                        case "novaLoc" -> {
                            String proprietario = l.get(0);
                            String id = l.get(1);
                            String novaLoc = l.get(3);
                            SmartDevice device = this.comunidade.getCasa(proprietario).getDevice(id).clone();
                            this.comunidade.getCasa(proprietario).removeDevice(id);
                            this.comunidade.getCasa(proprietario).addDevice(device, novaLoc);

                        }
                        case "remover" -> {
                            String proprietario = l.get(0);
                            String id = l.get(1);
                            this.comunidade.getCasa(proprietario).removeDevice(id);
                        }
                    }
                }
            }
        }

        for(CasaInteligente casaAtual: this.comunidade.getCasas().values()) {
            // SALTO PARA O TEMPO FINAL
            float totalSimulacao = casaAtual.simula(this.timeNow, timeEnd, comunidade.getFornecedor(casaAtual.getFornecedor()));


            if (consumos.containsKey(casaAtual.getProprietario())) {
                consumos.replace(casaAtual.getProprietario(), consumos.get(casaAtual.getProprietario()) + totalSimulacao); // criar um novo consumo para um novo dispositivo
            } else {
                consumos.put(casaAtual.getProprietario(), totalSimulacao);           // incrementar ao consumo que existia
            }


            // GERA FATURA
            Fatura f = new Fatura(idFatura, consumos.get(casaAtual.getProprietario()), casaAtual.getFornecedor(), casaAtual.getNIF(), casaAtual.getProprietario(), timeEnd);
            out.println(f);
            casaAtual.addFatura(f);

            String fornecedor = casaAtual.getFornecedor();
            this.comunidade.getFornecedor(fornecedor).adicionaFatura(casaAtual.getProprietario(), f);
            this.idFatura += 1;

            this.timeNow = timeStart;
        }

        this.timeNow = timeEnd;

        // NO FIM COLOCAR O CONSUMO DE TODOS OS DISPOSITIVOS A 0
        for(CasaInteligente c : this.comunidade.getCasas().values()){
            for(SmartDevice s : c.getDevices().values()){
                s.setConsumption(0);
            }
        }
    }

    /*
    public void simulacao(String timeSimul, String file) {
        LocalDate timeStart = this.timeNow;

        String[] timeSimulSplitted = timeSimul.split("/");
        LocalDate timeEnd = LocalDate.of(Integer.parseInt(timeSimulSplitted[2]), Integer.parseInt(timeSimulSplitted[1]), Integer.parseInt(timeSimulSplitted[0]));
        int flag = 0;
        TreeMap<String, List<List<String>>> actions;
        actions = SimulParser.simulParser(file);
        LocalDate simulDate;

        for(String prop: this.comunidade.getCasas().keySet()) {  // iterar sobre cada casa
            Map<String, Float> consumos = new HashMap<>();  // consumo de cada dispositivo para depois passar ao construtor da fatura
            CasaInteligente casaAtual = this.comunidade.getCasa(prop);

            for(String k: actions.keySet()) {
                String[] data = k.split("\\.");
                simulDate = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])); // data para a qual vamos saltar
                out.println(simulDate);
                // feita a simulação e guardados os consumos no mapa consumos
                    Map<String, Float> consumos_temp = new HashMap<>();
                    consumos_temp.putAll(casaAtual.simula(this.timeNow, simulDate, comunidade.getFornecedor(casaAtual.getFornecedor()) ));

                    for(String id: consumos_temp.keySet()){

                //        out.println(consumos_temp.get(id));

                        if(consumos.containsKey(id) ){
                            consumos.replace(id, consumos.get(id) + consumos_temp.get(id)); // criar um novo consumo para um novo dispositivo
                        } else {
                            consumos.put(id, consumos_temp.get(id));           // incrementar ao consumo que existia
                        }
                    }
                    this.timeNow = simulDate;

                if(flag == 0) {
                    for (List<String> l : actions.get(k)) {
                        switch (l.get(2)) {
                            case "turnOff" -> {
                                if (prop.equals(l.get(0))) {
                                    String proprietario = l.get(0);
                                    String id = l.get(1);
                                    this.comunidade.getCasa(proprietario).getDevice(id).turnOff();
                                }
                            }
                            case "turnOn" -> {
                                if (prop.equals(l.get(0))) {
                                    String proprietario = l.get(0);
                                    String id = l.get(1);
                                    this.comunidade.getCasa(proprietario).getDevice(id).turnOn();
                                }
                            }
                            case "mudar" -> {
                                if (prop.equals(l.get(0))) {
                                    String proprietario = l.get(0);
                                    String novoFornecedor = l.get(1);
                                    this.comunidade.getCasa(proprietario).setFornecedor(novoFornecedor);
                                }
                            }
                            case "mudarNumDisp" -> {
                                String proprietario = l.get(0);
                                int numDisps = Integer.parseInt(l.get(1));
                                this.comunidade.getFornecedor(proprietario).setNumeroDispositivos(numDisps);
                            }
                            case "novaLoc" -> {
                                if (prop.equals(l.get(0))) {
                                    String proprietario = l.get(0);
                                    String id = l.get(1);
                                    String novaLoc = l.get(3);
                                    SmartDevice device = this.comunidade.getCasa(proprietario).getDevice(id).clone();
                                    this.comunidade.getCasa(proprietario).removeDevice(id);
                                    this.comunidade.getCasa(proprietario).addDevice(device, novaLoc);
                                }
                            }
                            case "remover" -> {
                                if (prop.equals(l.get(0))) {
                                    String proprietario = l.get(0);
                                    String id = l.get(1);
                                    this.comunidade.getCasa(proprietario).removeDevice(id);
                                }
                            }
                        }
                    }
                }
            }
            flag = 1;

            // SALTO PARA O TEMPO FINAL
            Map<String, Float> consumos_temp2 = new HashMap<>();
            consumos_temp2.putAll(casaAtual.simula(this.timeNow, timeEnd, comunidade.getFornecedor(casaAtual.getFornecedor()) ));

            for(String id: consumos_temp2.keySet()){
                if(consumos.containsKey(id) ){
                    consumos.replace(id, consumos.get(id) + consumos_temp2.get(id)); // criar um novo consumo para um novo dispositivo
                } else {
                    consumos.put(id, consumos_temp2.get(id));           // incrementar ao consumo que existia
                }
            }

            // GERA FATURA
            Fatura f = new Fatura(idFatura, consumos, casaAtual.getFornecedor(), casaAtual.getNIF(), casaAtual.getProprietario(), timeEnd);
            out.println(f);
            casaAtual.addFatura(f);

            String fornecedor = casaAtual.getFornecedor();
            this.comunidade.getFornecedor(fornecedor).adicionaFatura(prop, f);
            this.idFatura += 1;

            this.timeNow = timeStart;
        }
        this.timeNow = timeEnd;

        // NO FIM COLOCAR O CONSUMO DE TODOS OS DISPOSITIVOS A 0
        for(CasaInteligente c : this.comunidade.getCasas().values()){
            for(SmartDevice s : c.getDevices().values()){
                s.setConsumption(0);
            }
        }
    }
    */

    public Comunidade getComunidade() {
        return this.comunidade;
    }

    public TreeMap<String, List<List<String>>> getActions(String file){
        return SimulParser.simulParser(file);
    }



}
