package CasaInteligente.SmartDevices;

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume máximo
    
    private int volume;
    private String channel;


    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        // initialise instance variables
        this.channel = "";
        this.volume = 0;
    }

    public SmartSpeaker(String s) {
        // initialise instance variables
        this.channel = s;
        this.volume = 0;
    }

    public SmartSpeaker(String cod, String channel, int i) {
        // initialise instance variables
        this.channel = channel;
        this.setVolume(i);
    }

    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }

    public int getVolume() {return this.volume;}

    public void setVolume(int i){
        if(i >= 0 && i <= 20 )
            this.volume = i;
    }

    public String getChannel() {return this.channel;}
    
    public void setChannel(String c) {this.channel = c;}

}