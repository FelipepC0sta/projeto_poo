import java.util.Random;

public abstract class Personagem {

    protected static final Random aleatorio = new Random();

    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int nivel;
    protected int ataque;
    protected int defesa;

    public Personagem(String nome, int vida, int nivel, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public int atacar(Personagem alvo) {
        int danoBase = Math.max(1, this.ataque - alvo.defesa / 2);
        double variacao = 0.8 + (aleatorio.nextDouble() * 0.4);
        int dano = Math.max(1, (int) (danoBase * variacao));
        alvo.receberDano(dano);
        return dano;
    }

    public void receberDano(int dano) {
        this.vida = Math.max(0, this.vida - dano);
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public String barraDeVida() {
        int tamanho = 20;
        int cheio = (int) ((double) vida / vidaMaxima * tamanho);
        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < tamanho; i++) {
            barra.append(i < cheio ? "#" : "-");
        }
        barra.append("] ").append(vida).append("/").append(vidaMaxima);
        return barra.toString();
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getNivel() {
        return nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }
}
