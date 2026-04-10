package jogo.controller;

import jogo.model.*;
import java.util.Scanner;

public class JogoController {

    private Jogador jogador;
    private Mapa mapa;
    private GerenciadorDeEventosAL gerenciadorDeEventosAL;
    private Scanner sc;
    private boolean primeiroDia = true;
    private int diaAtual = 1;
    private boolean avisoHojeExibido = false;
    private boolean foiParaSalaHoje = false;

    public void iniciarJogo() {
        sc = new Scanner(System.in);
        mapa = new Mapa();
        gerenciadorDeEventosAL = new GerenciadorDeEventosAL();
        jogador = new Jogador("PH", mapa.getCasa());
        loopPrincipal();
    }

    // ========= LOOP PRINCIPAL ============

    private void loopPrincipal() {
        int opcao;
        do {
            exibirAvisoInicioDoDia();
            exibirMenu();
            opcao = sc.nextInt();
            executarAcao(opcao);
        } while (opcao != 0 && !jogador.isFormado());

        if (jogador.isFormado()) {
            System.out.println("\n🎓 Parabéns! Você concluiu o jogo!");
        }
    }

    // ========== MENU ==========

    private void exibirMenu() {
        System.out.println("\n📅 Dia " + diaAtual);
        System.out.println("=== O que deseja fazer? ===");
        System.out.println("1 - Estudar");
        System.out.println("2 - Explorar");
        System.out.println("3 - Lanchar");
        System.out.println("4 - Interagir com NPC");
        System.out.println("5 - Ir para casa");
        System.out.println("6 - Ir para a UEFS");
        System.out.println("7 - Ver status");
        System.out.println("8 - Trabalhar");
        System.out.println("9 - Jogar dominó");
        System.out.println("0 - Sair");
    }

    private void executarAcao(int opcao) {
        switch (opcao) {
            case 1 -> jogador.estudar();
            case 2 -> explorar();
            case 3 -> jogador.lanchar(mapa);
            case 4 -> interagirNPC();
            case 5 -> {
                if (jogador.irParaCasa(mapa)) {
                    avancarDia();
                }
            }
            case 6 -> {
                jogador.irParaUEFS(mapa);
                if (primeiroDia) {
                    primeiroDia = false;
                }
            }
            case 7 -> mostrarStatus();
            case 8 -> jogador.trabalhar();
            case 9 -> jogador.lazer();
            case 0 -> System.out.println("Até logo!");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void explorar() {
        if (!jogador.podeExplorar()) {
            System.out.println("Você está muito cansado para explorar!");
            return;
        }

        if (jogador.getLocal() instanceof LocalCasa) {
            System.out.println("Você está em casa. Precisa pegar o ônibus primeiro!");
            return;
        }

        System.out.println("\nPara onde deseja ir?");
        System.out.println("1 - Cantina");
        System.out.println("2 - Sala de Aula");
        System.out.println("3 - Laboratório");
        System.out.println("4 - Colegiado");
        System.out.println("5 - DA de ECOMP");

        int escolha = sc.nextInt();

        switch (escolha) {
            case 1 -> {
                jogador.mudarLocal(mapa.getCantina());
                ;
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            case 2 -> {
                jogador.mudarLocal(mapa.getSalaDeAula());
                jogador.getLocal().eventoAoEntrar(jogador);
                jogador.setFoiParaSalaHoje(true);
            }
            case 3 -> {
                jogador.mudarLocal(mapa.getLaboratorio());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            case 4 -> {
                jogador.mudarLocal(mapa.getColegiado());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            case 5 -> {
                jogador.mudarLocal(mapa.getDa());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void mostrarStatus() {
        System.out.println("\n========= STATUS =========");
        System.out.println("👤 Nome: " + jogador.getNome());
        System.out.println("📍 Local: " + jogador.getLocal().getNomeLocal());
        System.out.println("⚡ Energia: " + jogador.getEnergia());
        System.out.println("❤️  Saúde: " + jogador.getSaude());
        System.out.println("💡 Motivação: " + jogador.getMotivacao());
        System.out.println("📚 Conhecimento: " + jogador.getNivelDeConhecimento());
        System.out.println("💰 Dinheiro: R$" + jogador.getDinheiro());
        System.out.println("🎓 Progresso: " + jogador.getProgresso() + "%");
        System.out.println("==========================");
    }


    private void interagirNPC() {
        Local local = jogador.getLocal();

        if (local.getPersonagensPresentes().isEmpty()) {
            System.out.println("Não há NPCs aqui.");
            return;
        }

        System.out.println("Escolha um NPC:");
        for (int i = 0; i < local.getPersonagensPresentes().size(); i++) {
            System.out.println(i + " - " + local.getPersonagensPresentes().get(i).getNome());
        }

        if (!sc.hasNextInt()) {
            System.out.println("Opção inválida!");
            sc.next(); // limpa o input
            return;
        }

        int escolha = sc.nextInt();

        if (escolha < 0 || escolha >= local.getPersonagensPresentes().size()) {
            System.out.println("Opção inválida!");
            return;
        }

        NPC npc = local.getPersonagensPresentes().get(escolha);
        jogador.interagirComNPC(npc);
    }


    // ========== TEMPO ==========

    private void avancarDia() {
        exibirAvisoDeAmanha();

        // verifica ANTES de incrementar
        verificarEventosObrigatorios();

        diaAtual++;
        avisoHojeExibido = false;
        gerenciadorDeEventosAL.sortearEventoAleatorio(jogador);
        jogador.setFoiParaSalaHoje(false);
    }

    private void verificarEventosObrigatorios() {
        int diaDoSemestre = (diaAtual - 1) % 21 + 1; // usa diaAtual atual, sem incremento
        switch (diaDoSemestre) {
            case 7  -> new EventoOBProva(1).aplicarEvento(jogador);
            case 14 -> new EventoOBProva(2).aplicarEvento(jogador);
            case 20 -> new EventoOBProva(3).aplicarEvento(jogador);
            case 21 -> new EventoOBFimDeSemestre().aplicarEvento(jogador);
        }
    }


    private void exibirAvisoInicioDoDia() {
        int diaDoSemestre = (diaAtual - 1) % 21 + 1;

        if (!avisoHojeExibido) {
            if (diaDoSemestre == 7 || diaDoSemestre == 14 || diaDoSemestre == 20) {
                System.out.println("📝 HOJE TEM PROVA! Vá para a Sala de Aula.");
                avisoHojeExibido = true;
            }
        }
    }

    private void exibirAvisoDeAmanha() {
        int diaDoSemestre = (diaAtual - 1) % 21 + 1;

        if (diaDoSemestre == 6 || diaDoSemestre == 13 || diaDoSemestre == 19) {
            System.out.println("⚠️  AMANHÃ TEM PROVA! Não se esqueça de ir para a Sala de Aula.");
        }
    }

}
