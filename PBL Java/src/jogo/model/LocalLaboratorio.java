package jogo.model;

public class LocalLaboratorio extends Local {
    public LocalLaboratorio(){
        super("Laboratório");
        adicionarNPC(new PersonagemProfessor());
    }

    @Override
    public void eventoAoEntrar(Jogador jogador){
        System.out.println("Você chegou ao laboratório. O professor está esperando em frente ao quadro.");
    }

}
