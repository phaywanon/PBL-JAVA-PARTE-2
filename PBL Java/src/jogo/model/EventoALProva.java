package jogo.model;
import java.util.Random;

public class EventoALProva extends EventosAleatorios {

    private int nivelProva;
    private Random random = new Random();

    public EventoALProva(int nivelProva) {
        super("Prova nível " + nivelProva);
        this.nivelProva = nivelProva;
    }

    @Override
    public void aplicarEvento(Jogador jogador) {

        // base do jogador
        double base = jogador.getConhecimentoSemestre()
                + (jogador.getNivelDeConhecimento() * 10);

        // fator aleatório (-10 até +10)
        int variacao = random.nextInt(21) - 10;

        double nota = base + variacao;

        double requisito;
        if (nivelProva == 1) {
            requisito = 40;
        } else if (nivelProva == 2) {
            requisito = 70;
        } else {
            requisito = 90;
        }

        System.out.println("Base: " + base);
        System.out.println("Variação: " + variacao);
        System.out.println("Nota final: " + nota);

        jogador.adicionarNota(nota);

        if (nota >= requisito) {
            System.out.println("Aprovado!");
            jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 5);
            jogador.setMotivacao(jogador.getMotivacao() + 5);
        } else {
            System.out.println("Reprovado...");
            jogador.setSaude(jogador.getSaude() - 5);
            jogador.setMotivacao(jogador.getMotivacao() - 10);
        }
    }
}
