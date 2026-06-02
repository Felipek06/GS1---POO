public abstract class ComponenteEspacial {

    // Atributos comuns a todos os componentes
    private String id;
    private String nome;
    private boolean status;
    private double temperatura;

    // Construtor
    public ComponenteEspacial(String id, String nome, double temperatura) {
        this.id = id;
        this.nome = nome;
        this.temperatura = temperatura;
        this.status = false;  // desligado por padrão de começo
    }

    public void ligarComponente() {
        this.status = true;
        System.out.println("[" + nome + "] Componente LIGADO.");
    }

    public void desligarComponente() {
        this.status = false;
        System.out.println("[" + nome + "] Componente DESLIGADO.");
    }

    public void exibirStatus() {
        System.out.println("-----------------------------");
        System.out.println("ID       : " + id);
        System.out.println("Nome     : " + nome);
        System.out.println("Status   : " + (status ? "LIGADO" : "DESLIGADO"));
        System.out.println("Temp.    : " + temperatura + "°C");
    }

    public abstract void executarDiagnostico();

    // Getters e Setters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isStatus() { return status; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
}