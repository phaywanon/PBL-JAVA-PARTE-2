package jogo.model;

public class EventoALFilaGigante extends EventosAleatorios{
    public EventoALFilaGigante() {
        super("Fila gigante no bandejão");
    }

    @Override
    public Class<? extends Local> getLocalPermitido() {
        return LocalCantina.class;
    }

    @Override
    public String aplicarEvento(Jogador jogador) {
        jogador.setSaude(jogador.getSaude() - 5);
        jogador.setEnergia(jogador.getEnergia() - 10);
        jogador.setMotivacao(jogador.getMotivacao() - 15);

        return getMensagem();
    }

    @Override
    public String getMensagem() {
        return "A fila do bandejão estava enorme e você acabou ficando tonto de tanta fome.\n-5 Saúde\n-10 Energia\n-15 Motivação";
    }

}

