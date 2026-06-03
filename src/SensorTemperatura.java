import java.util.Random;

public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAlerta;
    private double limiteMinimo;
    private String unidade;
    private Random random = new Random();

    public SensorTemperatura(String id, double limiteMinimo, double limiteAlerta) {
        super(id, "Sensor de Temperatura", 20.0);
        this.limiteMinimo = limiteMinimo;
        this.limiteAlerta = limiteAlerta;
        this.unidade = "°C";
        this.valorAtual = 0.0;
    }

    @Override
    public double lerValor() {
        valorAtual = limiteMinimo + (random.nextDouble() * (limiteAlerta - limiteMinimo + 40));
        valorAtual = Math.round(valorAtual * 10.0) / 10.0;
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        // Verifica se o valor lido está dentro de uma faixa fisicamente plausível
        // (evita confundir com getTemperatura(), que é a temp. interna do componente)
        return valorAtual > -273.15;  // acima do zero absoluto = sensor respondendo
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    @Override
    public void setLimiteAlerta(double limite) {
        this.limiteAlerta = limite;
    }

    @Override
    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    @Override
    public boolean estaEmAlerta() {
        return valorAtual > limiteAlerta || valorAtual < limiteMinimo;
    }

    @Override
    public void executarDiagnostico() {
        double leitura = lerValor();
        System.out.println("[Diagnóstico - " + getNome() + "]");
        System.out.println("  Leitura atual : " + leitura + unidade);
        System.out.println("  Limite mínimo : " + limiteMinimo + unidade);
        System.out.println("  Limite alerta : " + limiteAlerta + unidade);
        System.out.println("  Funcionando   : " + (verificarFuncionamento() ? "SIM" : "NÃO"));
        System.out.println("  Em alerta     : " + (estaEmAlerta() ? "SIM" : "NÃO"));
    }

    public double getValorAtual()   { return valorAtual; }
    public String getUnidade()      { return unidade; }
}