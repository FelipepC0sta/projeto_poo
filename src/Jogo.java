import java.util.Scanner;

public class Jogo {

    private static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Guardioes da Terra");
        System.out.println("Um RPG sobre Meio Ambiente");

        System.out.print("Qual e o seu nome, guardiao?");
        String nome = leitor.nextLine().trim();
        if (nome.isEmpty())
            nome = "Guardiao";

        Jogador jogador = new Jogador(nome);

        System.out.println("\nBem vindo," + nome);
        System.out.println("Voce e um ativista ambiental. Sua missao:");
        System.out.println("enfrentar aqueles que destroem o planeta");
        System.out.println("usando argumentos, dados e consciencia.\n");
        pausar();

        System.out.println("\n Capitulo 1: A Floresta \n");
        System.out.println("Voce chega na Amazonia e encontra uma area");
        System.out.println("sendo desmatada ilegalmente. O responsavel");
        System.out.println("e o Desmatador, um empresario ganancioso.\n");

        Inimigo desmatador = new Inimigo(
                "Desmatador", 70, 1, 10, 5,
                new String[] {
                        "Progresso exige sacrificios",
                        "Essa terra e minha, faco o que quiser",
                        "Ambientalista so atrapalha"
                });
        desmatador.definirPremiacao("Megafone da Verdade", 3, 0);

        if (!combate(jogador, desmatador))
            return;

        System.out.println("\nO desmatamento foi interrompido");
        System.out.println("A floresta comeca a se recuperar.\n");
        jogador.descansar();
        pausar();

        System.out.println("\n Capitulo 2: O Oceano \n");
        System.out.println("No litoral, praias estao cobertas de plastico.");
        System.out.println("A Magnata do Plastico se recusa a mudar");
        System.out.println("suas embalagens descartaveis.\n");

        Inimigo magnata = new Inimigo(
                "Magnata do Plastico", 100, 3, 14, 8,
                new String[] {
                        "Plastico e pratico e barato",
                        "Reciclar e problema do consumidor",
                        "Tem problemas maiores no mundo"
                });
        magnata.definirPremiacao("Escudo de Dados", 1, 4);

        if (!combate(jogador, magnata))
            return;

        System.out.println("\nA magnata aceitou usar embalagens biodegradaveis");
        System.out.println("Os oceanos agradecem.\n");
        jogador.descansar();
        pausar();

        System.out.println("\n Capitulo Final: A Corporacao \n");
        System.out.println("Todas as pistas levam a Corporacao Cinza,");
        System.out.println("que financia desmatamento, poluicao e");
        System.out.println("desinformacao climatica no mundo todo.");
        System.out.println("O Diretor e seu ultimo desafio.\n");

        jogador.descansar();

        Inimigo diretor = new Inimigo(
                "Diretor da Corporacao", 130, 5, 18, 10,
                new String[] {
                        "Eu controlo a midia",
                        "Sustentabilidade nao da lucro",
                        "Voce e so uma formiga contra um imperio"
                });
        diretor.definirPremiacao("Manto do Guardiao", 5, 5);

        if (!combate(jogador, diretor))
            return;

        System.out.println("Voce Venceu");
        System.out.println("A corporacao foi desmantelada.");
        System.out.println("Florestas se recuperam, oceanos respiram.");
        System.out.println(nome + " provou que o conhecimento");
        System.out.println("e a arma mais poderosa.\n");
        System.out.println("Na vida real, o planeta tambem precisa");
        System.out.println("de guardioes. Faca a sua parte\n");
        System.out.println("Obrigado por jogar");

        leitor.close();
    }

    private static boolean combate(Jogador jogador, Inimigo inimigo) {
        System.out.println("Confronto: " + jogador.getNome()
                + " vs " + inimigo.getNome() + "!\n");

        int turno = 1;

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println(" Turno " + turno);
            System.out.println(jogador.getNome() + ": " + jogador.barraDeVida());
            System.out.println(inimigo.getNome() + ": " + inimigo.barraDeVida());

            System.out.println("\nO que fazer?");
            System.out.println("1 Argumentar (ataque basico)");
            System.out.println("2 Argumento forte (mais dano, pode falhar)");
            System.out.println("3 Recuperar folego (cura leve)");
            System.out.print("Escolha (1-3): ");

            int escolha = lerEscolha(1, 3);

            switch (escolha) {
                case 1:
                    int dano = jogador.atacar(inimigo);
                    System.out.println(jogador.getNome()
                            + " apresenta argumentos Dano: " + dano);
                    break;
                case 2:
                    int danoEsp = jogador.ataqueEspecial(inimigo);
                    if (danoEsp == 0) {
                        System.out.println("O argumento nao convenceu... Errou");
                    } else {
                        System.out.println(jogador.getNome()
                                + " faz um argumento devastador Dano: " + danoEsp);
                    }
                    break;
                case 3:
                    int vidaAntes = jogador.getVida();
                    jogador.descansar();
                    int curou = jogador.getVida() - vidaAntes;
                    System.out.println(jogador.getNome()
                            + " recupera o folego +" + curou + " vida");
                    break;
            }

            if (!inimigo.estaVivo()) {
                System.out.println("\n" + inimigo.getNome() + " foi convencido\n");
                inimigo.entregarPremiacao(jogador);
                jogador.evoluir();
                return true;
            }

            System.out.println("\n\"" + inimigo.falarFrase() + "\"");
            int danoInimigo = inimigo.atacar(jogador);
            System.out.println(inimigo.getNome()
                    + " contra argumenta Dano: " + danoInimigo);

            if (!jogador.estaVivo()) {
                System.out.println("Game over");
                System.out.println(jogador.getNome() + " foi derrotado");
                System.out.println("Mas a luta pelo planeta continua");
                leitor.close();
                return false;
            }

            turno++;
            System.out.println();
        }

        return jogador.estaVivo();
    }

    private static int lerEscolha(int min, int max) {
        while (true) {
            try {
                int escolha = Integer.parseInt(leitor.nextLine().trim());
                if (escolha >= min && escolha <= max)
                    return escolha;
            } catch (NumberFormatException e) {

            }
            System.out.print("Invalido. Digite entre " + min + " e " + max + ": ");
        }
    }

    private static void pausar() {
        System.out.println("Pressione Enter para continuar");
        leitor.nextLine();
    }
}
