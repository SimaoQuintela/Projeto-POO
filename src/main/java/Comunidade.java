import CasaInteligente.CasaInteligente;
import ComercializadoresEnergia.Comercializador;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
public class Comunidade {
    private String nomeDaComunidade;
    private Map<String, CasaInteligente> casas;
    private Map<String, Comercializador> mercado;

    /**
     * Construtor que inicializa a classe Comunidade
     */
    public Comunidade(){
        this.nomeDaComunidade = "";
        this.casas = new HashMap<>();
        this.mercado = new HashMap<>();
    }

    /**
     * Construtor que inicializa a classe Comunidade
     * @param nomeDaComunidade Nome da comunidade
     */
    public Comunidade(String nomeDaComunidade){
        this.nomeDaComunidade = nomeDaComunidade;
        this.casas = new HashMap<>();
        this.mercado = new HashMap<>();
    }

    /**
     * Construtor que inicializa a classe Comunidade
     * @param nomeDaComunidade Nome da Comunidade
     * @param casas Estrutura que mapeia todas as casas da comunidade em que a Key é a morada e o Value é a Casa
     * @param mercado Estrutura que mapeia todos os comercializadores de energia em que a Key é o nome da companhia e o Value é a Companhia
     */
    public Comunidade(String nomeDaComunidade, Map<String, CasaInteligente> casas, Map<String, Comercializador> mercado){
        this.nomeDaComunidade = nomeDaComunidade;
        this.casas = casas.entrySet()
                          .stream()
                          .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
        this.mercado = mercado.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey(), e->e.getValue().clone()));

    }

    /**
     * Construtor que inicializa a classe Comunidade
     * @param c Objeto Comunidade
     */
    public Comunidade(Comunidade c){
        this(c.nomeDaComunidade, c.casas, c.mercado);
    }

    /**
     * Método equals que verifica se o objeto passado como parâmetro é igual ao objeto em questão
     * @param o Objeto passado como parâmetro
     * @return true se for igual false se for diferente
     */
    public boolean equals(Object o){
        if (o ==this)
            return true;

        if(o == null || o.getClass() != this.getClass())
            return false;

        Comunidade c = (Comunidade) o;

        return(this.nomeDaComunidade.equals(c.nomeDaComunidade) &&
               this.casas.equals(c.casas)                       &&
               this.mercado.equals(c.casas)
        );
    }

    /**
     * Método que cria um clone do objeto atual
     * @return cópia do objeto atual
     */
    public Comunidade clone(){
        return new Comunidade(this);
    }

    /**
     * Método que devolve uma String com informação acerca da classe
     * @return Devolve uma String com informação acerca da classe
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Nome da comunidade: ").append(this.getNomeDaComunidade());
        for(CasaInteligente c : this.casas.values()){
            sb.append(c.toString());
            sb.append("\n\n");
        }

        sb.append("\n------------- Comercializador -------------\n");
        for(Comercializador c : this.mercado.values()){
            sb.append(c.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Devolve a casa correspondente à string passada como parâmetro
     * @param proprietario Proprietário da casa
     * @return Casa cuja morada corresponde à passada como parâmetro
     */
    public CasaInteligente getCasa(String proprietario){
        return this.casas.get(proprietario);
    }

    public Comercializador getFornecedor(String fornecedor){
        return this.mercado.get(fornecedor);
    }

    /**
     * Adiciona uma casa à comunidade
     * @param proprietario Proprietário da casa
     * @param casa Objeto Casa Inteligente que vai ser adicionado à comunidade
     */
    public void addCasa(String proprietario, CasaInteligente casa){
        this.casas.put(proprietario, casa.clone());
    }


    //gets e sets
    /**
     * Método que devolve o nome da Comunidade
     * @return Nome da comunidade
     */
    public String getNomeDaComunidade() {
        return this.nomeDaComunidade;
    }

    /**
     * Método que devolve uma cópia da estrutura das casas
     * @return Devolve uma cópia da estrutura das casas
     */
    public Map<String, CasaInteligente> getCasas() {
        Map<String, CasaInteligente> new_casas = new HashMap<>();
        new_casas = this.casas.entrySet()
                              .stream()
                              .collect(toMap(e->e.getKey(), e->e.getValue().clone()));

        return new_casas;
    }

    /**
     * Método que devolve uma cópia da estrutura dos comercializadores de energia
     * @return Devolve uma cópia da estrutura dos comercializadores de energia
     */
    public Map<String, Comercializador> getMercado() {
        Map<String, Comercializador> new_mercado = new HashMap<>();
        new_mercado = this.mercado.entrySet()
                                  .stream()
                                  .collect(toMap(e->e.getKey(), e->e.getValue()));

        return new_mercado;
    }

    /**
     * Método que define o nomeDaComunidade
     * @param nomeDaComunidade Nome da Comunidade
     */
    public void setNomeDaComunidade(String nomeDaComunidade) {
        this.nomeDaComunidade = nomeDaComunidade;
    }

    /**
     * Método que adiciona uma casa à estrutura das casas
     * @param proprietario Proprietario da casa
     * @param casa Objeto Casa qeu vai ser associado à morada passada como parâmetro
     */
    public void setCasas(String proprietario, CasaInteligente casa) {
        this.casas.put(proprietario, casa.clone());
    }

    /**
     * Método que adiciona uma companhia à estrutura dos comercializadores de energia
     * @param companhia Nome da companhia
     * @param comercializador Objeto comercializador de energia
     */
    public void setMercado(String companhia, Comercializador comercializador){
        this.mercado.put(companhia, comercializador.clone());
    }

}
