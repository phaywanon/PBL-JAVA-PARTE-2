package jogo.model;

public class PersonagemColegas extends NPC{
    public PersonagemColegas(){
        super("Colegas", "COLEGAS");
    }

    @Override
    public String interagir(Jogador jogador) {
        jogador.setMotivacao(jogador.getMotivacao() + 3);
        jogador.setEnergia(jogador.getEnergia() + 2);
        jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 3);
        return "Seus colegas te explicam um conteúdo que você estava com dúvida!\n+3 Conhecimento do Semestre";
    }
}
