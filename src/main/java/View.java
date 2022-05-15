import java.io.IOException;
import java.util.*;

import static java.lang.System.out;

public class View {
    private Controller controller;
    private Scanner input;

    public View(Controller controller, Scanner input) {
        this.controller = controller;
        this.input = input;
    }

    public void run() throws IOException, ClassNotFoundException {
        int opcao;
        do {
            opcao = receberComandos();
            switch (opcao) {
                case 1: // simulação
                    this.controller.simulacao(controller.getComunidade());
                    break;
                case 2:
        //            criaCasa(this.controller.getComunidade());
                    break;
                case 3:
                    out.print("Insira o nome do ficheiro: ");
                    String objectFile = input.next();
                    this.controller.saveProgramObjects(objectFile);
                    break;
                case 4:
                    out.print("Insira o nome do ficheiro: ");
                    String file = input.next();
                    this.controller.loadProgramObjects(file);
                    break;
                case 5:
                    out.println("Insira o nome do ficheiro: ");
                    String textFile = input.next();
                    this.controller.saveProgramText(textFile);
                    break;
                case 6:
                    out.println("Insira o nome do ficheiro: ");
                    String textFileName = input.next();
                    this.controller.loadProgramText(textFileName);
                    break;
                case 7:
            //        estatisticas();
                    break;
                case 8:
                    this.controller.printComunity();
                    break;
                case 9:
                    break;
                default:
                    out.println("\nOpcao Inválida. Escolha um numero entre 0 e 9.\n");
                    break;
            }
        } while (opcao != 9);
    }

    public int receberComandos() {
        try {
            out.println("Introduza o numero da opcao que pretende:\n" +
                    "1. Simular\n" +
                    "2. Adicionar Casa\n" +
                    "3. Gravar em ficheiro de objetos\n" +
                    "4. Carregar ficheiro de objetos\n" +
                    "5. Gravar em ficheiro de texto\n" +
                    "6. Carregar ficheiro de texto\n" +
                    "7. Estatisticas\n" +
                    "8. Mostrar a comunidade\n" +
                    "9. Sair\n"
            );

            out.print("Opcao: ");


            return this.input.nextInt();
        } catch (Exception e) {
            return 99;
        }
    }

}
