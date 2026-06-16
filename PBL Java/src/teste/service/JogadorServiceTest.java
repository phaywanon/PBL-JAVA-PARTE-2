//package teste.service;
//import jogo.model.*;
//import jogo.service.JogadorService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class JogadorServiceTest {
//    private Jogador jogador;
//    private JogadorService service;
//    private Mapa mapa;
//
//    @BeforeEach
//    void setUp() {
//        mapa = new Mapa();
//        jogador = new Jogador("Teste", mapa.getCasa());
//        service = new JogadorService(jogador);
//    }
//
//    // ===== ESTUDAR =====
//
//    @Test
//    void estudarDeveAumentarConhecimentoComMotivacaoSuficiente() {
//        jogador.setMotivacao(50);
//        jogador.setEnergia(50);
//        double conhecimentoAntes = jogador.getNivelDeConhecimento();
//        service.estudar();
//        assertTrue(jogador.getNivelDeConhecimento() > conhecimentoAntes);
//    }
//
//    @Test
//    void estudarDeveConsumiEnergia() {
//        jogador.setMotivacao(50);
//        jogador.setEnergia(50);
//        service.estudar();
//        assertTrue(jogador.getEnergia() < 50);
//    }
//
//    @Test
//    void estudarNaoDeveExecutarComMotivacaoBaixa() {
//        jogador.setMotivacao(5);
//        jogador.setEnergia(50);
//        double conhecimentoAntes = jogador.getNivelDeConhecimento();
//        service.estudar();
//        assertEquals(conhecimentoAntes, jogador.getNivelDeConhecimento());
//    }
//
//    @Test
//    void estudarNaoDeveExecutarComEnergiaBaixa() {
//        jogador.setMotivacao(50);
//        jogador.setEnergia(5);
//        double conhecimentoAntes = jogador.getNivelDeConhecimento();
//        service.estudar();
//        assertEquals(conhecimentoAntes, jogador.getNivelDeConhecimento());
//    }
//
//    // ===== LANCHAR =====
//
//    @Test
//    void lancharDeveConsumiDinheiro() {
//        jogador.mudarLocal(mapa.getCantina());
//        jogador.setDinheiro(20.0);
//        service.lanchar(mapa);
//        assertEquals(15.0, jogador.getDinheiro());
//    }
//
//    @Test
//    void lancharDeveAumentarEnergia() {
//        jogador.mudarLocal(mapa.getCantina());
//        jogador.setDinheiro(20.0);
//        jogador.setEnergia(50);
//        service.lanchar(mapa);
//        assertTrue(jogador.getEnergia() > 50);
//    }
//
//    @Test
//    void lancharNaoDeveExecutarForaDaCantina() {
//        jogador.mudarLocal(mapa.getSalaDeAula());
//        jogador.setDinheiro(20.0);
//        double dinheiroAntes = jogador.getDinheiro();
//        service.lanchar(mapa);
//        assertEquals(dinheiroAntes, jogador.getDinheiro());
//    }
//
//    @Test
//    void lancharNaoDeveExecutarSemDinheiro() {
//        jogador.mudarLocal(mapa.getCantina());
//        jogador.setDinheiro(0.0);
//        int energiaAntes = jogador.getEnergia();
//        service.lanchar(mapa);
//        assertEquals(energiaAntes, jogador.getEnergia());
//    }
//
//    // ===== IR PARA CASA =====
//
//    @Test
//    void irParaCasaDeveRetornarTrueQuandoForaCasa() {
//        jogador.mudarLocal(mapa.getPontoDeOnibus());
//        jogador.setDinheiro(10.0);
//        assertTrue(service.irParaCasa(mapa));
//    }
//
//    @Test
//    void irParaCasaDeveRetornarFalseQuandoJaEstaCasa() {
//        // jogador já começa em casa
//        assertFalse(service.irParaCasa(mapa));
//    }
//
//    @Test
//    void irParaCasaSemDinheiroDeveConsumiEnergia() {
//        jogador.mudarLocal(mapa.getPontoDeOnibus());
//        jogador.setDinheiro(0.0);
//        jogador.setEnergia(90);
//        service.irParaCasa(mapa);
//        // energia vai para 90 (dorme) mas perdeu 15 antes — depois seta 90
//        // o que verificamos é que chegou em casa
//        assertTrue(jogador.getLocal() instanceof LocalCasa);
//    }
//
//    // ===== CURSAR DISCIPLINA =====
//
//    @Test
//    void cursarDisciplinaDeveAumentarConhecimentoNaSala() {
//        jogador.mudarLocal(mapa.getSalaDeAula());
//        jogador.setEnergia(80);
//        int conhecimentoAntes = jogador.getConhecimentoSemestre();
//        service.cursarDisciplina();
//        assertTrue(jogador.getConhecimentoSemestre() > conhecimentoAntes);
//    }
//
//    @Test
//    void cursarDisciplinaNaoDeveExecutarForaDaAula() {
//        jogador.mudarLocal(mapa.getCantina());
//        jogador.setEnergia(80);
//        int conhecimentoAntes = jogador.getConhecimentoSemestre();
//        service.cursarDisciplina();
//        assertEquals(conhecimentoAntes, jogador.getConhecimentoSemestre());
//    }
//
//    @Test
//    void cursarDisciplinaNaoDeveExecutarSemEnergia() {
//        jogador.mudarLocal(mapa.getSalaDeAula());
//        jogador.setEnergia(10);
//        int conhecimentoAntes = jogador.getConhecimentoSemestre();
//        service.cursarDisciplina();
//        assertEquals(conhecimentoAntes, jogador.getConhecimentoSemestre());
//    }
//
//    // ===== TRABALHAR =====
//
//    @Test
//    void trabalharDeveAdicionarDinheiro() {
//        jogador.mudarLocal(mapa.getLaboratorio());
//        jogador.setEnergia(80);
//        double dinheiroAntes = jogador.getDinheiro();
//        service.trabalhar();
//        assertTrue(jogador.getDinheiro() > dinheiroAntes);
//    }
//
//    @Test
//    void trabalharNaoDeveExecutarForaDoLab() {
//        jogador.mudarLocal(mapa.getCantina());
//        jogador.setEnergia(80);
//        double dinheiroAntes = jogador.getDinheiro();
//        service.trabalhar();
//        assertEquals(dinheiroAntes, jogador.getDinheiro());
//    }
//}
//
//
//
//
