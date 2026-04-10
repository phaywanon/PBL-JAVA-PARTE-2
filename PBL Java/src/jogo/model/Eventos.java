package jogo.model;

public abstract class Eventos {
    protected String descricao;

    public Eventos(String descricao) {
        this.descricao = descricao;
    }

    public abstract void aplicarEvento(Jogador jogador);

}
