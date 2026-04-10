package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventoAleatorioTest {

    @Test
    void eventoFestaDeveAlterarMotivacao() {
        Jogador j = new Jogador("PH");
        int motivacaoInicial = j.getMotivacao();

        EventosAleatorios evento = new EventoALFesta();
        evento.aplicarEvento(j);

        assertTrue(j.getMotivacao() > motivacaoInicial);
    }

    @Test
    void eventoDoencaDeveReduzirSaude() {
        Jogador j = new Jogador("PH");
        int saudeInicial = j.getSaude();

        EventosAleatorios evento = new EventoALDoente();
        evento.aplicarEvento(j);

        assertTrue(j.getSaude() < saudeInicial);
    }


}