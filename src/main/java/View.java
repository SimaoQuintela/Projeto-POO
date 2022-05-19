import ComercializadoresEnergia.Fatura;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.System.out;

public class View {
    private Controller controller;
    private Scanner scan;

    public View(Controller controller, Scanner scan) {
        this.controller = controller;
        this.scan = scan;
    }

    public int comandosDispositivos(){
        try {
            out.println("Introduza o numero da opcao que pretende:\n" +
                    "1. Ligar todos os dispositivos da comunidade\n" +
                    "2. Desligar todos os dispositivos da comunidade\n" +
                    "3. Ligar todos os dispositivos duma casa\n" +
                    "4. Desligar todos os dispositivos duma casa\n" +
                    "5. Sair\n"
            );
            out.print("Opcao: ");
            return this.scan.nextInt();
        } catch (Exception e) {
            return 99;
        }
    }

    public void estadoDispositivos(){
            int escolha;
            do {
                escolha = comandosDispositivos();
                switch (escolha){
                    case 1 -> {
                        this.controller.ligarDesligarComunidade(true);
                    }
                    case 2 -> {
                        this.controller.ligarDesligarComunidade(false);
                    }
                    case 3 -> {
                        out.print("Nome do proprietario da casa: ");
                        String prop = scan.next();
                        this.controller.ligarDesligarCasa(true, prop);
                    }
                    case 4 -> {
                        out.print("Nome do proprietario da casa: ");
                        String prop = scan.next();
                        this.controller.ligarDesligarCasa(false, prop);
                    }
                    case 5 -> {

                    }
                    default -> {
                        out.println("Opcao inexistente, escolha um numero entre 1 e 5");
                    }

                }
            } while(escolha != 5);

    }

    public void run() throws IOException, ClassNotFoundException, InterruptedException {
        int opcao;
        do {
            opcao = receberComandos();
            switch (opcao) {
                case 1 -> { // simulação
                    out.print("Data: ");
                    String data = scan.next();
                    out.print("Ficheiro: ");
                    String file = scan.next();
                    if(file.equals("None")){
                        file = null;
                    }
                    this.controller.simulacao(data, file);
                //    this.controller.cls();
                    out.println("Simulacao finalizada\n");
                }
                case 2 -> {
                    this.controller.cls();
                    estadoDispositivos();
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

    public void estatisticas() throws IOException, InterruptedException {
        int opcao;
        do {
            opcao = comandosEstatisticas();
            switch (opcao){
                case 1 -> {
                    out.print("Data de simulacao: ");
                    String simulacao = scan.next();
                    String[] line_splitted = simulacao.split("/");
                    LocalDate s = LocalDate.of(Integer.parseInt(line_splitted[2]), Integer.parseInt(line_splitted[1]), Integer.parseInt(line_splitted[0]));
                    Tuple t = this.controller.casaQueMaisGastou(s);
                    this.controller.cls();
                    out.println("Proprietario da casa que mais gastou: " + t.getP1());
                    out.println("Gastou: " + t.getP2() + " euros");
                }
                case 2 -> {
                    Tuple t = this.controller.comercializadorQueMaisFatura();
                    this.controller.cls();
                    out.println("O comercializador que mais faturou foi: " + t.getP1());
                    out.println("Faturou: " + t.getP2() + " euros");
                }

                case 3 -> {
                    out.print("Fornecedor: ");
                    String fornecedor = scan.next();
                    Map<String,List<Fatura>> listaFaturas = this.controller.listaFaturas(fornecedor);
                    this.controller.cls();
                    out.println("Lista de faturas emitidas por: " + fornecedor);
                    for(String prop: listaFaturas.keySet()) {
                        for(Fatura f: listaFaturas.get(prop)){
                            out.println(f);
                        }
                        out.println("\n");
                    }
                }
                case 4 -> {
                    out.print("Data de simulacao: ");
                    String simulacao = scan.next();
                    String[] line_splitted = simulacao.split("/");
                    LocalDate s = LocalDate.of(Integer.parseInt(line_splitted[2]), Integer.parseInt(line_splitted[1]), Integer.parseInt(line_splitted[0]));
                    List<Tuple> listaOrdenada = this.controller.ordenaConsumidores(s);
                    this.controller.cls();
                    for(Tuple t : listaOrdenada){
                        out.println("Proprietario: " + t.getP1());
                        out.println("Gastou: " + t.getP2());
                    }

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
                    "2. Ligar/Desligar dispositivos\n" +
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
                        "1. Casa que mais gastou num periodo\n" +
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
