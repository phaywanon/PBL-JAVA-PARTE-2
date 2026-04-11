package jogo.model;

public class PersonagemColegas extends NPC{
    public PersonagemColegas(){
        super("Colegas", "COLEGAS");
    }

    @Override
    public void interagir(Jogador jogador) {
        System.out.println("Seus colegas te explicam um conteúdo que você estava com dúvida!");
        jogador.setMotivacao(jogador.getMotivacao() + 3);
        jogador.setEnergia(jogador.getEnergia() + 2);
        jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 3);
    }
}
