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

    // Construtor
    public Jogador(String nome) {
        super(nome);
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

    // Local inicial do Jogador
    public Jogador(String nome, Local localinicial) {
        this(nome);
        this.local = localinicial;
    }

    // Para ele mudar de lugar
    public void mudarLocal(Local novoLocal) {
        this.local = novoLocal;
        System.out.println("Você agora está em: " + local.getNomeLocal());
    }


    // Métodos do Jogador
    public void estudar() {
        if (motivacao >= 10) {
            nivelDeConhecimento += 0.5;
            conhecimentoSemestre += 5;
            setEnergia(getEnergia() - 3);
            motivacao -= 2;
        } else {
            System.out.println("Você está muito desmotivado para estudar. Não vai conseguir absorver nada dessa maneira!");
        }
    }


    public void lanchar(Mapa mapa) {
        if (!(local instanceof LocalCantina)){
            System.out.println("Você precisa estar na cantina para comprar um lanche!");
            return;
        }
        if (dinheiro >= 5) {
            dinheiro -= 5;
            setEnergia(getEnergia() + 3);
            motivacao += 3;
            System.out.println("Você comprou um lanche e está levemente revigorado!");
        } else {
            System.out.println("Dinheiro insuficiente!");
        }

    }


    public void irParaCasa(Mapa mapa) {
        if (local instanceof LocalCasa) {
            System.out.println("Você já está em casa!");
            return;
        }
        mudarLocal(mapa.getPontoDeOnibus());
        if (dinheiro >= 3) {
            dinheiro -= 3;
            System.out.println("Você pegou o ônibus e chegou em casa.");
            mudarLocal(mapa.getCasa());
        } else {
            System.out.println("Você está sem dinheiro e vai precisar ir andando pra casa.");
            setEnergia(getEnergia() - 15);
            mudarLocal(mapa.getCasa());
        }
        setEnergia(90);
        System.out.println("Você dormiu e acordou mais disposto!");
    }


    public void irParaUEFS(Mapa mapa) {
        if (!(local instanceof LocalCasa)) {
            System.out.println("Você já está na UEFS!");
            return;
        }
        if (dinheiro >= 3) {
            dinheiro -= 3;
            mudarLocal(mapa.getPontoDeOnibus());
            System.out.println("Você chegou na UEFS!");
        } else {
            System.out.println("Sem dinheiro pra passagem! Vai andando...");
            setEnergia(getEnergia() - 15);
            mudarLocal(mapa.getPontoDeOnibus());
        }
    }


    public void adicionarNota(double nota){
        notaAcumulada += nota;
        provasFeitas++;
    }


    public void resetarSemestre() {
        notaAcumulada = 0;
        provasFeitas = 0;
        conhecimentoSemestre = 0;
    }


    public void cursarDisciplina() {
        if (!(local instanceof LocalSalaDeAula) &&
                !(local instanceof LocalLaboratorio)) {
            System.out.println("Você precisa estar na sala de aula ou no laboratório!");
            return;
        }
        if (getEnergia() >= 20) {
            this.conhecimentoSemestre += 15;
            setEnergia(getEnergia() - 20);
            this.progresso += 1.0; // Cada aula conta para a formatura
            System.out.println("Você assistiu à aula e aprendeu bastante!");
        } else {
            System.out.println("Você está cansado demais para assistir aula!");
        }
    }

    public void lazer() {
        if (!(local instanceof LocalDA)) {
            System.out.println("Você precisa estar no DA de ECOMP para se divertir!");
            return;
        }
        if (dinheiro >= 1) {
            motivacao += 10;
            setEnergia(getEnergia() + 5);
            dinheiro -= 1;
            System.out.println("Você jogou um dominó apostado no DA de ECOMP e conseguiu relaxar um pouco com as resenhas e risadas! (Apesar de ter perdido dinheiro kkkkk");
        } else {
            System.out.println("Se divertir custa dinheiro e você está com a conta zerada! Volte depois.");
        }

    }


    public void interagirComNPC(NPC npc) { npc.interagir(this); }


    public boolean isFormado() {
        return formado;
    }


    public void trabalhar() {
        if (!(local instanceof LocalLaboratorio)){
            System.out.println("Você precisa estar no laboratório para trabalhar!");
            return;
        }
        if (getEnergia() >= 20) {
            setDinheiro(getDinheiro() + 20);
            setEnergia(getEnergia() - 20);
            conhecimentoSemestre += 5;
            System.out.println("Você fez uma monitoria e ganhou 20 reais!");
        } else {
            System.out.println("Você está cansado demais para trabalhar.");
        }
    }
}

