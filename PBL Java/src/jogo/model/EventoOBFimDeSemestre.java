package jogo.model;

public class EventoOBFimDeSemestre extends EventosObrigatorios {
    public EventoOBFimDeSemestre(){
        super("Fim de semestre");
    }

    @Override
    public String aplicarEvento(Jogador jogador) {
        if (jogador.getProvasFeitas() < 3) {
            return "Você ainda não fez todas as provas.";
        }

        String msg = "";
        double media = jogador.getNotaAcumulada() / jogador.getProvasFeitas();
        msg += String.format("MÉDIA FINAL: %.2f\n", media);

        if (media >= 7) {
            jogador.setProgresso(jogador.getProgresso() + 10);
            jogador.setMotivacao(jogador.getMotivacao() + 20);
            msg += "✅ Semestre concluído! Progresso total: " + jogador.getProgresso() + "%";
        } else {
            jogador.setSaude(jogador.getSaude() - 30);
            msg += "❌ Você reprovou no semestre.";
        }

        jogador.resetarSemestre();
        return msg;
    }
}

