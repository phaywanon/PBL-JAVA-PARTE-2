package jogo.model;

public class LocalDA extends Local{
    public LocalDA(){
        super("DA de ECOMP");
        adicionarNPC(new PersonagemColegas());
    }

    @Override
    public String eventoAoEntrar(Jogador jogador){
        return "Você chegou no DA de ECOMP! Aproveite para descansar um pouco.";
    }
}
