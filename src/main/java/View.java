import java.io.IOException;
import java.util.*;

public class View {
    private Controller controller;
    private Scanner input;

    public View(Controller controller, Scanner input) {
        this.controller = controller;
        this.input = input;
    }

    public void run() {
        System.out.println(
                "  ____                                        _               _                               ______   __  __ \n" +
                " |  _ \\                                      (_)             | |                             |  ____| |  \\/  |\n" +
                " | |_) |   ___   _ __ ___    ______  __   __  _   _ __     __| |   ___       __ _    ___     | |__    | \\  / |\n" +
                " |  _ <   / _ \\ | '_ ` _ \\  |______| \\ \\ / / | | | '_ \\   / _` |  / _ \\     / _` |  / _ \\    |  __|   | |\\/| |\n" +
                " | |_) | |  __/ | | | | | |           \\ V /  | | | | | | | (_| | | (_) |   | (_| | | (_) |   | |      | |  | |\n" +
                " |____/   \\___| |_| |_| |_|            \\_/   |_| |_| |_|  \\__,_|  \\___/     \\__,_|  \\___/    |_|      |_|  |_|\n");

        int opcao;
        do {
            opcao = receberComandos();
            switch (opcao) {
                case 0:
                    System.out.println("\nSimulação encerrada.");
                    break;
                case 1:
                    lerFicheiro();
                    break;
                case 2:
                    gravarEmFicheiro();
                    break;
                case 3:
                    lerFicheiroObjetos();
                    break;
                case 4:
                    gravarEmFicheiroObjetos();
                    break;
                case 5:
                    adicionarCasaInteligente();
                    break;
                case 6:
                    adicionarSmartDevice();
                    break;
                case 7:
                    moverDevice();
                    break;
                case 8:
                    removerCasaInteligente();
                    break;
                case 9:
                    removerSmartDevice();
                    break;
                case 10:
                    simulacao();
                    break;
                case 11:
                    estatisticas();
                    break;
                default:
                    System.out.println("\nOpção Inválida. Escolha um numero entre 0 e 11.\n");
                    break;
            }
        } while (opcao != 0);
    }

    public int receberComandos() {
        try {
            System.out.println("Introduza o numero da opção que pretende:\n" +
                    "0. Sair\n" +
                    "1. Carregar ficheiro\n" +
                    "2. Gravar estado em ficheiro\n" +
                    "3. Carregar ficheiro de objetos\n" +
                    "4. Gravar estado em ficheiro de objetos\n" +
                    "5. Criar Casa Inteligente\n" +
                    "6. Adicionar Smart Device\n" +
                    "7. Mover Smart Device\n" +
                    "8. Remover Casa Inteligente\n" +
                    "9. Remover Smart Device\n" +
                    "10. Simulacao\n" +
                    "11. Estatisticas\n");

            System.out.print("Opção: ");
            return Integer.parseInt(this.input.next());
        } catch (Exception e) {
            return 99;
        }
    }

