public class Inimigo extends Personagem {

    private String[] frasesDeCombate;
    private String nomePremiacao;
    private int bonusAtaquePremiacao;
    private int bonusDefesaPremiacao;

    public Inimigo(String nome, int vida, int nivel, int ataque, int defesa,
            String[] frasesDeCombate) {
        super(nome, vida, nivel, ataque, defesa);
        this.frasesDeCombate = frasesDeCombate;
    }

    public void definirPremiacao(String nomeItem, int bonusAtq, int bonusDef) {
        this.nomePremiacao = nomeItem;
        this.bonusAtaquePremiacao = bonusAtq;
        this.bonusDefesaPremiacao = bonusDef;
    }

    public void entregarPremiacao(Jogador jogador) {
        if (nomePremiacao != null) {
            System.out.println(nome + " deixou cair: " + nomePremiacao);
            jogador.equipar(nomePremiacao, bonusAtaquePremiacao, bonusDefesaPremiacao);
        }
    }

    public String falarFrase() {
        return frasesDeCombate[aleatorio.nextInt(frasesDeCombate.length)];
    }
}
