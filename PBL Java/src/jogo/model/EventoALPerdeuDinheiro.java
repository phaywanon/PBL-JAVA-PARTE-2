package jogo.model;

public class EventoALPerdeuDinheiro extends EventosAleatorios {
    public EventoALPerdeuDinheiro() {
        super("Perdeu dinheiro");
    }

    @Override
    public String aplicarEvento(Jogador jogador) {
        jogador.setDinheiro(jogador.getDinheiro() - 10);

        return getMensagem();
    }

    public String getMensagem() {
        return "Sua calça estava com o bolso furado e você perdeu 10 reais.";
    }
}

