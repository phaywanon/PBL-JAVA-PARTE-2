package jogo.model;

public class EventoOBFimDeSemestre extends EventosObrigatorios {
    public EventoOBFimDeSemestre(){
        super("Fim de semestre");
    }

    @Override
    public void aplicarEvento(Jogador jogador){
        if (jogador.getProvasFeitas() < 3){
            System.out.println("Você ainda não fez todas as provas.");
            return;
        }
        double media = (jogador.getNotaAcumulada() / jogador.getProvasFeitas());
        System.out.printf("MÉDIA FINAL: %.2f%n", media);
        if (media >= 7){
            jogador.setProgresso(jogador.getProgresso() + 10);
            System.out.println("Semestre concluído! Progresso total: " + jogador.getProgresso() + "%");
            jogador.setMotivacao(jogador.getMotivacao() + 20);
        } else {
            System.out.println("Você reprovou no semestre.");
            jogador.setSaude(jogador.getSaude() - 30);
        }

        jogador.resetarSemestre();
    }
}

