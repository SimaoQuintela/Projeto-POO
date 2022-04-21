package ComercializadoresEnergia;

public class Comercializador{
    private int numeroDispositivos;
    private int valorBase;
    private int consumoDispositivo;
    private int imposto;
    private double PrecoDiaPorDispositivo = numeroDispositivos > 10?(valorBase * consumoDispositivo * (1 + imposto)) * 0.9 : (valorBase * consumoDispositivo * (1 + imposto)) * 0.75;


    /**
     * Construtor por omissão de Comercializador.
     */
    public Comercializador(){
        this.numeroDispositivos = 0;
        this.valorBase = 0;
        this.consumoDispositivo = 0;
        this.imposto = 0;
    }
    
    /**
     * Construtor parametrizado de CasaInteligente.
     * !!! faltam parametros
     */
    public Comercializador(int numeroDispositivos, int valorBase, int consumoDispositivo, int imposto){
        this.numeroDispositivos = numeroDispositivos;
        this.valorBase = valorBase;
        this.consumoDispositivo = consumoDispositivo;
        this.imposto = imposto;
    }
    
    /**
     * Construtor de cópia de Comercializador.
     * @param c Comercializador que é copiada.
     */
    public Comercializador(Comercializador c){
        this(c.numeroDispositivos, c.valorBase, c.consumoDispositivo, c.imposto);
    }
    
    public boolean equals(Object o){
        if (o == this)
            return true;

        if(o == null || o.getClass() != this.getClass())
            return false;

        Comercializador c = (Comercializador) o;

        return(
            this.getNumeroDispositivos() == (c.numeroDispositivos) &&
            this.getValorBase() == (c.valorBase) &&
            this.consumoDispositivo == (c.consumoDispositivo)    &&
            this.imposto == (c.imposto)
        );
    }
    
    /**
     * Método que devolve uma cópia da Comercializador recetora da mensagem.
     * @return Cópia do comercializador.
     */
    public Comercializador clone(){
        return new Comercializador(this);
    }
    
    /**
     * Método que produz uma string na qual está representado o Comercializador.
     * @return String que representa o comercializador.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Numero de Dispositivos: ").append(this.getNumeroDispositivos()).append("\n");
        sb.append("Valor Base: ").append(this.getValorBase()).append("\n");
        sb.append("Consumo de Dispositivo: ").append(this.getConsumoDispositivo()).append("\n");
        sb.append("Imposto: ").append(this.getImposto()).append("\n");

        return sb.toString();
    }

    /**
     * Método que altera o número de dispositivos.
     * @param num Número de dispositivos.
     */
    public void setNumeroDispositivos(int num){
        this.numeroDispositivos = num;
    }

    /**
     * Método que devolve o número de dispositivos.
     * @return Número de dispositivos.
     */
    public int getNumeroDispositivos(){
        return this.numeroDispositivos;
    }

    /**
     * Método que altera o valor base.
     * @param valor Valor base.
     */
    public void setValorBase(int valor){
        this.valorBase = valor;
    }

    /**
     * Método que devolve o valor base.
     * @return Valor base.
     */
    public int getValorBase(){
        return this.valorBase;
    }

    /**
     * Método que altera o consumo do dispositivo.
     * @param consumo Consumo do dispositivo
     */
    public void setConsumoDispositivo(int consumo){
        this.consumoDispositivo = consumo;
    }

    /**
     * Método que devolve o consumo do dispositivo.
     * @return Consumo do dispositivo.
     */
    public int getConsumoDispositivo(){
        return this.consumoDispositivo;
    }

    /**
     * Método que altera a taxa de imposto.
     * @param imposto Taxa de imposto.
     */
    public void setImposto(int imposto){
        this.imposto = imposto;
    }

    /**
     * Método que devolve a taxa de imposto.
     * @return Taxa de imposto.
     */
    public int getImposto(){
        return this.imposto;
    }
}
