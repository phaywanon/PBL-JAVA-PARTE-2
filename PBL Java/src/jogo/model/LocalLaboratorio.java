package jogo.model;

public class LocalLaboratorio extends Local {
    public LocalLaboratorio(){
        super("Laboratório");
        adicionarNPC(new PersonagemProfessor());
    }

    @Override
    public String eventoAoEntrar(Jogador jogador){
        return "Você chegou ao laboratório. Aqui você pode trabalhar como monitor. Por cada trabalho que fizer, ganhará 20 reais.";
    }

}
