package ComercializadoresEnergia;

import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.SmartDevice;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

// FALTA FAZER EQUALS, TOSTRING, ETC.
public class Fatura implements Serializable {
    private int codigo;
    private int nif;
    LocalDate dataEmissao;
    private Map<String, Float> consumoDevice; //id -> consumo
    private Map<String, SmartDevice> devices; //id -> SmartDevice
    private String empresa;
    private double total;

    /**
     * Construtor por omissão de ComercializadoresEnergia.Fatura.
     */
    public Fatura(){
        this.codigo = 0;
        this.nif = 0;
        this.consumoDevice = new HashMap<>();
        this.empresa = "";
        this.total = 0;
        this.devices = new HashMap<>();
        this.dataEmissao = LocalDate.now();
    }

    /**
     * Construtor parametrizado de ComercializadoresEnergia.Fatura.
     * @param codigo Código da ComercializadoresEnergia.Fatura.
     * @param consumoDevice Id's dos devices associados aos respetivos consumos.
     * @param c CasaInteligente.
     */
    public Fatura(int codigo, Map<String, Float> consumoDevice, CasaInteligente c, double total, LocalDate anyTime){
        this.codigo = codigo;
        this.nif = c.getNIF();
        this.empresa = c.getFornecedor();
        this.total = total;
        this.consumoDevice = consumoDevice.entrySet()
                                          .stream()
                                          .collect(toMap(e->e.getKey(), e->e.getValue()));
        this.devices = c.getDevices().entrySet()
                                     .stream()
                                     .collect(toMap(e->e.getKey(), e->e.getValue()));
        this.dataEmissao = anyTime;
    }

    /**
     * Construtor de cópia de ComercializadoresEnergia.Fatura.
     * @param f ComercializadoresEnergia.Fatura que é copiada.
     */
    public Fatura(Fatura f, CasaInteligente c){
        this.codigo = f.getCodigo();
        this.nif = f.getNif();
        this.empresa = f.getEmpresa();
        this.consumoDevice = f.getConsumoDevices().entrySet()
                                                  .stream()
                                                  .collect(toMap(e->e.getKey(), e->e.getValue()));
        this.devices = c.getDevices().entrySet()
                                     .stream()
                                     .collect(toMap(e->e.getKey(), e->e.getValue()));
        this.dataEmissao = f.getDataEmissao();
        this.total = f.getTotal();
    }

    /**
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nº de Fatura: ").append(this.codigo).append("\n");
        sb.append("Fornecedor: ").append(this.empresa).append("\n");
        sb.append("NIF: ").append(this.nif).append("\n");
        sb.append("Data de emissão: ").append(this.getDataEmissao().toString()).append("\n");
/* Para simplificação vamos colocar isto em comentário
        for(String id: this.consumoDevice.keySet()){
            sb.append("Id: ").append(this.devices.get(id).getID()).append(" | Consumo: ").append(this.consumoDevice.get(id)).append("\n");
        }
*/
        sb.append("Total: ").append(this.total).append("\n");

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
        return (this.codigo == f.getCodigo()                     &&
                this.nif == f.getNif()                           &&
                this.consumoDevice.equals(f.getConsumoDevices()) &&
                this.dataEmissao.equals(f.getDataEmissao())      &&
                this.devices.equals(f.getDevices())              &&
                this.empresa.equals(f.getEmpresa())              &&
                this.total == f.getTotal());
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

    public Map<String, Float> getConsumoDevices(){
        Map<String, Float> new_consumos = new HashMap<>();
        new_consumos = this.consumoDevice.entrySet()
                                         .stream()
                                         .collect(toMap(e->e.getKey(), e->e.getValue()));

        return new_consumos;
    }


    /**
     * Método que devolve o Comercializador.
     * @return Comercializador.
     */
    public String getEmpresa(){
        return this.empresa;
    }

    /**
     * Método que devolve o valor total da ComercializadoresEnergia.Fatura.
     * @return Valor total da ComercializadoresEnergia.Fatura.
     */
    public double getTotal(){
        return this.total;
    }

    /**
     * Método que devolve o Map com ID's dos SmartDevices associados aos respetivos SmartDevices.
     * @return Map com ID's associados aos respetivos SmartDevices.
     */
    public Map<String, SmartDevice> getDevices(){
        Map<String, SmartDevice> new_devices = new HashMap<>();
        new_devices = this.devices.entrySet()
                                  .stream()
                                  .collect(toMap(e->e.getKey(), e->e.getValue().clone()));

        return new_devices;
    }

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
    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
