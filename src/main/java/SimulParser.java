import static java.lang.System.out;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SimulParser {
    public static TreeMap<String, List<List<String>>> simulParser(String file){
        List<String> linhas = lerFicheiro(file);
        List<String[]> linhasSplitted = new ArrayList<>();
        TreeMap<String, List<List<String>>> actions = new TreeMap<>();

        for(String l: linhas){
            linhasSplitted.add(l.split(","));
        //    out.println(Arrays.toString(l.split(",")));
        }

        String linhaMaisRecente = linhasSplitted.get(0)[0];
        //out.println(linhaMaisRecente);


        for(String[] l: linhasSplitted){
            linhaMaisRecente = l[0];
            List<String> modif = new ArrayList<>();
            for(String s: l) {
                if (!s.equals(linhaMaisRecente))     // são diferentes
                    modif.add(s);
            }

            if(actions.containsKey(l[0])){
                actions.get(l[0]).add(modif);
            } else {
                List<List<String>> novo = new ArrayList<>();
                actions.put(linhaMaisRecente,novo);
                actions.get(linhaMaisRecente).add(modif);
            }
        }


        return actions;
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }




}
