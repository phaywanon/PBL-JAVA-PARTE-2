package jogo.model;

public class EventoALGreve extends EventosAleatorios {
    public EventoALGreve() {
        super("Greve");
    }

    @Override
    public String aplicarEvento(Jogador jogador) {
        jogador.setEnergia(jogador.getEnergia() + 10);
        jogador.setMotivacao(jogador.getMotivacao() - 10);
        jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() - 5);
        jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() - 10);
        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() - 1);

        return getMensagem();
    }

    public String getMensagem() {
        return "Os estudantes resolveram fechar o pórtico por conta dos preços abusivos dos ônibus em Feira de Santana. Apoie a causa!\n+10 Energia \n-10 Motivação \n-5 Desempenho Acadêmico \n -10 Conhecimento do Semesntre \n-1 Nível de Conhecimento";
    }
}