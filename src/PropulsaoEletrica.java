public class PropulsaoEletrica extends SistemaPropulsao {

    private double cargaBateria;    // 0 a 100 (%)
    private double taxaConsumoBateria; // % de bateria consumida por acionamento a 100%

    public PropulsaoEletrica(String id, double cargaInicial, double taxaConsumoBateria) {
        super(id, "Propulsão Elétrica (Íon)", 5.0); // empuxo máximo: 5 kN
        this.cargaBateria = cargaInicial;
        this.taxaConsumoBateria = taxaConsumoBateria;
    }

    @Override
    public void acelerar(double porcentagem) {
        if (!aplicarPotencia(porcentagem)) return;

        if (cargaBateria <= 0) {
            System.out.println("[ERRO] Bateria descarregada. Não é possível acelerar.");
            return;
        }

        double consumoAtual = (porcentagem / 100.0) * taxaConsumoBateria;
        cargaBateria -= consumoAtual;
        if (cargaBateria < 0) cargaBateria = 0;

        System.out.println("[" + getNome() + "] Acelerando a " + porcentagem + "% → Empuxo: " + getEmpuxoGerado() + " kN");
        System.out.println("  Carga da bateria: " + String.format("%.1f", cargaBateria) + "%");

        if (cargaBateria < 20) {
            System.out.println("[ALERTA] Bateria abaixo de 20%!");
        }
    }

    public void recarregarBateria(double quantidade) {
        if (quantidade < 0) {
            System.out.println("[ERRO] Quantidade de recarga não pode ser negativa.");
            return;
        }
        cargaBateria = Math.min(100.0, cargaBateria + quantidade);
        System.out.println("[" + getNome() + "] Bateria recarregada. Nível atual: " + String.format("%.1f", cargaBateria) + "%");
    }

    @Override
    public void executarDiagnostico() {
        super.executarDiagnostico();
        System.out.println("  Carga bateria : " + String.format("%.1f", cargaBateria) + "%");
        System.out.println("  Taxa consumo  : " + taxaConsumoBateria + "% por acionamento (100%)");
    }

    public double getCargaBateria()        { return cargaBateria; }
    public double getTaxaConsumoBateria()  { return taxaConsumoBateria; }
}