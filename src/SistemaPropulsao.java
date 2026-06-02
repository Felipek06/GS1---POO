public abstract class SistemaPropulsao extends ComponenteEspacial {

    private double potenciaAtual;  // 0 a 100 (%)
    private double empuxoGerado;   // em kN
    private double empuxoMaximo;   // definido por cada subclasse

    public SistemaPropulsao(String id, String nome, double empuxoMaximo) {
        super(id, nome, 25.0);
        this.potenciaAtual = 0.0;
        this.empuxoGerado = 0.0;
        this.empuxoMaximo = empuxoMaximo;
    }

    public void ligarMotor() {
        super.ligarComponente();
        System.out.println("[" + getNome() + "] Motor pronto para operação.");
    }

    public void desligarMotor() {
        this.potenciaAtual = 0.0;
        this.empuxoGerado = 0.0;
        super.desligarComponente();
        System.out.println("[" + getNome() + "] Empuxo zerado.");
    }

    // Cada subclasse define como a aceleração funciona
    public abstract void acelerar(double porcentagem);

    // Valida a operação antes de alterar potência e empuxo.
    protected boolean validarOperacao(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 100) {
            System.out.println("[ERRO] Potência deve estar entre 0 e 100. Valor recebido: " + porcentagem);
            return false;
        }
        if (!isStatus()) {
            System.out.println("[ERRO] Motor desligado. Ligue o motor antes de acelerar.");
            return false;
        }
        return true;
    }

    protected void atualizarPotencia(double porcentagem) {
        this.potenciaAtual = porcentagem;
        this.empuxoGerado = (porcentagem / 100.0) * empuxoMaximo;
    }

    // Valida e aplica a potência — centralizado para evitar repetição nas subclasses
    protected boolean aplicarPotencia(double porcentagem) {
        if (!validarOperacao(porcentagem)) {
            return false;
        }
        atualizarPotencia(porcentagem);
        return true;
    }

    @Override
    public void executarDiagnostico() {
        System.out.println("[Diagnóstico - " + getNome() + "]");
        System.out.println("  Status        : " + (isStatus() ? "LIGADO" : "DESLIGADO"));
        System.out.println("  Potência      : " + potenciaAtual + "%");
        System.out.println("  Empuxo gerado : " + empuxoGerado + " kN");
        System.out.println("  Empuxo máximo : " + empuxoMaximo + " kN");
        System.out.println("  Temperatura   : " + getTemperatura() + "°C");
    }

    public double getPotenciaAtual() { return potenciaAtual; }
    public double getEmpuxoGerado()  { return empuxoGerado; }
    public double getEmpuxoMaximo()  { return empuxoMaximo; }
    protected void setEmpuxoGerado(double empuxo) { this.empuxoGerado = empuxo; }
}
