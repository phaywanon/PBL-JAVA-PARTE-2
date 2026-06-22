package jogo.controller;

public class AcaoDisponivel {
    private final String label;
    private final java.util.function.Supplier<String> acao;

    public AcaoDisponivel(String label, java.util.function.Supplier<String> acao) {
        this.label = label;
        this.acao = acao;
    }

    public String getLabel() {
        return label;
    }

    public String executar() {
        return acao.get();
    }
}