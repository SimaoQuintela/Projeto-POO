package CasaInteligente.SmartDevices;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SmartCamera extends SmartDevice {
    private int xRes;
    private int yRes;
    private int fileSize;
    private float consumptionPerDay;
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
        this.consumptionPerDay = 0;
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
    public SmartCamera(String id, boolean status, int xRes, int yRes, int fileSize, float consumptionPerDay,  float custoInstalacao){
        super(id, status);
        this.xRes = xRes;
        this.yRes = yRes;
        this.fileSize = fileSize;
        this.consumptionPerDay = consumptionPerDay;
        this.consumption = 0;
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
        this.consumptionPerDay = 0;
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
        this.consumption = s.getxRes() * s.getyRes() * s.getFileSize() * s.getConsumption();
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
                this.xRes == c.xRes                                &&
                this.yRes == c.yRes                                &&
                this.fileSize == c.getFileSize()                   &&
                this.consumptionPerDay == c.getConsumptionPerDay() &&
                this.consumption == c.getConsumption()             &&
                this.time.equals(c.getTime())                      &&
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

        sb.append("Estado: ").append(super.getOn()).append("\n");
        sb.append("Resolucao: ").append("\n");
        sb.append("X: ").append(this.getxRes()).append("\n");
        sb.append("Y: ").append(this.getyRes()).append("\n");
        sb.append("Tamanho do ficheiro gerado: ").append(this.getFileSize()).append("\n");
        sb.append("Consumo por dia em Kw/H").append(this.getConsumptionPerDay()).append("\n");
        sb.append("Consumo total: ").append(this.getConsumption()).append("\n");
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
     * Método que calcula o consumo da SmartCamera.
     */
    public void consumo(LocalDateTime anyTime){
        if(this.getOn()){
            float between = ChronoUnit.DAYS.between(this.getTime(), anyTime);
            this.consumption += ( ((float)this.getxRes() *this.getyRes())/1000) * this.getFileSize() * this.getConsumptionPerDay() * between;
            this.time = anyTime;
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

    public float getConsumptionPerDay() {
        return this.consumptionPerDay;
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
