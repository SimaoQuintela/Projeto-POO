package CasaInteligente;
import CasaInteligente.SmartDevices.SmartDevice;

import java.util.*;
import java.util.stream.Collectors;

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
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices

    /**
     * Construtor por omissão de CasaInteligente.
     */
    public CasaInteligente() {
        // initialise instance variables
        this.morada = "";
        this.devices = new HashMap();
        this.locations = new HashMap();
    }

    /**
     * Construtor parametrizado de CasaInteligente.
     * @param morada
     */
    public CasaInteligente(String morada) {
        // initialise instance variables
        this.morada = morada;
        this.devices = new HashMap();
        this.locations = new HashMap();
    }

    public CasaInteligente(String morada, Map<String, SmartDevice> devices, Map<String, List<String>> locations){
        this(morada);
        this.devices = devices.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey(), e->e.getValue().clone()));

        this.locations = locations.entrySet()
                                  .stream()
                                  .collect(toMap(e-> e.getKey(), e->e.getValue()));
    }

    // verificar se a composição está bem aplicada
    public CasaInteligente(CasaInteligente c){
        this(c.morada, c.devices, c.locations);
    }

    // importante verificar se o equals está bem construído
    public boolean equals(Object o){
        if (o ==this)
            return true;

        if(o == null || o.getClass() != this.getClass())
            return false;

        CasaInteligente c = (CasaInteligente) o;

        return(
            this.getMorada().equals(c.morada) &&
            this.devices.equals(c.devices)    &&
            this.locations.equals(c.locations)
        );
    }

    public CasaInteligente clone(){
        return new CasaInteligente(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Morada: ").append(this.getMorada()).append("\n");
        sb.append("------------- Smart Devices ------------- ");
        for(String device: this.devices.keySet()){
            sb.append(this.devices.get(device).toString());
        }

        return sb.toString();
    }


    public void addRoom(String s) {
        List<String> roomDevices = new ArrayList<>();
        this.locations.put(s, roomDevices);
    }

    public boolean hasRoom(String s) {
        return this.locations.containsKey(s);
    }

    public void addToRoom(String s1, String s2) {
        if (hasRoom(s1))
            this.locations.get(s1).add(s2);
    }

    public boolean roomHasDevice(String s1, String s2) {
        return this.locations.get(s1).contains(s2);
    }

    public boolean existsDevice(String id) {
        return this.devices.containsKey(id);
    }

    public void addDevice(SmartDevice s, String location) {
        this.devices.put(s.getID(), s);
        this.locations.get(location).add(s.getID());
    }

    // gets e sets
    public String getMorada() {
        return this.morada;
    }

    public SmartDevice getDevice(String s) {
        return this.devices.get(s).clone();
    }

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

    public void setMorada(String morada) {
        this.morada = morada;
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
    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }

    public void setOn(String s, boolean b) {
        this.getDevice(s).setOn(b);
    }

    public void setAllOn(boolean b) {
        this.devices.values().forEach(s -> s.setOn(b));
    }

}
