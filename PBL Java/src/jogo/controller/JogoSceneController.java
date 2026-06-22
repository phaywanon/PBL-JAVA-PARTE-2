package jogo.controller;

import jogo.model.*;
import jogo.service.JogoService;

import java.util.ArrayList;
import java.util.List;

public class JogoSceneController {

    private final JogoService jogoService;

    public JogoSceneController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    // ===== Decisão de quais ações mostrar, por local =====

    public List<AcaoDisponivel> getAcoesParaLocalAtual() {
        Local local = jogoService.getJogador().getLocal();

        if (local instanceof LocalCasa)              return acoesCasa();
        else if (local instanceof LocalPontoDeOnibus) return acoesPonto();
        else                                          return acoesUEFS(local);
    }

    private List<AcaoDisponivel> acoesCasa() {
        List<AcaoDisponivel> acoes = new ArrayList<>();
        acoes.add(new AcaoDisponivel("🚌 Ir para a UEFS", jogoService::irParaUEFS));
        acoes.add(new AcaoDisponivel("📊 Ver status", this::statusCompleto));
        return acoes;
    }

    private List<AcaoDisponivel> acoesPonto() {
        List<AcaoDisponivel> acoes = new ArrayList<>();
        acoes.add(new AcaoDisponivel("🏫 Entrar na UEFS", () -> {
            jogoService.entrarNaUEFS();
            return "Você entrou na UEFS.";
        }));
        acoes.add(new AcaoDisponivel("🏠 Ir para casa", jogoService::irParaCasa));
        return acoes;
    }

    private List<AcaoDisponivel> acoesUEFS(Local local) {
        List<AcaoDisponivel> acoes = new ArrayList<>();
        acoes.add(new AcaoDisponivel("📖 Estudar", jogoService::estudar));
        acoes.add(new AcaoDisponivel("🚌 Ir ao ponto", jogoService::irParaPonto));
        acoes.add(new AcaoDisponivel("💾 Salvar", () -> {
            jogoService.salvarJogo(jogoService.getSlotAtual());
            return "💾 Jogo salvo!";
        }));

        if (local instanceof LocalCantina) {
            acoes.add(new AcaoDisponivel("🍽️ Lanchar", jogoService::lanchar));
        }
        if (local instanceof LocalSalaDeAula || local instanceof LocalLaboratorio) {
            acoes.add(new AcaoDisponivel("🎓 Cursar disciplina", jogoService::cursarDisciplina));
        }
        if (local instanceof LocalLaboratorio) {
            acoes.add(new AcaoDisponivel("💼 Trabalhar", jogoService::trabalhar));
        }
        if (local instanceof LocalDA) {
            acoes.add(new AcaoDisponivel("🎲 Jogar Dominó", jogoService::lazer));
        }

        return acoes;
    }

    // ===== Decisões de estado do jogo =====

    public boolean jogadorSeFormou() {
        return jogoService.getJogador().isFormado();
    }

    public boolean deveAvisarProva() {
        return !jogoService.isAvisoHojeExibido()
                && jogoService.getEventoService().hojeTemProva(jogoService.getDiaAtual());
    }

    public void marcarAvisoExibido() {
        jogoService.setAvisoHojeExibido(true);
    }

    public String statusCompleto() {
        Jogador j = jogoService.getJogador();
        return String.format("⚡%d  ❤️%d  💡%d  📚%.1f  💰R$%.0f  🎓%.0f%%",
                j.getEnergia(), j.getSaude(), j.getMotivacao(),
                j.getNivelDeConhecimento(), j.getDinheiro(), j.getProgresso());
    }
}
