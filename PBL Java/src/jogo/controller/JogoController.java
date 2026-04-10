package jogo.controller;

import jogo.model.*;
import java.util.Scanner;

public class JogoController {

    private Jogador jogador;
    private Scanner sc;

    public void iniciarJogo() {
        sc = new Scanner(System.in);
        jogador = new Jogador("PH", new LocalPontoDeOnibus());

        loopPrincipal();
    }

    private void explorar() {
        if (!jogador.podeExplorar()) {
            System.out.println("Você está muito cansado para explorar!");
            return;
        }

        System.out.println("\nPara onde deseja ir?");
        System.out.println("1 - Cantina");
        System.out.println("2 - Sala de Aula");
        System.out.println("3 - Laboratório");
        System.out.println("4 - Colegiado");
        System.out.println("5 - Ponto de ônibus");

        int escolha = sc.nextInt();

        switch (escolha) {
            case 1 -> {
                jogador.mudarLocal(new LocalCantina());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            case 2 -> {
                jogador.mudarLocal(new LocalSalaDeAula());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            case 3 -> {
                jogador.mudarLocal(new LocalLaboratorio());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            case 4 -> {
                jogador.mudarLocal(new LocalColegiado());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            case 5 -> {
                jogador.mudarLocal(new LocalPontoDeOnibus());
                jogador.getLocal().eventoAoEntrar(jogador);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void loopPrincipal() {
        int opcao;

        do {
            System.out.println("\n=== O que deseja fazer? ===");
            System.out.println("1 - Estudar");
            System.out.println("2 - Explorar");
            System.out.println("3 - Lanchar");
            System.out.println("4 - Pegar ônibus");
            System.out.println("5 - Interagir com NPC");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();

            executarAcao(opcao);

        } while (opcao != 0);
    }

    private void executarAcao(int opcao) {
        switch (opcao) {
            case 1 -> jogador.estudar();
            case 2 -> explorar();
            case 3 -> jogador.lanchar();
            case 4 -> jogador.pegarOnibus();
            case 5 -> interagirNPC();
        }
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

        int escolha = sc.nextInt();
        NPC npc = local.getPersonagensPresentes().get(escolha);

        jogador.interagirComNPC(npc);
    }
}