package jogo.model;

public class LocalDA extends Local{
    public LocalDA(){
        super("DA de ECOMP");
    }

    @Override
    public void eventoAoEntrar(Jogador jogador){
        System.out.println("Você chegou no DA de ECOMP! Aproveite para descansar um pouco.");
    }
}
