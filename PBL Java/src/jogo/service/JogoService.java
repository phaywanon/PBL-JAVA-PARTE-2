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
    private JogoRepository repository; // ← adiciona

    public JogoService() {
        this.repository = new JogoRepositoryJson();
        this.eventoService = new EventoService();
    }

    public String getSlotAtual() { return slotAtual; }

    // Delega ações pro JogadorService
    public void estudar()                    { jogadorService.estudar(); }
    public void lanchar()                    { jogadorService.lanchar(mapa); }
    public void lazer()                      { jogadorService.lazer(); }
    public void trabalhar()                  { jogadorService.trabalhar(); }
    public void cursarDisciplina()           { jogadorService.cursarDisciplina(); }
    public void interagirComNPC(NPC npc)     { jogadorService.interagirComNPC(npc); }

    public boolean irParaCasa() {
        boolean foiPraCasa = jogadorService.irParaCasa(mapa);
        if (foiPraCasa) avancarDia();
        return foiPraCasa;
    }

    public void irParaUEFS() {
        jogadorService.irParaUEFS(mapa);
        primeiroDia = false;
    }

    public void explorar(int escolha) {
        switch (escolha) {
            case 1 -> { jogador.mudarLocal(mapa.getCantina());    jogador.getLocal().eventoAoEntrar(jogador); }
            case 2 -> { jogador.mudarLocal(mapa.getSalaDeAula()); jogador.getLocal().eventoAoEntrar(jogador); jogador.setFoiParaSalaHoje(true); }
            case 3 -> { jogador.mudarLocal(mapa.getLaboratorio());jogador.getLocal().eventoAoEntrar(jogador); }
            case 4 -> { jogador.mudarLocal(mapa.getColegiado());  jogador.getLocal().eventoAoEntrar(jogador); }
            case 5 -> { jogador.mudarLocal(mapa.getDa());         jogador.getLocal().eventoAoEntrar(jogador); }
        }
    }

    // Avanço de dia — tudo que o controller fazia em avancarDia()
    private void avancarDia() {
        if (eventoService.amanhaTemProva(diaAtual)) {
            System.out.println("⚠️  AMANHÃ TEM PROVA!");
        }
        eventoService.verificarEventosObrigatorios(jogador, diaAtual);
        diaAtual++;
        avisoHojeExibido = false;
        eventoService.sortearEventoAleatorio(jogador);
        jogador.setFoiParaSalaHoje(false);
        salvarJogo(slotAtual);
    }

    // Getters para o controller consultar
    public Jogador getJogador()          { return jogador; }
    public Mapa getMapa()                { return mapa; }
    public int getDiaAtual()             { return diaAtual; }
    public boolean isAvisoHojeExibido()  { return avisoHojeExibido; }
    public void setAvisoHojeExibido(boolean v) { avisoHojeExibido = v; }
    public EventoService getEventoService() { return eventoService; }

    // JogoService
    public void irParaPonto()       { jogador.mudarLocal(mapa.getPontoDeOnibus()); }
    public void entrarNaUEFS()      { jogador.mudarLocal(mapa.getCantina()); } // ponto de entrada padrão
    public void fazerCarinhoNoBichinho() { jogadorService.interagirComNPC( new PersonagemBichinhos()); }


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
            case "Colegiado"       -> mapa.getColegiado();
            case "DA de ECOMP"     -> mapa.getDa();
            case "Ponto de Ônibus" -> mapa.getPontoDeOnibus();
            default                -> mapa.getCasa();
        };
    }

    public void novoJogo(String slot, String nome, String matricula) {
        this.slotAtual = slot;
        this.mapa = new Mapa();
        this.jogador = new Jogador(nome + " (" + matricula + ")", mapa.getCasa());
        this.jogadorService = new JogadorService(jogador);
        this.diaAtual = 1;
        salvarJogo(slotAtual);
        System.out.println("Novo jogo iniciado no " + slot + "!");
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
        System.out.println("Jogo salvo no " + slot + "!");
    }

    public void carregarJogo(String slot) {
        this.slotAtual = slot;
        EstadoDoJogo estado = repository.carregar(slot);
        if (estado == null) {
            System.out.println("Slot vazio!");
            return;
        }
        mapa = new Mapa();
        estadoParaJogador(estado);
        System.out.println("Jogo carregado!");
    }



}