package jogo.model;

public class PersonagemProfessor extends NPC {
    public PersonagemProfessor(){
        super("Professor", "PROFESSOR");
    }

    @Override
    public String interagir(Jogador jogador){
        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 3);
        jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 2);
        return "Você tirou dúvidas com a Professora!";
    }
}
