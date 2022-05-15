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
    private TreeMap<String, List<List<String>>> actions;
    private int idFatura;

    public Controller(Comunidade comunidade) {
        this.comunidade = new Comunidade(comunidade);
        this.actions = new TreeMap<>();
        this.idFatura = 1;
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

    //    out.println(this.comunidade.getCasa("Vicente de Carvalho Castro").toString());

        ois.close();
        fis.close();
    }

    public void printComunity(){
        out.println(this.comunidade);
    }


    public void simulacao(Comunidade comunidade) throws IOException, InterruptedException {
        this.actions = SimulParser.simulParser();
        LocalDate simulDate;
    //    List<Fatura> faturas = new ArrayList<>();
        for(String k: this.actions.keySet()){
            String[] data =  k.split("\\.");
            simulDate = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            for(String prop: comunidade.getCasas().keySet()){
                CasaInteligente casaAtual = comunidade.getCasa(prop);
                Comercializador fornecedorAtual = comunidade.getMercado().get(casaAtual.getFornecedor());
                fornecedorAtual.geraFatura(idFatura, casaAtual, simulDate);
            //    faturas = fornecedorAtual.getListaFaturas(casaAtual.getProprietario());
                this.idFatura += 1;

            }

            for(List<String> l : this.actions.get(k)){
                switch(l.get(2)){
                    case "turnOff" -> {
                        String proprietario = l.get(0);
                        String id = l.get(1);
                        comunidade.getCasa(proprietario).getDevice(id).turnOff();
                    }
                    case "turnOn" -> {
                        String proprietario = l.get(0);
                        out.println(proprietario);
                        String id = l.get(1);
                        comunidade.getCasa(proprietario).getDevice(id).turnOn();
                    }
                    case "mudar" -> {
                        String proprietario = l.get(0);
                        String novoFornecedor = l.get(1);
                        comunidade.getCasa(proprietario).setFornecedor(novoFornecedor);
                    }
                    case "mudarNumDisp" -> {
                        String proprietario = l.get(0);
                        int numDisps = Integer.parseInt(l.get(1));
                        comunidade.getFornecedor(proprietario).setNumeroDispositivos(numDisps);
                    }
                    case "novaLoc" -> {
                        String proprietario = l.get(0);
                        String id = l.get(1);
                        String novaLoc = l.get(3);
                        SmartDevice device = comunidade.getCasa(proprietario).getDevice(id).clone();
                        comunidade.getCasa(proprietario).removeDevice(id);
                        comunidade.getCasa(proprietario).addDevice(device, novaLoc);
                    }
                    case "remover" -> {
                        String proprietario = l.get(0);
                        String id = l.get(1);
                        comunidade.getCasa(proprietario).removeDevice(id);
                    }
                }
            }

        }

        for(String prop: comunidade.getCasas().keySet()){
            CasaInteligente c = this.comunidade.getCasa(prop);
            for(Fatura f: c.getFaturas()){
                out.print("Proprietario: ");
                out.println(c.getProprietario());
                out.println(f.toString());
            }

        }


        //    out.println(comunidade);

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
