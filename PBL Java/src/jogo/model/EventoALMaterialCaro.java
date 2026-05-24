package jogo.model;

public class EventoALMaterialCaro extends EventosAleatorios {
    public EventoALMaterialCaro() {
        super("Material muito caro");
    }

    @Override
    public Class<? extends Local> getLocalPermitido() {
        return LocalLaboratorio.class;
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        jogador.setDinheiro(jogador.getDinheiro() - 50);
        jogador.setEnergia(jogador.getEnergia() - 10);
        jogador.setMotivacao(jogador.getMotivacao() - 35);
    }

    public String getMensagem() {
        return "Uma fan do seu computador quebrou e ele está superaquecendo!\n" +
                "Você terá que comprar outra para não ficar sem codar.";
    }
}

