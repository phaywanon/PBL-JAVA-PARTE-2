package jogo.service;
import jogo.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventoService {
    private List<EventosAleatorios> eventos;
    private Random random = new Random();

    public EventoService() {
        eventos = new ArrayList<>();
        eventos.add(new EventoALDoente());
        eventos.add(new EventoALFesta());
        eventos.add(new EventoALFilaGigante());
        eventos.add(new EventoALGanhouDinheiro());
        eventos.add(new EventoALGreve());
        eventos.add(new EventoALMaterialCaro());
        eventos.add(new EventoALMilagre());
        eventos.add(new EventoALPerdeuDinheiro());
    }

    // Sorteia evento aleatório — retorna mensagem ou null se não sortear nada
    public String sortearEventoAleatorio(Jogador jogador) {
        if (jogador.getLocal() instanceof LocalCasa ||
                jogador.getLocal() instanceof LocalPontoDeOnibus) {
            return null;
        }

        if (random.nextInt(100) < 30) {
            EventosAleatorios evento = eventos.get(random.nextInt(eventos.size()));
            evento.aplicarEvento(jogador); // por enquanto ainda printa — ok!
            return evento.getDescricao();
        }
        return null;
    }

    // Verifica eventos obrigatórios pelo dia do semestre
    public void verificarEventosObrigatorios(Jogador jogador, int diaAtual) {
        int diaDoSemestre = (diaAtual - 1) % 21 + 1;
        switch (diaDoSemestre) {
            case 7  -> new EventoOBProva(1).aplicarEvento(jogador);
            case 14 -> new EventoOBProva(2).aplicarEvento(jogador);
            case 20 -> new EventoOBProva(3).aplicarEvento(jogador);
            case 21 -> {
                new EventoOBFimDeSemestre().aplicarEvento(jogador);
                new EventoOBFormatura().aplicarEvento(jogador);
            }
        }
    }

    // Avisa se amanhã tem prova
    public boolean amanhaTemProva(int diaAtual) {
        int diaDoSemestre = (diaAtual - 1) % 21 + 1;
        return diaDoSemestre == 6 || diaDoSemestre == 13 || diaDoSemestre == 19;
    }

    // Avisa se hoje tem prova
    public boolean hojeTemProva(int diaAtual) {
        int diaDoSemestre = (diaAtual - 1) % 21 + 1;
        return diaDoSemestre == 7 || diaDoSemestre == 14 || diaDoSemestre == 20;
    }
}