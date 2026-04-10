package jogo.model;

public class EventoALPerdeuDinheiro extends EventosAleatorios {
    public EventoALPerdeuDinheiro() {
        super("Perdeu dinheiro");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        System.out.println("Sua calça estava com o bolso furado e você perdeu 10 reais.");

        jogador.setDinheiro(jogador.getDinheiro() - 10);
    }
}

