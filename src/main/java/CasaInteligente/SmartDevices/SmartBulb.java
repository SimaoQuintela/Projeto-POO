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
    }

    /**
     * Construtor parametrizado de uma SmartBulb.
     * @param id Código que identifica a SmartBulb.
     * @param tone
     */
    public SmartBulb(String id, int tone) {
        // initialise instance variables
        super(id);
        this.tone = tone;
    }

    /**
     *
     * @param id
     */
    public SmartBulb(String id) {
        // initialise instance variables
        super(id);
        this.tone = NEUTRAL;
    }

    public SmartBulb(SmartBulb s){
        super(s.getID());
        this.tone = s.getTone();
    }

    public SmartBulb clone(){
        return new SmartBulb(this);
    }

    // dúvidas aqui, devo comparar com a class SmartDevice?
    public boolean equals(Object o){
        if(o == this)
            return true;

        if(o == null || this.getClass() != o.getClass())
            return false;

        SmartBulb s = (SmartBulb) o;

        return (this.tone == s.getTone());
    }

    // dúvidas aqui, devo invocar o toString da classe SmartDevice ?
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("------- Smart Bulb -------\n");
        sb.append("Tonalidade: ").append(this.getTone()).append("\n");

        return sb.toString();
    }


    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public int getTone() {
        return this.tone;
    }

}

