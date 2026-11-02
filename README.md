# Arena dos Campeões — Batalha em Turnos

## Sobre o projeto

Arena dos Campeões é um jogo de RPG desenvolvido em Java, executado pelo terminal e baseado em batalhas por turnos.

O jogador deve criar seu próprio herói, escolher uma classe e enfrentar diferentes inimigos ao longo de cinco fases. O objetivo é derrotar todos os adversários e chegar ao final da jornada com vida.

## Tecnologias utilizadas

- Java 21
- Programação Orientada a Objetos (POO)
- IntelliJ IDEA

## Classes de personagens

O jogador pode escolher entre três classes:

**Guerreiro**
- Vida: 130 pontos.
- Ataque: 25 pontos.
- Defesa: 20 pontos.
- Habilidade especial: 30% de chance de causar 10 pontos de dano adicionais.

**Mago**
- Vida: 80 pontos.
- Ataque: 35 pontos.
- Defesa: 30 pontos.
- Mana: 100 pontos.
- Habilidade especial: 30% de chance de realizar dois ataques em uma única ação.

**Arqueiro**
- Vida: 120 pontos.
- Ataque: 30 pontos.
- Defesa: 25 pontos.
- Habilidade especial: 30% de chance de evitar completamente o dano de um ataque inimigo.

## Inimigos

Durante a jornada, o jogador encontrará três tipos de inimigos:

- **Aranha:** possui pouca vida e pode receber bônus de ataque quando existem outras aranhas na batalha.
- **Zumbi:** possui resistência a parte do dano recebido.
- **Demônio:** é o chefe da última fase e possui chance de realizar dois ataques em um mesmo turno.

## Como funciona o jogo?

A jornada é dividida em cinco fases, com inimigos sorteados aleatoriamente de acordo com a etapa.

Durante as batalhas, o jogador pode escolher entre três ações:

1. **Atacar:** seleciona um inimigo para receber o ataque.
2. **Defender:** reduz o dano recebido durante o próximo turno dos inimigos.
3. **Usar item:** permite utilizar itens disponíveis no inventário, como poções.

O jogador e os inimigos alternam seus turnos até que todos os adversários sejam derrotados ou o herói perca toda a sua vida.

Ao avançar para uma nova fase, a vida e a defesa do herói são recuperadas. Os itens restantes no inventário são mantidos.

## Itens

O jogo possui dois tipos de poções:

- **Poção de Vida:** recupera 40 pontos de vida, respeitando o limite máximo do personagem.
- **Poção de Mana:** recupera 20 pontos de mana.

Cada classe começa a jornada com uma quantidade específica de itens.

## Como executar o projeto?

1. Abra o projeto na IDE IntelliJ IDEA.
2. Certifique-se de que o JDK 21 esteja configurado.
3. Localize a classe `Main.java`.
4. Execute o método `main()` para iniciar o jogo.
5. Siga as instruções apresentadas no terminal.

## Estrutura do projeto

O projeto está organizado em pacotes para separar as responsabilidades das classes:

- `personagem`: contém a classe base `Personagem`.
  - `herois`: contém as classes Guerreiro, Mago e Arqueiro.
  - `inimigos`: contém as classes Aranha, Zumbi e Demônio.
- `batalha`: contém as classes responsáveis pelo funcionamento do jogo e das batalhas.
- `utilitarios`: contém ferramentas auxiliares, como o gerador de números aleatórios.
- `Main.java`: ponto de entrada do programa.

## Objetivo do projeto

O objetivo deste projeto é aplicar os conceitos de Programação Orientada a Objetos em Java, utilizando conceitos como herança, encapsulamento, polimorfismo, classes, objetos e métodos, além de desenvolver a lógica de um jogo com batalhas por turnos.
