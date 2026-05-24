package jogo.teste;

import jogo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JogadorTest {

    private Jogador jogador;
    private Mapa mapa;

    @BeforeEach
    void setUp() {
        mapa = new Mapa();
        jogador = new Jogador("Teste", mapa.getCasa());
    }

    // ===== ATRIBUTOS INICIAIS =====

    @Test
    void jogadorDeveIniciarEmCasa() {
        assertTrue(jogador.getLocal() instanceof LocalCasa);
    }

    @Test
    void jogadorDeveIniciarComNomeCorreto() {
        assertEquals("Teste", jogador.getNome());
    }

    // ===== LIMITES DE ATRIBUTOS =====

    @Test
    void energiaNaoDevePassarDe110() {
        jogador.setEnergia(200);
        assertEquals(110, jogador.getEnergia());
    }

    @Test
    void energiaNaoDeveSerNegativa() {
        jogador.setEnergia(-50);
        assertEquals(0, jogador.getEnergia());
    }

    @Test
    void motivacaoNaoDevePassarDe110() {
        jogador.setMotivacao(200);
        assertEquals(110, jogador.getMotivacao());
    }

    @Test
    void motivacaoNaoDeveSerNegativa() {
        jogador.setMotivacao(-10);
        assertEquals(0, jogador.getMotivacao());
    }

    @Test
    void nivelDeConhecimentoNaoDevePassarDe110() {
        jogador.setNivelDeConhecimento(200);
        assertEquals(110, jogador.getNivelDeConhecimento());
    }

    @Test
    void nivelDeConhecimentoNaoDeveSerNegativo() {
        jogador.setNivelDeConhecimento(-5);
        assertEquals(0, jogador.getNivelDeConhecimento());
    }

    @Test
    void progressoNaoDevePassarDe100() {
        jogador.setProgresso(150);
        assertEquals(100, jogador.getProgresso());
    }

    @Test
    void progressoNaoDeveSerNegativo() {
        jogador.setProgresso(-10);
        assertEquals(0, jogador.getProgresso()); // valor não muda se negativo
    }

    // ===== COMPORTAMENTOS =====

    @Test
    void adicionarNotaDeveAcumularCorretamente() {
        jogador.adicionarNota(8.0);
        jogador.adicionarNota(7.0);
        assertEquals(15.0, jogador.getNotaAcumulada());
        assertEquals(2, jogador.getProvasFeitas());
    }

    @Test
    void resetarSemestreDeveLimparDados() {
        jogador.adicionarNota(9.0);
        jogador.setConhecimentoSemestre(50);
        jogador.resetarSemestre();
        assertEquals(0, jogador.getNotaAcumulada());
        assertEquals(0, jogador.getProvasFeitas());
        assertEquals(0, jogador.getConhecimentoSemestre());
    }

    @Test
    void podeExplorarDeveRetornarTrueComEnergiaAdequada() {
        jogador.setEnergia(50);
        assertTrue(jogador.podeExplorar());
    }

    @Test
    void podeExplorarDeveRetornarFalseComPoucaEnergia() {
        jogador.setEnergia(10);
        assertFalse(jogador.podeExplorar());
    }

    @Test
    void mudarLocalDeveAlterarLocalCorretamente() {
        jogador.mudarLocal(mapa.getCantina());
        assertTrue(jogador.getLocal() instanceof LocalCantina);
    }

    @Test
    void jogadorNaoDeveEstarFormadoNoInicio() {
        assertFalse(jogador.isFormado());
    }

    @Test
    void setFormadoDeveAtualizarEstado() {
        jogador.setFormado(true);
        assertTrue(jogador.isFormado());
    }
}
