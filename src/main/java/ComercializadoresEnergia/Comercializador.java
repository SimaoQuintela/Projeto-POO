package ComercializadoresEnergia;
// discutir esta classe com o grupo, não avançar sem debater detalhes da mesma
public class Comercializador{
    private int numeroDispositivos;
    private int valorBase;
    private int consumoDispositivo;
    private int imposto;
    private float PrecoDiaPorDispositivo = numeroDispositivos > 10?(valorBase ∗ consumoDispositivo * (1 + imposto)) ∗ 0.9 : (valorBase ∗ consumoDispositivo ∗ (1 + imposto)) ∗ 0.75


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
