package jogo.model;

public class LocalSalaDeAula extends Local{
    public LocalSalaDeAula(){
        super("Sala de Aula");
        adicionarNPC(new PersonagemProfessora());
    }

    @Override
    public String eventoAoEntrar(Jogador jogador){
        return "Você chegou na sala de aula. A professora está esperando em frente ao quadro.";
    }
}
