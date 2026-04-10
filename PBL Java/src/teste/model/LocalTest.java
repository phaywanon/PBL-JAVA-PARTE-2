package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocalTest {

    @Test
    void cantinaDeveTerNPC() {
        Local cantina = new LocalCantina();

        assertFalse(cantina.getPersonagensPresentes().isEmpty());
    }

    @Test
    void colegiadoDeveTerMaeli() {
        Local colegiado = new LocalColegiado();

        boolean temMaeli = colegiado.getPersonagensPresentes()
                .stream()
                .anyMatch(npc -> npc instanceof PersonagemMaeli);

        assertTrue(temMaeli);
    }
}