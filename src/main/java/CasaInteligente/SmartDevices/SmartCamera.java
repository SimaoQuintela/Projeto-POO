package CasaInteligente.SmartDevices;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SmartCamera extends SmartDevice implements Serializable {
    private int xRes;
    private int yRes;
    private int fileSize;
    private float custoInstalacao;

    /**
     * Construtor por omissão de SmartCamera.
     */
    public SmartCamera(){
        super();
        this.xRes = 0;
        this.yRes = 0;
        this.fileSize = 0;
        this.custoInstalacao = 0;
    }

    /**
     * Construtor parametrizado.
     * @param id Código que identifica a SmartCamera.
     * @param status Estado da SmartCamera.
     * @param xRes Resolução da SmartCamera no eixo do x.
     * @param yRes Resolução da SmartCamera no eixo do y
     * @param fileSize Tamanho do ficheiro da SmartCamera.
     */
    public SmartCamera(String id, boolean status, int xRes, int yRes, int fileSize, float consumptionPerDay,  int custoInstalacao){
        super(id, status, consumptionPerDay, custoInstalacao);
        this.xRes = xRes;
        this.yRes = yRes;
        this.fileSize = fileSize;
        this.custoInstalacao = custoInstalacao;
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
        this.custoInstalacao = 0;
    }

    /**
     * Construtor de cópia da SmartCamera.
     * @param s SmartCamera que é copiada para a nova.
     */
    public SmartCamera(SmartCamera s){
        super(s.getID(), s.getOn(), s.getConsumptionPerDay(), s.getCustoInstalacao());
        this.xRes = s.getxRes();
        this.yRes = s.getyRes();
        this.fileSize = s.getFileSize();
        this.custoInstalacao = s.getCustoInstalacao();
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
                this.fileSize == c.getFileSize()
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

        sb.append("Id: ").append(super.getID()).append("\n");
        sb.append("Estado: ").append(super.getOn()).append("\n");
        sb.append("Resolucao: ").append("\n");
        sb.append("X: ").append(this.getxRes()).append("\n");
        sb.append("Y: ").append(this.getyRes()).append("\n");
        sb.append("Tamanho do ficheiro gerado: ").append(this.getFileSize()).append("\n");
        sb.append("Consumo por dia em Kw/H: ").append(super.getConsumptionPerDay()).append("\n");
        sb.append("Consumo total: ").append(super.getConsumption()).append("\n");
        sb.append("Custo de instalacao: ").append(super.getCustoInstalacao()).append("\n");

        return sb.toString();
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
        consumo(super.getTime());
    }

    /**
     * Método que calcula o consumo da SmartCamera.
     */
    public void consumo(LocalDate anyTime){
        if(this.getOn()){
            float between = ChronoUnit.DAYS.between(super.getTime(), anyTime);
            super.setConsumption(( ((float)this.getxRes() *this.getyRes())/1000) * this.getFileSize() * super.getConsumptionPerDay() * between);
            super.setTime(anyTime);
        } else {
            super.setConsumption(0);
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


}
