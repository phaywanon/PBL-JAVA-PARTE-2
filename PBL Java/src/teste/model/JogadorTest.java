package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JogadorTest {

    private Jogador jogador;
    private Local pontodeonibus;
    private Local casa;

    @BeforeEach
    void setup() {
        pontodeonibus = new LocalPontoDeOnibus();
        casa = new LocalCasa();
        jogador = new Jogador("PH", pontodeonibus);
    }


    @Test
    void testeCriacaoJogador() {
        assertEquals("PH", jogador.getNome());
        assertEquals(100, jogador.getEnergia());
        assertEquals("Ponto de ônibus da UEFS", jogador.getLocal().getNomeLocal());
        assertEquals(0.0, jogador.getDesempenhoAcademico());
        assertEquals(100, jogador.getMotivacao());
    }

    @Test
    void testeMudarLocal() {
        jogador.mudarLocal(casa);
        assertEquals("Casa", jogador.getLocal().getNomeLocal());
    }

    @Test
    void testeEstudar() {
        int energiaInicial = jogador.getEnergia();
        double nivelDeConhecimentoInicial = jogador.getNivelDeConhecimento();
        int conhecimentoSemetreInicial = jogador.getConhecimentoSemestre();

        jogador.estudar();

        assertEquals(energiaInicial - 3, jogador.getEnergia());
        assertEquals(nivelDeConhecimentoInicial + 0.5, jogador.getNivelDeConhecimento());
        assertEquals(conhecimentoSemetreInicial + 5, jogador.getConhecimentoSemestre());
    }

    @Test
    void testeLancharComDinheiro(){
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getCantina());
        jogador.setDinheiro(10);

        int energiaInicial = jogador.getEnergia();
        int motivacaoInicial = jogador.getMotivacao();

        jogador.lanchar(mapa);

        assertEquals(5, jogador.getDinheiro());
        assertEquals(energiaInicial + 3, jogador.getEnergia());
        assertEquals(motivacaoInicial + 3, jogador.getMotivacao());
    }

    @Test
    void testeLancharSemDinheiro(){
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getCantina());
        jogador.setDinheiro(3);

        jogador.lanchar(mapa);

        assertEquals(3, jogador.getDinheiro());
    }

    @Test
    void testePegarOnibusComDinheiroParaCasa(){
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getPontoDeOnibus());
        jogador.setDinheiro(10);

        jogador.irParaCasa(mapa);

        assertEquals("Casa", jogador.getLocal().getNomeLocal());
        assertEquals(7, jogador.getDinheiro());
    }

    @Test
    void testePegarOnibusSemDinheiroParaCasa() {
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getPontoDeOnibus());
        jogador.setDinheiro(2);
        int energiaInicial = jogador.getEnergia();

        jogador.irParaCasa(mapa);

        assertEquals("Casa", jogador.getLocal().getNomeLocal());
        assertEquals(energiaInicial - 15, jogador.getEnergia());
    }

    @Test
    void testePegarOnibusComDinheiroParaUefs(){
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getCasa());
        jogador.setDinheiro(10);

        jogador.irParaUEFS(mapa);

        assertEquals("Ponto de ônibus da UEFS", jogador.getLocal().getNomeLocal());
        assertEquals(7, jogador.getDinheiro());
    }

    @Test
    void testePegarOnibusSemDinheiroParaUefs() {
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getCasa());
        jogador.setDinheiro(2);
        int energiaInicial = jogador.getEnergia();

        jogador.irParaUEFS(mapa);

        assertEquals("Ponto de ônibus da UEFS", jogador.getLocal().getNomeLocal());
        assertEquals(energiaInicial - 15, jogador.getEnergia());
    }

    @Test
    void deveIrParaCasaDeQualquerLocal() {
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getCantina());
        jogador.setDinheiro(10);

        jogador.irParaCasa(mapa);

        assertEquals("Casa", jogador.getLocal().getNomeLocal());
    }

    @Test
    void naoDeveIrParaCasaSeJaEstiverEmCasa() {
        Mapa mapa = new Mapa();
        Jogador jogador = new Jogador("PH", mapa.getCasa());
        jogador.setDinheiro(10);

        jogador.irParaCasa(mapa);

        assertEquals("Casa", jogador.getLocal().getNomeLocal());
        assertEquals(10, jogador.getDinheiro()); // dinheiro intacto, não cobrou passagem
    }


    @Test
    void naoDeveFormarSemProgresso() {
        Jogador j = new Jogador("PH");
        new EventoOBFormatura().aplicarEvento(j);
        assertFalse(j.isFormado());
    }
}
