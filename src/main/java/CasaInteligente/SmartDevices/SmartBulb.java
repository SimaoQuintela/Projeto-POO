package CasaInteligente.SmartDevices;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartBulb extends SmartDevice {
    public static final int WARM = 80;
    public static final int NEUTRAL = 60;
    public static final int COLD = 40;

    private int tone;
    private int dimensions;
    private LocalDateTime time;

    // falta adicionar esta var aos construtores e métodos
    private float dailyConsumption;


    /**
     * Construtor por omissão de uma SmartBulb
     */
    public SmartBulb() {
        // initialise instance variables
        super();
        this.tone = NEUTRAL;
        this.time = LocalDateTime.now();
        this.dimensions = 0;
        this.dailyConsumption = 0;
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     * @param tone Tonalidade da lâmpada.
     * @param dimensions Dimensões da lâmpada.
     * @param time
     */
    //COMPLETAR DOCUMENTAÇAO
    public SmartBulb(String id, boolean status, int tone, int dimensions, LocalDateTime time) {
        // initialise instance variables
        super(id, status);
        this.tone = tone;
        this.dimensions = dimensions;
        this.dailyConsumption = 0;
        this.time = time;
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     */
    public SmartBulb(String id) {
        // initialise instance variables
        super(id);
        this.tone = NEUTRAL;
        this.dimensions = 0;
        this.dailyConsumption = 0;
        this.time = LocalDateTime.now();
    }

    /**
     * Construtor de cópia de uma SmartBulb.
     * @param s SmartBulb que é copiada para a nova.
     */
    public SmartBulb(SmartBulb s){
        super(s.getID());
        this.tone = s.getTone();
        this.dimensions = s.getDimensions();
        this.dailyConsumption = 0;
        this.time = LocalDateTime.now();
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
    // dúvidas aqui, devo comparar com a class SmartDevice?
    public boolean equals(Object o){
        if(o == this)
            return true;

        if(o == null || this.getClass() != o.getClass())
            return false;

        SmartBulb s = (SmartBulb) o;

        return (this.tone == s.getTone() &&
                this.dimensions == s.getDimensions());
    }

    /**
     * Método que produz uma string na qual está representada a SmartBulb.
     * @return string que representa a SmartBulb.
     */
    // dúvidas aqui, devo invocar o toString da classe SmartDevice ?
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("------- Smart Bulb -------\n");
        sb.append("Tonalidade: ").append(this.getTone()).append("\n");

        return sb.toString();
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
     * Método que devolve a referência base de tempo.
     * @return Referência de tempo.
     */
    public LocalDateTime getTime(){
        return this.time;
    }

    /**
     * Método que devolve o consumo diário de energia da SmartBulb.
     * @return Consumo diário da SmartBulb.
     */
    public float getDailyConsumption(){
        return this.dailyConsumption;
    }

    /**
     * Método que atualiza a referência base de tempo.
     */
    public void resetTime(){
        LocalDateTime temp = this.time;
        setTime(LocalDateTime.now());
        Duration duration = Duration.between(temp, this.time);
        long interval = duration.toHours();

        if(this.getOn()){
            float consumption = (float)(this.tone * interval) / 1000;
            this.dailyConsumption = (this.dailyConsumption + consumption) / 2;
        }
    }

}

