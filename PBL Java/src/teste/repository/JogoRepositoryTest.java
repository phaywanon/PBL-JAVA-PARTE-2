package teste.repository;

import jogo.model.*;
import jogo.repository.JogoRepository;
import jogo.repository.JogoRepositoryJson;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JogoRepositoryTest{

    private JogoRepository repository;
    private static final String SLOT_TESTE = "slot_teste_unitario";

    @BeforeEach
    void setUp() {
        repository = new JogoRepositoryJson();
    }

    @AfterEach
    void tearDown() {
        // limpa o arquivo de teste após cada teste
        repository.deletar(SLOT_TESTE);
    }

    @Test
    void salvarDevecriarArquivo() {
        EstadoDoJogo estado = criarEstadoPadrao();
        repository.salvar(estado, SLOT_TESTE);
        assertTrue(repository.slotOcupado(SLOT_TESTE));
    }

    @Test
    void carregarDeveRetornarNullParaSlotVazio() {
        assertNull(repository.carregar("slot_que_nao_existe"));
    }

    @Test
    void salvarECarregarDevemPreservarDados() {
        EstadoDoJogo original = criarEstadoPadrao();
        repository.salvar(original, SLOT_TESTE);

        EstadoDoJogo carregado = repository.carregar(SLOT_TESTE);

        assertNotNull(carregado);
        assertEquals(original.getNomeJogador(), carregado.getNomeJogador());
        assertEquals(original.getDiaAtual(), carregado.getDiaAtual());
        assertEquals(original.getEnergia(), carregado.getEnergia());
        assertEquals(original.getDinheiro(), carregado.getDinheiro());
        assertEquals(original.getProgresso(), carregado.getProgresso());
        assertEquals(original.getLocalAtual(), carregado.getLocalAtual());
    }

    @Test
    void deletarDeveRemoverArquivo() {
        EstadoDoJogo estado = criarEstadoPadrao();
        repository.salvar(estado, SLOT_TESTE);
        assertTrue(repository.slotOcupado(SLOT_TESTE));

        repository.deletar(SLOT_TESTE);
        assertFalse(repository.slotOcupado(SLOT_TESTE));
    }

    @Test
    void slotOcupadoDeveRetornarFalseParaSlotVazio() {
        assertFalse(repository.slotOcupado("slot_inexistente_xyz"));
    }

    @Test
    void salvarDeveSubstituirSaveExistente() {
        EstadoDoJogo v1 = criarEstadoPadrao();
        v1.setDiaAtual(1);
        repository.salvar(v1, SLOT_TESTE);

        EstadoDoJogo v2 = criarEstadoPadrao();
        v2.setDiaAtual(10);
        repository.salvar(v2, SLOT_TESTE);

        EstadoDoJogo carregado = repository.carregar(SLOT_TESTE);
        assertEquals(10, carregado.getDiaAtual());
    }

    private EstadoDoJogo criarEstadoPadrao() {
        EstadoDoJogo e = new EstadoDoJogo();
        e.setSlot(SLOT_TESTE);
        e.setNomeJogador("JogadorTeste");
        e.setDiaAtual(5);
        e.setEnergia(80);
        e.setSaude(90);
        e.setMotivacao(70);
        e.setDinheiro(50.0);
        e.setNivelDeConhecimento(2.5);
        e.setConhecimentoSemestre(30);
        e.setDesempenhoAcademico(10.0);
        e.setProgresso(15.0);
        e.setNotaAcumulada(85.0);
        e.setProvasFeitas(1);
        e.setFormado(false);
        e.setFoiParaSalaHoje(false);
        e.setLocalAtual("Casa");
        e.setUltimoAcesso("2026-05-11T02:00:00");
        return e;
    }
}

