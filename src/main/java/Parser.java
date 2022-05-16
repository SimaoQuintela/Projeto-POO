import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartSpeaker;
import ComercializadoresEnergia.Comercializador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Parser {
        public static void parse(Comunidade comunidade, String file){
            List<String> linhas = lerFicheiro(file);
            int id_generator = 0;
            int imposto = 0;
            int valorBase = 0;

            String[] linhaPartida;
            String divisaoMaisRecente = null;
            CasaInteligente casaMaisRecente = null;
            for (String linha : linhas) {
                linhaPartida = linha.split(":", 2);
                String[] campos = linhaPartida[1].split(",");
                switch (linhaPartida[0]) {
                    case "Imposto" -> {
                        imposto = Integer.parseInt(linhaPartida[1]);
                    }

                    case "ValorBase" -> {
                        valorBase = Integer.parseInt(linhaPartida[1]);
                    }

                    case "Fornecedor" -> {
                        String nomeEmpresa = campos[0];
                        int numeroDispositivos = Integer.parseInt(campos[1]);
                        Comercializador comercializador = new Comercializador(nomeEmpresa, numeroDispositivos, valorBase, imposto);
                        comunidade.setMercado(nomeEmpresa, comercializador);
                    }

                    case "Casa" -> {
                        casaMaisRecente = parseCasa(campos);
                        String proprietario = campos[0];
                        comunidade.setCasas(proprietario, casaMaisRecente);
                    }

                    case "Divisao" -> {
                        if (casaMaisRecente == null) out.println("Linha inválida.");
                        divisaoMaisRecente = linhaPartida[1];
                        assert casaMaisRecente != null;
                        comunidade.getCasa(casaMaisRecente.getProprietario()).addRoom(divisaoMaisRecente);
                    }

                    case "SmartBulb" -> {
                        if (divisaoMaisRecente == null) out.println("Linha inválida.");
                        SmartBulb sd = parseSmartBulb(campos, Integer.toString(id_generator), valorBase);
                        assert casaMaisRecente != null;
                        comunidade.getCasa(casaMaisRecente.getProprietario()).addDevice(sd, divisaoMaisRecente);
                        id_generator += 1;
                    }

                    case "SmartCamera" -> {
                        if (divisaoMaisRecente == null) out.println("Linha inválida.");
                        SmartCamera sc = parseSmartCamera(campos, Integer.toString(id_generator), valorBase);
                        assert casaMaisRecente != null;
                        comunidade.getCasa(casaMaisRecente.getProprietario()).addDevice(sc, divisaoMaisRecente);
                        id_generator += 1;
                    }

                    case "SmartSpeaker" -> {
                        if (divisaoMaisRecente == null) out.println("Linha inválida.");
                        SmartSpeaker sp = parseSmartSpeaker(campos, Integer.toString(id_generator), valorBase);
                        assert casaMaisRecente != null;
                        comunidade.getCasa(casaMaisRecente.getProprietario()).addDevice(sp, divisaoMaisRecente);
                        id_generator += 1;
                    }

                    default -> {
                        out.println("Linha invalida 2.");
                        out.println(linha);
                    }
                }
            }
        }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public static SmartBulb parseSmartBulb(String[] s, String id, int valorBase) {
        int tone = switch (s[0]) {
            case "Warm" -> 80;
            case "Neutral" -> 60;
            case "Cold" -> 40;
            default -> 0;
        };

        if (tone == 0) out.println("Linha inválida.");

        int diametro = Integer.parseInt(s[1]);
        float consumo = Float.parseFloat(s[2]);
        if(consumo < valorBase){
            consumo = valorBase;
        }

        SmartBulb new_bulb = new SmartBulb(id, true, tone, diametro, consumo, 5);
        return new_bulb.clone();
    }

    public static SmartCamera parseSmartCamera(String[] campos, String id, int valorBase){

            String[] splitByRightParenthesis = campos[0].split("x", 2);
            String xRes = splitByRightParenthesis[0].substring(1);
            String yRes = splitByRightParenthesis[1].substring(0, splitByRightParenthesis[1].length() -1);

            int tamanho = Integer.parseInt(campos[1]);
            float consumo = Float.parseFloat(campos[2]);

            if(consumo < valorBase){
                consumo = valorBase;
            }
            SmartCamera new_camera = new SmartCamera(id, true, Integer.parseInt(xRes), Integer.parseInt(yRes), tamanho, consumo, 10);
            return new_camera.clone();
    }

    public static SmartSpeaker parseSmartSpeaker(String[] campos, String id, int valorBase) {
            int volume = Integer.parseInt(campos[0]);
            String channel = campos[1];
            String brand = campos[2];
            float consumo = Float.parseFloat(campos[3]);

            if(consumo < valorBase){
                consumo = valorBase;
            }
            SmartSpeaker new_speaker = new SmartSpeaker(id, true, channel, volume, brand, consumo, 7);
            return new_speaker.clone();
    }
    public static CasaInteligente parseCasa(String[] input){
            String nome = input[0];
            int NIF = Integer.parseInt(input[1]);
            String comercializador = input[2];

            return new CasaInteligente(nome, NIF, comercializador);
        }


}
