//package teste.model;
//
//import jogo.model.*;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class EventoFimDeSemestreTest {
//
//    @Test
//    void passarNoSemestre() {
//        Mapa mapa = new Mapa();
//        Jogador j = new Jogador("PH", mapa.getCasa());
//
//        j.adicionarNota(80);
//        j.adicionarNota(70);
//        j.adicionarNota(90);
//
//        EventoOBFimDeSemestre evento = new EventoOBFimDeSemestre();
//
//        evento.aplicarEvento(j);
//
//        assertTrue(j.getProgresso() > 0);
//    }
//
//    @Test
//    void reprovarNoSemestre() {
//        Mapa mapa = new Mapa();
//        Jogador j = new Jogador("PH", mapa.getCasa());
//
//        j.adicionarNota(10);
//        j.adicionarNota(20);
//        j.adicionarNota(30);
//
//        EventoOBFimDeSemestre evento = new EventoOBFimDeSemestre();
//
//        evento.aplicarEvento(j);
//
//        assertTrue(j.getSaude() < 100);
//    }
//}