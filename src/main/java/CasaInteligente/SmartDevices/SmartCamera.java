package CasaInteligente.SmartDevices;

public class SmartCamera extends SmartDevice {
    private Resolution resolution;
    private int fileSize;
    private int consumption; //tamanho do ficheiro * resolução

    /**
     * Construtor por omissão de SmartCamera.
     */
    public SmartCamera(){
        super();
        this.resolution = new Resolution();
        this.fileSize = 0;
        this.consumption = 0;
    }

    /**
     * Construtor parametrizado.
     * @param id Código que identifica a SmartCamera.
     * @param status Estado da SmartCamera.
     * @param res Resolução da SmartCamera.
     * @param size Tamanho do ficheiro da SmartCamera.
     */
    public SmartCamera(String id, boolean status, Resolution res, int size){
        super(id, status);
        setResolution(res);
        setFileSize(size);
        setConsumption(res.getX() * res.getY() * size);
    }

    /**
     * Construtor parametrizado.
     * @param id Código que identifica a SmartCamera.
     * @param status Estado da SmartCamera.
     */
    public SmartCamera(String id, boolean status){
        super(id, status);
        this.resolution = new Resolution();
        this.fileSize = 0;
        this.consumption = 0;
    }

    /**
     * Construtor de cópia da SmartCamera.
     * @param s SmartCamera que é copiada para a nova.
     */
    public SmartCamera(SmartCamera s){
        super(s.getID(), s.getOn());
        setResolution(s.getResolution());
        setFileSize(s.getFileSize());
        setConsumption(s.getResolution().getX() * s.getResolution().getY() * s.getFileSize());
    }

    /**
     * Método que altera a resolução da SmartCamera.
     * @param res Nova resolução.
     */
    public void setResolution(Resolution res){
        this.resolution = new Resolution(res);
    }

    /**
     * Método que devolve a resolução da SmartCamera.
     * @return Resolução da SmartCamera.
     */
    public Resolution getResolution(){
        return this.resolution;
    }

    /**
     * Método que altera o tamanho do ficheiro da SmartCamera.
     * @param size Novo tamanho do ficheiro.
     */
    public void setFileSize(int size){
        this.fileSize = size;
    }

    /**
     * Método que devolve o tamanho do ficheiro da SmartCamera.
     * @return Tamanho do ficheiro.
     */
    public int getFileSize(){
        return this.fileSize;
    }

    /**
     * Método que altera o consumo energético da SmartCamera.
     * @param consumption Novo consumo energético da SmartCamera.
     */
    public void setConsumption(int consumption){
        this.consumption = consumption;
    }

    /**
     * Método que devolve o consumo energético da SmartCamera.
     * @return Consumo energético da SmartCamera.
     */
    public int getConsumption(){
        return this.consumption;
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
        return (this.resolution.equals(c.getResolution()) &&
                this.fileSize == c.getFileSize() &&
                this.consumption == c.getConsumption());
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
        sb.append(this.resolution.toString());
        sb.append("\nTamanho do ficheiro gerado: ");
        sb.append(this.fileSize);
        sb.append("\nConsumo energético: ");
        sb.append(this.consumption);
        return sb.toString();
    }

}
