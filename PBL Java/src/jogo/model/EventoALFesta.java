package jogo.model;

public class EventoALFesta extends EventosAleatorios{
    public EventoALFesta(){
        super("Calourada");
    }

    @Override
    public Class<? extends Local> getLocalPermitido() {
        return LocalCantina.class;
    }


    @Override
    public String aplicarEvento(Jogador jogador) {
        jogador.setMotivacao(jogador.getMotivacao() + 20);
        jogador.setEnergia(jogador.getEnergia() - 10);

        return getMensagem();
    }

    public String getMensagem() {
        return "Teve uma calourada no campus e você se divertiu bastante!";
    }

}
