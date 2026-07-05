package jogo.model;

import java.util.Random;

public class PersonagemMaeli extends NPC {
    private Random random = new Random();

    private String[] dicas = {
            "Dica da Maeli: não deixe para ir à Sala de Aula no dia da prova. Estudar ao longo do semestre melhora muito suas notas.",
            "Dica da Maeli: fique atento aos dias 7, 14 e 20 do semestre. São os dias de prova.",
            "Dica da Maeli: se sua energia estiver baixa, voltar para casa pode ser melhor do que insistir em estudar cansado.",
            "Dica da Maeli: a Cantina ajuda a recuperar energia e motivação, mas lembre-se de guardar dinheiro para o ônibus.",
            "Dica da Maeli: o Laboratório é ótimo para trabalhar e ganhar dinheiro, mas também consome bastante energia.",
            "Dica da Maeli: conversar com NPCs pode melhorar seus atributos. Nem tudo se resolve só estudando.",
            "Dica da Maeli: se amanhã tiver prova, organize seu dia para passar pela Sala de Aula antes de dormir.",
            "Dica da Maeli: o DA de ECOMP pode recuperar sua motivação, mas cuidado para não gastar tudo no dominó.",
            "Dica da Maeli: manter equilíbrio entre energia, motivação e conhecimento é mais importante do que focar em um único atributo.",
            "Dica da Maeli: salvar o jogo antes de tomar decisões importantes nunca é má ideia."
    };

    public PersonagemMaeli (){
        super("Maeli", "SECRETÁRIA");
    }

    @Override
    public String interagir(Jogador jogador) {
        jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 2);
        String dica = dicas[random.nextInt(dicas.length)];
        return """
                
                %s
                
                +2 Desempenho Acadêmico
                """.formatted(dica);
    }
}