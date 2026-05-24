package teste.controller;

import jogo.controller.JogoController;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private final PrintStream saidaOriginal = System.out;
    private ByteArrayOutputStream saidaCapturada;

    @BeforeEach
    void setUp() {
        saidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaCapturada));
    }

    @AfterEach
    void tearDown() {
        System.setOut(saidaOriginal);
        System.setIn(System.in);
        // limpa saves criados nos testes
        new File("saves/slot1.json").delete();
        new File("saves/slot2.json").delete();
    }

    private void simularInputs(String... inputs) {
        String inputSimulado = String.join("\n", inputs) + "\n";
        System.setIn(new ByteArrayInputStream(inputSimulado.getBytes()));
        new JogoController().iniciarJogo();
    }

    private String saida() {
        return saidaCapturada.toString();
    }

    // ===== MENU INICIAL =====

    @Test
    void menuInicialDeveExibirTitulo() {
        simularInputs("0");
        assertTrue(saida().contains("BIXOQUEST"));
    }

    @Test
    void menuInicialDeveExibirOpcoes() {
        simularInputs("0");
        assertTrue(saida().contains("Novo Jogo"));
        assertTrue(saida().contains("Carregar Jogo"));
        assertTrue(saida().contains("Deletar Save"));
    }

    @Test
    void opcaoSairDeveExibirMensagemDespedida() {
        simularInputs("0");
        assertTrue(saida().contains("Até logo!"));
    }

    @Test
    void inputStringDeveSerRejeitado() {
        simularInputs("abc", "0");
        assertTrue(saida().contains("Digite um número válido!"));
    }

    @Test
    void inputForaDoRangeDeveSerRejeitado() {
        simularInputs("9", "0"); // 9 fora de 0-3
        assertTrue(saida().contains("Digite um número entre"));
    }

    // ===== SLOTS =====

    @Test
    void novoJogoDeveExibirSlots() {
        simularInputs("1", "0", "0"); // novo jogo, volta (slot 0), sair
        assertTrue(saida().contains("SLOTS DE SAVE"));
    }

    @Test
    void carregarSlotVazioDeveMostrarAviso() {
        new File("saves/slot3.json").delete(); // garante vazio
        simularInputs("2", "3", "0", "0"); // carregar, slot3, volta, sair
        assertTrue(saida().contains("Slot vazio"));
    }

    // ===== LOOP DO JOGO =====

    @Test
    void statusDeveExibirInformacoesDoJogador() {
        // novo jogo slot1, nome, matricula, opção 8 (status), sair
        simularInputs("1", "1", "Heroi", "111", "8", "0");
        assertTrue(saida().contains("STATUS"));
        assertTrue(saida().contains("Energia"));
        assertTrue(saida().contains("Dinheiro"));
    }

    @Test
    void novoJogoDevePedirNomeEMatricula() {
        simularInputs("1", "1", "Heroi", "111", "0");
        assertTrue(saida().contains("Digite seu nome"));
        assertTrue(saida().contains("Digite sua matrícula"));
    }
}