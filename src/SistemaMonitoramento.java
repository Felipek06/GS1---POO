import java.util.Scanner;

public class SistemaMonitoramento {

    // Sensores
    private static SensorTemperatura sensorTemp    = new SensorTemperatura("S-001", -50.0, 80.0);
    private static SensorPressao     sensorPressao = new SensorPressao("S-002", 70.0, 130.0);
    private static SensorRadiacao    sensorRad     = new SensorRadiacao("S-003", 0.0, 1.0);

    // Propulsão
    private static PropulsaoQuimica  propQuimica   = new PropulsaoQuimica("P-001", 10000.0, 50.0);
    private static PropulsaoEletrica propEletrica  = new PropulsaoEletrica("P-002", 100.0, 0.5);

    // Dados da missão
    private static DadosMissao dadosMissao = new DadosMissao(
            "Missão Artemis-X",
            6,
            85.0,
            "Terra → Lua → Marte",
            "ALFA-7729",
            "FIAP2026"
    );

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  PLATAFORMA DE MONITORAMENTO ESPACIAL  ║");
        System.out.println("╚════════════════════════════════════════╝");

        int opcao = -1;
        while (opcao != 0) {
            try {
                exibirMenuPrincipal();
                opcao = lerInt("Escolha: ");
                switch (opcao) {
                    case 1 -> menuSensores();
                    case 2 -> menuPropulsao();
                    case 3 -> menuDadosMissao();
                    case 4 -> simularAlertas();
                    case 5 -> exibirStatusCompleto();
                    case 0 -> System.out.println("\n[SISTEMA] Encerrando plataforma. Boa viagem!");
                    default -> System.out.println("[ERRO] Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("[ERRO INESPERADO] " + e.getMessage());
            }
        }
        scanner.close();
    }

    // ===========================
    // MENU PRINCIPAL
    // ===========================
    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Verificar Sensores");
        System.out.println("2. Controlar Propulsão");
        System.out.println("3. Gerenciar Dados da Missão");
        System.out.println("4. Simular Alertas");
        System.out.println("5. Exibir Status Completo");
        System.out.println("0. Sair");
    }

