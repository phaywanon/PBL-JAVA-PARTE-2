package jogo.model;

public class EventoALMilagre extends EventosAleatorios {
    public EventoALMilagre() {
        super("Milagre acadêmico");
    }

    @Override
    public Class<? extends Local> getLocalPermitido() {
        return LocalSalaDeAula.class;
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 2);
        jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 20);
        jogador.setSaude(jogador.getSaude() + 5);
        jogador.setEnergia(jogador.getEnergia() + 10);
        jogador.setMotivacao(jogador.getMotivacao() + 15);
    }

    public String getMensagem() {
        return "Milagre acadêmico! Você leu apenas um parágrafo e entendeu TODO o assunto da prova.";
    }
}

