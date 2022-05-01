package ComercializadoresEnergia;

import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.SmartDevice;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

// FALTA FAZER EQUALS, TOSTRING, ETC.
public class Fatura {
    private int codigo;
    private String nome;
    private int nif;
    private Map<String, Float> consumoDevice; //id -> consumo
    private Map<String, SmartDevice> devices; //id -> SmartDevice
    private Comercializador empresa;
    private float total;

    /**
     * Construtor por omissão de ComercializadoresEnergia.Fatura.
     */
    public Fatura(){
        this.codigo = 0;
        this.nome = "";
        this.nif = 0;
        this.consumoDevice = new HashMap<>();
        this.empresa = new Comercializador();
        this.total = 0;
        this.devices = new HashMap<>();
    }

    /**
     * Construtor parametrizado de ComercializadoresEnergia.Fatura.
     * @param codigo Código da ComercializadoresEnergia.Fatura.
     * @param nome Nome do cliente.
     * @param nif Número de identificação fiscal.
     * @param consumoDevice Id's dos devices associados aos respetivos consumos.
     * @param empresa Comercializador da ComercializadoresEnergia.Fatura.
     * @param c CasaInteligente.
     */
    public Fatura(int codigo, Map<String, Float> consumoDevice, CasaInteligente c){
        this.setCodigo(1000);
        this.setNome(c.getProprietario());
        this.setNif(c.getNIF());
        this.setEmpresa(c.getFornecedor());
        this.setConsumoDevice(consumoDevice);
        this.setDevices(c.getDevices());
        calculaValores(c);
    }

    /**
     * Construtor de cópia de ComercializadoresEnergia.Fatura.
     * @param f ComercializadoresEnergia.Fatura que é copiada.
     */
    public Fatura(Fatura f, CasaInteligente c){
        this.setCodigo(f.getCodigo());
        this.setNome(f.getNome());
        this.setNif(f.getNif());
        this.setConsumoDevice(f.getConsumoDevices());
        this.setDevices(f.getDevices());
        this.setEmpresa(f.getEmpresa());
        calculaValores(c);
    }

    /**
     * Método que calcula o custo energético de cada dispositivo assim como o valor final da ComercializadoresEnergia.Fatura.
     */
    public void calculaValores(CasaInteligente c){
        float valor = 0;
        for(String id: this.devices.keySet()){
            double valPerDevice = this.empresa.contaConsumoDispositivo(c, this.devices.get(id));
            this.consumoDevice.putIfAbsent(id, (float)valPerDevice);
            valor += valPerDevice;
        }

        this.total = valor;
    }

    /**
     * Método que devolve o código identificador da ComercializadoresEnergia.Fatura.
     * @return Código identificador da ComercializadoresEnergia.Fatura.
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

    //VERIFICAR ESTE METODO
    /*public Map<Integer, SmartDevice> getConsumoDevices(){
        Map<String, Float> new_consumos = new HashMap<>();
        new_consumos = this.consumoDevice.entrySet()
                .stream()
                .collect(toMap(e->e.getKey(), e->e.getValue()));

        return getConsumoDevices();
    }*/

    /**
     * Método que devolve o Map com ID's dos SmartDevices associados aos respetivos consumos.
     * @return Map com ID's dos SmartDevices associados aos respetivos consumos.
     */
    public Map<String, Float> getConsumoDevices(){
        return this.consumoDevice;
    }

    /**
     * Método que devolve o Map com ID's dos SmartDevices associados aos respetivos SmartDevices.
     * @return Map com ID's associados aos respetivos SmartDevices.
     */
    public Map<String, SmartDevice> getDevices(){
        return this.devices;
    }

    /**
     * Método que altera o código identificador da ComercializadoresEnergia.Fatura.
     * @param codigo Código identificador da ComercializadoresEnergia.Fatura.
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
     * Método que altera o Map com os ID's associados aos respetivos SmartDevices.
     * @param devices Map com os ID's associados aos respetivos SmartDevices.
     */
    public void setDevices(Map<String, SmartDevice> devices){
        for(String id: devices.keySet()){
            this.devices.put(id, devices.get(id).clone());
        }
    }

    /**
     * Método que altera o Comercializador.
     * @param empresa Comercializador.
     */
    public void setEmpresa(Comercializador empresa){
        this.empresa = new Comercializador(empresa.clone());
    }

    /**
     * Método que devolve o Comercializador.
     * @return Comercializador.
     */
    public Comercializador getEmpresa(){
        return this.empresa.clone();
    }

    /**
     * Método que devolve o valor total da ComercializadoresEnergia.Fatura.
     * @return Valor total da ComercializadoresEnergia.Fatura.
     */
    public float getTotal(){
        return this.total;
    }

    /**
     * Método que devolve a ComercializadoresEnergia.Fatura em formato de texto.
     * @return ComercializadoresEnergia.Fatura em formato de texto.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nº da ComercializadoresEnergia.Fatura: ").append(this.codigo).append("\n");
        sb.append("Nome: ").append(this.nome).append("\n");
        sb.append("Número de identificação fiscal: ").append(this.nif).append("\n");

        for(String id: this.consumoDevice.keySet()){
            // sb.append("Id: ").append(this.devices.get(id)).append("    Consumo: ").append(this.devices.get(id).get ALTERAR ISTO, PROVAVELMENTE METER VAR CONSUMO NA CLASSE ABSTRATA
            sb.append("Id: ").append(this.devices.get(id).toString()).append(this.consumoDevice.get(id)).append("\n\n");
        }

        sb.append(this.empresa.toString());
        sb.append("Valor final: ").append(this.total).append("\n");

        return sb.toString();
    }

    /**
     * Método que cria uma cópia da ComercializadoresEnergia.Fatura original.
     * @return Cópia da ComercializadoresEnergia.Fatura original.
     */
    public Fatura clone(CasaInteligente c){
        return new Fatura(this, c);
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
        return (this.codigo == f.getCodigo() &&
                this.nome.equals(f.getNome()) &&
                this.nif == f.getNif() &&
                this.consumoDevice.equals(f.getConsumoDevices()) &&
                this.devices.equals(f.getDevices()) &&
                this.empresa.equals(f.getEmpresa()) &&
                this.total == f.getTotal());
    }
}
