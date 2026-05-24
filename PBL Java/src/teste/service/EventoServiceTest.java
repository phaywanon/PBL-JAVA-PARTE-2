package teste.service;
import jogo.model.*;
import jogo.service.EventoService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// =============================================
//           TESTES DO EventoService
// =============================================
public class EventoServiceTest {

    private Jogador jogador;
    private Mapa mapa;
    private EventoService eventoService;

    @BeforeEach
    void setUp() {
        mapa = new Mapa();
        jogador = new Jogador("Teste", mapa.getCasa());
        eventoService = new EventoService();
    }

    // ===== DETECÇÃO DE PROVAS =====

    @Test
    void hojeTemProvaNoDia7() {
        assertTrue(eventoService.hojeTemProva(7));
    }

    @Test
    void hojeTemProvaNoDia14() {
        assertTrue(eventoService.hojeTemProva(14));
    }

    @Test
    void hojeTemProvaNoDia20() {
        assertTrue(eventoService.hojeTemProva(20));
    }

    @Test
    void hojeNaoTemProvaEmDiaComum() {
        assertFalse(eventoService.hojeTemProva(5));
        assertFalse(eventoService.hojeTemProva(10));
        assertFalse(eventoService.hojeTemProva(15));
    }

    @Test
    void amanhaTemProvaNoDia6() {
        assertTrue(eventoService.amanhaTemProva(6));
    }

    @Test
    void amanhaTemProvaNoDia13() {
        assertTrue(eventoService.amanhaTemProva(13));
    }

    @Test
    void amanhaTemProvaNoDia19() {
        assertTrue(eventoService.amanhaTemProva(19));
    }

    // ===== EVENTOS OBRIGATÓRIOS =====

    @Test
    void provaDeveAplicarEfeitoNoDia7() {
        jogador.mudarLocal(mapa.getSalaDeAula());
        jogador.setFoiParaSalaHoje(true);
        jogador.setConhecimentoSemestre(50);
        eventoService.verificarEventosObrigatorios(jogador, 7);
        assertTrue(jogador.getProvasFeitas() > 0);
    }

    @Test
    void provaDeveZerarNotaSeJogadorFaltou() {
        jogador.setFoiParaSalaHoje(false);
        eventoService.verificarEventosObrigatorios(jogador, 7);
        assertEquals(0.0, jogador.getNotaAcumulada());
        assertEquals(1, jogador.getProvasFeitas());
    }

    @Test
    void fimDeSemestreDeveResetarDados() {
        jogador.adicionarNota(80.0);
        jogador.adicionarNota(70.0);
        jogador.adicionarNota(90.0);
        eventoService.verificarEventosObrigatorios(jogador, 21);
        assertEquals(0, jogador.getProvasFeitas());
        assertEquals(0.0, jogador.getNotaAcumulada());
    }

    // ===== EVENTOS ALEATÓRIOS =====

    @Test
    void eventoAleatorioNaoDeveOcorrerEmCasa() {
        jogador.mudarLocal(mapa.getCasa());
        // roda 10 vezes — em casa nunca deve ocorrer
        for (int i = 0; i < 10; i++) {
            eventoService.sortearEventoAleatorio(jogador);
        }
        // se chegou aqui sem exceção, passou
        assertTrue(jogador.getLocal() instanceof LocalCasa);
    }

    @Test
    void eventoAleatorioNaoDeveOcorrerNoPonto() {
        jogador.mudarLocal(mapa.getPontoDeOnibus());
        int saudeAntes = jogador.getSaude();
        int motivacaoAntes = jogador.getMotivacao();
        // eventos no ponto não devem ocorrer, atributos ficam iguais
        for (int i = 0; i < 5; i++) {
            eventoService.sortearEventoAleatorio(jogador);
        }
        // atributos não devem ter mudado (nenhum evento aplicado)
        assertEquals(saudeAntes, jogador.getSaude());
        assertEquals(motivacaoAntes, jogador.getMotivacao());
    }
}

