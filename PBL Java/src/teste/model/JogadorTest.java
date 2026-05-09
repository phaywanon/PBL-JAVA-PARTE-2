//package teste.model;
//
//import jogo.model.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class JogadorTest {
//
//    private Jogador jogador;
//    private Local pontodeonibus;
//    private Local casa;
//
//    @BeforeEach
//    void setup() {
//        pontodeonibus = new LocalPontoDeOnibus();
//        casa = new LocalCasa();
//        jogador = new Jogador("PH", pontodeonibus);
//    }
//
//
//    @Test
//    void testeCriacaoJogador() {
//        assertEquals("PH", jogador.getNome());
//        assertEquals(100, jogador.getEnergia());
//        assertEquals("Ponto de ônibus da UEFS", jogador.getLocal().getNomeLocal());
//        assertEquals(0.0, jogador.getDesempenhoAcademico());
//        assertEquals(100, jogador.getMotivacao());
//    }
//
//    @Test
//    void testeMudarLocal() {
//        jogador.mudarLocal(casa);
//        assertEquals("Casa", jogador.getLocal().getNomeLocal());
//    }
//
//    @Test
//    void testeEstudar() {
//        int energiaInicial = jogador.getEnergia();
//        double nivelDeConhecimentoInicial = jogador.getNivelDeConhecimento();
//        int conhecimentoSemetreInicial = jogador.getConhecimentoSemestre();
//
//        jogador.estudar();
//
//        assertEquals(energiaInicial - 3, jogador.getEnergia());
//        assertEquals(nivelDeConhecimentoInicial + 0.5, jogador.getNivelDeConhecimento());
//        assertEquals(conhecimentoSemetreInicial + 5, jogador.getConhecimentoSemestre());
//    }
//
//    @Test
//    void testeLancharComDinheiro(){
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getCantina());
//        jogador.setDinheiro(10);
//
//        int energiaInicial = jogador.getEnergia();
//        int motivacaoInicial = jogador.getMotivacao();
//
//        jogador.lanchar(mapa);
//
//        assertEquals(5, jogador.getDinheiro());
//        assertEquals(energiaInicial + 3, jogador.getEnergia());
//        assertEquals(motivacaoInicial + 3, jogador.getMotivacao());
//    }
//
//    @Test
//    void testeLancharSemDinheiro(){
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getCantina());
//        jogador.setDinheiro(3);
//
//        jogador.lanchar(mapa);
//
//        assertEquals(3, jogador.getDinheiro());
//    }
//
//    @Test
//    void testePegarOnibusComDinheiroParaCasa(){
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getPontoDeOnibus());
//        jogador.setDinheiro(10);
//
//        jogador.irParaCasa(mapa);
//
//        assertEquals("Casa", jogador.getLocal().getNomeLocal());
//        assertEquals(7, jogador.getDinheiro());
//    }
//
//    @Test
//    void testePegarOnibusSemDinheiroParaCasa() {
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getPontoDeOnibus());
//        jogador.setDinheiro(2);
//        int energiaInicial = jogador.getEnergia();
//
//        jogador.irParaCasa(mapa);
//
//        assertEquals("Casa", jogador.getLocal().getNomeLocal());
//        assertEquals(90, jogador.getEnergia());
//        assertEquals(2, jogador.getDinheiro()); // não pagou passagem
//    }
//
//    @Test
//    void testePegarOnibusComDinheiroParaUefs(){
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getCasa());
//        jogador.setDinheiro(10);
//
//        jogador.irParaUEFS(mapa);
//
//        assertEquals("Ponto de ônibus da UEFS", jogador.getLocal().getNomeLocal());
//        assertEquals(7, jogador.getDinheiro());
//    }
//
//    @Test
//    void testePegarOnibusSemDinheiroParaUefs() {
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getCasa());
//        jogador.setDinheiro(2);
//        int energiaInicial = jogador.getEnergia();
//
//        jogador.irParaUEFS(mapa);
//
//        assertEquals("Ponto de ônibus da UEFS", jogador.getLocal().getNomeLocal());
//        assertEquals(energiaInicial - 15, jogador.getEnergia());
//    }
//
//    @Test
//    void deveIrParaCasaDeQualquerLocal() {
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getCantina());
//        jogador.setDinheiro(10);
//
//        jogador.irParaCasa(mapa);
//
//        assertEquals("Casa", jogador.getLocal().getNomeLocal());
//    }
//
//    @Test
//    void naoDeveIrParaCasaSeJaEstiverEmCasa() {
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getCasa());
//        jogador.setDinheiro(10);
//
//        jogador.irParaCasa(mapa);
//
//        assertEquals("Casa", jogador.getLocal().getNomeLocal());
//        assertEquals(10, jogador.getDinheiro()); // dinheiro intacto, não cobrou passagem
//    }
//
//
//    @Test
//    void naoDeveFormarSemProgresso() {
//        Mapa mapa = new Mapa();
//        Jogador j = new Jogador("PH", mapa.getCasa());
//        new EventoOBFormatura().aplicarEvento(j);
//        assertFalse(j.isFormado());
//    }
//
//    @Test
//    void testeEstudarSemMotivacao() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setMotivacao(5); // abaixo do mínimo para estudar
//
//        double conhecimentoAntes = jogador.getNivelDeConhecimento();
//        jogador.estudar();
//
//        assertEquals(conhecimentoAntes, jogador.getNivelDeConhecimento());
//    }
//
//    @Test
//    void testeMotivacaoNaoPassaDoLimite() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setMotivacao(200);
//
//        assertEquals(110, jogador.getMotivacao());
//    }
//
//    @Test
//    void testeMotivacaoNaoFicaNegativa() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setMotivacao(-50);
//
//        assertEquals(0, jogador.getMotivacao());
//    }
//
//    @Test
//    void testeEnergiaMaxima() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setEnergia(200);
//
//        assertEquals(110, jogador.getEnergia());
//    }
//
//    @Test
//    void testeEnergiaMinima() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setEnergia(-10);
//
//        assertEquals(0, jogador.getEnergia());
//    }
//
//    @Test
//    void testeProgressoNaoPassaDe100() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setProgresso(150);
//
//        assertEquals(100.0, jogador.getProgresso());
//    }
//
//    @Test
//    void testeProgressoNaoFicaNegativo() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.setProgresso(-10);
//
//        assertEquals(0.0, jogador.getProgresso());
//    }
//
//    @Test
//    void testeResetarSemestre() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        jogador.adicionarNota(80);
//        jogador.adicionarNota(70);
//        jogador.setConhecimentoSemestre(50);
//
//        jogador.resetarSemestre();
//
//        assertEquals(0, jogador.getNotaAcumulada());
//        assertEquals(0, jogador.getProvasFeitas());
//        assertEquals(0, jogador.getConhecimentoSemestre());
//    }
//
//    @Test
//    void testeTrabalharForaDoLaboratorio() {
//        Jogador jogador = new Jogador("PH", new LocalCantina());
//        double dinheiroAntes = jogador.getDinheiro();
//
//        jogador.trabalhar();
//
//        assertEquals(dinheiroAntes, jogador.getDinheiro());
//    }
//
//    @Test
//    void testeTrabalharNoLaboratorio() {
//        Mapa mapa = new Mapa();
//        Jogador jogador = new Jogador("PH", mapa.getLaboratorio());
//        jogador.setDinheiro(0);
//
//        jogador.trabalhar();
//
//        assertEquals(20, jogador.getDinheiro());
//    }
//
//    @Test
//    void testeLazerForaDoDA() {
//        Jogador jogador = new Jogador("PH", new LocalCantina());
//        jogador.setDinheiro(10);
//        int motivacaoAntes = jogador.getMotivacao();
//
//        jogador.lazer();
//
//        assertEquals(motivacaoAntes, jogador.getMotivacao());
//    }
//
//    @Test
//    void testeCursarDisciplinaForaDaSala() {
//        Jogador jogador = new Jogador("PH", new LocalCantina());
//        int conhecimentoAntes = jogador.getConhecimentoSemestre();
//
//        jogador.cursarDisciplina();
//
//        assertEquals(conhecimentoAntes, jogador.getConhecimentoSemestre());
//    }
//
//    @Test
//    void testeFoiParaSalaHoje() {
//        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
//        assertFalse(jogador.isFoiParaSalaHoje());
//
//        jogador.setFoiParaSalaHoje(true);
//        assertTrue(jogador.isFoiParaSalaHoje());
//    }
//
//
//
//
//
//
//
//}
