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


    public Controller(Comunidade comunidade) {
        this.comunidade = new Comunidade(comunidade);
        this.idFatura = 1;
        this.timeNow = LocalDate.now();
    }

    public void cls() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    /**
     * Método que diz qual a casa que mais gastou num certo período de tempo
     * @param periodo período de tempo
     * @return proprietário da casa que mais gastou durante o período passado como parâmetro
     */
    public Tuple casaQueMaisGastou(LocalDate periodo){
        float max = 0;
        String propMaisGastou = "";
        for(String prop: this.comunidade.getCasas().keySet()){
            for(Fatura f: this.comunidade.getCasa(prop).getFaturas()){
                if(f.getDataEmissao().equals(periodo) && f.valorTotal() > max){
                    max = f.valorTotal();
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
        for(String com: this.comunidade.getMercado().keySet()){
            float faturacao = this.comunidade.getFornecedor(com).calculaFaturacao();
            if(faturacao > max){
                max = faturacao;
                comMaisFaturou = com;
            }
        }

        Tuple t = new Tuple(comMaisFaturou, max);
        return t;
    }

    public Map<String,List<Fatura>> listaFaturas(String fornecedor){
        return this.comunidade.getFornecedor(fornecedor).getFaturas();
    }

    public List<Tuple> ordenaConsumidores(LocalDate periodo){
        List<Tuple> consumidores_ordenados = new ArrayList<>();

        for(String prop: this.comunidade.getCasas().keySet()){
            float consumo = 0;
            for(Fatura f: this.comunidade.getCasa(prop).getFaturas()){
                if(f.getDataEmissao().equals(periodo)){
                    consumo += f.valorTotal();
                }
            }
            Tuple t = new Tuple(prop, consumo);
            consumidores_ordenados.add(t);
        }

        consumidores_ordenados.sort(new ConsumeComparator());
        return consumidores_ordenados;
    }




    public void saveProgramText(String textFile) throws IOException {
        SaveProgramText.saveTextMode(this.comunidade, textFile);
    }

    public void loadProgramText(String textFile) throws IOException{
        Parser.parse(this.comunidade, textFile);
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
        out.println(timeStart);

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

    public Comunidade getComunidade() {
        return this.comunidade;
    }

    public TreeMap<String, List<List<String>>> getActions(String file){
        return SimulParser.simulParser(file);
    }



}
