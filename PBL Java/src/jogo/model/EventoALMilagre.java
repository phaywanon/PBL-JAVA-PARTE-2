package jogo.model;

public class EventoALMilagre extends EventosAleatorios {
    public EventoALMilagre() {
        super("Milagre acadêmico");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        System.out.println("Milagre acadêmico! 10 minutos antes da prova, você gravou exatamente as fórmulas que cairam na prova");

        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 2);
        jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 20);
        jogador.setSaude(jogador.getSaude() + 5);
        jogador.setEnergia(jogador.getEnergia() + 10);
        jogador.setMotivacao(jogador.getMotivacao() + 15);
    }
}

