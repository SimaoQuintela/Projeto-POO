package CasaInteligente.SmartDevices;

/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos. 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartDevice {

    private String id;
    private boolean on;

    /**
     * Constructor for objects of class SmartDevice
     */
    public SmartDevice() {
        this.id = "";
        this.on = false;
    }

    public SmartDevice(String s) {
        this.id = s;
        this.on = false;
    }

    public SmartDevice(String s, boolean on){
        this.id = s;
        this.on = on;
    }

    public SmartDevice(SmartDevice s) {
        this(s.getID(), s.getOn());
    }

    public SmartDevice clone(){
        return new SmartDevice(this);
    }

    public boolean equals(Object o){
        if(this == o) {
            return true;
        }

        if(o == null || this.getClass() != o.getClass()) {
            return false;
        }

        SmartDevice s = (SmartDevice)o;

        return (
                this.id.equals(s.getID()) &&
                this.getOn() == s.getOn()
        );
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(this.getID()).append("\n");
        sb.append("Ligado: ").append(this.getOn()).append("\n");

        return sb.toString();
    }

    public boolean getOn() {return this.on;}

    public String getID() {return this.id;}

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public void setOn(boolean b) {this.on = b;}

}
