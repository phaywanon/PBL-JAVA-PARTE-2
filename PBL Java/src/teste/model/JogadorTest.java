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
    void setup(){
        pontodeonibus = new LocalPontoDeOnibus();
        casa = new LocalCasa();
        jogador = new Jogador("PH", pontodeonibus);
    }


    @Test
    void testeCriacaoJogador(){
        assertEquals("PH", jogador.getNome());
        assertEquals(100, jogador.getEnergia());
        assertEquals("Ponto de ônibus", jogador.getLocal().getNomeLocal());
        assertEquals(0.0, jogador.getDesempenhoAcademico());
        assertEquals(100, jogador.getMotivacao());
    }

    @Test
    void testeMudarLocal(){
        jogador.mudarLocal(casa);
        assertEquals("Casa", jogador.getLocal().getNomeLocal());
    }

    @Test
    void testeEstudar(){
        int energiaInicial = jogador.getEnergia();
        double nivelDeConhecimentoInicial = jogador.getNivelDeConhecimento();

        jogador.estudar();

        assertEquals(energiaInicial - 3, jogador.getEnergia());
        assertEquals(nivelDeConhecimentoInicial + 0.5, jogador.getNivelDeConhecimento());
    }

    @Test
    void testeExplorarComEnergiaAlta() {
        jogador.setEnergia(25);

        //jogador.explorar();

        assertEquals("Cantina", jogador.getLocal().getNomeLocal());
    }

    @Test
    void testeExplorarComEnergiaBaixa() {
        jogador.setEnergia(5);

        //jogador.explorar();

        assertEquals("Casa", jogador.getLocal().getNomeLocal());
    }

    @Test
    void testeLancharComDinheiro(){
        jogador.setDinheiro(10);

        int energiaInicial = jogador.getEnergia();
        int motivacaoInicial = jogador.getMotivacao();

        jogador.lanchar();

        assertEquals(5, jogador.getDinheiro());
        assertEquals(energiaInicial + 3, jogador.getEnergia());
        assertEquals(motivacaoInicial + 3, jogador.getMotivacao());
    }

    @Test
    void testeLancharSemDinheiro(){
        jogador.setDinheiro(3);

        jogador.lanchar();

        assertEquals(3, jogador.getDinheiro());
    }

    @Test
    void testePegarOnibusComDinheiro(){
        jogador.setDinheiro(10);

        jogador.pegarOnibus();

        assertEquals("Casa", jogador.getLocal().getNomeLocal());
        assertEquals(7, jogador.getDinheiro());
    }

    @Test
    void testePegarOnibusSemDinheiro() {
        jogador.setDinheiro(2);
        int energiaInicial = jogador.getEnergia();

        jogador.pegarOnibus();

        assertEquals("Casa", jogador.getLocal().getNomeLocal());
        assertEquals(energiaInicial - 15, jogador.getEnergia());
    }
}
