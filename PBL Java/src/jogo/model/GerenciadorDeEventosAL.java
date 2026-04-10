package jogo.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorDeEventosAL {
    private List<EventosAleatorios> eventos;
    private Random random = new Random();

    public GerenciadorDeEventosAL() {
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

    public void sortearEventoAleatorio(Jogador jogador) {
        if (random.nextInt(100) < 30) {
            EventosAleatorios evento = eventos.get(random.nextInt(eventos.size()));
            System.out.println("\n⚡ EVENTO: " + evento.descricao);
            evento.aplicarEvento(jogador);
        }
    }
}
