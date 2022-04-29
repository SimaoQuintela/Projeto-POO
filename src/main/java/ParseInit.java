import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartSpeaker;
import ComercializadoresEnergia.Comercializador;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.System.out;

//PROVAVELMENTE POSSO APAGAR ESTA CLASSE
public class ParseInit {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("testes.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        Comunidade comunidade = new Comunidade("Lord Eder forever");
        ArrayList<String> global_data = new ArrayList<>();

        String line_readed;
        while(scan.hasNextLine()){
            line_readed = scan.nextLine();
            lines.add(line_readed);
        }

        ArrayList<String[]> line_splitted_by_space = new ArrayList<>();
        for(String s: lines){
            line_splitted_by_space.add(s.split(" "));
        }

        // prints the string splitted by space
        /*
        for(String[] s: line_splitted_by_space){
            out.println(Arrays.toString(s));
        }
        */
        for(String[] line: line_splitted_by_space){
            switch (line[0]){
                case "casa":
                    parse_casa(line, comunidade);
                    break;
                case "comercializador":
                    parse_comercializador(global_data, line, comunidade);
                    break;
                case "SmartBulb":
                    parse_smart_bulb(line, comunidade);
                    break;
                case "SmartSpeaker":
                    parse_smart_speaker(line, comunidade);
                    break;
                case "SmartCamera":
                    parse_smart_camera(line, comunidade);
                    break;
                default:
                    global_data.add(line[0]);
                    out.print("Something went wrong at line: ");
                    out.println(Arrays.toString(line));
                    break;
            }
        }

        testa_programa(comunidade);

    }

    // Classe 100% bem interpretada em ficheiro, conseguimos inicializar uma casa "vazia" até
    public static void parse_casa(String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();
        String proprietario = "";
        int numeroPorta = 0;
        long NIF = 0;
        String morada = "";
        String fornecedor = "";

        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        for(String[] l: line_splitted_by_2dot){
            switch (l[0]){
                case "proprietario":
                    proprietario = l[1].replace("_", " ");
                    break;
                case "porta":
                    numeroPorta = Integer.parseInt(l[1]);
                    break;
                case "NIF":
                    NIF = Long.parseLong(l[1]);
                    break;
                case "morada":
                    morada = l[1].replace("_", " ");
                    break;
                case "fornecedor":
                    fornecedor = l[1];
                    break;
                default:
                    break;
            }
        }

        CasaInteligente casa = new CasaInteligente(proprietario, numeroPorta, NIF, morada, fornecedor);
        comunidade.setCasas(morada, casa);
    }

    public static void parse_smart_bulb(String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();
        String morada = "";
        String espaco = "";
        String id = "";
        boolean status = false;
        int numeroPorta = 0;
        float custoInstalacao = 0;

        int tone = 0;
        int dimensions = 0;

        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        //  Dar print ao array partido
        /*
        out.println("Print ah String partida pelos :");
        for(String[] l : line_splitted_by_2dot){
            out.println(Arrays.toString(l));
        }
         */
        for(String[] l: line_splitted_by_2dot){
            switch (l[0]){
                case "morada":
                    morada = l[1].replace("_", " ");
                    break;
                case "porta":
                    numeroPorta = Integer.parseInt(l[1]);
                    break;
                case "espaco":
                    espaco = l[1];
                    break;
                case "id":
                    id = l[1];
                    break;
                case "on/off":
                    if(l[1].equals("on"))
                        status = true;
                    break;
                case "tone":
                    if(l[1].equals("WARM"))
                        tone = SmartBulb.WARM;
                    else if(l[1].equals("NEUTRAL"))
                        tone = SmartBulb.NEUTRAL;
                    else
                        tone = SmartBulb.COLD;
                    break;
                case "dimensions":
                    dimensions = Integer.parseInt(l[1]);
                    break;
                case "custo":
                    custoInstalacao = Float.parseFloat(l[1]);
                default:
                    break;

            }
        }
/*
        out.println("---- comunidade.getCasa(morada) ----");
        out.println(comunidade.getCasa(morada).toString());

        out.println("---- comunidade.getCasa(morada).getNumeroDePorta() ----");
        out.println(comunidade.getCasa(morada).getNumeroDePorta());
*/
        if(comunidade.getCasa(morada) != null && comunidade.getCasa(morada).getNumeroDePorta() == numeroPorta){
            SmartBulb bulb = new SmartBulb(id, status, tone, dimensions, LocalDateTime.now(), custoInstalacao);
            out.println("Criacao de bulb com sucesso!");
            comunidade.getCasa(morada).addDevice(bulb, espaco);
        } else {
            out.println("Nao consegui criar a Smart Bulb");
        }

    }

