public class Jogador extends Personagem {

    private String equipamento;
    private int bonusAtaque;
    private int bonusDefesa;

    public Jogador(String nome) {
        super(nome, 100, 1, 12, 8);
        this.equipamento = "Nenhum";
        this.bonusAtaque = 0;
        this.bonusDefesa = 0;
    }

    public int ataqueEspecial(Personagem alvo) {
        boolean acertou = aleatorio.nextInt(100) < 70;
        if (!acertou) {
            return 0;
        }
        int danoBase = Math.max(1, (int) ((this.ataque + bonusAtaque) * 1.5) - alvo.getDefesa() / 2);
        double variacao = 0.9 + (aleatorio.nextDouble() * 0.3);
        int dano = Math.max(1, (int) (danoBase * variacao));
        alvo.receberDano(dano);
        return dano;
    }

    @Override
    public int atacar(Personagem alvo) {
        int ataqueOriginal = this.ataque;
        this.ataque += bonusAtaque;
        int dano = super.atacar(alvo);
        this.ataque = ataqueOriginal;
        return dano;
    }

    public void evoluir() {
        this.nivel++;
        this.vidaMaxima += 15;
        this.vida = this.vidaMaxima;
        this.ataque += 3;
        this.defesa += 2;

        System.out.println("\n Nivel aumentado! ");
        System.out.println(nome + " alcancou o nivel " + nivel + "!");
        System.out.println("Vida: " + vidaMaxima + " | Ataque: " + ataque + " | Defesa: " + defesa);
    }

    public void equipar(String nomeItem, int bonusAtq, int bonusDef) {
        this.equipamento = nomeItem;
        this.bonusAtaque = bonusAtq;
        this.bonusDefesa = bonusDef;
        System.out.println(nome + " equipou: " + nomeItem
                + " (ATQ+" + bonusAtq + " DEF+" + bonusDef + ")");
    }

    public void descansar() {
        int cura = (int) (vidaMaxima * 0.3);
        this.vida = Math.min(vida + cura, vidaMaxima);
    }

    @Override
    public void receberDano(int dano) {
        int danoReal = Math.max(1, dano - bonusDefesa);
        super.receberDano(danoReal);
    }

    public String getEquipamento() {
        return equipamento;
    }
}
