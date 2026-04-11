package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventoAleatorioTest {

    @Test
    void eventoFestaDeveAlterarMotivacao() {
        Mapa mapa = new Mapa();
        Jogador j = new Jogador("PH", mapa.getCasa());
        int motivacaoInicial = j.getMotivacao();

        EventosAleatorios evento = new EventoALFesta();
        evento.aplicarEvento(j);

        assertTrue(j.getMotivacao() > motivacaoInicial);
    }

    @Test
    void eventoDoencaDeveReduzirSaude() {
        Mapa mapa = new Mapa();
        Jogador j = new Jogador("PH", mapa.getCasa());
        int saudeInicial = j.getSaude();

        EventosAleatorios evento = new EventoALDoente();
        evento.aplicarEvento(j);

        assertTrue(j.getSaude() < saudeInicial);
    }


}