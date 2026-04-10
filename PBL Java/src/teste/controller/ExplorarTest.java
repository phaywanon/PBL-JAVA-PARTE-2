package teste.controller;

import jogo.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExplorarTest {

    @Test
    void naoDeveExplorarComEnergiaBaixa() {
        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
        jogador.setEnergia(5);

        assertFalse(jogador.podeExplorar());
    }

    @Test
    void devePoderExplorarComEnergiaAlta() {
        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
        jogador.setEnergia(25);

        assertTrue(jogador.podeExplorar());
    }
}