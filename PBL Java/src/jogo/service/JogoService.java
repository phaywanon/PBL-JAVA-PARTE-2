package jogo.service;
import java.time.LocalDateTime;
import jogo.model.*;
import jogo.repository.JogoRepository;
import jogo.repository.JogoRepositoryJson;

public class JogoService {
    private Jogador jogador;
    private Mapa mapa;
    private int diaAtual = 1;
    private boolean avisoHojeExibido = false;
    private boolean primeiroDia = true;
    private String slotAtual;
    private JogadorService jogadorService;
    private EventoService eventoService;
    private JogoRepository repository;
    private String matriculaAtual;

    public JogoService() {
        this.repository = new JogoRepositoryJson();
        this.eventoService = new EventoService();
    }

    public String getSlotAtual() { return slotAtual; }

    // Delega ações pro JogadorService
    public String estudar()                    { return jogadorService.estudar(); }
    public String lanchar()                    { return jogadorService.lanchar(mapa); }
    public String lazer()                      { return jogadorService.lazer(); }
    public String trabalhar()                  { return jogadorService.trabalhar(); }
    public String cursarDisciplina()           { return jogadorService.cursarDisciplina(); }
    public String interagirComNPC(NPC npc)     { return jogadorService.interagirComNPC(npc); }

    public String irParaCasa() {
        String msg = jogadorService.irParaCasa(mapa);
        if (!msg.contains("já está em casa")) {
            msg += "\n" + avancarDia();
        }
        return msg;
    }

    public String irParaUEFS() {
        primeiroDia = false;
        return jogadorService.irParaUEFS(mapa);
    }

    public String explorar(int escolha) {
        switch (escolha) {
            case 1 -> jogador.mudarLocal(mapa.getCantina());
            case 2 -> { jogador.mudarLocal(mapa.getSalaDeAula()); jogador.setFoiParaSalaHoje(true); }
            case 3 -> jogador.mudarLocal(mapa.getLaboratorio());
            case 4 -> jogador.mudarLocal(mapa.getColegiado());
            case 5 -> jogador.mudarLocal(mapa.getDa());
            default -> { return "Opção inválida!"; }
        }

        String msg = "";
        String descricaoLocal = jogador.getLocal().eventoAoEntrar(jogador);
        msg += descricaoLocal + "\n";

        String evento = eventoService.sortearEventoAleatorio(jogador);
        if (evento != null) msg += evento;

        return msg;
    }

    // Avanço de dia — tudo que o controller fazia em avancarDia()
    private String avancarDia() {
        String msg = "";
        String eventos = eventoService.verificarEventosObrigatorios(jogador, diaAtual);
        if (eventos != null && !eventos.isBlank()) msg += eventos + "\n";
        jogador.setFoiParaSalaHoje(false);
        diaAtual++;
        avisoHojeExibido = false;
        if (eventoService.amanhaTemProva(diaAtual)) {
            msg += "⚠️ AMANHÃ TEM PROVA!";
        }
        salvarJogo(slotAtual);
        return msg;
    }

    // Getters para o controller consultar
    public Jogador getJogador()          { return jogador; }
    public Mapa getMapa()                { return mapa; }
    public int getDiaAtual()             { return diaAtual; }
    public boolean isAvisoHojeExibido()  { return avisoHojeExibido; }
    public void setAvisoHojeExibido(boolean v) { avisoHojeExibido = v; }
    public EventoService getEventoService() { return eventoService; }

    // JogoService
    public String irParaPonto() {
        jogador.mudarLocal(mapa.getPontoDeOnibus());
        return jogador.getLocal().eventoAoEntrar(jogador);
    }

    public void entrarNaUEFS()      { jogador.mudarLocal(mapa.getCantina()); } // ponto de entrada padrão


    // Jogador → EstadoDoJogo (pra salvar)
    private EstadoDoJogo jogadorParaEstado(String slot) {
        EstadoDoJogo e = new EstadoDoJogo();
        e.setSlot(slot);
        e.setNomeJogador(jogador.getNome());
        e.setUltimoAcesso(LocalDateTime.now().toString());
        e.setDiaAtual(diaAtual);
        e.setEnergia(jogador.getEnergia());
        e.setSaude(jogador.getSaude());
        e.setMotivacao(jogador.getMotivacao());
        e.setDinheiro(jogador.getDinheiro());
        e.setNivelDeConhecimento(jogador.getNivelDeConhecimento());
        e.setConhecimentoSemestre(jogador.getConhecimentoSemestre());
        e.setDesempenhoAcademico(jogador.getDesempenhoAcademico());
        e.setProgresso(jogador.getProgresso());
        e.setNotaAcumulada(jogador.getNotaAcumulada());
        e.setProvasFeitas(jogador.getProvasFeitas());
        e.setFormado(jogador.isFormado());
        e.setFoiParaSalaHoje(jogador.isFoiParaSalaHoje());
        e.setLocalAtual(jogador.getLocal().getNomeLocal()); // salva o nome
        e.setMatricula(matriculaAtual);
        return e;
    }

    // EstadoDoJogo → Jogador (ao carregar)
    private void estadoParaJogador(EstadoDoJogo e) {
        diaAtual = e.getDiaAtual();
        jogador = new Jogador(e.getNomeJogador(), localPorNome(e.getLocalAtual()));
        jogador.setEnergia(e.getEnergia());
        jogador.setSaude(e.getSaude());
        jogador.setMotivacao(e.getMotivacao());
        jogador.setDinheiro(e.getDinheiro());
        jogador.setNivelDeConhecimento(e.getNivelDeConhecimento());
        jogador.setConhecimentoSemestre(e.getConhecimentoSemestre());
        jogador.setDesempenhoAcademico(e.getDesempenhoAcademico());
        jogador.setProgresso(e.getProgresso());
        jogador.setFormado(e.isFormado());
        jogador.setFoiParaSalaHoje(e.isFoiParaSalaHoje());
        jogadorService = new JogadorService(jogador);
    }

    // converte nome do local de volta pra objeto
    private Local localPorNome(String nome) {
        return switch (nome) {
            case "Cantina"         -> mapa.getCantina();
            case "Sala de Aula"    -> mapa.getSalaDeAula();
            case "Laboratório"     -> mapa.getLaboratorio();
            case "Colegiado de ECOMP"       -> mapa.getColegiado();
            case "DA de ECOMP"     -> mapa.getDa();
            case "Ponto de ônibus da UEFS" -> mapa.getPontoDeOnibus();
            default                -> mapa.getCasa();
        };
    }

    public boolean novoJogo(String slot, String nome, String matricula) {
        EstadoDoJogo existente = repository.carregar(slot);
        if (existente != null) return false;
        this.slotAtual = slot;
        this.matriculaAtual = matricula;
        this.mapa = new Mapa();
        this.jogador = new Jogador(nome + " (" + matricula + ")", mapa.getCasa());
        this.jogadorService = new JogadorService(jogador);
        this.diaAtual = 1;
        salvarJogo(slot);
        return true;
    }

    public EstadoDoJogo carregarEstado(String slot) {
        return repository.carregar(slot); // só retorna o estado, sem iniciar jogo
    }

    public void deletarJogo(String slot) {
        repository.deletar(slot);
    }

    public void salvarJogo(String slot) {
        EstadoDoJogo estado = jogadorParaEstado(slot);
        repository.salvar(estado, slot);
    }

    public void carregarJogo(String slot) {
        this.slotAtual = slot;
        EstadoDoJogo estado = repository.carregar(slot);
        if (estado == null) return;
        mapa = new Mapa();
        estadoParaJogador(estado);
    }
}