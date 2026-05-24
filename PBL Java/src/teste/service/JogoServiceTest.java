package teste.service;

import jogo.model.*;
import jogo.service.JogoService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JogoServiceTest {

    private JogoService jogoService;
    private static final String SLOT_TESTE = "slot_service_teste";

    @BeforeEach
    void setUp() {
        jogoService = new JogoService();
        jogoService.novoJogo(SLOT_TESTE, "Testador", "9999");
    }

    @AfterEach
    void tearDown() {
        jogoService.deletarJogo(SLOT_TESTE);
    }

    @Test
    void novoJogoDeveCriarJogadorComNomeCorreto() {
        assertEquals("Testador (9999)", jogoService.getJogador().getNome());
    }

    @Test
    void novoJogoDeveIniciarNoDia1() {
        assertEquals(1, jogoService.getDiaAtual());
    }

    @Test
    void novoJogoDeveIniciarEmCasa() {
        assertTrue(jogoService.getJogador().getLocal() instanceof LocalCasa);
    }

    @Test
    void salvarECarregarDevePreservarDiaAtual() {
        jogoService.getJogador().mudarLocal(jogoService.getMapa().getPontoDeOnibus());
        jogoService.irParaCasa(); // avança pra dia 2
        jogoService.salvarJogo(SLOT_TESTE);

        JogoService outro = new JogoService();
        outro.carregarJogo(SLOT_TESTE);
        assertEquals(2, outro.getDiaAtual());
    }

    @Test
    void carregarJogoDeveRestaurarAtributosDoJogador() {
        jogoService.getJogador().setDinheiro(999.0);
        jogoService.getJogador().setMotivacao(42);
        jogoService.salvarJogo(SLOT_TESTE);

        JogoService outro = new JogoService();
        outro.carregarJogo(SLOT_TESTE);
        assertEquals(999.0, outro.getJogador().getDinheiro());
        assertEquals(42, outro.getJogador().getMotivacao());
    }

    @Test
    void carregarJogoDeveRestaurarLocalDoJogador() {
        jogoService.getJogador().mudarLocal(jogoService.getMapa().getSalaDeAula());
        jogoService.salvarJogo(SLOT_TESTE);

        JogoService outro = new JogoService();
        outro.carregarJogo(SLOT_TESTE);
        assertTrue(outro.getJogador().getLocal() instanceof LocalSalaDeAula);
    }

    @Test
    void carregarSlotVazioNaoDeveQuebrar() {
        JogoService outro = new JogoService();
        outro.carregarJogo("slot_inexistente_xyz");
        assertNull(outro.getJogador());
    }

    @Test
    void irParaCasaDeveAvancarDia() {
        jogoService.getJogador().mudarLocal(jogoService.getMapa().getPontoDeOnibus());
        jogoService.irParaCasa();
        assertEquals(2, jogoService.getDiaAtual());
    }

    @Test
    void irParaCasaDeveRetornarFalseSeJaEstaCasa() {
        assertFalse(jogoService.irParaCasa());
        assertEquals(1, jogoService.getDiaAtual()); // dia não avança
    }

    @Test
    void irParaUEFSDeveLevarAoPonto() {
        jogoService.irParaUEFS();
        assertTrue(jogoService.getJogador().getLocal() instanceof LocalPontoDeOnibus);
    }

    @Test
    void explorarCaso1DeveIrParaCantina() {
        jogoService.explorar(1);
        assertTrue(jogoService.getJogador().getLocal() instanceof LocalCantina);
    }

    @Test
    void explorarCaso2DeveMarcarFoiParaSala() {
        jogoService.explorar(2);
        assertTrue(jogoService.getJogador().isFoiParaSalaHoje());
    }

    @Test
    void deletarJogoDeveRemoverSave() {
        jogoService.salvarJogo(SLOT_TESTE);
        jogoService.deletarJogo(SLOT_TESTE);
        assertNull(jogoService.carregarEstado(SLOT_TESTE));
    }
}