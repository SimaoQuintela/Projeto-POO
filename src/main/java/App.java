import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartSpeaker;
import ComercializadoresEnergia.Comercializador;


import java.io.FileNotFoundException;

import java.util.Scanner;
import static java.lang.System.out;

public class App {
    static Scanner scan = new Scanner(System.in);
    static Comunidade comunidade = new Comunidade("Gualtar");

    public static void main(String args[]) throws InterruptedException, FileNotFoundException {
        Comunidade comunidade = new Comunidade("Jackson");
        Parser.parse(comunidade);
        out.println("---- Inicio dos testes ----");
        out.println(comunidade);


    }

}