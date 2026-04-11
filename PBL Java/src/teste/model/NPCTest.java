package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {

    @Test
    void maeliDeveAumentarDesempenho() {
        Mapa mapa = new Mapa();
        Jogador j = new Jogador("PH", mapa.getCasa());
        double desempenhoInicial = j.getDesempenhoAcademico();

        NPC maeli = new PersonagemMaeli();
        maeli.interagir(j);

        assertTrue(j.getDesempenhoAcademico() > desempenhoInicial);
    }
}