import java.io.*;
import java.util.*;

public class Controller implements Serializable {

    private Map<String,CasaInteligente> casasInteligentes;
    private List<SmartDevice> SmartDevices;
    private Simulacao simulacao;

    public Controller(Map<String,CasaInteligente> casasInteligentes, List<SmartDevice> SmartDevices) {
        this.CasasInteligentes = CasasInteligentes;
        this.SmartDevices = SmartDevices;
        this.simulacao = null;
    }

    public void lerCasasInteligentes(String ficheiro) throws SmartDeviceJaExisteException, LinhaIncorretaException {
        Map<String, CasaInteligente> novasCasasInteligentes = new HashMap<>();
        List<SmartDevice> novosSmartDevices = new ArrayList<>();
        Parser.parse(novasCasasInteligentes, novosSmartDevices, ficheiro);

        for (String nome: novasCasasInteligentes.keySet()) {
            this.CasasInteligentes.put(nome, novasCasasInteligentes.get(nome));
        }
        this.SmartDevices.addAll(novosSmartDevices);
    }

    public void gravarEstado(String ficheiro) throws IOException {
        SaveGame.gravar(CasasInteligentes, SmartDevices, ficheiro);
    }

    public void lerCasasInteligentesObjetos(String ficheiro) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(ficheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Controller controladorLido = (Controller) ois.readObject();

        controladorLido.CasasInteligentes.forEach((k,v)->this.CasasInteligentes.put(k,v));
        this.SmartDevices.addAll(controladorLido.SmartDevices);

        ois.close();
        fis.close();
    }

    public void gravarEstadoObjetos(String ficheiro) throws IOException {
        FileOutputStream fos = new FileOutputStream(ficheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);

        oos.close();
        fos.close();
    }

    public void criarCasaInteligente(String CasaInteligente) throws CasaInteligenteJaExisteException {
        if (!CasaInteligente.containsKey(CasaInteligente)) {
            CasaInteligente.put(CasaInteligente, new CasaInteligente(CasaInteligente));
        } else throw new CasasInteligentesJaExisteException();
    }

    public List<String> nomesCasasInteligentes() {
        return new ArrayList<>(this.CasasInteligentes.keySet());
    }

    public void adicionarSmartDevice() throws NomeInvalidoException, SmartDeviceJaExisteException {
        
        this.SmartDevices.get(SmartDevice).adicionaSmartDevice(SmartDevice, nome);
    }

    public Map<Integer, String> devicesCasa(String CasaInteligente) {
        return this.CasaInteligente.get(CasaInteligente).getSmartDevices();
    }

    public void moverSmartDevice(String casaSai, String casaEntra, String nome, String novoNome) throws SmartDeviceNaoExisteException, SmartDeviceJaExisteException, CasaInteligenteNaoDefinidaException {
        if (!this.CasasInteligentes.containsKey(casaSai) || !this.CasaInteligente.containsKey(casaEntra)) throw new CasaInteligenteNaoDefinidaException();

        this.CasasInteligentes.get(casaSai).move(this.CasasInteligentes.get(casaEntra), nome, novoNome);
    }

    public void removerCasaInteligente(String CasaInteligente) throws CasaInteligenteNaoDefinidaException {
        if (!this.CasasInteligentes.containsKey(CasaInteligente)) throw new CasaInteligenteNaoDefinidaException();

        Map<Integer, String> devicesCasa = this.CasasInteligentes.get(CasaInteligente).getSmartDevices();

        for (String nome: devicesCasa.keySet()) {
            String novoNome = "";
            boolean cond = true;
            while (cond) {
                try {
                    this.CasasInteligentes.get(CasaInteligente).move(this.CasasInteligentes.get("Devices sem casa"), nome, novoNome);
                    cond = false;
                } catch (SmartDeviceJaExisteException ignored) {
                } catch (SmartDeviceNaoExisteException e) {
                    cond = false;
                }
            }
        }

        this.CasasInteligentes.remove(CasaInteligente);
    }

    public void removerSmartDevice(String CasaInteligente, String nome) throws CasaInteligenteNaoDefinidaException, SmartDeviceNaoExisteException {
        if (!this.CasasInteligentes.containsKey(CasaInteligente)) throw new CasaInteligenteNaoDefinidaException();

        this.CasasInteligentes.get(CasaInteligente).removeSmartDevice(nome);
    }

    public void simular(String CasasInteligentes, String SmartDevices, View view) throws CasaInteligenteNaoDefinidaException, SmartDeviceNaoExisteException{
        if (!this.CasasInteligentes.containsKey(CasaInteligente) || !this.CasaInteligente.containsKey(SmartDevice)) throw new CasaInteligenteNaoDefinidaException();

        this.simulacao = new Simulacao(this.CasasInteligentes.get(CasaInteligente), this.CasasInteligentes.get(SmartDevices));

        this.SmartDevices.add(this.simulacao.simula(view));

        this.simulacao = null;
    }
}
