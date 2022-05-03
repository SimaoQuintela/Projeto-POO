import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartSpeaker;
import ComercializadoresEnergia.Comercializador;


import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.out;

public class App {
    static Scanner scan = new Scanner(System.in);
    static Comunidade comunidade = new Comunidade("Gualtar");

    public static void main(String args[]) throws InterruptedException, FileNotFoundException {
        Comunidade comunidade = new Comunidade("Jackson");
        Parser.parse(comunidade);
        TreeMap<String, List<List<String>>> actions = new TreeMap<>();
        out.println("---- Inicio dos testes ----");
        out.println(comunidade);

        actions = SimulParser.simulParser();
        for(String k: actions.keySet()){
            out.print(k.toString() + " : ");
            for(List<String> l2 : actions.get(k)){
                out.print(l2.toString() + " | ");
            }
            out.println();
        }

        // Avançar no tempo
        LocalDateTime teste = LocalDateTime.now();
        LocalDateTime novo = LocalDateTime.of(2022, 05, 02, 20, 10).minusMinutes(10); // ano - mes - dia - hora - minuto - segundo
        out.println(ChronoUnit.HOURS.between(novo, teste));

    }


}