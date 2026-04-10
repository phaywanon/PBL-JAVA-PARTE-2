package jogo.model;

public class EventoALFilaGigante extends EventosAleatorios{
    public EventoALFilaGigante() {
        super("Fila gigante no bandejão");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        System.out.println("A fila do bandejão estava enorme e você acabou ficando tonto de tanta fome.");

        jogador.setSaude(jogador.getSaude() - 5);
        jogador.setEnergia(jogador.getEnergia() - 10);
        jogador.setMotivacao(jogador.getMotivacao() - 15);
    }
}

