package jogo.model;

public class EventoOBFormatura extends EventosObrigatorios {
    public EventoOBFormatura(){
        super("FORMATURA");
    }

    @Override
    public String aplicarEvento(Jogador jogador) {
        if (jogador.getProgresso() >= 100){
            jogador.setFormado(true);
        }
        return "PARABÉNS! VOCÊ É O MAIS NOVO FORMANDO EM ENGENHARIA DE COMPUTAÇÃO DA UNIVERSIDADE ESTADUAL DE FEIRA DE SANTANA!";
    }
}
