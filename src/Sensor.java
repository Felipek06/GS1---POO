public interface Sensor {

    // Realiza a leitura do valor atual do sensor
    double lerValor();

    // Verifica se o sensor está operando corretamente
    boolean verificarFuncionamento();

    // Retorna o tipo do sensor (ex: "Temperatura", "Pressão")
    String retornarTipo();

    // Define o limite máximo aceitável antes de gerar alerta
    void setLimiteAlerta(double limite);

    // Verifica se o valor atual ultrapassou o limite de alerta
    boolean estaEmAlerta();
}