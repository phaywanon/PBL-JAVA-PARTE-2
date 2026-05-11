package jogo.model;

import java.time.LocalDateTime;

public class EstadoDoJogo {
    private String slot;
    private String nomeJogador;
    private String ultimoAcesso;
    private int diaAtual;

    // atributos do Jogador — só primitivos
    private int energia;
    private int saude;
    private int motivacao;
    private double dinheiro;
    private double nivelDeConhecimento;
    private int conhecimentoSemestre;
    private double desempenhoAcademico;
    private double progresso;
    private double notaAcumulada;
    private int provasFeitas;
    private boolean formado;
    private boolean foiParaSalaHoje;
    private String localAtual; // salva só o NOME do local, não o objeto

    // construtor vazio obrigatório pro Gson
    public EstadoDoJogo() {}

    // getters e setters de todos os campos
    public String getSlot() { return slot; }
    public void setSlot(String slot) { this.slot = slot; }
    public String getNomeJogador() { return nomeJogador; }
    public void setNomeJogador(String nomeJogador) { this.nomeJogador = nomeJogador; }
    public String getUltimoAcesso() { return ultimoAcesso; }
    public void setUltimoAcesso(String ultimoAcesso) { this.ultimoAcesso = ultimoAcesso; }
    public int getDiaAtual() { return diaAtual; }
    public void setDiaAtual(int diaAtual) { this.diaAtual = diaAtual; }
    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }
    public int getSaude() { return saude; }
    public void setSaude(int saude) { this.saude = saude; }
    public int getMotivacao() { return motivacao; }
    public void setMotivacao(int motivacao) { this.motivacao = motivacao; }
    public double getDinheiro() { return dinheiro; }
    public void setDinheiro(double dinheiro) { this.dinheiro = dinheiro; }
    public double getNivelDeConhecimento() { return nivelDeConhecimento; }
    public void setNivelDeConhecimento(double v) { this.nivelDeConhecimento = v; }
    public int getConhecimentoSemestre() { return conhecimentoSemestre; }
    public void setConhecimentoSemestre(int v) { this.conhecimentoSemestre = v; }
    public double getDesempenhoAcademico() { return desempenhoAcademico; }
    public void setDesempenhoAcademico(double v) { this.desempenhoAcademico = v; }
    public double getProgresso() { return progresso; }
    public void setProgresso(double progresso) { this.progresso = progresso; }
    public double getNotaAcumulada() { return notaAcumulada; }
    public void setNotaAcumulada(double v) { this.notaAcumulada = v; }
    public int getProvasFeitas() { return provasFeitas; }
    public void setProvasFeitas(int provasFeitas) { this.provasFeitas = provasFeitas; }
    public boolean isFormado() { return formado; }
    public void setFormado(boolean formado) { this.formado = formado; }
    public boolean isFoiParaSalaHoje() { return foiParaSalaHoje; }
    public void setFoiParaSalaHoje(boolean v) { this.foiParaSalaHoje = v; }
    public String getLocalAtual() { return localAtual; }
    public void setLocalAtual(String localAtual) { this.localAtual = localAtual; }
}