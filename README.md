# GS1-POO

Projeto desenvolvido em Java para simular uma **Plataforma de Monitoramento Espacial**. A aplicação roda no console e permite acompanhar sensores, controlar sistemas de propulsão e gerenciar dados de uma missão espacial.

## Integrantes 

Felipe Krzyzanovski os Santos Menezes - RM 564878
Lucas Ferrari LIma - RM 563119
Leonardo Lopes Oliveira - RM 565437

## Objetivo

O objetivo do projeto é aplicar conceitos de Programação Orientada a Objetos em um sistema de monitoramento, usando classes abstratas, interfaces, herança, encapsulamento, polimorfismo e validações de dados.

## Funcionalidades

- Verificação de sensores de temperatura, pressão e radiação.
- Simulação de leituras com geração de alertas quando os valores saem dos limites definidos.
- Controle de propulsão química e elétrica.
- Cálculo de empuxo com base na potência informada.
- Consumo de combustível na propulsão química.
- Consumo e recarga de bateria na propulsão elétrica.
- Exibição e atualização de dados da missão.
- Proteção de coordenadas e código de acesso por senha.
- Diagnóstico completo dos sensores e sistemas de propulsão.
- Exibição do status geral da plataforma.

## Tecnologias utilizadas

- Java
- IntelliJ IDEA
- Programação Orientada a Objetos

## Estrutura do projeto

```text
GS1-POO/
├── src/
│   ├── ComponenteEspacial.java
│   ├── DadosMissao.java
│   ├── PropulsaoEletrica.java
│   ├── PropulsaoQuimica.java
│   ├── Sensor.java
│   ├── SensorPressao.java
│   ├── SensorRadiacao.java
│   ├── SensorTemperatura.java
│   ├── SistemaMonitoramento.java
│   └── SistemaPropulsao.java
├── .gitignore
└── GS1-POO.iml
```

## Principais classes

### `SistemaMonitoramento`

Classe principal do sistema. Contém o método `main`, exibe o menu no console e coordena as operações de sensores, propulsão, dados da missão, alertas e status completo.

### `ComponenteEspacial`

Classe abstrata base para componentes espaciais. Define atributos comuns como `id`, `nome`, `status` e `temperatura`, além de métodos para ligar, desligar e exibir status.

### `Sensor`

Interface que define o comportamento padrão dos sensores:

- `lerValor()`
- `verificarFuncionamento()`
- `retornarTipo()`
- `setLimiteAlerta()`
- `estaEmAlerta()`

### Sensores

As classes `SensorTemperatura`, `SensorPressao` e `SensorRadiacao` implementam a interface `Sensor` e herdam de `ComponenteEspacial`. Cada uma possui seus próprios limites, unidade de medida e lógica de diagnóstico.

### `SistemaPropulsao`

Classe abstrata para sistemas de propulsão. Centraliza o controle de potência, empuxo gerado e validação antes da aceleração.

### Propulsões

- `PropulsaoQuimica`: simula consumo de combustível em litros.
- `PropulsaoEletrica`: simula consumo e recarga de bateria.

### `DadosMissao`

Classe responsável pelos dados da missão, como nome, número de tripulantes, combustível e trajetória. Também protege coordenadas e código de acesso por senha.

## Conceitos de POO aplicados

- **Abstração:** uso das classes abstratas `ComponenteEspacial` e `SistemaPropulsao`.
- **Encapsulamento:** atributos privados com acesso controlado por getters, setters e validações.
- **Herança:** sensores e propulsores reaproveitam comportamentos das classes base.
- **Polimorfismo:** sensores diferentes são tratados pela interface `Sensor`.
- **Interface:** `Sensor` define um contrato comum para todos os tipos de sensores.

## Como executar

### Pelo IntelliJ IDEA

1. Abra o projeto no IntelliJ IDEA.
2. Verifique se o JDK está configurado no projeto.
3. Abra o arquivo `src/SistemaMonitoramento.java`.
4. Execute o método `main`.

### Pelo terminal

Na raiz do projeto, execute:

```bash
javac -d out src/*.java
java -cp out SistemaMonitoramento
```

## Menu principal

Ao iniciar o sistema, o usuário verá as opções:

```text
1. Verificar Sensores
2. Controlar Propulsão
3. Gerenciar Dados da Missão
4. Simular Alertas
5. Exibir Status Completo
0. Sair
```

## Dados de teste

A missão inicial configurada no sistema é:

- Missão: `Missão Artemis-X`
- Tripulantes: `6`
- Combustível: `85%`
- Trajetória: `Terra → Lua → Marte`
- Código de acesso: `ALFA-7729`
- Senha para dados restritos: `FIAP2026`

## Observações

- As leituras dos sensores são geradas aleatoriamente a cada execução.
- Alguns valores podem gerar alertas dependendo dos limites configurados.
- Para acelerar um sistema de propulsão, primeiro é necessário ligar o motor.
- Coordenadas e código de acesso só são exibidos quando a senha correta é informada.

## Autor

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos.
