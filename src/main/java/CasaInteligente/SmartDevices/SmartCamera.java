package CasaInteligente.SmartDevices;

import java.time.Duration;
import java.time.LocalDateTime;

public class SmartCamera extends SmartDevice {
    private int xRes;
    private int yRes;
    private int fileSize;
    private float consumption; //tamanho do ficheiro * resolução
    private float custoInstalacao;
    private LocalDateTime time;

    /**
     * Construtor por omissão de SmartCamera.
     */
    public SmartCamera(){
        super();
        this.xRes = 0;
        this.yRes = 0;
        this.fileSize = 0;
        this.consumption = 0;
        this.custoInstalacao = 0;
        this.time = LocalDateTime.now();
    }

    /**
     * Construtor parametrizado.
     * @param id Código que identifica a SmartCamera.
     * @param status Estado da SmartCamera.
     * @param xRes Resolução da SmartCamera no eixo do x.
     * @param yRes Resolução da SmartCamera no eixo do y
     * @param fileSize Tamanho do ficheiro da SmartCamera.
     */
    public SmartCamera(String id, boolean status, int xRes, int yRes, int fileSize, float custoInstalacao){
        super(id, status);
        this.xRes = xRes;
        this.yRes = yRes;
        this.fileSize = fileSize;
        this.consumption = this.xRes * this.yRes * this.fileSize;
        this.custoInstalacao = custoInstalacao;
        this.time = LocalDateTime.now();
    }

    /**
     * Construtor parametrizado.
     * @param id Código que identifica a SmartCamera.
     * @param status Estado da SmartCamera.
     */
    public SmartCamera(String id, boolean status){
        super(id, status);
        this.xRes = 0;
        this.yRes = 0;
        this.fileSize = 0;
        this.consumption = 0;
        this.custoInstalacao = 0;
        this.time = LocalDateTime.now();
    }

    /**
     * Construtor de cópia da SmartCamera.
     * @param s SmartCamera que é copiada para a nova.
     */
    public SmartCamera(SmartCamera s){
        super(s.getID(), s.getOn());
        this.xRes = s.getxRes();
        this.yRes = s.getyRes();
        this.fileSize = s.getFileSize();
        this.consumption = s.getxRes() * s.getyRes() * s.getFileSize();
        this.custoInstalacao = s.getCustoInstalacao();
        this.time = s.getTime();
    }

    /**
     * Método que verifica a igualdade entre a SmartCamera e um outro objeto.
     * @param obj Objeto que é comparado com a SmartCamera.
     * @return Booleano que indica se são iguais.
     */
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if((obj == null) || (obj.getClass() != this.getClass())){
            return false;
        }

        SmartCamera c = (SmartCamera) obj;
        return (
                this.xRes == c.xRes                    &&
                this.yRes == c.yRes                    &&
                this.fileSize == c.getFileSize()       &&
                this.consumption == c.getConsumption() &&
                this.time.equals(c.getTime())          &&
                this.custoInstalacao == c.getCustoInstalacao()
        );
    }

    /**
     * Método que devolve uma cópia da SmartCamera.
     * @return cópia da SmartCamera.
     */
    public SmartCamera clone(){
        return new SmartCamera(this);
    }

    /**
     * Método que produz uma string na qual está representada a SmartCamera.
     * @return String que representa a SmartCamera.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Resolucao: ").append("\n");
        sb.append("X: ").append(this.getxRes()).append("\n");
        sb.append("Y: ").append(this.getyRes()).append("\n");
        sb.append("Tamanho do ficheiro gerado: ").append(this.fileSize).append("\n");
        sb.append("Consumo energetico: ").append(this.consumption).append("\n");
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
        resetTime();
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
            float temp_consumption = (float)((long) this.getxRes() * this.getyRes() * this.getFileSize() * interval) / 1000;
            this.consumption = this.consumption + temp_consumption;
        }
    }


    /**
     * Getter que nos dá a resolução no eixo do x
     * @return resolução no eixo do x
     */
    public int getxRes() {
        return this.xRes;
    }

    /**
     * Getter que nos dá a resolução no eixo do y
     * @return resolução no eixo do y
     */
    public int getyRes() {
        return this.yRes;
    }

    /**
     * Método que devolve o tamanho do ficheiro da SmartCamera.
     * @return Tamanho do ficheiro.
     */
    public int getFileSize(){
        return this.fileSize;
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
     * Método que devolve o tempo desde o último reset
     * @return Tempo em que ocorreu o último reset
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * setter que nos coloca em xRes o valor passado como parâmetro
     * @param xRes resolução no eixo do x
     */
    public void setxRes(int xRes) {
        this.xRes = xRes;
    }

    /**
     * setter que nos coloca em yRes o valor passado como parâmetro
     * @param yRes resolução no eixo do y
     */
    public void setyRes(int yRes) {
        this.yRes = yRes;
    }

    /**
     * Método que altera o tamanho do ficheiro da SmartCamera.
     * @param size Novo tamanho do ficheiro.
     */
    public void setFileSize(int size){
        this.fileSize = size;
    }

    /**
     * Método que altera o consumo energético da SmartCamera.
     * @param consumption Novo consumo energético da SmartCamera.
     */
    public void setConsumption(int consumption){
        this.consumption = consumption;
    }

    /**
     * Método que coloca na variável time o tempo passado como parâmetro
     * @param time Valor que corresponde a um tempo de reset
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Método que devolve o custo de instalação de um Smart Device
     * @param custoInstalacao Custo de instalação do Smart Device
     */
    public void setCustoInstalacao(float custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }

}
