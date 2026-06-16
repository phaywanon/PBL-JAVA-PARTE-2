package jogo.model;

public class EventoOBProva extends EventosObrigatorios {
    private int nivelProva;

    public EventoOBProva(int nivelProva) {
        super("Prova nível " + nivelProva);
        this.nivelProva = nivelProva;
    }

    @Override
    public String aplicarEvento(Jogador jogador) {
        String msg = "📝 Prova nível " + nivelProva + " acontecendo!\n";

        if (!jogador.isFoiParaSalaHoje()) {
            jogador.adicionarNota(0);
            jogador.setMotivacao(jogador.getMotivacao() - 15);
            jogador.setSaude(jogador.getSaude() - 10);
            return msg + "⛔ Você perdeu a prova por não estar na sala!";
        }

        double baseEsforco     = Math.min(jogador.getConhecimentoSemestre(), 100);
        double baseConhecimento = (jogador.getNivelDeConhecimento() / 110.0) * 2;
        double notaBruta        = (baseEsforco / 100.0) * 8 + baseConhecimento;
        double nota             = Math.min(Math.round(notaBruta * 100.0) / 100.0, 10.0);

        jogador.adicionarNota(nota);

        double requisito = switch (nivelProva) {
            case 1 -> 4.0;
            case 2 -> 6.0;
            case 3 -> 7.0;
            default -> 5.0;
        };

        msg += String.format("Nota: %.2f | Requisito: %.1f\n", nota, requisito);

        if (nota >= requisito) {
            jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 5);
            jogador.setMotivacao(jogador.getMotivacao() + 5);
            msg += "✅ Aprovado!";
        } else {
            jogador.setSaude(jogador.getSaude() - 5);
            jogador.setMotivacao(jogador.getMotivacao() - 10);
            msg += "❌ Reprovado.";
        }

        return msg;
    }
}