package CasaInteligente.SmartDevices;

public class SmartCamera extends SmartDevice {
    private int xRes;
    private int yRes;
    private int fileSize;
    private float consumption; //tamanho do ficheiro * resolução

    // Falar sobre coerência no uso de this..... ou get/set methods

    /**
     * Construtor por omissão de SmartCamera.
     */
    public SmartCamera(){
        super();
        this.xRes = 0;
        this.yRes = 0;
        this.fileSize = 0;
        this.consumption = 0;
    }

    /**
     * Construtor parametrizado.
     * @param id Código que identifica a SmartCamera.
     * @param status Estado da SmartCamera.
     * @param xRes Resolução da SmartCamera no eixo do x.
     * @param yRes Resolução da SmartCamera no eixo do y
     * @param fileSize Tamanho do ficheiro da SmartCamera.
     */
    public SmartCamera(String id, boolean status, int xRes, int yRes, int fileSize){
        super(id, status);
        setxRes(xRes);
        setyRes(yRes);
        setFileSize(fileSize);
        setConsumption(this.getxRes() * this.getyRes() * fileSize);
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
    }

    /**
     * Construtor de cópia da SmartCamera.
     * @param s SmartCamera que é copiada para a nova.
     */
    public SmartCamera(SmartCamera s){
        super(s.getID(), s.getOn());
        setxRes(s.getxRes());
        setyRes(s.getyRes());
        setFileSize(s.getFileSize());
        setConsumption(s.getxRes() * s.getyRes() * s.getFileSize());
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
                this.xRes == c.xRes              &&
                this.yRes == c.yRes              &&
                this.fileSize == c.getFileSize() &&
                this.consumption == c.getConsumption()
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
        sb.append("------- Smart Bulb -------\n");
        sb.append("Resolução: ");
        sb.append("\nX: ").append(this.getxRes());
        sb.append("\nY: ").append(this.getyRes());
        sb.append("\nTamanho do ficheiro gerado: ");
        sb.append(this.fileSize);
        sb.append("\nConsumo energético: ");
        sb.append(this.consumption);
        return sb.toString();
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
}
