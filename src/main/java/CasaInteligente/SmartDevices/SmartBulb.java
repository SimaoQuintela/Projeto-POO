package CasaInteligente.SmartDevices;

/**
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartBulb extends SmartDevice {
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    
    private int tone;
    private int dimensions;
    private int dailyConsumption;


    /**
     * Construtor por omissão de uma SmartBulb
     */
    public SmartBulb() {
        // initialise instance variables
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
    public SmartBulb(String id, int tone, int dimensions) {
        // initialise instance variables
        super(id);
        this.tone = tone;
        this.dimensions = dimensions;
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
    }

    /**
     * Construtor de cópia de uma SmartBulb.
     * @param s SmartBulb que é copiada para a nova.
     */
    public SmartBulb(SmartBulb s){
        super(s.getID());
        this.tone = s.getTone();
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

}

