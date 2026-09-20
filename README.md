# Guardiões da Terra

Um jogo de terminal no estilo RPG de turnos, onde o jogador assume o papel de um ativista ambiental que enfrenta vilões que destroem o planeta usando argumentos, dados e consciência como armas.

## Tema

O jogo aborda questões ambientais reais como **desmatamento ilegal na Amazônia**, **poluição plástica nos oceanos** e **desinformação climática corporativa**. O combate é feito por meio de debates e argumentação, não violência.

## Como Rodar

### Pré-requisito

- **Java JDK 8** ou superior instalado.
  - Para verificar, abra o terminal e digite: `java -version`
  - Se não tiver instalado, baixe em: https://adoptium.net

### Passo a passo

1. **Clone o repositório:**
```bash
git clone https://github.com/FelipepC0sta/projeto_poo.git
cd projeto_poo
```

2. **Compile o projeto:**
```bash
javac src/*.java -d out
```

3. **Execute o jogo:**
```bash
java -cp out Jogo
```

### Usando uma IDE (IntelliJ, Eclipse, VS Code)

1. Abra a pasta do projeto na IDE.
2. Certifique-se de que a pasta `src` está marcada como diretório de fontes (source root).
3. Execute a classe `Jogo.java` (contém o método `main`).

## Estrutura do Projeto

```
projeto_poo/
├── src/
│   ├── Personagem.java      # Classe base (atributos e métodos comuns)
│   ├── Jogador.java          # Herda de Personagem (nível, evolução, equipamentos)
│   ├── Inimigo.java          # Herda de Personagem (frases, premiação)
│   └── Jogo.java             # Classe principal (história, combate, main)
├── out/                      # Classes compiladas (gerado automaticamente)
└── README.md
```

## Gameplay

- O jogador começa no **nível 1** e evolui a cada vitória.
- A cada turno, o jogador pode escolher entre:
  - **Argumentar** — ataque básico com dano consistente.
  - **Argumento forte** — dano maior, porém com 30% de chance de falhar.
  - **Recuperar fôlego** — restaura parte da vida.
- Inimigos derrotados deixam **equipamentos** que aumentam ataque e defesa.
- O dano dos ataques varia a cada turno graças ao uso de `Random`.

## Capítulos

| Capítulo | Cenário | Inimigo | Equipamento |
|---|---|---|---|
| 1 | Floresta Amazônica | Desmatador | Megafone da Verdade |
| 2 | Litoral / Oceano | Magnata do Plástico | Escudo de Dados |
| 3 | Corporação Global | Diretor da Corporação | Manto do Guardião |

## Conceitos de POO Utilizados

- **Herança**: `Jogador` e `Inimigo` estendem a classe `Personagem`.
- **Encapsulamento**: atributos protegidos com acesso via getters.
- **Polimorfismo**: `Jogador` sobrescreve os métodos `atacar()` e `receberDano()`.
- **Princípio DRY**: atributos e métodos comuns definidos uma única vez em `Personagem`.
