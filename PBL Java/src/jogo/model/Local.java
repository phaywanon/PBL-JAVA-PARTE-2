package jogo.model;
import java.util.ArrayList;
import java.util.List;

public abstract class Local {
    private String nome;
    private List<NPC> personagensPresentes; // Lista de quem está lá

    public Local(String nome) {
        this.nome = nome;
        this.personagensPresentes = new ArrayList<>();
    }

    // Método que cada local implementa do seu jeito
    public abstract String eventoAoEntrar(Jogador jogador);

    public void adicionarNPC(NPC npc) {
        personagensPresentes.add(npc);
    }

    public List<NPC> getPersonagensPresentes() {
        return personagensPresentes;
    }

    public String getNomeLocal() {
        return nome;
    }
}