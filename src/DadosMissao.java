public class DadosMissao {

    // Atributos gerais (validados nos setters)
    private String nomeMissao;
    private int numeroDeTripulantes;
    private double nivelCombustivel;  // 0 a 100 (%)
    private String trajetoria;

    // Atributos restritos (exigem senha)
    private double coordenadaX;
    private double coordenadaY;
    private double coordenadaZ;
    private String codigoDeAcesso;

    private final String senhaRestricao;

    public DadosMissao(String nomeMissao, int numeroDeTripulantes, double nivelCombustivel,
                       String trajetoria, String codigoDeAcesso, String senhaRestricao) {
        this.nomeMissao = nomeMissao;
        this.trajetoria = trajetoria;
        this.codigoDeAcesso = codigoDeAcesso;
        this.senhaRestricao = senhaRestricao;
        this.coordenadaX = 0.0;
        this.coordenadaY = 0.0;
        this.coordenadaZ = 0.0;
        setNumeroDeTripulantes(numeroDeTripulantes);
        setNivelCombustivel(nivelCombustivel);
    }

    // -------------------------
    // Getters e Setters com validação
    // -------------------------

    public String getNomeMissao() { return nomeMissao; }

    public int getNumeroDeTripulantes() { return numeroDeTripulantes; }
    public void setNumeroDeTripulantes(int n) {
        if (n < 0) {
            System.out.println("[ERRO] Número de tripulantes não pode ser negativo.");
            return;
        }
        this.numeroDeTripulantes = n;
    }

    public double getNivelCombustivel() { return nivelCombustivel; }
    public void setNivelCombustivel(double nivel) {
        if (nivel < 0 || nivel > 100) {
            System.out.println("[ERRO] Nível de combustível deve estar entre 0 e 100.");
            return;
        }
        this.nivelCombustivel = nivel;
        verificarCombustivel();
    }

    public String getTrajetoria() { return trajetoria; }
    public void setTrajetoria(String trajetoria) { this.trajetoria = trajetoria; }

    // -------------------------
    // Métodos restritos (exigem senha)
    // -------------------------

    public void setCoordenadas(double x, double y, double z, String senha) {
        if (!validarSenha(senha)) return;
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.coordenadaZ = z;
        System.out.println("[OK] Coordenadas atualizadas.");
    }

    public void exibirCoordenadas(String senha) {
        if (!validarSenha(senha)) return;
        System.out.println("  Coordenadas: X=" + coordenadaX + " | Y=" + coordenadaY + " | Z=" + coordenadaZ);
    }

    public void exibirCodigoDeAcesso(String senha) {
        if (!validarSenha(senha)) return;
        System.out.println("  Código de acesso: " + codigoDeAcesso);
    }

    // -------------------------
    // Métodos internos
    // -------------------------

    private boolean validarSenha(String senha) {
        if (!senhaRestricao.equals(senha)) {
            System.out.println("[ACESSO NEGADO] Senha incorreta.");
            return false;
        }
        return true;
    }

    private void verificarCombustivel() {
        if (nivelCombustivel < 20.0) {
            System.out.println("[ALERTA CRITICO] Combustível abaixo de 20%! Nível atual: " + nivelCombustivel + "%");
        }
    }

    public void exibirResumo() {
        System.out.println("=== DADOS DA MISSÃO ===");
        System.out.println("  Missão        : " + nomeMissao);
        System.out.println("  Tripulantes   : " + numeroDeTripulantes);
        System.out.println("  Combustível   : " + nivelCombustivel + "%");
        System.out.println("  Trajetória    : " + trajetoria);
        System.out.println("  Coordenadas   : [RESTRITO]");
        System.out.println("  Código acesso : [RESTRITO]");
    }
}