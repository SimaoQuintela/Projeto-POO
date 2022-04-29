import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartSpeaker;
import ComercializadoresEnergia.Comercializador;

import static java.lang.System.out;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {

        public static void main(String args[]){
            Comunidade comunidade = new Comunidade("Rumo ao 20");
            List<String> linhas = lerFicheiro("dados.txt");
            int id_generator = 0;

            String[] linhaPartida;
            String divisaoMaisRecente = null;
            CasaInteligente casaMaisRecente = null;
            for (String linha : linhas) {
                linhaPartida = linha.split(":", 2);
                String[] campos = linhaPartida[1].split(",");
                out.println(linhaPartida[1]);
                switch(linhaPartida[0]){
                    case "Fornecedor":
                        String nomeEmpresa = linhaPartida[1];
                        Comercializador comercializador = new Comercializador(nomeEmpresa);
                        comunidade.setMercado(nomeEmpresa, comercializador);
                        break;
                    case "Casa":
                        casaMaisRecente = parseCasa(campos).clone();
                        String proprietario = campos[1];
                        comunidade.setCasas(proprietario, casaMaisRecente);
                        break;
                    case "Divisao":
                        if (casaMaisRecente == null) System.out.println("Linha inválida.");
                        divisaoMaisRecente = linhaPartida[1];
                        casaMaisRecente.addRoom(divisaoMaisRecente);
                        break;
                    case "SmartBulb":
                        if (divisaoMaisRecente == null) System.out.println("Linha inválida.");
                        SmartBulb sd = parseSmartBulb(campos, Integer.toString(id_generator));
                        casaMaisRecente.addDevice(sd, divisaoMaisRecente);
                        id_generator +=1;
                        break;
                    case "SmartCamera":
                        if (divisaoMaisRecente == null) System.out.println("Linha inválida.");
                        SmartCamera sc = parseSmartCamera(campos, Integer.toString(id_generator));
                        casaMaisRecente.addDevice(sc, divisaoMaisRecente);
                        id_generator +=1;
                        break;
                    case "SmartSpeaker":
                        if (divisaoMaisRecente == null) System.out.println("Linha inválida.");
                        SmartSpeaker sp = parseSmartSpeaker(campos, Integer.toString(id_generator));
                        casaMaisRecente.addDevice(sp, divisaoMaisRecente);
                        id_generator +=1;
                        break;
                    default:
                        System.out.println("Linha inválida.");
                        break;
                }
            }

            System.out.println("done!");
        }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public static SmartBulb parseSmartBulb(String[] s, String id) {
        int tone = 0;

        switch (s[0]){
            case "Warm":
                tone = SmartBulb.WARM;
                break;
            case "Neutral":
                tone = SmartBulb.NEUTRAL;
                break;
            case "Cold":
                tone = SmartBulb.COLD;
                break;
        }
        if (tone == 0) System.out.println("Linha inválida.");

        int diametro = Integer.parseInt(s[1]);
        float consumo = Float.parseFloat(s[2]);

        SmartBulb new_bulb = new SmartBulb(id, false, tone, diametro, consumo, 5);
        return new_bulb.clone();
    }

    public static SmartCamera parseSmartCamera(String[] campos, String id){
            String[] splitByParenthesis = campos[0].split("()", 1);
            String[] splitByXandY = campos[0].split("x", 1);
            int x = Integer.parseInt(splitByXandY[0]);
            int y = Integer.parseInt(splitByXandY[1]);
            int tamanho = Integer.parseInt(campos[1]);
            float consumo = Float.parseFloat(campos[2]);

            SmartCamera new_camera = new SmartCamera(id, false, x, y, tamanho, consumo, 10);
            return new_camera.clone();
    }

    public static SmartSpeaker parseSmartSpeaker(String[] campos, String id) {
        int volume = Integer.parseInt(campos[0]);
        String channel = campos[1];
        String brand = campos[2];
        float consumption = Float.parseFloat(campos[3]);

        SmartSpeaker new_speaker = new SmartSpeaker(id, false, channel, volume, brand, consumption, 7);
        return new_speaker.clone();
    }

        public static CasaInteligente parseCasa(String[] input){
            String nome = input[0];
            long NIF = Long.parseLong(input[1]);
            String comercializador = input[2];


            return new CasaInteligente(nome, NIF, comercializador);
        }


}
