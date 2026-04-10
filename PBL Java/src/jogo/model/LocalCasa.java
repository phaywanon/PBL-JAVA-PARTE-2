package jogo.model;

public class LocalCasa extends Local{
    public LocalCasa(){
        super("Casa");
    }

    @Override
    public void eventoAoEntrar(Jogador jogador){
        System.out.println("Você chegou em casa. Descanse para revigorar suas energias!");
    }

}
