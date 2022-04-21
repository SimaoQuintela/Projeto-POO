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
//        Map<String, CasaInteligente> casas = new HashMap<>();
//        Map<String, Comercializador> mercado = new HashMap<>();

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
        for(String[] s: line_splitted_by_space){
            out.println(Arrays.toString(s));
        }

        for(String[] line: line_splitted_by_space){
            switch (line[0]){
                case "casa":
                    parse_casa(line, comunidade);
                    break;
                case "devices":
                    parse_devices(line, comunidade);
                    break;
                case "locations":
                //    parse_locations(line, comunidade);
                    break;
                case "comercializador":
                //    parse_comercializador(line, comunidade);
                    break;
                default:
                    out.print("Something went wrong at line: ");
                    out.println(Arrays.toString(line));
            }
        }

        testa_programa(comunidade);

    }

    // Classe 100% bem interpretada em ficheiro, conseguimos inicializar uma casa "vazia" até
    public static void parse_casa(String[] line, Comunidade comunidade){
        ArrayList<String[]> line_splitted_by_2dot = new ArrayList<>();
        String morada = "";
        String fornecedor = "";

        for(String l: line){
            line_splitted_by_2dot.add(l.split(":"));
        }

        //  Dar print ao array partido
        out.println("Print ah String partida pelos :");
        for(String[] l : line_splitted_by_2dot){
            out.println(Arrays.toString(l));
        }


        for(String[] l: line_splitted_by_2dot){
            switch (l[0]){
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

        CasaInteligente casa = new CasaInteligente(morada, fornecedor);
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

        for(String[] l: line_splitted_by_2dot){
            switch (l[0]){
                case "morada":
                    morada = l[1];
                    break;
                case "SmartBulb", "SmartSpeaker", "SmartCamera":
                    deviceType = l[0];
                    break;
                default:
                    break;
            }
        }

    }




    public static void testa_programa(Comunidade comunidade){
        out.println("---- Comunidade para String ----");
        out.println(comunidade.toString());

    }


}
