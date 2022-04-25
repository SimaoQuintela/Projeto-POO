package ComercializadoresEnergia;

/**
 * Tornar coerente a escrita. Quando se trata do objeto da classe usar this, quando se trata dum objeto fora da classe usar gets/setss
 */

public class Comercializador{
    private String nomeEmpresa;
    private int numeroDispositivos;
    private int valorBase;
    private int consumoDispositivo;
    private int imposto;
    private double precoDiaPorDispositivo = numeroDispositivos > 10?(valorBase * consumoDispositivo * (1 + imposto)) * 0.9 : (valorBase * consumoDispositivo * (1 + imposto)) * 0.75;


    /**
     * Construtor por omissão de Comercializador.
     */
    public Comercializador(){
        this.nomeEmpresa = "";
        this.numeroDispositivos = 0;
        this.valorBase = 0;
        this.consumoDispositivo = 0;
        this.imposto = 0;
    }
    
    /**
     * Construtor parametrizado de CasaInteligente.
     * !!! faltam parametros
     */
    public Comercializador(String nomeEmpresa, int numeroDispositivos, int valorBase,/* int consumoDispositivo,*/ int imposto){
        this.nomeEmpresa = nomeEmpresa;
        this.numeroDispositivos = numeroDispositivos;
        this.valorBase = valorBase;
        this.consumoDispositivo = 0;
        this.imposto = imposto;
    }
    
    /**
     * Construtor de cópia de Comercializador.
     * @param c Comercializador que é copiada.
     */
    public Comercializador(Comercializador c){
        this(c.nomeEmpresa, c.numeroDispositivos, c.valorBase,/* c.consumoDispositivo,*/ c.imposto);
    }

    // falta documentar
    public boolean equals(Object o){
        if (o == this)
            return true;

        if(o == null || o.getClass() != this.getClass())
            return false;

        Comercializador c = (Comercializador) o;

        return(
            this.nomeEmpresa.equals(c.nomeEmpresa)                 &&
            this.getNumeroDispositivos() == (c.numeroDispositivos) &&
            this.getValorBase() == (c.valorBase)                   &&
            this.consumoDispositivo == (c.consumoDispositivo)      &&
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

        sb.append("Nome do comercializador: ").append(this.getNomeEmpresa()).append("\n");
        sb.append("Numero de Dispositivos: ").append(this.getNumeroDispositivos()).append("\n");
        sb.append("Valor Base: ").append(this.getValorBase()).append("\n");
        sb.append("Consumo de Dispositivo: ").append(this.getConsumoDispositivo()).append("\n");
        sb.append("Imposto: ").append(this.getImposto()).append("\n");

        return sb.toString();
    }

    /**
     * Método que devolve o número de dispositivos.
     * @return Número de dispositivos.
     */
    public int getNumeroDispositivos(){
        return this.numeroDispositivos;
    }

    /**
     * Método que devolve o valor base.
     * @return Valor base.
     */
    public int getValorBase(){
        return this.valorBase;
    }

    /**
     * Método que devolve o nome do comercializador correspondente
     * @return Nome do comercializador
     */
    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    /**
     * Método que devolve o consumo do dispositivo.
     * @return Consumo do dispositivo.
     */
    public int getConsumoDispositivo(){
        return this.consumoDispositivo;
    }

    /**
     * Método que devolve a taxa de imposto.
     * @return Taxa de imposto.
     */
    public int getImposto(){
        return this.imposto;
    }

    /**
     * Coloca na variável de instância nomeEmpresa a string passada como parâmetro
     * @param nomeEmpresa Nome do comercializador
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * Método que altera o número de dispositivos.
     * @param num Número de dispositivos.
     */
    public void setNumeroDispositivos(int num){
        this.numeroDispositivos = num;
    }

    /**
     * Método que altera o valor base.
     * @param valor Valor base.
     */
    public void setValorBase(int valor){
        this.valorBase = valor;
    }

    /**
     * Método que altera o consumo do dispositivo.
     * @param consumo Consumo do dispositivo
     */
    public void setConsumoDispositivo(int consumo){
        this.consumoDispositivo = consumo;
    }

    /**
     * Método que altera a taxa de imposto.
     * @param imposto Taxa de imposto.
     */
    public void setImposto(int imposto){
        this.imposto = imposto;
    }
}
