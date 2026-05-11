package jogo.repository;

import jogo.model.EstadoDoJogo;

public interface JogoRepository {
    void salvar(EstadoDoJogo estado, String slot);
    EstadoDoJogo carregar(String slot);
    void deletar(String slot);
    boolean slotOcupado(String slot);
}