package CasaInteligente.SmartDevices;

import java.time.LocalDateTime;

/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos.
 */
public abstract class SmartDevice {
    private String id;
    private boolean on;
    private LocalDateTime time;
    private float consumption;
    private float consumptionPerDay;
    private int custoInstalacao;

    /**
     * Construtor por omissão de um SmartDevice.
     */
    public SmartDevice() {
        this.id = "";
        this.on = false;
        this.consumption = 0;
        this.consumptionPerDay = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = 0;
    }

    /**
     * Construtor parametrizado de um SmartDevice.
     * @param id Código que identifica o SmartDevice.
     */
    public SmartDevice(String id) {
        this.id = id;
        this.on = false;
        this.consumption = 0;
        this.consumptionPerDay = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = 0;
    }

    /**
     * Construtor parametrizado de um SmartDevice.
     * @param id Código que identifica o SmartDevice.
     * @param status Estado do SmartDevice.
     */
    public SmartDevice(String id, boolean status) {
        this.id = id;
        this.on = status;
        this.consumption = 0;
        this.consumptionPerDay = 0;
        this.time = LocalDateTime.now();
        this.custoInstalacao = 0;
    }

    /**
     * Construtor parametrizado de um SmartDevice.
     * @param id Código que identifica o SmartDevice.
     * @param status Estado (ligado ou desligado) do SmartDevice.
     */
    public SmartDevice(String id, boolean status, float consumptionPerDay, int custoInstalacao){
        this.id = id;
        this.on = status;
        this.consumption = 0;
        this.consumptionPerDay = consumptionPerDay;
        this.time = LocalDateTime.now();
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * Construtor de cópia de um SmartDevice.
     * @param s SmartDevice utilizado no processo de cópia para o novo SmartDevice.
     */
    public SmartDevice(SmartDevice s) {
        this(s.getID(), s.getOn(), s.getConsumptionPerDay(), s.getCustoInstalacao());
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

    /**
     * Método que calcula o consumo do SmartDevice.
     */
    public abstract void consumo(LocalDateTime reset_time);

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
     * Método que devolve o consumo de energia do SmartDevice.
     * @return Consumo do SmartDevice.
     */
    public float getConsumption(){
        return this.consumption;
    }

    /**
     * Método que devolve o consumo diário do SmartDevice.
     * @return Consumo diário do SmartDevice.
     */
    public float getConsumptionPerDay() {
        return this.consumptionPerDay;
    }

    /**
     * Método que devolve o custo de instalação do SmartDevice.
     * @return Custo de instalação do SmartDevice.
     */
    public int getCustoInstalacao() {
        return this.custoInstalacao;
    }

    /**
     * Método que devolve o momento em que o estado do SmartDevice foi alterado pela última vez.
     * @return Momento em que o estado do SmartDevice foi alterado pela última vez.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

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

    /**
     * Método que altera o consumo energético da SmartCamera.
     * @param consumption Novo consumo energético da SmartCamera.
     */
    public void setConsumption(float consumption){
        this.consumption = consumption;
    }

    /**
     * Método que altera o custo de instalação do SmartDevice.
     * @param custoInstalacao Custo de instalação do SmartDevice.
     */
    public void setCustoInstalacao(int custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * Método que altera o consumo diário do SmartDevice.
     * @param consumptionPerDay Consumo diário do SmartDevice.
     */
    public void setConsumptionPerDay(float consumptionPerDay) {
        this.consumptionPerDay = consumptionPerDay;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
