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
    public void aplicarEvento(Jogador jogador) {
        jogador.setDinheiro(jogador.getDinheiro() + 20);
    }

    @Override
    public String getMensagem() {
        return "Você achou 20 reais no chão!";
    }
}