    public static void parse_smart_speaker(String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();
        String morada = "";
        String espaco = "";
        String id = "";
        boolean status = false;
        int numeroPorta = 0;
        float custoInstalacao = 0;

        int volume = 0;
        String channel = "";
        String brand = "";

        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        for(String[] l: line_splitted_by_2dot){
            switch (l[0]){
                case "morada":
                    morada = l[1].replace("_", " ");
                    break;
                case "porta":
                    numeroPorta = Integer.parseInt(l[1]);
                    break;
                case "espaco":
                    espaco = l[1];
                    break;
                case "id":
                    id = l[1];
                    break;
                case "on/off":
                    if(l[1].equals("on"))
                        status = true;
                    break;
                case "volume":
                    volume = Integer.parseInt(l[1]);
                    break;
                case "channel":
                    channel = l[1];
                    break;
                case "brand":
                    brand = l[1];
                    break;
                case "custo":
                    custoInstalacao = Float.parseFloat(l[1]);
                    break;
                default:
                    break;

            }
        }

        if(comunidade.getCasa(morada) != null && comunidade.getCasa(morada).getNumeroDePorta() == numeroPorta){
            SmartSpeaker speaker = new SmartSpeaker(id, status, channel, volume, brand, custoInstalacao);
            out.println("Criacao de speaker com sucesso!");
            comunidade.getCasa(morada).addDevice(speaker, espaco);
        } else {
            out.println("Nao consegui criar o Smart Speaker");
        }

    }

    public static void parse_smart_camera(String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();
        String morada = "";
        String espaco = "";
        String id = "";
        boolean status = false;
        int numeroPorta = 0;
        float custoInstalacao = 0;

        int xRes = 0;
        int yRes = 0;
        int fileSize = 0;


        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        for(String[] l: line_splitted_by_2dot){
            switch (l[0]){
                case "morada":
                    morada = l[1].replace("_", " ");
                    break;
                case "porta":
                    numeroPorta = Integer.parseInt(l[1]);
                    break;
                case "espaco":
                    espaco = l[1];
                    break;
                case "id":
                    id = l[1];
                    break;
                case "on/off":
                    if(l[1].equals("on"))
                        status = true;
                    break;
                case "xRes":
                    xRes = Integer.parseInt(l[1]);
                    break;
                case "yRes":
                    yRes = Integer.parseInt(l[1]);
                    break;
                case "fileSize":
                    fileSize = Integer.parseInt(l[1]);
                    break;
                case "custo":
                    custoInstalacao = Float.parseFloat(l[1]);
                    break;
                default:
                    break;

            }
        }

        if(comunidade.getCasa(morada) != null && comunidade.getCasa(morada).getNumeroDePorta() == numeroPorta){
            SmartCamera camera = new SmartCamera(id, status, xRes, yRes, fileSize, custoInstalacao);
            out.println("Criacao de camera com sucesso!");
            comunidade.getCasa(morada).addDevice(camera, espaco);
        } else {
            out.println("Nao consegui criar a Smart Camera");
        }

    }

    /**
     * Pode fazer falta adicionar a parte correspondente à fórmula
     * @param global_data Array de dados globais pré-definidos
     * @param line Linha a ser interpretada
     * @param comunidade Comunidade ao qual estamos a adicionar o comercializador
     */
    public static void parse_comercializador(ArrayList<String> global_data, String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();
        String nomeEmpresa = "";
        int numeroDispositivos = 0;
        int valorBase = 0;
        int imposto = 0;


        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        for(String l: global_data){
            line_splitted_by_2dot.add(l.split(":"));
        }

        for(String[] l : line_splitted_by_2dot){
            switch (l[0]){
                case "nomeEmpresa":
                    nomeEmpresa = l[1];
                    break;
                case "numeroDispositivos":
                    numeroDispositivos = Integer.parseInt(l[1]);
                    break;
                case "valorBase":
                    valorBase = Integer.parseInt(l[1]);
                    break;
                case "imposto":
                    imposto = Integer.parseInt(l[1]);
                    break;
                default:
                    break;

            }
        }
        Comercializador comercializador = new Comercializador(nomeEmpresa, numeroDispositivos, valorBase, imposto);
        comunidade.setMercado(nomeEmpresa, comercializador);
    }






    public static void testa_programa(Comunidade comunidade){
        out.println("---- Inicio dos testes ----");
        out.println(comunidade.toString());

    }


}
