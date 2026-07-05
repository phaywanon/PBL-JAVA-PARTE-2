package jogo.model;

public class EventoALGanhouDinheiro extends EventosAleatorios {
    public EventoALGanhouDinheiro() {
        super("Ganhou dinheiro");
    }

    @Override
    public Class<? extends Local> getLocalPermitido() {
        return LocalCantina.class;
    }

    @Override
    public String aplicarEvento(Jogador jogador) {
        jogador.setDinheiro(jogador.getDinheiro() + 20);

        return getMensagem();
    }

    @Override
    public String getMensagem() {
        return "Você achou 20 reais no chão!\n+20 Dinheiro";
    }
}
