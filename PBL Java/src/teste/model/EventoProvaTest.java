package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventoProvaTest {

    @Test
    void aprovarQuandoNotaAlta() {
        Mapa mapa = new Mapa();
        Jogador j = new Jogador("PH", mapa.getCasa());
        j.setConhecimentoSemestre(50);
        j.setNivelDeConhecimento(5);

        EventoOBProva prova = new EventoOBProva(1);

        prova.aplicarEvento(j);

        assertTrue(j.getNotaAcumulada() > 0);
    }

    @Test
    void reprovarQuandoNotaBaixa() {
        Mapa mapa = new Mapa();
        Jogador j = new Jogador("PH", mapa.getCasa());
        j.setConhecimentoSemestre(0);
        j.setNivelDeConhecimento(0);

        EventoOBProva prova = new EventoOBProva(3);

        prova.aplicarEvento(j);

        assertTrue(j.getMotivacao() < 100);
    }
}

