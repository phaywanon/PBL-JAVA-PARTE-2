package jogo.model;

public class LocalCantina extends Local{
    public LocalCantina(){
        super("Cantina");
        adicionarNPC(new PersonagemColegas());
    }

    @Override
    public String eventoAoEntrar(Jogador jogador){
        return "Você está na cantina. Um grupo de colegas seus estão estudando e conversando nas mesas. Deseja se aproximar deles?\nNa cantina, você também pode comprar lanches para se revigorar. Um lanche custa 5 reais.";
    }
}
