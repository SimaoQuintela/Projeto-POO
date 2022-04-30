package CasaInteligente.SmartDevices;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 */
public class SmartBulb extends SmartDevice {
    public static final int WARM = 80;
    public static final int NEUTRAL = 60;
    public static final int COLD = 40;

    private int tone;
    private int dimensions;
    private LocalDateTime time;
    private float consumptionPerDay;
    private float consumption;
    private float custoInstalacao;


    /**
     * Construtor por omissão de uma SmartBulb
     */
    public SmartBulb() {
        super();
        this.tone = NEUTRAL;
        this.time = LocalDateTime.now();
        this.dimensions = 0;
        this.consumptionPerDay = 0;
        this.consumption = 0;
        this.custoInstalacao = 0;
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     * @param tone Tonalidade da lâmpada.
     * @param dimensions Dimensões da lâmpada.
     */
    public SmartBulb(String id, boolean status, int tone, int dimensions, float consumptionPerDay, float custoInstalacao) {
        super(id, status);
        this.tone = tone;
        this.dimensions = dimensions;
        this.consumptionPerDay = consumptionPerDay;
        this.consumption = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     */
    public SmartBulb(String id) {
        super(id);
        this.tone = NEUTRAL;
        this.dimensions = 0;
        this.consumptionPerDay = 0;
        this.consumption = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = 0;
    }

    /**
     * Construtor de cópia de uma SmartBulb.
     * @param s SmartBulb que é copiada para a nova.
     */
    public SmartBulb(SmartBulb s){
        super(s.getID());
        this.tone = s.getTone();
        this.dimensions = s.getDimensions();
        this.consumptionPerDay = s.getConsumptionPerDay();
        this.consumption = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = s.getCustoInstalacao();
    }

    /**
     * Método que cria uma cópia da SmartBulb recetora da mensagem.
     * @return cópia da SmartBulb recetora da mensagem.
     */
    public SmartBulb clone(){
        return new SmartBulb(this);
    }

    /**
     * Método que verifica a igualdade entre a SmartBulb e um outro objeto.
     * @param o Objeto comparado com a SmartBulb.
     * @return Booleano que indica se são iguais.
     */
    public boolean equals(Object o){
        if(o == this)
            return true;

        if(o == null || this.getClass() != o.getClass())
            return false;

        SmartBulb s = (SmartBulb) o;

        return (
                this.tone == s.getTone()                           &&
                this.dimensions == s.getDimensions()               &&
                this.time == s.getTime()                           &&
                this.consumptionPerDay == s.getConsumptionPerDay() &&
                this.consumption == s.getConsumption()             &&
                this.custoInstalacao == s.getCustoInstalacao()
        );
    }

    /**
     * Método que produz uma string na qual está representada a SmartBulb.
     * @return string que representa a SmartBulb.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Estado: ").append(super.getOn()).append("\n");
        sb.append("Tonalidade: ").append(this.getTone()).append("\n");
        sb.append("Dimensoes: ").append(this.getDimensions()).append("\n");
        sb.append("Custo de instalacao: ").append(this.getCustoInstalacao()).append("\n");
        sb.append("Consumo por dia em Kw/H").append(this.getConsumptionPerDay()).append("\n");
        sb.append("Consumo total: ").append(this.getConsumption());
        sb.append("Custo de instalacao: ").append(this.getCustoInstalacao()).append("\n");

        return sb.toString();
    }

    /**
     * Método que liga um SmartDevice
     */
    public void turnOn() {
        super.setOn(true);
        this.time = LocalDateTime.now();
    }

    /**
     * Método que desliga um SmartDevice
     */
    public void turnOff() {
        super.setOn(false);
        this.time = LocalDateTime.now();
        this.consumo(this.time);
    }


    /**
     * Método que calcula o consumo da SmartBulb.
     */
    public void consumo(LocalDateTime anyTime) {
        if(this.getOn()){
            // escolhemos dias porque a variável consumptionPerDay representa 1 dia, em KwH
            float between = ChronoUnit.DAYS.between(this.getTime(), anyTime);
            this.consumption += this.getTone() * this.getConsumptionPerDay() * between;
            this.setTime(anyTime);
        }
    }

    /**
     * Método que devolve o custo de instalação de um Smart Device
     */
    private float getCustoInstalacao() {
        return this.custoInstalacao;
    }

    /**
     * Método que devolve a tonalidade da SmartBulb.
     * @return Tonalidade da SmartBulb.
     */
    public int getTone() {
        return this.tone;
    }

    /**
     * Método que devolve as dimensões da SmartBulb.
     * @return Dimensões da SmartBulb.
     */
    public int getDimensions(){
        return this.dimensions;
    }

    /**
     * Método que devolve a referência base de tempo.
     * @return Referência de tempo.
     */
    public LocalDateTime getTime(){
        return this.time;
    }

    public float getConsumptionPerDay() {
        return this.consumptionPerDay;
    }

    /**
     * Método que devolve o consumo diário de energia da SmartBulb.
     * @return Consumo diário da SmartBulb.
     */
    public float getConsumption(){
        return this.consumption;
    }

    /**
     * Método que altera as dimensões da SmartBulb.
     * @param dim Novas dimensões da SmartBulb.
     */
    public void setDimensions(int dim){
        this.dimensions = dim;
    }

    /**
     * Método que altera a referência de tempo.
     * @param time Referência de tempo.
     */
    public void setTime(LocalDateTime time){
        this.time = time;
    }

    /**
     * Método que altera a tonalidade da SmartBulb.
     * @param t Nova tonalidade da SmartBulb.
     */
    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public void setConsumptionPerDay(float consumptionPerDay) {
        this.consumptionPerDay = consumptionPerDay;
    }

    /**
     * Método que altera o consumo energético da SmartCamera.
     * @param consumption Novo consumo energético da SmartCamera.
     */
    public void setConsumption(int consumption){
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

