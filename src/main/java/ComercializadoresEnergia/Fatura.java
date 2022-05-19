package ComercializadoresEnergia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 * A Fatura é responsável pelo processo de faturação.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Fatura implements Serializable {
    private int codigo;
    private int nif;
    private LocalDate dataEmissao;
    private float total;
    private String empresa;
    private String cliente;

    /**
     * Construtor por omissão de ComercializadoresEnergia.Fatura.
     */
    public Fatura(){
        this.codigo = 0;
        this.nif = 0;
        this.total = 0;
    //    this.consumoDevice = new HashMap<>();
        this.empresa = "";
        this.cliente = "";
        this.dataEmissao = LocalDate.now();
    }

    /**
     * Construtor parametrizado de ComercializadoresEnergia.Fatura.
     * @param codigo Código da ComercializadoresEnergia.Fatura.
     */
    public Fatura(int codigo, float total, String fornecedor, int NIF, String cliente, LocalDate anyTime){
        this.codigo = codigo;
        this.nif = NIF;
        this.empresa = fornecedor;
        this.cliente = cliente;
        this.total = total;
        this.dataEmissao = anyTime;
    }

    /**
     * Construtor de cópia de ComercializadoresEnergia.Fatura.
     * @param f ComercializadoresEnergia.Fatura que é copiada.
     */
    public Fatura(Fatura f){
        this.codigo = f.getCodigo();
        this.nif = f.getNif();
        this.empresa = f.getEmpresa();
        this.total = f.getTotal();
        this.dataEmissao = f.getDataEmissao();

    }

    /**
     * Método que devolve a Fatura em formato de texto.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Numero de Fatura: ").append(this.codigo).append("\n");
        sb.append("Cliente: ").append(this.cliente).append("\n");
        sb.append("Fornecedor: ").append(this.empresa).append("\n");
        sb.append("NIF: ").append(this.nif).append("\n");
        sb.append("Data de emissao: ").append(this.getDataEmissao().toString()).append("\n");
        sb.append("Total: ").append(this.total).append("\n");

        return sb.toString();
    }

    /**
     * Método que cria uma cópia da ComercializadoresEnergia.Fatura original.
     * @return Cópia da ComercializadoresEnergia.Fatura original.
     */
    public Fatura clone(){
        return new Fatura(this);
    }

    /**
     * Método que averigua a igualdade entre a ComercializadoresEnergia.Fatura e um outro objeto.
     * @param obj Objeto comparado com a ComercializadoresEnergia.Fatura.
     * @return Booleano que indica o resultado da comparação.
     */
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if((obj == null) || (obj.getClass() != this.getClass())){
            return false;
        }

        Fatura f = (Fatura) obj;
        return (this.codigo == f.getCodigo()                     &&
                this.nif == f.getNif()                           &&
                this.dataEmissao.equals(f.getDataEmissao())      &&
                this.empresa.equals(f.getEmpresa())
        );
    }

    /**
     * Método que devolve o valor total da Fatura.
     * @return Valor total da Fatura.
     */
    public float getTotal(){

        return this.total;
    }

    /**
     * Método que devolve o código identificador da ComercializadoresEnergia.Fatura.
     * @return Código identificador da ComercializadoresEnergia.Fatura.
     */
    public int getCodigo(){
        return this.codigo;
    }

    /**
     * Método que devolve o número de identificação fiscal do cliente.
     * @return Número de identificação fiscal.
     */
    public int getNif(){
        return this.nif;
    }

    /**
     * Método que devolve o Comercializador.
     * @return Comercializador.
     */
    public String getEmpresa(){
        return this.empresa;
    }

    /**
     * Método que devolve o nome do Cliente.
     * @return Nome do cliente.
     */
    public String getCliente() {
        return this.cliente;
    }

    /**
     * Método que devolve a data em que a Fatura foi emitida.
     * @return Data de emissão da Fatura.
     */
    public LocalDate getDataEmissao() {
        return this.dataEmissao;
    }

    /**
     * Método que altera o código identificador da ComercializadoresEnergia.Fatura.
     * @param codigo Código identificador da ComercializadoresEnergia.Fatura.
     */
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    /**
     * Método que altera o número de identificação fiscal.
     * @param nif
     */
    public void setNif(int nif){
        this.nif = nif;
    }

    /**
     * Método que altera o valor total da Fatura.
     * @param total Valor total da Fatura.
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * Método que altera o nome do Cliente na Fatura.
     * @param cliente Nome do cliente.
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que altera o Comercializador.
     * @param empresa Comercializador.
     */
    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }

    /**
     * Método que altera a data de emissão da Fatura.
     * @param dataEmissao Data de emissão da Fatura.
     */
    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
