package CasaInteligente.SmartDevices;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 */
public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 100; //volume máximo

    private int volume;
    private String channel;
    private String brand;


    /**
     * Construtor por omissão de SmartSpeaker.
     */
    public SmartSpeaker() {
        // initialise instance variables
        super();
        this.channel = "";
        this.volume = 0;
        this.brand = "";
    }

    /**
     * ACABAR DOCUMENTAÇAO
     * Construtor parametrizado de SmartSpeaker.
     * @param s Canal de rádio.
     * @param volume Volume da SmartSpeaker.
     * @param brand Marca da SmartSpeaker.
     */
    public SmartSpeaker(String s, String brand, int volume) {
        // initialise instance variables
        super();
        this.channel = s;
        this.volume = volume;
        this.brand = brand;
    }

    /**
     * Construtor parametrizado de SmartSpeaker.
     * @param cod Código que identifica a SmartSpeaker.
     * @param channel Canal de rádio.
     * @param volume Volume da SmartSpeaker.
     * @param brand Marca da SmartSpeaker.
     */
    public SmartSpeaker(String cod, boolean on, String channel, int volume, String brand, float consumptionPerDay, int custoInstalacao) {
        // initialise instance variables
        super(cod, on, consumptionPerDay, custoInstalacao);
        this.channel = channel;
        this.volume = volume;
        this.brand = brand;
    }

    /**
     * Construtor de cópia de SmartSpeaker.
     * @param s SmartSpeaker que é copiada.
     */
    public SmartSpeaker(SmartSpeaker s){
        this(s.getID() , s.getOn(), s.getChannel(), s.getVolume(), s.getBrand(), s.getConsumptionPerDay(), s.getCustoInstalacao());
    }

    /**
     * Método que cria uma cópia de uma SmartSpeaker.
     * @return Cópia da SmartSpeaker.
     */
    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }

    /**
     * Método que verifica a igualdade entre a SmartSpeaker e um outro objeto.
     * @param o objeto comparado com a SmartSpeaker.
     * @return Booleano que indica o resultado da comparação.
     */
    public boolean equals(Object o){
        if(this == o)
            return true;

        if(o == null || o.getClass() != this.getClass())
            return false;

        SmartSpeaker s = (SmartSpeaker) o;

        return(
                this.volume == s.getVolume()                       &&
                this.getChannel().equals(s.getChannel())           &&
                this.brand.equals(s.getBrand())
        );
    }

    /**
     * Método que produz uma string na qual está representada a SmartSpeaker.
     * @return String que representa a SmartSpeaker.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Estado: ").append(super.getOn()).append("\n");
        sb.append("Canal: ").append(this.getChannel()).append("\n");
        sb.append("Volume: ").append(this.getVolume()).append("\n");
        sb.append("Marca: ").append(this.getBrand()).append("\n");
        sb.append("Consumo por dia em Kw/H: ").append(super.getConsumptionPerDay()).append("\n");
        sb.append("Consumo total: ").append(super.getConsumption()).append("\n");
        sb.append("Custo de instalacao: ").append(super.getCustoInstalacao()).append("\n");

        return sb.toString();
    }

    /**
     * Método que liga um SmartDevice
     */
    public void turnOn() {
        super.setOn(true);
        super.setTime(LocalDateTime.now());
    }

    /**
     * Método que desliga um SmartDevice
     */
    public void turnOff() {
        super.setOn(false);
        super.setTime(LocalDateTime.now());
        consumo(super.getTime());
    }


    /**
     * Método que calcula o consumo da SmartSpeaker.
     */
    public void consumo(LocalDateTime anyTime){
        if(this.getOn()){
            float between = ChronoUnit.DAYS.between(super.getTime(), anyTime);
            super.setConsumption(this.getVolume() * super.getConsumptionPerDay() * between);
            super.setTime(anyTime);
        } else {
            super.setConsumption(0);
        }
    }

    /**
     * Método que aumenta uma unidade no volume da SmartSpeaker.
     */
    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    /**
     * Método que reduz uma unidade no volume da SmartSpeaker.
     */
    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }

    /**
     * Método que devolve o volume da SmartSpeaker.
     * @return Volume da SmartSpeaker.
     */
    public int getVolume() {return this.volume;}

    /**
     * Método que devolve o canal de rádio que está a tocar na SmartSpeaker.
     * @return Canal de rádio que está a tocar na SmartSpeaker.
     */
    public String getChannel() {return this.channel;}

    /**
     * Método que devolve a marca da SmartSpeaker.
     * @return Marca da SmartSpeaker.
     */
    public String getBrand(){
        return this.brand;
    }


    /**
     * Método que altera o canal de rádio que toca na SmartSpeaker.
     * @param c Novo canal de rádio que toca na SmartSpeaker.
     */
    public void setChannel(String c) {this.channel = c;}

    /**
     * Método que altera a marca da SmartSpeaker.
     * @param s Nova marca da SmartSpeaker.
     */
    public void setBrand(String s){
        this.brand = s;
    }

    /**
     * Método que altera o volume da SmartSpeaker.
     * @param i Novo volume da SmartSpeaker.
     */
    public void setVolume(int i){
        if(i >= 0 && i <= 20 )
            this.volume = i;
    }


}
