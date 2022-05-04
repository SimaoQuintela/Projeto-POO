import CasaInteligente.*;
import CasaInteligente.SmartDevices.SmartDevice;
import ComercializadoresEnergia.*;


import java.io.FileNotFoundException;
import java.net.SocketImpl;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class App {
    static Scanner scan = new Scanner(System.in);
    static Comunidade comunidade = new Comunidade("Gualtar");

    public static void main(String args[]) throws InterruptedException, FileNotFoundException {
        Comunidade comunidade = new Comunidade("Jackson");
        Parser.parse(comunidade);
        TreeMap<String, List<List<String>>> actions = new TreeMap<>();
        out.println("---- Inicio dos testes ----");
    //    out.println(comunidade);
        actions = SimulParser.simulParser();
        simulacao(comunidade, actions);

        // Avançar no tempo
        LocalDateTime teste = LocalDateTime.now();
        LocalDateTime novo = LocalDateTime.of(2022, 5, 2, 20, 10).minusMinutes(10); // ano - mes - dia - hora - minuto - segundo
        out.println(ChronoUnit.HOURS.between(novo, teste));
    }

    /**
     */
    public static void simulacao(Comunidade comunidade, TreeMap<String, List<List<String>>> actions){
        LocalDateTime simulDate;
        int idFatura = 1;
        List<Fatura> faturas = new ArrayList<>();
        for(String k: actions.keySet()){
            String[] data =  k.split("\\.");
            simulDate = LocalDateTime.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));

            for(String prop: comunidade.getCasas().keySet()){
                CasaInteligente casaAtual = comunidade.getCasa(prop).clone();
                Comercializador fornecedorAtual = comunidade.getMercado().get(casaAtual.getFornecedor());
                fornecedorAtual.geraFatura(idFatura, casaAtual, simulDate);
                faturas = fornecedorAtual.getListaFaturas(casaAtual.getProprietario());
                idFatura += 1;

            }
            for(List<String> l : actions.get(k)){
                out.println(l);
                switch(l.get(2)){
                    case "turnOff" -> { // ESTE TÁ A FUNCIONAR BEM
                        String proprietario = l.get(0);
                        String id = l.get(1);
                        comunidade.getCasa(proprietario).getDevice(id).turnOff();
                    }
                    case "turnOn" -> { // ESTE TÁ A FUNCIONAR BEM
                        String proprietario = l.get(0);
                        String id = l.get(1);
                        comunidade.getCasa(proprietario).getDevice(id).turnOn();
                    }
                    case "mudar" -> {  // ESTE TÁ A FUNCIONAR BEM
                        String proprietario = l.get(0);
                        String novoFornecedor = l.get(1);
                        comunidade.getCasa(proprietario).setFornecedor(novoFornecedor);
                    }
                    case "mudarNumDisp" -> { // ESTE TÁ A FUNCIONAR BEM
                        String proprietario = l.get(0);
                        int numDisps = Integer.parseInt(l.get(1));
                        comunidade.getFornecedor(proprietario).setNumeroDispositivos(numDisps);
                    }
                    case "novaLoc" -> { // Não está a funcionar bem
                        String proprietario = l.get(0);
                        String id = l.get(1);
                        String novaLoc = l.get(3);
                        SmartDevice device = comunidade.getCasa(proprietario).getDevice(id);
                        comunidade.getCasa(proprietario).removeDevice(id);
                        comunidade.getCasa(proprietario).addDevice(device, novaLoc);
                    }
                    case "remover" -> { // Não está a funcionar bem, tem a ver com localizações
                        String proprietario = l.get(0);
                        String id = l.get(1);
                        comunidade.getCasa(proprietario).removeDevice(id);
                    }
                }
            }

           // for(Fatura f: faturas){
           //     out.println(f.toString());
           // }
        }
        out.println(" SIMULAÇÃO EFETUADA ");

    //    out.println(comunidade);

    }


}