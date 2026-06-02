public class PropulsaoQuimica extends SistemaPropulsao {

    private double nivelCombustivelPropulsor;  // em litros
    private double taxaConsumo;                // litros por acionamento a 100%

    public PropulsaoQuimica(String id, double combustivelInicial, double taxaConsumo) {
        super(id, "Propulsão Química", 500.0); // empuxo máximo: 500 kN
        this.nivelCombustivelPropulsor = combustivelInicial;
        this.taxaConsumo = taxaConsumo;
    }

    @Override
    public void acelerar(double porcentagem) {
        if (!aplicarPotencia(porcentagem)) return;

        double consumoAtual = (porcentagem / 100.0) * taxaConsumo;
        nivelCombustivelPropulsor -= consumoAtual;
        if (nivelCombustivelPropulsor < 0) nivelCombustivelPropulsor = 0;

        System.out.println("[" + getNome() + "] Acelerando a " + porcentagem + "% → Empuxo: " + getEmpuxoGerado() + " kN");
        System.out.println("  Combustível restante: " + String.format("%.1f", nivelCombustivelPropulsor) + " L");

        if (nivelCombustivelPropulsor < 1000) {
            System.out.println("[ALERTA] Combustível do propulsor abaixo de 1.000 L!");
        }
    }

    @Override
    public void executarDiagnostico() {
        super.executarDiagnostico();
        System.out.println("  Combustível   : " + String.format("%.1f", nivelCombustivelPropulsor) + " L");
        System.out.println("  Taxa consumo  : " + taxaConsumo + " L por acionamento (100%)");
    }

    public double getNivelCombustivelPropulsor() { return nivelCombustivelPropulsor; }
    public double getTaxaConsumo()               { return taxaConsumo; }
}