    // ===========================
    // MENU SENSORES
    // ===========================
    private static void menuSensores() {
        System.out.println("\n--- SENSORES ---");
        System.out.println("1. Ler Sensor de Temperatura");
        System.out.println("2. Ler Sensor de Pressão");
        System.out.println("3. Ler Sensor de Radiação");
        System.out.println("4. Diagnóstico de todos os sensores");
        System.out.println("0. Voltar");

        try {
            int op = lerInt("Escolha: ");
            switch (op) {
                case 1 -> executarLeituraSensor(sensorTemp, "°C");
                case 2 -> executarLeituraSensor(sensorPressao, " kPa");
                case 3 -> executarLeituraSensor(sensorRad, " mSv/h");
                case 4 -> {
                    sensorTemp.executarDiagnostico();
                    sensorPressao.executarDiagnostico();
                    sensorRad.executarDiagnostico();
                }
                case 0 -> {}
                default -> System.out.println("[ERRO] Opção inválida.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao operar sensor: " + e.getMessage());
        }
    }

    // Método genérico para leitura de qualquer sensor
    private static void executarLeituraSensor(Sensor sensor, String unidade) {
        try {
            double valor = sensor.lerValor();
            System.out.println("[" + sensor.retornarTipo() + "] Leitura: " + valor + unidade);
            verificarAlertaSensor(sensor, valor, unidade);
        } catch (Exception e) {
            System.out.println("[ERRO] Falha na leitura do sensor " + sensor.retornarTipo() + ": " + e.getMessage());
        }
    }

    // ===========================
    // MENU PROPULSÃO
    // ===========================
    private static void menuPropulsao() {
        System.out.println("\n--- PROPULSÃO ---");
        System.out.println("1. Ligar Propulsão Química");
        System.out.println("2. Desligar Propulsão Química");
        System.out.println("3. Acelerar (Química)");
        System.out.println("4. Ligar Propulsão Elétrica");
        System.out.println("5. Desligar Propulsão Elétrica");
        System.out.println("6. Acelerar (Elétrica)");
        System.out.println("7. Recarregar Bateria (Elétrica)");
        System.out.println("8. Diagnóstico completo de propulsão");
        System.out.println("0. Voltar");

        try {
            int op = lerInt("Escolha: ");
            switch (op) {
                case 1 -> propQuimica.ligarMotor();
                case 2 -> propQuimica.desligarMotor();
                case 3 -> {
                    double pot = lerDouble("Informe a potência (0-100): ");
                    propQuimica.acelerar(pot);
                }
                case 4 -> propEletrica.ligarMotor();
                case 5 -> propEletrica.desligarMotor();
                case 6 -> {
                    double pot = lerDouble("Informe a potência (0-100): ");
                    propEletrica.acelerar(pot);
                }
                case 7 -> {
                    double carga = lerDouble("Quantidade a recarregar (0-100): ");
                    propEletrica.recarregarBateria(carga);
                }
                case 8 -> {
                    propQuimica.executarDiagnostico();
                    propEletrica.executarDiagnostico();
                }
                case 0 -> {}
                default -> System.out.println("[ERRO] Opção inválida.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Falha no sistema de propulsão: " + e.getMessage());
        }
    }

    // ===========================
    // MENU DADOS DA MISSÃO
    // ===========================
    private static void menuDadosMissao() {
        System.out.println("\n--- DADOS DA MISSÃO ---");
        System.out.println("1. Exibir resumo");
        System.out.println("2. Atualizar combustível");
        System.out.println("3. Ver coordenadas (requer senha)");
        System.out.println("4. Atualizar coordenadas (requer senha)");
        System.out.println("5. Ver código de acesso (requer senha)");
        System.out.println("0. Voltar");

        try {
            int op = lerInt("Escolha: ");
            switch (op) {
                case 1 -> dadosMissao.exibirResumo();
                case 2 -> {
                    double nivel = lerDouble("Novo nível de combustível (0-100): ");
                    dadosMissao.setNivelCombustivel(nivel);
                }
                case 3 -> {
                    String senha = lerString("Senha: ");
                    dadosMissao.exibirCoordenadas(senha);
                }
                case 4 -> {
                    String senha = lerString("Senha: ");
                    double x = lerDouble("Coordenada X: ");
                    double y = lerDouble("Coordenada Y: ");
                    double z = lerDouble("Coordenada Z: ");
                    dadosMissao.setCoordenadas(x, y, z, senha);
                }
                case 5 -> {
                    String senha = lerString("Senha: ");
                    dadosMissao.exibirCodigoDeAcesso(senha);
                }
                case 0 -> {}
                default -> System.out.println("[ERRO] Opção inválida.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao gerenciar dados da missão: " + e.getMessage());
        }
    }

    // ===========================
    // SIMULAÇÃO DE ALERTAS
    // ===========================
    private static void simularAlertas() {
        System.out.println("\n=== SIMULANDO LEITURAS E VERIFICANDO ALERTAS ===");
        try {
            executarLeituraSensor(sensorTemp, "°C");
            executarLeituraSensor(sensorPressao, " kPa");
            executarLeituraSensor(sensorRad, " mSv/h");
        } catch (Exception e) {
            System.out.println("[ERRO] Falha na simulação de alertas: " + e.getMessage());
        }
    }

    // ===========================
    // STATUS COMPLETO
    // ===========================
    private static void exibirStatusCompleto() {
        try {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║         STATUS COMPLETO          ║");
            System.out.println("╚══════════════════════════════════╝");
            dadosMissao.exibirResumo();
            System.out.println();
            sensorTemp.exibirStatus();
            sensorPressao.exibirStatus();
            sensorRad.exibirStatus();
            System.out.println();
            propQuimica.exibirStatus();
            propEletrica.exibirStatus();
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao exibir status: " + e.getMessage());
        }
    }

    // ===========================
    // LÓGICA DE ALERTAS
    // ===========================
    private static void verificarAlertaSensor(Sensor sensor, double valor, String unidade) {
        if (!sensor.verificarFuncionamento()) {
            emitirAlerta("CRITICO", sensor.retornarTipo() + " com FALHA DE FUNCIONAMENTO!");
        } else if (sensor.estaEmAlerta()) {
            emitirAlerta("ALERTA", sensor.retornarTipo() + " fora dos limites: " + valor + unidade);
        } else {
            System.out.println("[OK] " + sensor.retornarTipo() + " dentro dos limites.");
        }
    }

    private static void emitirAlerta(String nivel, String mensagem) {
        switch (nivel) {
            case "ATENCAO" -> System.out.println("[ATENCAO] " + mensagem);
            case "ALERTA"  -> System.out.println("[ALERTA]  " + mensagem);
            case "CRITICO" -> System.out.println("[CRITICO] " + mensagem);
            default        -> System.out.println("[" + nivel + "] " + mensagem);
        }
    }

    // ===========================
    // UTILITÁRIOS DE LEITURA
    // ===========================
    private static int lerInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Entrada inválida. Digite um número inteiro.");
            return -1;
        }
    }

    private static double lerDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Entrada inválida. Digite um número válido.");
            return 0;
        }
    }

    private static String lerString(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            System.out.println("[ERRO] Falha na leitura da entrada.");
            return "";
        }
    }
}