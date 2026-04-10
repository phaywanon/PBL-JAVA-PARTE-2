package jogo.model;

public class LocalCantina extends Local{
    public LocalCantina(){
        super("Cantina");
        adicionarNPC(new PersonagemColegas());
    }

    @Override
    public void eventoAoEntrar(Jogador jogador){
        System.out.println("Você está na cantina. Um grupo de colegas seus estão estudando e conversando nas mesas. Deseja se aproximar deles?" +
                " Na cantina, você também pode comprar lanches para se revigorar. Um lanche custa 5 reais.");
    }
}
