import CasaInteligente.CasaInteligente;
import ComercializadoresEnergia.Comercializador;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.System.out;

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
                //case "devices":  PROVAVELMENTE VOU TROCAR "DEVICES" POR "SMARTBULB", "SMARTSPEAKER" E "SMARTCAMERA"
                //    parse_devices(line, comunidade);
                //    break;
                case "comercializador":
                    parse_comercializador(global_data, line, comunidade);
                    break;
                default:
                    global_data.add(line[0]);
                    out.print("Something went wrong at line: ");
                    out.println(Arrays.toString(line));
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
                    morada = l[1];
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

    public static void parse_devices(String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();

        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        //  Dar print ao array partido
        out.println("Print ah String partida pelos :");
        for(String[] l : line_splitted_by_2dot){
            out.println(Arrays.toString(l));
        }
    }


/* PROVAVELMENTE VOU REMOVER ESTA FUNÇÃO
    public static void parse_locations(String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();
        String morada = "";
        String espaco = "";
        List<String> ids = new ArrayList<>();

        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        //  Dar print ao array partido

        out.println("Print ah String de locations partida pelos :");
        for(String[] l : line_splitted_by_2dot){
            out.println(Arrays.toString(l));
        }

        for(String[] l: line_splitted_by_2dot){
            switch (l[0]){
                case "morada":
                    morada = l[1];
                    break;
                case "espaco":
                    espaco = l[1];
                    break;
                case "ids":
                    for(int i = 1; i< l.length; i+=1){
                        ids.add(l[i]);
                    }

            }
        }

        //Adicionar uma divisão à casa
        comunidade.getCasa(morada).addRoom(espaco);
        //Adicionar os ids dos dispositivos associados à divisão que foi criada
        for(String id: ids){
            comunidade.getCasa(morada).addToRoom(espaco, id);
        }

    }
    */

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
