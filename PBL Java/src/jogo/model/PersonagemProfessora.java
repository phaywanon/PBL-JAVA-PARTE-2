package jogo.model;

public class PersonagemProfessora extends NPC {
    public PersonagemProfessora(){
        super("Professora", "PROFESSORA");
    }

    @Override
    public String interagir(Jogador jogador){
        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 3);
        jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 2);
        return "Você tirou dúvidas com a Professora!\n+3 Nível de Conhecimento\n+2 Desempenho Acadêmico";
    }
}
