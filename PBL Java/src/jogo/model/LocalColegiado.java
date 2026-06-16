package jogo.model;

public class LocalColegiado extends Local{
    public LocalColegiado(){
        super("Colegiado de ECOMP");
        adicionarNPC(new PersonagemMaeli());
    }

    @Override
    public String eventoAoEntrar(Jogador jogador){
        return"Você entrou no colegiado. Maeli é a secretária/amiga de todo estudante de ECOMP! " +
                "Ela sempre lhe dará dicas de como melhorar seu desempenho acadêmico.";
    }

}
