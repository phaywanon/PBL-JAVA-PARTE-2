package jogo.model;

public class PersonagemMaeli extends NPC {

    public PersonagemMaeli (){
        super("Maeli", "SECRETÁRIA");
    }

    @Override
    public void interagir(Jogador jogador) {
        System.out.println("Maeli: 'O Colegiado está aberto! Gostaria de alguma ajuda?");
        jogador.setDesempenhoAcademico(jogador.getDesempenhoAcademico() + 2);
    }
}
