package jogo.service;

import jogo.model.*;

public class JogadorService{
    private Jogador jogador;

    public JogadorService(Jogador jogador) {
        this.jogador = jogador;
    }

    // Métodos do Jogador
    public String estudar() {
        if (jogador.getMotivacao() < 10) {
            return "Você está muito desmotivado para estudar!";
        }
        if (jogador.getEnergia() < 10) {
            return "Você está cansado demais para estudar!";
        }

        jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 0.5);
        jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 5);
        jogador.setEnergia(jogador.getEnergia() - 3);
        jogador.setMotivacao(jogador.getMotivacao() - 2);

        return "Você estudou e absorveu bastante conteúdo!";
    }


    public String lanchar(Mapa mapa) {
        if (!(jogador.getLocal() instanceof LocalCantina)) {
            return "Você precisa estar na cantina para comprar um lanche!";
        }
        if (jogador.getDinheiro() >= 5) {
            jogador.setDinheiro(jogador.getDinheiro() - 5);
            jogador.setEnergia(jogador.getEnergia() + 3);
            jogador.setMotivacao(jogador.getMotivacao() + 3);
            jogador.setSaude(jogador.getSaude() + 5);
            return "Você comprou um lanche e está levemente revigorado!";
        } else {
            return "Dinheiro insuficiente!";
        }

    }


    public String irParaCasa(Mapa mapa) {
        if (jogador.getLocal() instanceof LocalCasa) {
            return "Você já está em casa!" ;
        }

        String msg = "";

        jogador.mudarLocal(mapa.getPontoDeOnibus());

        if (jogador.getDinheiro() >= 3) {
            jogador.setDinheiro(jogador.getDinheiro() - 3);
            msg += "Você pegou o ônibus e chegou em casa.\n";
        } else {
            msg +="Você está sem dinheiro e vai precisar ir andando pra casa.\n";
            jogador.setEnergia(jogador.getEnergia() - 15);
        }
        jogador.mudarLocal(mapa.getCasa());
        jogador.setEnergia(90);
        jogador.setSaude(Math.min(jogador.getSaude() + 15, 100));
        msg += "\nVocê dormiu, descansou e acordou mais disposto!";

        return msg;
    }


    public String irParaUEFS(Mapa mapa) {
        if (!(jogador.getLocal() instanceof LocalCasa)) {
            return "Você já está na UEFS!";
        }
        if (jogador.getDinheiro() >= 3) {
            jogador.setDinheiro(jogador.getDinheiro() - 3);
            jogador.mudarLocal(mapa.getPontoDeOnibus());
            return "Você chegou na UEFS!";
        } else {
            jogador.setEnergia(jogador.getEnergia() - 15);
            jogador.mudarLocal(mapa.getPontoDeOnibus());
            return "Sem dinheiro pra passagem! Vai andando...";
        }
    }

    public String cursarDisciplina() {
        if (!(jogador.getLocal() instanceof LocalSalaDeAula) &&
                !(jogador.getLocal() instanceof LocalLaboratorio)) {
            return "Você precisa estar na sala de aula ou no laboratório!";
        }
        if (jogador.getEnergia() >= 20) {
            jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 15);
            jogador.setEnergia(jogador.getEnergia() - 20);
            return "Você assistiu à aula e aprendeu bastante!";
        } else {
            return "Você está cansado demais para assistir aula!";
        }
    }

    public String lazer() {
        if (!(jogador.getLocal() instanceof LocalDA)) {
            return "Você precisa estar no DA de ECOMP para se divertir!";
        }
        if (jogador.getDinheiro() >= 1) {
            jogador.setMotivacao(jogador.getMotivacao() + 10);
            jogador.setEnergia(jogador.getEnergia() + 5);
            jogador.setDinheiro(jogador.getDinheiro() - 1);
            return "Você jogou um dominó apostado no DA de ECOMP e conseguiu relaxar um pouco com as resenhas e risadas! (Apesar de ter perdido dinheiro kkkkk)";
        } else {
            return "Se divertir custa dinheiro e você está com a conta zerada! Volte depois.";
        }

    }


    public String interagirComNPC(NPC npc) {
        return npc.interagir(jogador);
    }

    public String trabalhar() {
        if (!(jogador.getLocal() instanceof LocalLaboratorio)) {
            return "Você precisa estar no laboratório para trabalhar!";
        }
        if (jogador.getEnergia() >= 20) {
            jogador.setDinheiro(jogador.getDinheiro() + 20);
            jogador.setEnergia(jogador.getEnergia() - 20);
            jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 5);
            return "Você fez uma monitoria e ganhou 20 reais!";
        } else {
            return "Você está cansado demais para trabalhar.";
        }
    }

}
