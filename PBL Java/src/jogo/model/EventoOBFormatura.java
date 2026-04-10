package jogo.model;

public class EventoOBFormatura extends EventosObrigatorios {
    public EventoOBFormatura(){
        super("FORMATURA");
    }

    @Override
    public void aplicarEvento(Jogador jogador) {
        if (jogador.getProgresso() >= 100){
            System.out.println("PARABÉNS! VOCÊ É O MAIS NOVO FORMANDO EM ENGENHARIA DE COMPUTAÇÃO DA UNIVERSIDADE ESTADUAL DE FEIRA DE SANATANA!");
            jogador.setFormado(true);
        }
    }
}
