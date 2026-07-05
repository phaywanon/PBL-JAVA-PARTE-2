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
    public String aplicarEvento(Jogador jogador) {
        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 2);
        jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 20);
        jogador.setSaude(jogador.getSaude() + 5);
        jogador.setEnergia(jogador.getEnergia() + 10);
        jogador.setMotivacao(jogador.getMotivacao() + 15);

        return getMensagem();
    }

    public String getMensagem() {
        return "Milagre acadêmico! Você leu apenas um parágrafo e entendeu TODO o assunto da prova.\n+2 Nível de Conhecimento\n+20 Conhecimento do Semestre\n+5 Saúde\n+10 Energia\n+15 Motivação";
    }
}

