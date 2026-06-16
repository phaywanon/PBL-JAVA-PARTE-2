package jogo.model;
import java.util.Random;

public class EventoALProva extends EventosAleatorios {
    private int nivelProva;
    private double nota;
    private double requisito;
    private boolean aprovado;
    private double variacao;
    private Random random = new Random();

    public EventoALProva(int nivelProva) {
        super("Prova nível " + nivelProva);
        this.nivelProva = nivelProva;
    }

    @Override
    public Class<? extends Local> getLocalPermitido() {
        return LocalSalaDeAula.class;
    }


    @Override
    public String aplicarEvento(Jogador jogador) {
        double baseEsforco = Math.min(jogador.getConhecimentoSemestre(), 100);
        double baseConhecimento = (jogador.getNivelDeConhecimento() / 110.0) * 2;
        variacao = (random.nextDouble() * 2) - 1;

        double notaBruta = (baseEsforco / 100.0) * 8 + baseConhecimento;
        nota = Math.min(Math.round(notaBruta * 100.0) / 100.0, 10.0);
        if (nota < 0) nota = 0;

        jogador.adicionarNota(nota);

        requisito = switch (nivelProva) {
            case 1 -> 4.0;
            case 2 -> 6.0;
            case 3 -> 7.0;
            default -> 5.0;
        };

        aprovado = nota >= requisito;

        if (aprovado) {
            jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 5);
            jogador.setMotivacao(jogador.getMotivacao() + 5);
        } else {
            jogador.setSaude(jogador.getSaude() - 5);
            jogador.setMotivacao(jogador.getMotivacao() - 10);
        }

        return getMensagem(); // ← usa o texto que já existia
    }

    @Override
    public String getMensagem() {
        String resultado = aprovado
                ? "Você foi bem na prova surpresa!"
                : "A prova surpresa te pegou desprevenido...";

        return """
               Você teve uma PROVA SURPRESA!
               
               Nível da prova: %d
               Nota final: %.1f
               Nota mínima: %.1f
               
               %s
               """.formatted(
                nivelProva,
                nota,
                requisito,
                resultado
        );
    }
}