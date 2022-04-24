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
    // Apresentar sugestão sobre adicionar uma variável consumo em SmartDevice por causa da abstração
    // Apresentar sugestão sobre adicionar uma variável abstrata custoInstalacao

    /**
     * Construtor por omissão de um SmartDevice.
     */
    public SmartDevice() {
        this.id = "";
        this.on = false;
    }

    /**
     * Construtor parametrizado de um SmartDevice.
     * @param id Código que identifica o SmartDevice.
     */
    public SmartDevice(String id) {
        this.id = id;
        this.on = false;
    }

    /**
     * Construtor parametrizado de um SmartDevice.
     * @param id Código que identifica o SmartDevice.
     * @param status Estado (ligado ou desligado) do SmartDevice.
     */
    public SmartDevice(String id, boolean status){
        this.id = id;
        this.on = status;
    }

    /**
     * Construtor de cópia de um SmartDevice.
     * @param s SmartDevice utilizado no processo de cópia para o novo SmartDevice.
     */
    public SmartDevice(SmartDevice s) {
        this(s.getID(), s.getOn());
    }

    /**
     * Método que devolve uma cópia do SmartDevice recetor da mensagem.
     * @return cópia do SmartDevice recetor da mensagem.
     */
    public SmartDevice clone(){
        return new SmartDevice(this);
    }

    /**
     * Método que verifica a igualdade entre um objeto e o SmartDevice recetor da mensagem.
     * @param o objeto comparado com o SmartDevice.
     * @return booleano que indica se são iguais.
     */
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

    /**
     * Método que produz uma String na qual está representado o SmartDevice.
     * @return string que representa o SmartDevice.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(this.getID()).append("\n");
        sb.append("Ligado: ").append(this.getOn()).append("\n");

        return sb.toString();
    }


    /**
     * Método que devolve o estado do SmartDevice.
     * @return Estado do SmartDevice.
     */
    public boolean getOn() {return this.on;}

    /**
     * Método que devolve o código que identifica o SmartDevice.
     * @return Código que identifica o SmartDevice.
     */
    public String getID() {return this.id;}


    /**
     * Método que liga o SmartDevice.
     */
    public void turnOn() {
        this.on = true;
    }

    /**
     * Método que desliga o SmartDevice.
     */
    public void turnOff() {
        this.on = false;
    }

    /**
     * Método que altera o estado do SmartDevice.
     * @param b Novo estado do SmartDevice.
     */
    public void setOn(boolean b) {this.on = b;}

}
