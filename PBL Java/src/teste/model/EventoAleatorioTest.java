//package teste.model;
//
//import jogo.model.*;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class EventoAleatorioTest {
//
//    @Test
//    void eventoFestaDeveAlterarMotivacao() {
//        Mapa mapa = new Mapa();
//        Jogador j = new Jogador("PH", mapa.getCasa());
//        int motivacaoInicial = j.getMotivacao();
//
//        EventosAleatorios evento = new EventoALFesta();
//        evento.aplicarEvento(j);
//
//        assertTrue(j.getMotivacao() > motivacaoInicial);
//    }
//
//    @Test
//    void eventoDoencaDeveReduzirSaude() {
//        Mapa mapa = new Mapa();
//        Jogador j = new Jogador("PH", mapa.getCasa());
//        int saudeInicial = j.getSaude();
//
//        EventosAleatorios evento = new EventoALDoente();
//        evento.aplicarEvento(j);
//
//        assertTrue(j.getSaude() < saudeInicial);
//    }
//
//    @Test
//    void eventoMilagreDeveAumentarConhecimento() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        int conhecimentoAntes = jogador.getConhecimentoSemestre();
//
//        new EventoALMilagre().aplicarEvento(jogador);
//
//        assertTrue(jogador.getConhecimentoSemestre() > conhecimentoAntes);
//    }
//
//    @Test
//    void eventoPerdeuDinheiroDeveReduzirDinheiro() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setDinheiro(50);
//
//        new EventoALPerdeuDinheiro().aplicarEvento(jogador);
//
//        assertEquals(40, jogador.getDinheiro());
//    }
//
//    @Test
//    void eventoGreveDeveReduzirDesempenho() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        double desempenhoAntes = jogador.getDesempenhoAcademico();
//
//        new EventoALGreve().aplicarEvento(jogador);
//
//        assertTrue(jogador.getDesempenhoAcademico() <= desempenhoAntes);
//    }
//
//    @Test
//    void eventoFilaGiganteDeveReduzirEnergia() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        int energiaAntes = jogador.getEnergia();
//
//        new EventoALFilaGigante().aplicarEvento(jogador);
//
//        assertTrue(jogador.getEnergia() < energiaAntes);
//    }
//
//    @Test
//    void aplicarEventoDeveReduzirAtributosCorretamente() {
//        Mapa mapa = new Mapa();
//        Jogador j = new Jogador("PH", mapa.getCasa());
//
//        j.setDinheiro(100);
//        j.setEnergia(80);
//        j.setMotivacao(70);
//
//        EventoALMaterialCaro evento = new EventoALMaterialCaro();
//
//        evento.aplicarEvento(j);
//
//        assertEquals(50, j.getDinheiro());   // 100 - 50
//        assertEquals(70, j.getEnergia());    // 80 - 10
//        assertEquals(35, j.getMotivacao());  // 70 - 35
//    }
//}
