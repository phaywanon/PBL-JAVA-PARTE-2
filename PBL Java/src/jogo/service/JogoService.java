package jogo.service;

import jogo.model.*;

public class JogoService {
    private Jogador jogador;
    private Mapa mapa;
    private int diaAtual = 1;
    private boolean avisoHojeExibido = false;
    private boolean primeiroDia = true;

    private JogadorService jogadorService;
    private EventoService eventoService;

    public JogoService() {
        this.mapa = new Mapa();
        this.jogador = new Jogador("PH", mapa.getCasa());
        this.jogadorService = new JogadorService(jogador);
        this.eventoService = new EventoService();
        System.out.println("Você está em casa.");
    }

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
}