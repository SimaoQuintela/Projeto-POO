import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.Resolution;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartSpeaker;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import static java.lang.System.out;

public class App {
    static Scanner scan = new Scanner(System.in);
    static Comunidade comunidade = new Comunidade("Gualtar");

    public static void main(String args[]) throws InterruptedException {
        out.println("1- Criar casa");
        out.println("2- Criar fornecedor");
        out.println("3- Sair");
        int opcao = scan.nextInt();
        switch(opcao){
            case 1:
                criaCasa();
                break;
            case 2:
           //     criaFornecedor();
            case 3:
                System.exit(1);
                break;
            default:
                out.println("Opção inexistente");
        }
    }

    public static void limpaConsola(){
        System.out.print("\033\143");
    }


    public static void criaCasa() throws InterruptedException {
        // morada
        out.print("Insira a morada: ");
        String morada = scan.next();
        out.print("\n");

        // fornecedor
        out.print("Insira o fornecedor: ");
        String fornecedor = scan.next();
        out.print("\n");

        // adicionar casa à cidade
        CasaInteligente casa = new CasaInteligente(morada, fornecedor);
        comunidade.addCasa(morada, casa);

        out.println("1- Adicionar dispositivos");
        out.println("2- Não adicionar dispositivos");
        out.print("Opção: ");
        int opcao = scan.nextInt();
        out.print("\n");

        switch (opcao){
            case 1:
                out.print("Localização: ");
                String localizacao = scan.next();
                out.print("\n");
                limpaConsola();
                adicionarDispositivos(casa, localizacao);
                break;
            case 2:
                limpaConsola();
            //    main();
                break;
            default:
                out.println("Opção inexistente");
                break;
        }


    }

    public static void adicionarDispositivos(CasaInteligente casa, String localizacao) throws InterruptedException {
        // opção de adicionar dispositivos
        out.println("Adicione dispositivos à casa");
        out.println("1- Smart Bulb");
        out.println("2- Smart Camera");
        out.println("3- Smart Speaker");

        out.print("Opção: ");
        int opcao = scan.nextInt();
        out.print("\n\n");

        out.println("Id: ");
        String id = scan.next();

        out.println("1- On");
        out.println("2- Off");
        int stat = scan.nextInt();
        boolean status;
        if(stat == 1)
            status = true;
        else
            status = false;

        out.print("\n");
        switch (opcao){
            // Smart Bulb
            case 1:
                out.println("Tonalidade:");
                out.println("0- Cold");
                out.println("1- Neutral");
                out.println("2- Warm");
                out.print("Opção: ");
                int tonalidade = scan.nextInt();
                if(tonalidade == 0){
                    tonalidade = SmartBulb.COLD;
                } else if(tonalidade == 1){
                    tonalidade = SmartBulb.NEUTRAL;
                } else {
                    tonalidade = SmartBulb.WARM;
                }
                out.print("\n\n");

                out.print("Dimensões: ");
                int dimensoes = scan.nextInt();
                out.print("\n");

                // criação de uma nova lâmpada
                SmartBulb new_bulb = new SmartBulb(id, status, tonalidade, dimensoes);
                casa.addDevice(new_bulb, localizacao);
                out.println("Smart Bulb criada");
                TimeUnit.SECONDS.sleep(2);
                limpaConsola();
                break;
            case 2:
                // Smart Camera
                out.println("Resolução");
                out.print("X: ");
                int x = scan.nextInt();
                out.print("\n");

                out.print("Y: ");
                int y = scan.nextInt();
                out.print("\n");

                int x2 = 0;
                int y2 = 0;
                out.print("Tamanho do ficheiro: ");
                int size = scan.nextInt();
                out.print("\n");

                SmartCamera new_camera = new SmartCamera(id, status, x2, y2, size);
                casa.addDevice(new_camera, localizacao);
                out.println("Smart Camera criada");
                TimeUnit.SECONDS.sleep(2);
                limpaConsola();
                break;
            case 3:
                // Smart Speaker
                out.print("Canal: ");
                String channel = scan.next();
                out.print("\n");

                out.print("Marca: ");
                String brand = scan.next();
                out.print("\n");

                out.print("Volume: ");
                int volume;
                do{
                    volume = scan.nextInt();
                    if(volume >= 0 && volume <= 20){
                        out.println("Valor inválido, insira um valor entre 0 e 20");
                        out.print("Volume: ");
                    }
                    out.print("\n");
                } while(volume >= 0 && volume <= 20);

                SmartSpeaker new_speaker = new SmartSpeaker(id, status, channel, volume, brand);
                casa.addDevice(new_speaker, localizacao);
                TimeUnit.SECONDS.sleep(2);
                limpaConsola();
                break;
            default:
                out.println("Opção inexistente");
                TimeUnit.SECONDS.sleep(2);
                limpaConsola();
                break;
        }
    }

}
