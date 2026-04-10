package teste.model;

import jogo.model.Jogador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonagemTest {
    @Test
    void testeCriacaoPersonagemViaJogador() {
        Jogador j = new Jogador("Pedro");

        assertEquals("Pedro", j.getNome());
        assertEquals(100, j.getEnergia());
        assertEquals(100, j.getSaude());
    }
}