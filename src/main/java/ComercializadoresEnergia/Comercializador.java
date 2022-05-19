package ComercializadoresEnergia;

import CasaInteligente.CasaInteligente;
import CasaInteligente.SmartDevices.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Tornar coerente a escrita. Quando se trata do objeto da classe usar this, quando se trata dum objeto fora da classe usar gets/setss
 */

public class Comercializador implements Serializable {
    private String nomeEmpresa;
    private int numeroDispositivos;
    private int valorBase;
    private int imposto;
    private Map<String, List<Fatura>> faturas;  // Proprietário -> Lista de Faturas
    //private double precoDiaPorDispositivo = numeroDispositivos > 10?(valorBase * consumoDispositivo * (1 + imposto)) * 0.9 : (valorBase * consumoDispositivo * (1 + imposto)) * 0.75;

    /**
     * Construtor por omissão de Comercializador.
     */
    public Comercializador(){
        this.nomeEmpresa = "";
        this.numeroDispositivos = 0;
        this.valorBase = 0;
        this.imposto = 0;
        this.faturas = new HashMap<>();
    }

    /**
     * Construtor parametrizado de Comercializador.
     * @param nomeEmpresa Nome do Comercializador.
     */
    public Comercializador(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
        this.numeroDispositivos = 0;
        this.valorBase = 0;
        this.imposto = 0;
        this.faturas = new HashMap<>();
    }

    /**
     * Construtor parametrizado de Comercializador.
     * @param nomeEmpresa Nome do Comercializador.
     * @param numeroDispositivos Número de dispositivos.
     * @param valorBase Valor mínimo do custo do serviço.
     * @param imposto Imposto aplicado no calculo do custo final.
     */
    public Comercializador(String nomeEmpresa, int numeroDispositivos, int valorBase, int imposto){
        this.nomeEmpresa = nomeEmpresa;
        this.numeroDispositivos = numeroDispositivos;
        this.valorBase = valorBase;
        this.imposto = imposto;
        this.faturas = new HashMap<>();
    }

    /**
     * Construtor de cópia de Comercializador.
     * @param c Comercializador que é copiada.
     */
    public Comercializador(Comercializador c){
        this(c.nomeEmpresa, c.numeroDispositivos, c.valorBase,/* c.consumoDispositivo,*/ c.imposto);
    }

    /**
     * Método que verifica a igualdade entre o Comercializador e um outro objeto.
     * @param o Objeto que é comparado com o Comercializador.
     * @return Booleano que indica o resultado da comparação.
     */
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
        sb.append("Imposto: ").append(this.getImposto()).append("\n");

        return sb.toString();
    }

    public float calculaFaturacao(){
        float faturacao = 0;
        for(List<Fatura> l: this.faturas.values()){
            for(Fatura f: l){
                faturacao += f.getTotal();
            }
        }

        return faturacao;
    }

    public void adicionaFatura(String prop, Fatura f){
        if(this.faturas.containsKey(prop)) {
            this.faturas.get(prop).add(f);
        } else {
            List<Fatura> listaFaturas = new ArrayList<>();
            listaFaturas.add(f);
            this.faturas.put(prop, listaFaturas);
        }
    }


    /**
     * Método que calcula o consumo de um Dispositivo.
     */
    public double contaConsumoDispositivo(SmartDevice s, LocalDate before, LocalDate after, int numDevices){
        double r = 0;
        s.consumo(before, after);
        if(numDevices > this.numeroDispositivos) {
            r = s.getConsumption()/1500 * (1 + ((float)this.imposto)/100) * 0.9;
        } else {
            r = s.getConsumption()/1500 * (1 + ((float)this.imposto)/100) * 0.75;
        }
        r = Math.round(r*100)/100;

        return r;
    }

    public void writeInFile(FileWriter writer) throws IOException{
        String line = "Fornecedor:" + this.getNomeEmpresa() + "," + this.getNumeroDispositivos() + "\n";
        writer.write(line);
        writer.flush();
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
     * Método que devolve a taxa de imposto.
     * @return Taxa de imposto.
     */
    public int getImposto(){
        return this.imposto;
    }

    /**
     * FALTA DOCUMENTAR
     * @return
     */
    public Map<String, List<Fatura>> getFaturas() {
        Map<String, List<Fatura>> new_faturas = new HashMap<>();
        new_faturas = this.faturas.entrySet()
                                  .stream()
                                  .collect(toMap(e->e.getKey(), e->e.getValue()));

        return new_faturas;
    }

    public List<Fatura> getListaFaturas(String prop){
        return this.faturas.get(prop);
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
     * Método que altera a taxa de imposto.
     * @param imposto Taxa de imposto.
     */
    public void setImposto(int imposto){
        this.imposto = imposto;
    }


}
