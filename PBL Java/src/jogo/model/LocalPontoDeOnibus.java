package jogo.model;

public class LocalPontoDeOnibus extends Local{
    public LocalPontoDeOnibus(){
        super("Ponto de ônibus da UEFS");
        adicionarNPC(new PersonagemBichinhos());
    }

    @Override
    public String eventoAoEntrar(Jogador jogador){
        return "Você chegou ao ponto de ônibus. A passagem custa 3 reais.\n Há uns cachorrinhos fofos por aqui! Use 'Interagir com NPC' para fazer carinho.";
    }

}
