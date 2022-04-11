package CasaInteligente.SmartDevices;

public class Resolution {
    /**
     * Dimensão do eixo vertical.
     */
    private int x;
    /**
     * Dimensão do eixo horizontal.
     */
    private int y;

    /**
     * Construtor por omissão de Resolution.
     */
    public Resolution(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construtor parametrizado de Resolution.
     * @param x Dimensão do eixo vertical.
     * @param y Dimensão do eixo horizontal.
     */
    public Resolution(int x, int y){
        setX(x);
        setY(y);
    }

    /**
     * Construtor de cópia de Resolution.
     * @param r Resolution utilizada no processo de cópia para a nova Resolution.
     */
    public Resolution(Resolution r){
        setX(r.getX());
        setY(r.getY());
    }

    /**
     * Método que devolve a dimensão do eixo vertical.
     * @return Dimensão do eixo vertical
     */
    public int getX(){
        return this.x;
    }

    /**
     * Método que altera a dimensão do eixo vertical.
     * @param x Nova dimensão do eixo vertical.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Método que devolve a dimensão do eixo horizontal.
     * @return Dimensão do eixo horizontal.
     */
    public int getY(){
        return this.y;
    }

    /**
     * Método que altera a dimensão do eixo horizontal.
     * @param y Nova dimensão do eixo horizontal.
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Método que cria uma cópia da Resolution recetora da mensagem.
     * @return Cópia da Resolution.
     */
    public Resolution clone(){
        return new Resolution(this);
    }

    /**
     * Método que verifica a igualdade entre a Resolution e um outro objeto.
     * @param obj Objeto comparado com a Resolution.
     * @return Booleano que indica se são iguais.
     */
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if((obj == null) || (obj.getClass() != this.getClass())){
            return false;
        }

        Resolution r = (Resolution) obj;
        return (this.x == r.getX() &&
                this.y == r.getY());
    }

    /**
     * Método que produz uma string na qual está representada a Resolution.
     * @return String que representa a Resolution.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.x);
        sb.append("x");
        sb.append(this.y);
        return sb.toString();
    }
}
