package jogo.model;

public class LocalPontoDeOnibus extends Local{
    public LocalPontoDeOnibus(){
        super("Ponto de ônibus da UEFS");
        adicionarNPC(new PersonagemBichinhos());
        adicionarNPC(new PersonagemBichinhos());
    }

    @Override
    public void eventoAoEntrar(Jogador jogador){
        System.out.println("Você chegou ao ponto de ônibus. A passagem custa 3 reais.");
    }

}
