import java.io.IOException;
import java.util.*;

import static java.lang.System.out;

public class View {
    private Controller controller;
    private Scanner scan;

    public View(Controller controller, Scanner scan) {
        this.controller = controller;
        this.scan = scan;
    }

    public void run() throws IOException, ClassNotFoundException, InterruptedException {
        int opcao;
        do {
            opcao = receberComandos();
            switch (opcao) {
                case 1 -> { // simulação
                    this.controller.simulacao(controller.getComunidade());
                //    this.controller.cls();
                    out.println("Simulacao finalizada\n");
                }
                case 2 -> {
                    //            criaCasa(this.controller.getComunidade());
                }
                case 3 -> {
                    out.print("Insira o nome do ficheiro: ");
                    String objectFile = scan.next();
                    this.controller.saveProgramObjects(objectFile);
                    this.controller.cls();
                    out.println("Ficheiro gravado com sucesso!");
                }
                case 4 -> {
                    out.print("Insira o nome do ficheiro: ");
                    String file = scan.next();
                    this.controller.loadProgramObjects(file);
                    this.controller.cls();
                    out.println("Ficheiro carregado com sucesso!");
                }
                case 5 -> {
                    out.print("Insira o nome do ficheiro: ");
                    String textFile = scan.next();
                    this.controller.saveProgramText(textFile);
                    this.controller.cls();
                    out.println("Ficheiro gravado com sucesso!");
                }
                case 6 -> {
                    out.print("Insira o nome do ficheiro: ");
                    String textFileName = scan.next();
                    this.controller.loadProgramText(textFileName);
                    this.controller.cls();
                    out.println("Ficheiro carregado com sucesso!");
                }
                case 7 -> {
                    this.controller.cls();
                    estatisticas();
                }
                case 8 -> {
                    this.controller.printComunity();
                }
                case 9 -> {

                }
                default -> {
                    this.controller.cls();
                    out.println("\nOpcao Inválida. Escolha um numero entre 0 e 9.\n");
                }
            }
        } while (opcao != 9);
    }

    public void estatisticas(){
        int opcao;
        do {
            opcao = comandosEstatisticas();
            switch (opcao){
                case 1 -> {
                    out.print("Data de inicio: ");
                    String inicio = scan.next();
                    out.print("Data de fim: ");
                    String fim = scan.next();
                    // CHAMAR MÉTODO
                }
                case 2 -> {
                    // CHAMAR MÉTODO QUE CALCULA O FORNECEDOR COM MAIOR VOLUME DE FATURAÇÃO
                }

                case 3 -> {
                    out.print("Fornecedor: ");
                    String fornecedor = scan.next();
                    // CHAMAR MÉTODO QUE LISTA AS FATURAS DESTE FORNECEDOR
                }
                case 4 -> {
                    out.print("Data de inicio: ");
                    String inicio = scan.next();
                    out.print("Data de fim: ");
                    String fim = scan.next();
                    // CHAMAR MÉTODO QUE ORDENA OS MAIORES CONSUMIDORES DE ENERGIA. HINT: USAR O CASE 1
                    int x = 10;
                }
                case 5 -> {

                }
                default -> {
                    out.println("Opcao inexistente, escolha um numero entre 1 e 5");
                }

            }
        } while(opcao != 5);
    }

    /**
     * Comandos para a interface principal
     * @return
     */
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


            return this.scan.nextInt();
        } catch (Exception e) {
            return 99;
        }
    }

    /**
     * Interface para as opções de escolha das estatisticas
     * @return
     */
    public int comandosEstatisticas(){
        try{
            out.println("Introduza o numero da opcao que pretende:\n" +
                        "1. Casa que mais gastou num período\n" +
                        "2. Comercializador com maior volume de faturacao\n" +
                        "3. Listagem de faturas emitidas por um fornecedor\n" +
                        "4. Ordenacao dos maiores consumidores de energia num periodo\n" +
                        "5. Sair\n"
            );

            out.print("Opcao: ");

        } catch (Exception e){
            return 99;
        }

        return this.scan.nextInt();
    }




}
