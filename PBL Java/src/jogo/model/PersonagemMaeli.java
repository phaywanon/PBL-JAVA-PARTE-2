package jogo.model;

public class PersonagemMaeli extends NPC {

    public PersonagemMaeli (){
        super("Maeli", "SECRETÁRIA");
    }

    @Override
    public String interagir(Jogador jogador) {
        jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 2);
        return "Maeli: 'O Colegiado está aberto! Gostaria de alguma ajuda?";
    }
}
