package teste.model;

import jogo.model.Jogador;
import jogo.model.Mapa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonagemTest {
    @Test
    void testeCriacaoPersonagemViaJogador() {
        Mapa mapa = new Mapa();
        Jogador j = new Jogador("PH", mapa.getCasa());

        assertEquals("PH", j.getNome());
        assertEquals(100, j.getEnergia());
        assertEquals(100, j.getSaude());
    }
}