import CasaInteligente.CasaInteligente;
import ComercializadoresEnergia.Comercializador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class SaveProgramText {
    public static void saveTextMode(Comunidade c, String file) throws IOException {
        FileWriter writer = new FileWriter(file);

        int tax = 0;
        int baseValue = 0;


        for(Comercializador com: c.getMercado().values()){
            tax = com.getImposto();
            baseValue = com.getValorBase();
            break;
        }

        writer.write("Imposto:" + tax + "\n");
        writer.flush();
        writer.write("ValorBase:" + baseValue + "\n");
        writer.flush();

        for (Comercializador c2: c.getMercado().values()) {
            c2.writeInFile(writer);
        }

        for(CasaInteligente casa: c.getCasas().values()){
            casa.writeInFile(writer);
        }

        writer.close();
    }
}
