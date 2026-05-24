package jogo.model;

public class Jogador extends Personagem {
    // Atributos
    private double nivelDeConhecimento;
    private int conhecimentoSemestre;
    private int motivacao = 100;
    private double dinheiro;
    private double desempenhoAcademico = 0.0;
    private double progresso = 0.0;
    private Local local;
    private double notaAcumulada = 0.0;
    private int provasFeitas = 0;
    private boolean formado = false;
    private boolean foiParaSalaHoje = false;


    // Construtor/Local inicial do Jogador
    public Jogador(String nome, Local localinicial) {
        super(nome);
        this.local = localinicial;
    }

    // Getters e Setters
    public int getConhecimentoSemestre() {
        return conhecimentoSemestre;
    }

    public void setConhecimentoSemestre(int conhecimentoSemestre) {
        this.conhecimentoSemestre = conhecimentoSemestre;
    }

    public double getNivelDeConhecimento() {
        return nivelDeConhecimento;
    }

    public void setNivelDeConhecimento(double nivelDeConhecimento) {
        if (nivelDeConhecimento <= 0) {
            this.nivelDeConhecimento = 0;
        } else if (nivelDeConhecimento >= 110) {
            this.nivelDeConhecimento = 110;
        } else {
            this.nivelDeConhecimento = nivelDeConhecimento;
        }
    }

    public int getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(int motivacao) {
        if (motivacao <= 0) {
            this.motivacao = 0;
        } else if (motivacao >= 110) {
            this.motivacao = 110;
        } else {
            this.motivacao = motivacao;
        }
    }

    public double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public double getDesempenhoAcademico() {
        return desempenhoAcademico;
    }

    public void setDesempenhoAcademico(double desempenhoAcademico) {
        if (desempenhoAcademico <= 0) {
            this.desempenhoAcademico = 0;
        } else if (desempenhoAcademico >= 110) {
            this.desempenhoAcademico = 110;
        } else {
            this.desempenhoAcademico = desempenhoAcademico;
        }
    }

    public double getProgresso() {
        return progresso;
    }

    public void setProgresso(double progresso) {
        if (progresso >= 100){
            this.progresso = 100;
        } else if (progresso >= 0)
            this.progresso = progresso;
    }

    public double getNotaAcumulada() {
        return notaAcumulada;
    }

    public int getProvasFeitas() {
        return provasFeitas;
    }

    public boolean podeExplorar() {
        return getEnergia() >= 20;
    }

    public void setFormado(boolean formado) {
        this.formado = formado;
    }


    public Local getLocal() {
        return local;
    }


    // Para ele mudar de lugar
    public void mudarLocal(Local novoLocal) {
        this.local = novoLocal;
    }


    // Métodos do Jogador
    public void adicionarNota(double nota){
        notaAcumulada += nota;
        provasFeitas++;
    }


    public void resetarSemestre() {
        notaAcumulada = 0;
        provasFeitas = 0;
        conhecimentoSemestre = 0;
    }

    public boolean isFormado() {
        return formado;
    }

    public boolean isFoiParaSalaHoje() { return foiParaSalaHoje; }
    public void setFoiParaSalaHoje(boolean foi) { this.foiParaSalaHoje = foi; }
}

