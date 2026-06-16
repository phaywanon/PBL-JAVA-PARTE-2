package jogo.model;

public class LocalCasa extends Local{
    public LocalCasa(){
        super("Casa");
    }

    @Override
    public String eventoAoEntrar(Jogador jogador){
        return "Você chegou em casa. Descanse para revigorar suas energias!";
    }

}
