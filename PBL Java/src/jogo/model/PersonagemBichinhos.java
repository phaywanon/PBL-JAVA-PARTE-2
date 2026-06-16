package jogo.model;

public class PersonagemBichinhos extends NPC {
    public PersonagemBichinhos(){
        super("Bichinho", "BICHINHO");
    }

    @Override
    public String interagir(Jogador jogador) {
        jogador.setMotivacao(jogador.getMotivacao() + 5);
        jogador.setEnergia(jogador.getEnergia() - 1);
        return "Você fez carinho num bichinho do campus e se sentiu mais animado! 🐱";
    }
}
