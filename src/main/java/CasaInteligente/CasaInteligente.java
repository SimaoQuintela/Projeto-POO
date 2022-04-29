package CasaInteligente;
import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartCamera;
import CasaInteligente.SmartDevices.SmartDevice;

import java.util.*;

import static java.lang.System.out;
import static java.util.stream.Collectors.toMap;

/**
 * A CasaInteligente faz a gestão dos SmartDevices que existem e dos
 * espaços (as salas) que existem na casa.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CasaInteligente {
    private String proprietario;
    private int numeroDePorta;
    private long NIF;
    private String morada;
    private String fornecedor;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices

    /**
     * Construtor por omissão de CasaInteligente.
     */
    public CasaInteligente() {
        this.proprietario = "";
        this.numeroDePorta = 0;
        this.NIF = 0;
        this.morada = "";
        this.fornecedor = "";
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
    }

    /**
     * ESTE CONSTRUTOR É O CORRETO
     * @param proprietario
     * @param NIF
     * @param fornecedor
     */
    public CasaInteligente(String proprietario, long NIF, String fornecedor){
        this.proprietario = proprietario;
        this.NIF = NIF;
        this.fornecedor = fornecedor;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
    }


    /**
     * Construtor parametrizado de CasaInteligente.
     * @param proprietario Proprietário da casa
     * @param numeroDePorta Número de porta da casa
     * @param NIF NIF do proprietário da casa
     * @param fornecedor Fornecedor de energia da casa
     * @param morada Morada da casa.
     */
    public CasaInteligente(String proprietario, int numeroDePorta, long NIF, String morada, String fornecedor) {
        this.proprietario = proprietario;
        this.numeroDePorta = numeroDePorta;
        this.NIF = NIF;
        this.morada = morada;
        this.fornecedor = fornecedor;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
    }

    /**
     * Construtor parametrizado de CasaInteligente.
     * @param morada Morada da CasaInteligente.
     * @param devices Dispositivos existentes na casa.
     * @param locations Divisões da CasaInteligente.
     */
    public CasaInteligente(String proprietario, int numeroDePorta, long NIF, String morada, String fornecedor, Map<String, SmartDevice> devices, Map<String, List<String>> locations){
        this(proprietario, numeroDePorta, NIF, morada, fornecedor);
        this.devices = devices.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey(), e->e.getValue().clone()));

        this.locations = locations.entrySet()
                                  .stream()
                                  .collect(toMap(e-> e.getKey(), e->e.getValue()));
    }

    /**
     * Construtor de cópia de CasaInteligente.
     * @param c CasaInteligente que é copiada.
     */
    public CasaInteligente(CasaInteligente c){
        this(c.proprietario, c.numeroDePorta, c.NIF, c.morada, c.fornecedor, c.devices, c.locations);
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
            this.numeroDePorta == c.numeroDePorta     &&
            this.NIF == c.NIF                         &&
            this.getMorada().equals(c.morada)         &&
            this.getFornecedor().equals(c.fornecedor) &&
            this.devices.equals(c.devices)            &&
            this.locations.equals(c.locations)
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
        sb.append("Numero de porta: ").append(this.numeroDePorta).append("\n");
        sb.append("Morada: ").append(this.getMorada()).append("\n");
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
            sb.append("id: ").append(id).append("\n");
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
/* Método redundante
    /**
     * Método que adiciona o identificador de um SmartDevice a uma divisão da CasaInteligente.
     * @param s1 Divisão da CasaInteligente.
     * @param s2 Código de identificação do SmartDevice.

    public void addToRoom(String s1, String s2) {
        if (hasRoom(s1))
            this.locations.get(s1).add(s2);
    }
*/
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
            this.locations.put(location, ids);
        }
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
     * Método que devolve o número da porta da casa
     * @return Número da porta
     */
    public int getNumeroDePorta() {
        return this.numeroDePorta;
    }

    /**
     * Método que devolve o NIF do proprietário da casa
     * @return NIF do proprietário da casa
     */
    public double getNIF() {
        return this.NIF;
    }

    /**
     * Método que devolve a morada da CasaInteligente.
     * @return Morada da CasaInteligente.
     */
    public String getMorada() {
        return this.morada;
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
        return this.devices.get(s).clone();
    }

    /**
     * Método que devolve a estrutura como todos os devices da casa
     * @return HashMap com os devices da casa
     */
    public Map<String, SmartDevice> getDevices() {
        Map<String, SmartDevice> new_devices = new HashMap<>();
        new_devices = this.devices.entrySet()
                                  .stream()
                                  .collect(toMap(e->e.getKey(), e->e.getValue().clone()));


        return new_devices;
    }

    /* dúvidas aqui ao adicionar os values
    public Map<String, List<String>> getLocations() {
        Map<String, SmartDevice> new_locations = new HashMap<>();
        new_locations = this.locations.entrySet()
                                      .stream()
                                      .collect(toMap(e->e.getKey(),    ));


        return new_locations;
    }
    */

    /**
     * Método que coloca na variável proprietário o nome passado como parâmetro
     * @param proprietario String passada como parâmetro que vai ser colocada na variável proprietário
     */
    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Método que coloca na variável numeroDePorta o inteiro passado como parâmetro
     * @param numeroDePorta Inteiro passado como parâmetro que vai ser colocado na variável numeroDePorta
     */
    public void setNumeroDePorta(int numeroDePorta) {
        this.numeroDePorta = numeroDePorta;
    }

    /**
     * Método que coloca na variável NIF o double passado como parâmetro
     * @param NIF Double passado como parâmetro que vai ser colocado na variável NIF
     */
    public void setNIF(long NIF) {
        this.NIF = NIF;
    }

    /**
     * Método que altera a morada da CasaInteligente.
     * @param morada Morada atribuída à CasaInteligente.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Método que coloca na variável fornecedor o valor passado como parâmetro
     * @param fornecedor Nome do fornecedor
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /* discutir com o grupo estes dois métodos
    public void setDevices(Map<String, SmartDevice> devices) {
        this.devices = devices.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    public void setLocations(Map<String, List<String>> locations) {
        this.locations = locations.entrySet()
                                  .stream()
                                  .collect(toMap(e-> e.getKey(), e->e.getValue()));
    }
    */

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
