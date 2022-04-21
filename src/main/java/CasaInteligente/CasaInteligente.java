package CasaInteligente;
import CasaInteligente.SmartDevices.SmartDevice;

import java.util.*;
import java.util.stream.Collectors;

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
    private String morada;
    private String fornecedor;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices

    /**
     * Construtor por omissão de CasaInteligente.
     */
    public CasaInteligente() {
        // initialise instance variables
        this.morada = "";
        this.fornecedor = "";
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
    }

    /**
     * Construtor parametrizado de CasaInteligente.
     * @param morada Morada da CasaInteligente.
     */
    public CasaInteligente(String morada, String fornecedor) {
        // initialise instance variables
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
    public CasaInteligente(String morada, String fornecedor, Map<String, SmartDevice> devices, Map<String, List<String>> locations){
        this(morada, fornecedor);
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
    // verificar se a composição está bem aplicada
    public CasaInteligente(CasaInteligente c){
        this(c.morada, c.fornecedor, c.devices, c.locations);
    }

    /**
     * Método que verifica a igualdade entre a CasaInteligente e um outro objeto.
     * @param o Objeto que é compara com a CasaInteligente.
     * @return Booleano que indica o resultado da comparação.
     */
    // importante verificar se o equals está bem construído
    public boolean equals(Object o){
        if (o ==this)
            return true;

        if(o == null || o.getClass() != this.getClass())
            return false;

        CasaInteligente c = (CasaInteligente) o;

        return(
            this.getMorada().equals(c.morada) &&
            this.getFornecedor().equals(c.fornecedor) &&
            this.devices.equals(c.devices)    &&
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

        sb.append("Morada: ").append(this.getMorada());
        sb.append("\nFornecedor: ").append(this.getFornecedor());
        sb.append("\n------------- Locations -------------\n");

        for(String division: locations.keySet()){
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

    /**
     * Método que adiciona o identificador de um SmartDevice a uma divisão da CasaInteligente.
     * @param s1 Divisão da CasaInteligente.
     * @param s2 Código de identificação do SmartDevice.
     */
    public void addToRoom(String s1, String s2) {
        if (hasRoom(s1))
            this.locations.get(s1).add(s2);
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
        this.locations.get(location).add(s.getID());
    }

    public void addLocation(String location, List<String> ids){
        this.locations.put(location, ids);
    }

    /**
     * Método que devolve a morada da CasaInteligente.
     * @return Morada da CasaInteligente.
     */
    // gets e sets
    public String getMorada() {
        return this.morada;
    }

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

    //DOCUMENTAR
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
     * Método que altera a morada da CasaInteligente.
     * @param morada Morada atribuída à CasaInteligente.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

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
