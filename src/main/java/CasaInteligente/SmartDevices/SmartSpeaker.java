package CasaInteligente.SmartDevices;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 */
public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume máximo

    private int volume;
    private String channel;
    private String brand;
    private float consumption; //configurar o consumo nos métodos da classe
    private LocalDateTime time;
    private float custoInstalacao;


    /**
     * Construtor por omissão de SmartSpeaker.
     */
    public SmartSpeaker() {
        // initialise instance variables
        super();
        this.channel = "";
        this.volume = 0;
        this.brand = "";
        this.consumption = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = 0;
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
        this.consumption = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = 0;
    }

    /**
     * Construtor parametrizado de SmartSpeaker.
     * @param cod Código que identifica a SmartSpeaker.
     * @param channel Canal de rádio.
     * @param volume Volume da SmartSpeaker.
     * @param brand Marca da SmartSpeaker.
     */
    public SmartSpeaker(String cod, boolean on, String channel, int volume, String brand, float custoInstalacao) {
        // initialise instance variables
        super(cod, on);
        this.channel = channel;
        this.volume = volume;
        this.brand = brand;
        this.consumption = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * Construtor de cópia de SmartSpeaker.
     * @param s SmartSpeaker que é copiada.
     */
    public SmartSpeaker(SmartSpeaker s){
        this(s.getID() , s.getOn(), s.getChannel(), s.getVolume(), s.getBrand(), s.getCustoInstalacao());
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
                this.volume == s.getVolume()             &&
                this.getChannel().equals(s.getChannel()) &&
                this.brand.equals(s.getBrand())          &&
                this.consumption == s.getConsumption()   &&
                this.time.equals(s.getTime())            &&
                this.custoInstalacao == s.getCustoInstalacao()
        );
    }

    /**
     * Método que produz uma string na qual está representada a SmartSpeaker.
     * @return String que representa a SmartSpeaker.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("------- Smart Speaker -------\n");
        sb.append("Canal: ").append(this.getChannel()).append("\n");
        sb.append("Volume: ").append(this.getVolume()).append("\n");
        sb.append("Marca: ").append(this.getBrand()).append("\n");
        sb.append("Consumo: ").append(this.getConsumption()).append("\n");
        sb.append("Custo de instalacao: ").append(this.getCustoInstalacao()).append("\n");

        return sb.toString();
    }

    /**
     * Método que liga um SmartDevice
     */
    public void turnOn() {
        super.setOn(true);
        //this.time = LocalDateTime.now();
    }

    /**
     * Método que desliga um SmartDevice
     */
    public void turnOff() {
        super.setOn(false);
        //resetTime();
    }


    /*public void resetTime(){
        LocalDateTime temp = this.time;
        setTime(LocalDateTime.now());
        Duration duration = Duration.between(temp, this.time);
        long interval = duration.toHours();

        if(this.getOn()){
            float temp_consumption = (float)(this.getVolume() * this.getBrand().length() * interval) / 1000;
            this.consumption = this.consumption + temp_consumption;
        }
    }*/

    /**
     * Método que calcula o consumo da SmartSpeaker.
     */
    public void consumo(){
        if(this.getOn()){
            this.consumption = (float)(this.volume * this.brand.length());
        }else{
            this.consumption = 0;
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
     * Método que devolve o tempo desde o último reset
     * @return Tempo em que ocorreu o último reset
     */
    public LocalDateTime getTime() {
        return this.time;
    }

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
     * Método que devolve o consumo energético da SmartCamera.
     * @return Consumo energético da SmartCamera.
     */
    public float getConsumption(){
        return this.consumption;
    }

    /**
     * Método que devolve o custo de instalação de um Smart Device
     */
    private float getCustoInstalacao() {
        return this.custoInstalacao;
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

    /**
     * Método que coloca na variável time o tempo passado como parâmetro
     * @param time Valor que corresponde a um tempo de reset
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Método que altera o consumo energético da SmartCamera.
     * @param consumption Novo consumo energético da SmartCamera.
     */
    public void setConsumption(float consumption){
        this.consumption = consumption;
    }

    /**
     * Método que devolve o custo de instalação de um Smart Device
     * @param custoInstalacao Custo de instalação do Smart Device
     */
    public void setCustoInstalacao(float custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }

}
