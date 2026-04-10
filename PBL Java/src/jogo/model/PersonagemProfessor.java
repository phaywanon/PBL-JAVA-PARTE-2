package jogo.model;

public class PersonagemProfessor extends NPC {
    public PersonagemProfessor(){
        super("Professor", "PROFESSOR");
    }

    @Override
    public void interagir(Jogador jogador){
        System.out.println("Você tirou dúvidas com a Professora!");
        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 3);
        jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 2);

    }
}
