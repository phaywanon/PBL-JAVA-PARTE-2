package jogo.model;

public class EventoOBProva extends EventosObrigatorios {
    private int nivelProva;

    public EventoOBProva(int nivelProva) {
        super("Prova nível " + nivelProva);
        this.nivelProva = nivelProva;
    }

    @Override
    public void aplicarEvento (Jogador jogador){
        System.out.println("📝 Prova nível " + nivelProva + " acontecendo!");

        if (!(jogador.isFoiParaSalaHoje())){
            System.out.println("⛔ Você perdeu a prova por não estar na sala!");

            jogador.adicionarNota(0); // zerou
            jogador.setMotivacao(jogador.getMotivacao() - 15);
            jogador.setSaude(jogador.getSaude() - 10);

            return;
        }

        double nota = jogador.getConhecimentoSemestre() + (jogador.getNivelDeConhecimento()* 10);
        jogador.adicionarNota(nota);
        double requisito = 0.0;

        if (nivelProva == 1) {
            requisito = 40;
        } else if (nivelProva == 2) {
            requisito = 70;
        } else if (nivelProva == 3) {
            requisito = 90;
        }

        System.out.println("=========================================================================================");
        System.out.println("Nota: " + nota);

        if (nota >= requisito) {
            System.out.println("Aprovado!");
            jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 5);
            jogador.setMotivacao(jogador.getMotivacao() + 5);
        } else {
            System.out.println("Reprovado.");
            jogador.setSaude(jogador.getSaude() - 5);
            jogador.setMotivacao(jogador.getMotivacao() - 10);
        }
    }
}

