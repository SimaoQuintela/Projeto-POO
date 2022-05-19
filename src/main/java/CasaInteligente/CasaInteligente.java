package CasaInteligente;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartDevice;
import ComercializadoresEnergia.Comercializador;
import ComercializadoresEnergia.Fatura;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * A CasaInteligente faz a gestão dos SmartDevices que existem e dos
 * espaços (as salas) que existem na casa.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CasaInteligente implements Serializable {
    private String proprietario;
    //private int numeroDePorta;
    private int NIF;
    //private String morada;
    private String fornecedor;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices

    private List<Fatura> faturas; // lista de faturas que foram geradas e associadas à casa




    /**
     * Construtor por omissão de CasaInteligente.
     */
    public CasaInteligente() {
        this.proprietario = "";
        this.NIF = 0;
        this.fornecedor = "";
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.faturas = new ArrayList<>();
    }

    /**
     * ESTE CONSTRUTOR É O CORRETO
     * @param proprietario Nome do proprietário
     * @param NIF Número de identificação fiscal do proprietário.
     * @param fornecedor Fornecedor.
     */
    public CasaInteligente(String proprietario, int NIF, String fornecedor){
        this.proprietario = proprietario;
        this.NIF = NIF;
        this.fornecedor = fornecedor;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.faturas = new ArrayList<>();
    }


    /**
     * Construtor parametrizado de CasaInteligente.
     * @param devices Dispositivos existentes na casa.
     * @param locations Divisões da CasaInteligente.
     */
    public CasaInteligente(String proprietario, int NIF, String fornecedor, Map<String, SmartDevice> devices, Map<String, List<String>> locations){
        this(proprietario, NIF, fornecedor);
        this.devices = devices.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey(), e->e.getValue().clone()));

        this.locations = locations.entrySet()
                                  .stream()
                                  .collect(toMap(e-> e.getKey(), e->e.getValue()));
        this.faturas = new ArrayList<>();
    }

    /**
     * Construtor de cópia de CasaInteligente.
     * @param c CasaInteligente que é copiada.
     */
    public CasaInteligente(CasaInteligente c){
        this(c.proprietario, c.NIF, c.fornecedor, c.devices, c.locations);
    }

    /**
     * Método que verifica a igualdade entre a CasaInteligente e um outro objeto.
     * @param o Objeto que é compara com a CasaInteligente.
     * @return Booleano que indica o resultado da comparação.
     */
    public boolean equals(Object o){
        if (o ==this)
            return true;

        if(o == null || o.getClass() != this.getClass())
            return false;

        CasaInteligente c = (CasaInteligente) o;

        return(
            this.proprietario.equals(c.proprietario)  &&
            this.NIF == c.NIF                         &&
            this.getFornecedor().equals(c.fornecedor) &&
            this.devices.equals(c.devices)            &&
            this.locations.equals(c.locations)        &&
            this.faturas.equals(c.faturas)
        );
    }

    /**
     * Método que devolve uma cópia da CasaInteligente recetora da mensagem.
     * @return Cópia da CasaInteligente.
     */
    public CasaInteligente clone(){
        return new CasaInteligente(this);
    }

    /**
     * Método que produz uma string na qual está representada a CasaInteligente.
     * @return String que representa a CasaInteligente.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n------------- Casa Inteligente -------------\n");
        sb.append("Proprietario: ").append(this.proprietario).append("\n");
        sb.append("NIF: ").append(this.NIF).append("\n");
        sb.append("Fornecedor: ").append(this.getFornecedor()).append("\n");

        sb.append("------------- Devices -------------\n");
        for(String id: this.devices.keySet()){
            if(this.devices.get(id) instanceof SmartBulb){
                sb.append("     Smart Bulb\n");
            } else if(this.devices.get(id) instanceof SmartCamera){
                sb.append("     Smart Camera\n");
            } else {
                sb.append("     Smart Speaker\n");
            }

            // CASO NAO QUEIRA MOSTRAR O CONTEÚDO DO DEVICE METO ISTO EM COMENTARIO
            sb.append(this.devices.get(id).toString());
        }

        sb.append("------------- Locations -------------\n");
        for(String division: this.locations.keySet()){
            sb.append("Divisao: ").append(division);
            sb.append("\nIds dos dispositivos: ");
            for(String id: this.locations.get(division)){
                sb.append(id).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void writeInFile(FileWriter writer) throws IOException {
        String line = "Casa:" + this.getProprietario() + "," + this.getNIF() + "," + this.getFornecedor() + "\n";
        writer.write(line);
        writer.flush();

        for(String loc: this.locations.keySet()){
            String line_aux = "Divisao:" + loc + "\n";
            writer.write(line_aux);
            writer.flush();
            for(String idDevice: this.locations.get(loc)){
                this.devices.get(idDevice).writeInFile(writer);
            }
        }
    }

    /**
     * Método que adiciona uma nova divisão à CasaInteligente.
     * @param s Nova divisão da CasaInteligente.
     */
    public void addRoom(String s) {
        List<String> roomDevices = new ArrayList<>();
        this.locations.put(s, roomDevices);
    }

    /**
     * Método que verifica se uma determinada divisão existe na CasaInteligente.
     * @param s Divisão que se pretende saber se existe na CasaInteligente.
     * @return Booleano que indica se a Divisão existe na CasaInteligente.
     */
    public boolean hasRoom(String s) {
        return this.locations.containsKey(s);
    }

    /**
     * Método que verifica se um determinado SmartDevice existe numa determinada divisão da CasaInteligente.
     * @param s1 Divisão da CasaInteligente.
     * @param s2 Código de identificação do SmartDevice.
     * @return Booleano que indica se o SmartDevice existe na divisão da CasaInteligente.
     */
    public boolean roomHasDevice(String s1, String s2) {
        return this.locations.get(s1).contains(s2);
    }

    /**
     * Método que verifica se um determinado SmartDevice existe na CasaInteligente.
     * @param id Código de identificação do SmartDevice.
     * @return
     */
    public boolean existsDevice(String id) {
        return this.devices.containsKey(id);
    }

    /**
     * Método que adiciona um SmartDevice a uma determinada divisão da CasaInteligente.
     * @param s SmartDevice que é adicionado.
     * @param location Divisão da casa à qual é adicionado o SmartDevice.
     */
    public void addDevice(SmartDevice s, String location) {
        this.devices.put(s.getID(), s);

        if (hasRoom(location)) {
            this.locations.get(location).add(s.getID());
        } else {
            List<String> ids = new ArrayList<>();
            ids.add(s.getID());
            this.addLocation(location, ids);
        }
    }

    /**
     * Método que remove um device da casa
     * @param id
     */
    public void removeDevice(String id){
        this.devices.remove(id);
        for(String s : this.locations.keySet()){
            if(this.locations.get(s).contains(id)){
                for(int i = 0; i<this.locations.get(s).size(); i+=1){
                    if(this.locations.get(s).get(i).equals(id)){
                        this.locations.get(s).remove(i);
                    }
                }
            }
        }
    }

    public void addFatura(Fatura f){
        this.faturas.add(f);
    }


    public float simula(LocalDate before, LocalDate after, Comercializador c){
        float total = 0;
        double consumoDisp;
        for (String s : this.devices.keySet()) {
            consumoDisp = c.contaConsumoDispositivo(this.devices.get(s), before, after, this.devices.keySet().size());
            total += consumoDisp;
        }

        return total;
    }

    public void addLocation(String location, List<String> ids){
        this.locations.put(location, ids);
    }

    // gets e sets
    /**
     * Método que devolve o nome do proprietário
     * @return Nome do proprietário da casa
     */
    public String getProprietario() {
        return this.proprietario;
    }

    /**
     * Método que devolve o NIF do proprietário da casa
     * @return NIF do proprietário da casa
     */
    public int getNIF() {
        return this.NIF;
    }

    /**
     * Método que devolve o nome do fornecedor de energia
     * @return
     */
    public String getFornecedor() {
        return this.fornecedor;
    }

    /**
     * Método que devolve um determinado SmartDevice através de um código de identificação.
     * @param s Código de identificação do SmartDevice.
     * @return SmartDevice.
     */
    public SmartDevice getDevice(String s) {
        return this.devices.get(s);
    }

    /**
     * Método que devolve a estrutura como todos os devices da casa
     * @return HashMap com os devices da casa
     */
    public Map<String, SmartDevice> getDevices() {
        Map<String, SmartDevice> new_devices = new HashMap<>();
        new_devices = this.devices.entrySet()
                                  .stream()
                                  .collect(toMap(e->e.getKey(), e->e.getValue()));


        return new_devices;
    }

    /**
     * Método que devolve o Map com as divisões da casa associadas à lista de ID's de devices presentes nas respetivas divisões.
     * @return Map com as divisões da casa.
     */
    // dúvidas aqui ao adicionar os values
    public Map<String, List<String>> getLocations() {
        Map<String, List<String>> new_locations = new HashMap<>();
        new_locations = this.locations.entrySet()
                                      .stream()
                                      .collect(toMap(e->e.getKey(),  e->e.getValue()));


        return new_locations;
    }

    public List<Fatura> getFaturas() {

        return new ArrayList<>(this.faturas);
    }

    /**
     * Método que coloca na variável proprietário o nome passado como parâmetro
     * @param proprietario String passada como parâmetro que vai ser colocada na variável proprietário
     */
    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Método que coloca na variável NIF o double passado como parâmetro
     * @param NIF Double passado como parâmetro que vai ser colocado na variável NIF
     */
    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    /**
     * Método que coloca na variável fornecedor o valor passado como parâmetro
     * @param fornecedor Nome do fornecedor
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * Método que altera o Map com os ID's associados aos respetivos dispositivos.
     * @param devices Map com os dispositivos.
     */
    // discutir com o grupo estes dois métodos
    public void setDevices(Map<String, SmartDevice> devices) {
        this.devices = devices.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    /**
     * Método que altera o Map com as divisões da casa associadas à lista de dispositivos presentes nas respetivas divisões.
     * @param locations Map com as divisões da casa.
     */
    public void setLocations(Map<String, List<String>> locations) {
        this.locations = locations.entrySet()
                                  .stream()
                                  .collect(toMap(e-> e.getKey(), e->e.getValue()));
    }

    /**
     * Método que liga um SmartDevice através do seu código de identificação.
     * @param devCode Código de identificação do SmartDevice.
     */
    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }

    /**
     * Método que desliga um SmartDevice através do seu código de identificação.
     * @param devCode Código de indentificação do SmartDevice.
     */
    public void setDeviceOff(String devCode){
        this.devices.get(devCode).turnOff();
    }

    /**
     * Método que atribui um estado a um determinado SmartDevice através do código de identificação.
     * @param s Código de identificação do SmartDevice.
     * @param b Estado atribuído ao SmartDevice.
     */
    public void setOn(String s, boolean b) {
        this.getDevice(s).setOn(b);
    }

    /**
     * Método que atribui um determinado estado a todos os SmartDevices da CasaInteligente.
     * @param b Estado atribuído a todos os SmartDevices.
     */
    public void setAllOn(boolean b) {
        this.devices.values().forEach(s -> s.setOn(b));
    }

    /**
     * Método que liga todos os SmartDevices de uma determinada divisão da CasaInteligente.
     * @param location Divisão da CasaInteligente.
     */
    public void turnOnDevicesFromLocation(String location){
        List<String> temp = this.locations.get(location);
        for(String code: temp){
            this.devices.get(code).turnOn();
        }
    }

    /**
     * Método que desliga todos os SmartDevices de uma determinada divisão da CasaInteligente.
     * @param location Divisão da CasaInteligente.
     */
    public void turnOffDevicesFromLocation(String location){
        List<String> temp = this.locations.get(location);
        for(String code: temp){
            this.devices.get(code).turnOff();
        }
    }

}
