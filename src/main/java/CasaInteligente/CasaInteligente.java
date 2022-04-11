package CasaInteligente;
import CasaInteligente.SmartDevices.SmartDevice;

import java.util.*;


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
     * Constructor for objects of class CasaInteligente
     */
    public CasaInteligente() {
        // initialise instance variables
        this.morada = "";
        this.devices = new HashMap();
        this.locations = new HashMap();
    }

    public CasaInteligente(String morada) {
        // initialise instance variables
        this.morada = morada;
        this.devices = new HashMap();
        this.locations = new HashMap();
    }

    
    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }

    public boolean existsDevice(String id) {
        return this.devices.containsKey(id);
    }
    
    public void addDevice(SmartDevice s) {
        this.devices.put(s.getID(), s);
    }

    public SmartDevice getDevice(String s) {
        return this.devices.get(s);
    }

    public void setOn(String s, boolean b) {
        this.getDevice(s).setOn(b);
    }

    public void setAllOn(boolean b) {
        this.devices.values().forEach(s -> s.setOn(b));
    }

    public void addRoom(String s) {
        List<String> roomDevices = new ArrayList<>();
        this.locations.put(s, roomDevices);
    }

    public boolean hasRoom(String s) {
        return this.locations.containsKey(s);
    }

    public void addToRoom (String s1, String s2) {
        if(hasRoom(s1))
            this.locations.get(s1).add(s2);


    }

    public boolean roomHasDevice (String s1, String s2) {
        return this.locations.get(s1).contains(s2);
    }
}
