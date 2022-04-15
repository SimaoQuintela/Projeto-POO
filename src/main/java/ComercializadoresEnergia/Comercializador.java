package ComercializadoresEnergia;
// discutir esta classe com o grupo, não avançar sem debater detalhes da mesma
public class Comercializador{
    private int numeroDispositivos;
    private int valorBase;
    private int consumoDispositivo;
    private int imposto;
    private float PrecoDiaPorDispositivo = numeroDispositivos > 10?(valorBase ∗ consumoDispositivo * (1 + imposto)) ∗ 0.9 : (valorBase ∗ ConsumoDispositivo ∗ (1 + Imposto)) ∗ 0.75
}
