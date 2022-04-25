package CasaInteligente.SmartDevices;

/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos.
 */
public abstract class SmartDevice {
    private String id;
    private boolean on;

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
    public abstract SmartDevice clone();

    /**
     * Método que verifica a igualdade entre um objeto e o SmartDevice recetor da mensagem.
     * @param o objeto comparado com o SmartDevice.
     * @return booleano que indica se são iguais.
     */
    public abstract boolean equals(Object o);

    /**
     * Método que produz uma String na qual está representado o SmartDevice.
     * @return string que representa o SmartDevice.
     */
    public abstract String toString();

    /**
     * Método que liga o SmartDevice.
     */
    public abstract void turnOn();

    /**
     * Método que desliga o SmartDevice.
     */
    public abstract void turnOff();

    public abstract void resetTime();

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
     * Método que altera o estado do SmartDevice.
     * @param b Novo estado do SmartDevice.
     */
    public void setOn(boolean b) {this.on = b;}

    /**
     * Coloca em id o valor passado como parâmetro
     * @param id Id do SmartDevice
     */
    public void setId(String id) {
        this.id = id;
    }
}
