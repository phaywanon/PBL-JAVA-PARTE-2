package jogo.model;

public class LocalLaboratorio extends Local {
    public LocalLaboratorio(){
        super("Laboratório");
        adicionarNPC(new PersonagemProfessor());
    }

    @Override
    public void eventoAoEntrar(Jogador jogador){
        System.out.println("Você chegou ao laboratório. Aqui você pode trabalhar como monitor. Por cada trabalho que fizer, ganhará 20 reais.");
    }

}
