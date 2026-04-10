package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {

    @Test
    void maeliDeveAumentarDesempenho() {
        Jogador j = new Jogador("PH");
        double desempenhoInicial = j.getDesempenhoAcademico();

        NPC maeli = new PersonagemMaeli();
        maeli.interagir(j);

        assertTrue(j.getDesempenhoAcademico() > desempenhoInicial);
    }
}