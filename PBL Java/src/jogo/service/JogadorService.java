package jogo.service;

import jogo.model.*;

public class JogadorService{
    private Jogador jogador;

    public JogadorService(Jogador jogador) {
        this.jogador = jogador;
    }

    // Métodos do Jogador
    public void estudar() {
        if (jogador.getMotivacao() < 10) {
            System.out.println("Você está muito desmotivado para estudar!");
            return;
        }
        if (jogador.getEnergia() < 10) {
            System.out.println("Você está cansado demais para estudar!");
            return;
        }
        if (jogador.getMotivacao() >= 10) {
            jogador.setNivelDeConhecimento(jogador.getNivelDeConhecimento() + 0.5);
            jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 5);
            jogador.setEnergia(jogador.getEnergia() - 3);
            jogador.setMotivacao(jogador.getMotivacao() - 2);
        } else {
            System.out.println("Você está muito desmotivado para estudar. Não vai conseguir absorver nada dessa maneira!");
        }
    }

    public void lanchar(Mapa mapa) {
        if (!(jogador.getLocal() instanceof LocalCantina)) {
            System.out.println("Você precisa estar na cantina para comprar um lanche!");
            return;
        }
        if (jogador.getDinheiro() >= 5) {
            jogador.setDinheiro(jogador.getDinheiro() - 5);
            jogador.setEnergia(jogador.getEnergia() + 3);
            jogador.setMotivacao(jogador.getMotivacao() + 3);
            System.out.println("Você comprou um lanche e está levemente revigorado!");
        } else {
            System.out.println("Dinheiro insuficiente!");
        }

    }


    public boolean irParaCasa(Mapa mapa) {
        if (jogador.getLocal() instanceof LocalCasa) {
            System.out.println("Você já está em casa!");
            return false;
        }
        jogador.mudarLocal(mapa.getPontoDeOnibus());
        if (jogador.getDinheiro() >= 3) {
            jogador.setDinheiro(jogador.getDinheiro() - 3);
            System.out.println("Você pegou o ônibus e chegou em casa.");
        } else {
            System.out.println("Você está sem dinheiro e vai precisar ir andando pra casa.");
            jogador.setEnergia(jogador.getEnergia() - 15);
        }
        jogador.mudarLocal(mapa.getCasa());
        jogador.setEnergia(90);
        jogador.setSaude(Math.min(jogador.getSaude() + 15, 100));
        System.out.println("Você dormiu, descansou e acordou mais disposto!");

        return true;
    }


    public void irParaUEFS(Mapa mapa) {
        if (!(jogador.getLocal() instanceof LocalCasa)) {
            System.out.println("Você já está na UEFS!");
            return;
        }
        if (jogador.getDinheiro() >= 3) {
            jogador.setDinheiro(jogador.getDinheiro() - 3);
            jogador.mudarLocal(mapa.getPontoDeOnibus());
            System.out.println("Você chegou na UEFS!");
        } else {
            System.out.println("Sem dinheiro pra passagem! Vai andando...");
            jogador.setEnergia(jogador.getEnergia() - 15);
            jogador.mudarLocal(mapa.getPontoDeOnibus());
        }
    }

    public void cursarDisciplina() {
        if (!(jogador.getLocal() instanceof LocalSalaDeAula) &&
                !(jogador.getLocal() instanceof LocalLaboratorio)) {
            System.out.println("Você precisa estar na sala de aula ou no laboratório!");
            return;
        }
        if (jogador.getEnergia() >= 20) {
            jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 15);
            jogador.setEnergia(jogador.getEnergia() - 20);
            System.out.println("Você assistiu à aula e aprendeu bastante!");
        } else {
            System.out.println("Você está cansado demais para assistir aula!");
        }
    }

    public void lazer() {
        if (!(jogador.getLocal() instanceof LocalDA)) {
            System.out.println("Você precisa estar no DA de ECOMP para se divertir!");
            return;
        }
        if (jogador.getDinheiro() >= 1) {
            jogador.setMotivacao(jogador.getMotivacao() + 10);
            jogador.setEnergia(jogador.getEnergia() + 5);
            jogador.setDinheiro(jogador.getDinheiro() - 1);
            System.out.println("Você jogou um dominó apostado no DA de ECOMP e conseguiu relaxar um pouco com as resenhas e risadas! (Apesar de ter perdido dinheiro kkkkk");
        } else {
            System.out.println("Se divertir custa dinheiro e você está com a conta zerada! Volte depois.");
        }

    }


    public void interagirComNPC(NPC npc) {
        npc.interagir(jogador);
    }

    public void trabalhar() {
        if (!(jogador.getLocal() instanceof LocalLaboratorio)) {
            System.out.println("Você precisa estar no laboratório para trabalhar!");
            return;
        }
        if (jogador.getEnergia() >= 20) {
            jogador.setDinheiro(jogador.getDinheiro() + 20);
            jogador.setEnergia(jogador.getEnergia() - 20);
            jogador.setConhecimentoSemestre(jogador.getConhecimentoSemestre() + 5);
            System.out.println("Você fez uma monitoria e ganhou 20 reais!");
        } else {
            System.out.println("Você está cansado demais para trabalhar.");
        }
    }

}
