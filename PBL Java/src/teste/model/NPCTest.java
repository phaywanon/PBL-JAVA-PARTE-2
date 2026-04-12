package teste.model;

import jogo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {

    @Test
    void maeliDeveAumentarDesempenho() {
        Mapa mapa = new Mapa();
        Jogador j = new Jogador("PH", mapa.getCasa());
        double desempenhoInicial = j.getDesempenhoAcademico();

        NPC maeli = new PersonagemMaeli();
        maeli.interagir(j);

        assertTrue(j.getDesempenhoAcademico() > desempenhoInicial);
    }

    @Test
    void professorDeveAumentarConhecimento() {
        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
        double conhecimentoAntes = jogador.getNivelDeConhecimento();

        new PersonagemProfessor().interagir(jogador);

        assertTrue(jogador.getNivelDeConhecimento() > conhecimentoAntes);
    }

    @Test
    void colegasDevemAumentarConhecimentoSemestre() {
        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
        int conhecimentoAntes = jogador.getConhecimentoSemestre();

        new PersonagemColegas().interagir(jogador);

        assertTrue(jogador.getConhecimentoSemestre() > conhecimentoAntes);
    }

    @Test
    void bichinhoDeveAumentarMotivacao() {
        Jogador jogador = new Jogador("PH", new LocalPontoDeOnibus());
        int motivacaoAntes = jogador.getMotivacao();

        new PersonagemBichinhos().interagir(jogador);

        assertTrue(jogador.getMotivacao() > motivacaoAntes);
    }
}