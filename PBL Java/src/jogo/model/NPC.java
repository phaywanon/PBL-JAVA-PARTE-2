package jogo.model;

public abstract class NPC extends Personagem {
    private String tipo;

    public NPC(String nome, String tipo) {
        super(nome);
        this.tipo = tipo;
    }

    public abstract String interagir(Jogador jogador);

    public String getTipo() { return tipo; }
}





