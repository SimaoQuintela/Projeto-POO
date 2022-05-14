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
        boolean sair = false;
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
                    this.controller.saveProgram();
                    break;
                case 4:
                    out.print("Insira o nome do ficheiro: ");
                    String file = input.nextLine();
                    Controller.loadProgram(file);
                    break;
                case 5:
            //        gravarEmFicheiroObjetos();
                    break;
                case 6:
            //        carregarFicheiroObjetos();
                    break;
                case 7:
            //        estatisticas();
                    break;
                case 8:
                    sair = true;
                    break;
                default:
                    out.println("\nOpção Inválida. Escolha um numero entre 0 e 5.\n");
                    break;
            }
        } while (!sair);
    }

    public int receberComandos() {
        try {
            out.println("Introduza o numero da opção que pretende:\n" +
                    "1. Simular\n" +
                    "2. Adicionar Casa\n" +
                    "3. Gravar em ficheiro\n" +
                    "4. Carregar ficheiro\n" +
                    "5. Gravar em ficheiro de objetos\n" +
                    "6. Carregar ficheiro de objetos\n" +
                    "7. Estatisticas\n" +
                    "8. Sair\n"
            );

            out.print("Opção: ");


            return this.input.nextInt();
        } catch (Exception e) {
            return 99;
        }
    }


/*
    public void gravarEmFicheiro() {
        System.out.print("Introduza o nome do ficheiro onde pretende gravar: ");
        String ficheiro = this.input.next();

        try {
            this.controller.gravarEstado(ficheiro);
            System.out.println("\nGravação concluída.\n");
        } catch (IOException e) {
            System.out.println("\nImpossível gravar no ficheiro " + ficheiro + ".\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void lerFicheiroObjetos() {
        try {
            System.out.print("Introduza o nome do ficheiro que pretende ler: ");

            this.controller.lerCasasInteligentesObjetos(this.input.next());

            System.out.println("\nInformação do ficheiro carregada.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nImpossivel ler ficheiro.\n" + e.toString());
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void gravarEmFicheiroObjetos() {
        System.out.print("Introduza o nome do ficheiro onde pretende gravar: ");
        String ficheiro = this.input.next();

        try {
            this.controller.gravarEstadoObjetos(ficheiro);
            System.out.println("\nGravação concluída.\n");
        } catch (IOException e) {
            System.out.println("\nImpossível gravar no ficheiro " + ficheiro + ".\n");
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
            System.out.println(e.toString());
        }
    }
*/




}
