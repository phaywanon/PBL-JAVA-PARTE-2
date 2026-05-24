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

        if ((!jogador.isFoiParaSalaHoje())){
            System.out.println("⛔ Você perdeu a prova por não estar na sala!");

            jogador.adicionarNota(0); // zerou
            jogador.setMotivacao(jogador.getMotivacao() - 15);
            jogador.setSaude(jogador.getSaude() - 10);

            return;
        }

        // conhecimentoSemestre tem teto de 100 pontos
        double baseEsforco = Math.min(jogador.getConhecimentoSemestre(), 100);
        // nivelDeConhecimento vai de 0 a 110, normaliza pra 0-2 (bônus)
        double baseConhecimento = (jogador.getNivelDeConhecimento() / 110.0) * 2;

        // nota final de 0 a 10, com 2 casas decimais
        double notaBruta = (baseEsforco / 100.0) * 8 + baseConhecimento;
        double nota = Math.min(Math.round(notaBruta * 100.0) / 100.0, 10.0);

        jogador.adicionarNota(nota);

        double requisito = switch (nivelProva) {
            case 1 -> 4.0;
            case 2 -> 6.0;
            case 3 -> 7.0;
            default -> 5.0;
        };

        System.out.println("==========================================");
        System.out.printf("Nota: %.2f | Requisito: %.1f%n", nota, requisito);

        if (nota >= requisito) {
            System.out.println("✅ Aprovado!");
            jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 5);
            jogador.setMotivacao(jogador.getMotivacao() + 5);
        } else {
            System.out.println("❌ Reprovado.");
            jogador.setSaude(jogador.getSaude() - 5);
            jogador.setMotivacao(jogador.getMotivacao() - 10);
        }
    }
}