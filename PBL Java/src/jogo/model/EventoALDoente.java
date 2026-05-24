package jogo.model;

public class EventoALDoente extends EventosAleatorios{
    public EventoALDoente() {
        super("Doença");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        jogador.setSaude(jogador.getSaude() - 20);
        jogador.setEnergia(jogador.getEnergia() - 10);
        jogador.setMotivacao(jogador.getMotivacao() - 10);
    }

    @Override
    public String getMensagem() {
        return "Você comeu algo estragado no bandejão e ficou doente...";
    }
}

