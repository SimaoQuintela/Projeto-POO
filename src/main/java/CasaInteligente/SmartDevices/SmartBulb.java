package CasaInteligente.SmartDevices;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;

/**
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 */
public class SmartBulb extends SmartDevice implements Serializable {
    private static final int WARM = 80;
    private static final int NEUTRAL = 60;
    private static final int COLD = 40;

    private int tone;
    private int dimensions;


    /**
     * Construtor por omissão de uma SmartBulb
     */
    public SmartBulb() {
        super();
        this.tone = NEUTRAL;
        this.dimensions = 0;
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     * @param tone Tonalidade da lâmpada.
     * @param dimensions Dimensões da lâmpada.
     */
    public SmartBulb(String id, boolean status, int tone, int dimensions, float consumptionPerDay, int custoInstalacao) {
        super(id, status, consumptionPerDay, custoInstalacao);
        this.tone = tone;
        this.dimensions = dimensions;
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     */
    public SmartBulb(String id) {
        super(id);
        this.tone = NEUTRAL;
        this.dimensions = 0;
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     */
    public SmartBulb(String id, boolean status) {
        super(id, status);
        this.tone = NEUTRAL;
        this.dimensions = 0;
    }

    /**
     * Construtor de cópia de uma SmartBulb.
     * @param s SmartBulb que é copiada para a nova.
     */
    public SmartBulb(SmartBulb s){
        super(s.getID(), s.getOn(), s.getConsumptionPerDay(), s.getCustoInstalacao());
        this.tone = s.getTone();
        this.dimensions = s.getDimensions();
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
                this.dimensions == s.getDimensions()
        );
    }

    /**
     * Método que produz uma string na qual está representada a SmartBulb.
     * @return string que representa a SmartBulb.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(super.getID()).append("\n");
        sb.append("Estado: ").append(super.getOn()).append("\n");
        sb.append("Tonalidade: ").append(this.getTone()).append("\n");
        sb.append("Dimensoes: ").append(this.getDimensions()).append("\n");
        sb.append("Custo de instalacao: ").append(super.getCustoInstalacao()).append("\n");
        sb.append("Consumo por dia em Kw/H: ").append(super.getConsumptionPerDay()).append("\n");
        sb.append("Consumo total: ").append(super.getConsumption()).append("\n");

        return sb.toString();
    }

    public void writeInFile(FileWriter writer) throws IOException {
        String tone = "";

        if(this.getTone() == 80){
            tone = "Warm";
        } else if(this.getTone() == 60) {
            tone = "Neutral";
        } else {
            tone = "Cold";
        }

        String line = "SmartBulb:" + tone + "," + this.getDimensions() + "," +
                      super.getConsumptionPerDay() + "\n";

        writer.write(line);
        writer.flush();
    }


    /**
     * Método que liga um SmartDevice
     */
    public void turnOn() {
        super.setOn(true);
        super.setTime(LocalDate.now());
    }

    /**
     * Método que desliga um SmartDevice
     */
    public void turnOff() {
        super.setOn(false);
        super.setTime(LocalDate.now());
    }

    /**
     * Método que calcula o consumo da SmartBulb.
     */
    public void consumo(LocalDate before, LocalDate after) {
        if(this.getOn()){
            float between = ChronoUnit.DAYS.between(before, after);
            super.setConsumption(super.getConsumption() + this.getTone()/80 * super.getConsumptionPerDay() * between);
            super.setTime(after);
        }

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
     * Método que altera a tonalidade da SmartBulb.
     * @param t Nova tonalidade da SmartBulb.
     */
    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

}

