package jogo.model;

public class EventoALFesta extends EventosAleatorios{
    public EventoALFesta(){
        super("Calourada");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        System.out.println("Teve uma calourada no campus e você se divertiu bastante!");

        jogador.setMotivacao(jogador.getMotivacao() + 20);
        jogador.setEnergia(jogador.getEnergia() - 10);
    }
}
