import java.io.*;
import java.time.LocalDate;
import java.util.*;
import CasaInteligente.*;
import CasaInteligente.SmartDevices.SmartDevice;
import ComercializadoresEnergia.Comercializador;
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

    public TreeMap<String, List<List<String>>> getActions(){
        return SimulParser.simulParser();
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


    public void simulacao(String timeSimul) {
        LocalDate timeStart = LocalDate.now();
        String[] timeSimulSplitted = timeSimul.split("/");
        LocalDate timeEnd = LocalDate.of(Integer.parseInt(timeSimulSplitted[2]), Integer.parseInt(timeSimulSplitted[1]), Integer.parseInt(timeSimulSplitted[0]));

        TreeMap<String, List<List<String>>> actions;
        actions = SimulParser.simulParser();
        LocalDate simulDate;

        for(String prop: this.comunidade.getCasas().keySet()) {  // iterar sobre cada casa
            Map<String, Float> consumos = new HashMap<>();  // consumo de cada dispositivo para depois passar ao construtor da fatura
            CasaInteligente casaAtual = this.comunidade.getCasa(prop);

            for(String k: actions.keySet()) {
                String[] data = k.split("\\.");
                simulDate = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])); // data para a qual vamos saltar
                // feita a simulação e guardados os consumos no mapa consumos
                Map<String, Float> consumos_temp = new HashMap<>();
                consumos_temp.putAll(casaAtual.simula(this.timeNow, simulDate, comunidade.getFornecedor(casaAtual.getFornecedor()) ));

                for(String id: consumos_temp.keySet()){
                    if(consumos.containsKey(id) ){
                        consumos.replace(id, consumos.get(id) + consumos_temp.get(id)); // criar um novo consumo para um novo dispositivo
                    } else {
                        consumos.put(id, consumos_temp.get(id));           // incrementar ao consumo que existia
                    }
                }
                this.timeNow = simulDate;

                for(List<String> l : actions.get(k)){
                    switch(l.get(2)){
                        case "turnOff" -> {
                            if(prop.equals(l.get(0))) {
                                String proprietario = l.get(0);
                                String id = l.get(1);
                                this.comunidade.getCasa(proprietario).getDevice(id).turnOff();
                            }
                        }
                        case "turnOn" -> {
                            if(prop.equals(l.get(0))) {
                                String proprietario = l.get(0);
                                String id = l.get(1);
                                this.comunidade.getCasa(proprietario).getDevice(id).turnOn();
                            }
                        }
                        case "mudar" -> {
                            if(prop.equals(l.get(0))) {
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
                            if(prop.equals(l.get(0))) {
                                String proprietario = l.get(0);
                                String id = l.get(1);
                                String novaLoc = l.get(3);
                                SmartDevice device = this.comunidade.getCasa(proprietario).getDevice(id).clone();
                                this.comunidade.getCasa(proprietario).removeDevice(id);
                                this.comunidade.getCasa(proprietario).addDevice(device, novaLoc);
                            }
                        }
                        case "remover" -> {
                            if(prop.equals(l.get(0))) {
                                String proprietario = l.get(0);
                                String id = l.get(1);
                                this.comunidade.getCasa(proprietario).removeDevice(id);
                            }
                        }
                    }
                }
            }

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
            this.timeNow = timeEnd;

            // GERA FATURA
            Fatura f = new Fatura(idFatura, consumos, casaAtual.getFornecedor(), casaAtual.getNIF(), casaAtual.getProprietario(), timeEnd);
            out.println(f);
            casaAtual.addFatura(f);

            String fornecedor = casaAtual.getFornecedor();
            this.comunidade.getFornecedor(fornecedor).adicionaFatura(prop, f);
            this.idFatura += 1;

            this.timeNow = timeStart;
        }

        // NO FIM COLOCAR O CONSUMO DE TODOS OS DISPOSITIVOS A 0
    }

    public Comunidade getComunidade() {
        return this.comunidade;
    }

/*
    public void gravarEstado(String ficheiro) throws IOException {
        SaveGame.gravar(CasasInteligentes, SmartDevices, ficheiro);
    }

    public void lerCasasInteligentesObjetos(String ficheiro) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(ficheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Controller controladorLido = (Controller) ois.readObject();

        controladorLido.CasasInteligentes.forEach((k,v)->this.CasasInteligentes.put(k,v));
        this.SmartDevices.addAll(controladorLido.SmartDevices);

        ois.close();
        fis.close();
    }

    public void gravarEstadoObjetos(String ficheiro) throws IOException {
        FileOutputStream fos = new FileOutputStream(ficheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        oos.close();
        fos.close();
    }

*/




}