    public void lerFicheiro() {
        try {
            System.out.print("Introduza o nome do ficheiro que pretende ler: ");

            this.controller.lerCasaInteligente(this.input.next());

            System.out.println("\nInformação do ficheiro carregada.\n");
        } catch (LinhaIncorretaException | SmartDeviceJaExisteException e) {
            System.out.println("\nFicheiro está mal formado.\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

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

    public void adicionarCasaInteligente() {
        System.out.print("Introduza o nome da casa inteligente que pretende criar: ");
        String casaInteligente = input.next();

        try {
            this.controller.criarCasaInteligente(casaInteligente);
            System.out.println("\nCasa Inteligente criada com sucesso.\n");
        } catch (CasaInteligenteJaExisteException e) {
            System.out.println("\nA casa inteligente " + casaInteligente + " ja existe.\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void adicionarDevice() {
        try {
            System.out.println("Escolha a casa a que pretende adicionar o smart device: ");
            List<String> nomesEquipas = this.controller.nomesCasasInteligentes();

            for(int i = 0; i < nomesCasasInteligentes.size(); i++) System.out.println(i + ". " + nomesCasasInteligentes.get(i));
            System.out.print("\nCasa Inteligente (0-" + (nomesEquipas.size()-1) + "): ");

            int casa = Integer.parseInt(this.input.next());

            String casaInteligente = nomesCasasInteligentes.get(casa);

            System.out.println("\nSelecione o tipo de smart device: ");
            System.out.println("0. Smart Bulb");
            System.out.println("1. Smart Camera");
            System.out.println("2. Smart Speaker");
            
            System.out.print("Tipo de selecionado: ");
            int tipo = Integer.parseInt(this.input.next());

            System.out.print("\nIntroduza o nome do smart device: ");
            String nome =  input.next(); 
            
            System.out.println("\nSmart Device adicionado com sucesso.\n");
        
        } catch (SmartDeviceException e){
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nA casa que escolheu nao existe.\n");
        } catch (Exception e) {
            System.out.println("\nA informaçao submetida nao esta correta.\n");
        }
    }

    public void moverSmartDevice() {
        try {
            System.out.println("Escolha a casa de onde pretende mover o smart device:");
            List<String> nomesCasasInteligentes = this.controller.nomesCasasInteligentes();

            for(int i = 0; i < nomesCasasInteligentes.size(); i++) System.out.println(i + ". " + nomesCasasInteligentes.get(i));
            System.out.print("\nCasa inteligente (0-" + (nomesCasasInteligentes.size()-1) + "): ");

            int casa = Integer.parseInt(this.input.next());
            String casaSai = nomesCasasInteligentes.get(casa);

            Map<Integer,String> nomesDevices = this.controller.devicesCasa(casaSai);
            for (int nome: nomesDevices.keySet()) {
                System.out.println(nomesDevices.get(nome));
            }

            System.out.print("\nNome: ");
            int nome = Integer.parseInt(this.input.next());

            System.out.println("Escolha a casa para onde pretende mover o smart device:");
            nomesCasasInteligentes = this.controller.nomesCasasInteligentes();

            for(int i = 0; i < nomesCasasInteligentes.size(); i++) System.out.println(i + ". " + nomesCasasInteligentes.get(i));
            System.out.print("\nCasa Inteligentes (0-" + (nomesCasasInteligentes.size()-1) + "): ");

            casa = Integer.parseInt(this.input.next());
            String casaEntra = nomesCasasInteligentes.get(casa);

            nomesSmartDevices = this.controller.devicesCasa(casaEntra);
            System.out.println("Nomes ja existentes: ");
            for(int nome: nomesSmartDevices.keySet()) {
                System.out.print(nome + "  ");
            }

            System.out.print("\nEscolha o novo nome do smart device: ");
            int novoNome = Integer.parseInt(this.input.next());

            this.controller.moverSmartDevice(casaSai, casaEntra, nome, novoNome);

            System.out.println("\nSmart Device movido com sucesso.\n");
        } catch (SmartDeviceJaExisteException | SmartDeviceNaoExisteException e) {
            System.out.println("\nEscolha do smart device incorreta.\n");
        } catch (CasaNaoDefinidaException e) {
            System.out.println("\nCasa escolhida nao existe.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nNome escolhido invalido.\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void removerCasaInteligente() {
        try {
            System.out.println("Escolha a casa que pretende remover: ");
            List<String> nomesCasasInteligentes = this.controller.nomesCasasInteligentes();

            for(int i = 0; i < nomesCasasInteligentes.size(); i++) System.out.println(i + ". " + nomesCasasInteligentes.get(i));
            System.out.print("\nCasa (0-" + (nomesCasasInteligentes.size()-1) + "): ");

            int casa = Integer.parseInt(this.input.next());
            String equipa = nomesEquipas.get(casa);

            this.controller.removerCasaInteligente(casa);

            System.out.println("\nCasa removida com sucesso.\n");
        } catch (CasaNaoDefinidaException | IndexOutOfBoundsException e) {
            System.out.println("\nCasa escolhida nao existe.\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void removerSmartDevice() {
        try {
            System.out.println("Escolha a casa de onde pretende remover o smart device: ");
            List<String> nomesCasasInteligentes = this.controller.nomesCasasInteligentes();

            for(int i = 0; i < nomesCasasInteligentes.size(); i++) System.out.println(i + ". " + nomesCasasInteligentes.get(i));
            System.out.print("\nCasa (0-" + (nomesCasasInteligentes.size()-1) + "): ");

            int casa = Integer.parseInt(this.input.next());
            String casa = nomesCasasInteligentes.get(casa);

            System.out.println("Escolha o smart device que pretende remover: ");
            Map<Integer, String> nomesSmartDevices = this.controller.devicesCasa(casa);

            for (int nome: nomesSmartDevices.keySet()) {
                System.out.println(nomesJogadores.get(nome));
            }

            System.out.print("Nome do smart device: ");
            int device = Integer.parseInt(this.input.next());

            this.controller.removerSmartDevice(casa, SmartDevice);
            System.out.println("\nSmart device removido com sucesso.\n");
        } catch (CasaNaoDefinidaException e) {
            System.out.println("\nEquipa escolhida nao existe.\n");
        } catch (SmartDeviceNaoExisteException e) {
            System.out.println("\nJogador escolhido nao existe.\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void estatistica() {
        try {
            System.out.println("\nIntroduza a opção que preferir: \n" +
                    "1. Qual a casa que mais gastou\n" +
                    "2. Qual o comercializador com maior volume de facturacao\n" +
                    "3. Listar as facturas emitidas por um comercializador\n" +
                    "4. Ordenar os maiores consumidores de energia");

            System.out.print("\nOpçao: ");
            int opcao = Integer.parseInt(this.input.next());
            switch (opcao) {
                case 1:
                    System.out.println("\nA casa " + casa + " foi a que mais gastou\n");
                    break;
                case 2:
                    System.out.println("\nO comercializador " + comercializador + " foi o que teve maior faturacao\n");
                    break;
                case 3:
                    System.out.println("\nO comercializador " + comercializador + " teve as faturas:\n");
                    break;
                case 4:
                    System.out.println("\nConsumidores ordenados por maior consumo:\n");
                    break;
                default:
                    System.out.println("\nOpcao invalida.\n");
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }

    public void simulacao() {
        try {
            System.out.println("Escolha a Casa Inteligente que pretende: ");
            List<String> nomeCasasInteligentes = this.controller.nomesCasasInteligentes();

            for(int i = 0; i < nomesCasasInteligentes.size(); i++) System.out.println(i + ". " + nomesCasasInteligentes.get(i));
            System.out.print("\nCasa: (0-" + (nomesCasasInteligentes.size()-1) + "): ");

            int nome = Integer.parseInt(this.input.next());
            String casa = nomesCasasInteligentes.get(nome);


            this.controller.simularJogo(CasaInteligente, SmartDevices, this);
        } catch (SimulacaoImpossivelException e) {
            System.out.println("\nNao foi possivel fazer a simulacao do jogo.\n");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro. Tente outra vez.\n");
        }
    }
}
