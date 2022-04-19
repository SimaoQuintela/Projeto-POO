import CasaInteligente.CasaInteligente;
import ComercializadoresEnergia.Comercializador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

public class ParseInit {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("initialization.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        Comunidade comunidade = new Comunidade();
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

        /* prints the string splitted by space
        for(String[] s: line_splited){
            out.println(Arrays.toString(s));
        }
        */


        for(String[] line: line_splitted_by_space){
            switch (line[0]){
                case "casa":
                    parse_casa(line, comunidade);
                    break;
                case "devices":
                    parse_devices(line, comunidade);
                    break;
                case "locations":
                    parse_locations(line, comunidade);
                    break;
                case "comercializador":
                    parse_comercializador(line, comunidade);
                    break;
                default:
                    out.print("Something went wrong at line: ");
                    out.println(line);
            }
        }



    }


}
