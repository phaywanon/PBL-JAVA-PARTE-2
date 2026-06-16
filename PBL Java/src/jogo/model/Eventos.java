package jogo.model;

public abstract class Eventos {
    protected String descricao;

    public String getDescricao() {
        return descricao;
    }

    public Eventos(String descricao) {
        this.descricao = descricao;
    }

    public abstract String aplicarEvento(Jogador jogador);

}
