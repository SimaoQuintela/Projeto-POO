import CasaInteligente.SmartDevices.SmartDevice;
import ComercializadoresEnergia.Comercializador;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

// FALTA FAZER EQUALS, TOSTRING, ETC.
public class Fatura {
    private int codigo;
    private String nome;
    private int nif;
    private Map<String, Float> consumoDevice; //id -> consumo
    private Comercializador empresa;
    private float total;

    /**
     * Construtor por omissão.
     */
    public Fatura(){
        this.codigo = 0;
        this.nome = "";
        this.nif = 0;
        this.consumoDevice = new HashMap<>();
        this.empresa = new Comercializador();
        this.total = 0;
    }

    public Fatura(int codigo, String nome, int nif, Map<Integer, SmartDevice> consumoDevice, Comercializador empresa, int total){
        setCodigo(codigo);
        setNome(nome);
        setNif(nif);

    }

    public void valorFinal(){

    }

    /**
     * Método que devolve o código identificador da Fatura.
     * @return Código identificador da Fatura.
     */
    public int getCodigo(){
        return this.codigo;
    }

    /**
     * Método que devolve o nome do cliente na fatura.
     * @return Nome do cliente.
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Método que devolve o número de identificação fiscal do cliente.
     * @return Número de identificação fiscal.
     */
    public int getNif(){
        return this.nif;
    }

    public Map<Integer, SmartDevice> getConsumoDevices(){
        Map<String, Float> new_consumos = new HashMap<>();
        new_consumos = this.consumoDevice.entrySet()
                .stream()
                .collect(toMap(e->e.getKey(), e->e.getValue()));

        return getConsumoDevices();
    }

    /**
     * Método que altera o código identificador da Fatura.
     * @param codigo Código identificador da Fatura.
     */
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    /**
     * Método que altera o nome do cliente na fatura.
     * @param nome Nome do cliente.
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Método que altera o número de identificação fiscal.
     * @param nif
     */
    public void setNif(int nif){
        this.nif = nif;
    }

    /**
     * Método que altera o Map com os consumos associados aos respetivos dispositivos.
     * @param consumoDevice Map com os consumos associados aos respetivos dispositivos.
     */
    public void setConsumoDevice(Map<String, Float> consumoDevice){
        this.consumoDevice = consumoDevice.entrySet()
                .stream()
                .collect(toMap(e->e.getKey(), e->e.getValue() ));

    }




    /**
     * Método que altera o Comercializador.
     * @param empresa Comercializador.
     */
    public void setEmpresa(Comercializador empresa){
        this.empresa = new Comercializador(empresa);
    }

    /**
     * Método que devolve o Comercializador.
     * @return Comercializador.
     */
    public Comercializador getEmpresa(){
        return this.empresa;
    }
}
