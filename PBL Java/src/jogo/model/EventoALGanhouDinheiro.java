package jogo.model;

public class EventoALGanhouDinheiro extends EventosAleatorios {
    public EventoALGanhouDinheiro() {
        super("Ganhou dinheiro");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        System.out.println("Você achou 20 reais no chão!");

        jogador.setDinheiro(jogador.getDinheiro() + 20);
    }
}
