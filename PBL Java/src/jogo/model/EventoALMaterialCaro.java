package jogo.model;

public class EventoALMaterialCaro extends EventosAleatorios {
    public EventoALMaterialCaro() {
        super("Material muito caro");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        System.out.println("Uma fan do seu computador quebrou e ele está superaquecendo! " +
                "Você terá que comprar outra para não ficar sem codar.");

        jogador.setDinheiro(jogador.getDinheiro() - 50);
        jogador.setEnergia(jogador.getEnergia() - 10);
        jogador.setMotivacao(jogador.getMotivacao() - 35);
    }
}

