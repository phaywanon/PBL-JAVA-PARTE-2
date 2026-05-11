//package jogo.controller;
//
//import jogo.model.*;
//import java.util.Scanner;
//
//public class JogoController {
//
//    private Jogador jogador;
//    private Mapa mapa;
//    private GerenciadorDeEventosAL gerenciadorDeEventosAL;
//    private Scanner sc;
//    private boolean primeiroDia = true;
//    private int diaAtual = 1;
//    private boolean avisoHojeExibido = false;
//
//    public void iniciarJogo() {
//        sc = new Scanner(System.in);
//        mapa = new Mapa();
//        gerenciadorDeEventosAL = new GerenciadorDeEventosAL();
//        jogador = new Jogador("PH", mapa.getCasa());
//        loopPrincipal();
//    }
//
//    // ========= LOOP PRINCIPAL ============
//
//    private void loopPrincipal() {
//        int opcao;
//        do {
//            exibirAvisoInicioDoDia();
//            exibirMenu();
//            opcao = sc.nextInt();
//            executarAcao(opcao);
//        } while (opcao != 0 && !jogador.isFormado());
//
//        if (jogador.isFormado()) {
//            System.out.println("\n🎓 Parabéns! Você concluiu o jogo!");
//        }
//    }
//
//    // ========== MENU ==========
//
//    private void exibirMenu() {
//        System.out.println("\n📅 Dia " + diaAtual);
//        System.out.println("=== O que deseja fazer? ===");
//        System.out.println("1 - Estudar");
//        System.out.println("2 - Explorar");
//        System.out.println("3 - Lanchar");
//        System.out.println("4 - Interagir com NPC");
//        System.out.println("5 - Ir para casa");
//        System.out.println("6 - Ir para a UEFS");
//        System.out.println("7 - Ver status");
//        System.out.println("8 - Trabalhar");
//        System.out.println("9 - Jogar dominó");
//        System.out.println("0 - Sair");
//    }
//
//    private void executarAcao(int opcao) {
//        switch (opcao) {
//            case 1 -> jogador.estudar();
//            case 2 -> explorar();
//            case 3 -> jogador.lanchar(mapa);
//            case 4 -> interagirNPC();
//            case 5 -> {
//                if (jogador.irParaCasa(mapa)) {
//                    avancarDia();
//                }
//            }
//            case 6 -> {
//                jogador.irParaUEFS(mapa);
//                if (primeiroDia) {
//                    primeiroDia = false;
//                }
//            }
//            case 7 -> mostrarStatus();
//            case 8 -> jogador.trabalhar();
//            case 9 -> jogador.lazer();
//            case 0 -> System.out.println("Até logo!");
//            default -> System.out.println("Opção inválida!");
//        }
//    }
//
//    private void explorar() {
//        if (!jogador.podeExplorar()) {
//            System.out.println("Você está muito cansado para explorar!");
//            return;
//        }
//
//        if (jogador.getLocal() instanceof LocalCasa) {
//            System.out.println("Você está em casa. Precisa pegar o ônibus primeiro!");
//            return;
//        }
//
//        System.out.println("\nPara onde deseja ir?");
//        System.out.println("1 - Cantina");
//        System.out.println("2 - Sala de Aula");
//        System.out.println("3 - Laboratório");
//        System.out.println("4 - Colegiado");
//        System.out.println("5 - DA de ECOMP");
//
//        int escolha = sc.nextInt();
//
//        switch (escolha) {
//            case 1 -> {
//                jogador.mudarLocal(mapa.getCantina());
//                jogador.getLocal().eventoAoEntrar(jogador);
//            }
//            case 2 -> {
//                jogador.mudarLocal(mapa.getSalaDeAula());
//                jogador.getLocal().eventoAoEntrar(jogador);
//                jogador.setFoiParaSalaHoje(true);
//            }
//            case 3 -> {
//                jogador.mudarLocal(mapa.getLaboratorio());
//                jogador.getLocal().eventoAoEntrar(jogador);
//            }
//            case 4 -> {
//                jogador.mudarLocal(mapa.getColegiado());
//                jogador.getLocal().eventoAoEntrar(jogador);
//            }
//            case 5 -> {
//                jogador.mudarLocal(mapa.getDa());
//                jogador.getLocal().eventoAoEntrar(jogador);
//            }
//            default -> System.out.println("Opção inválida!");
//        }
//    }
//
//    private void mostrarStatus() {
//        System.out.println("\n========= STATUS =========");
//        System.out.println("👤 Nome: " + jogador.getNome());
//        System.out.println("📍 Local: " + jogador.getLocal().getNomeLocal());
//        System.out.println("⚡ Energia: " + jogador.getEnergia());
//        System.out.println("❤️  Saúde: " + jogador.getSaude());
//        System.out.println("💡 Motivação: " + jogador.getMotivacao());
//        System.out.println("📚 Conhecimento: " + jogador.getNivelDeConhecimento());
//        System.out.println("💰 Dinheiro: R$" + jogador.getDinheiro());
//        System.out.println("🎓 Progresso: " + jogador.getProgresso() + "%");
//        System.out.println("==========================");
//    }
//
//
//    private void interagirNPC() {
//        Local local = jogador.getLocal();
//
//        if (local.getPersonagensPresentes().isEmpty()) {
//            System.out.println("Não há NPCs aqui.");
//            return;
//        }
//
//        System.out.println("Escolha um NPC:");
//        for (int i = 0; i < local.getPersonagensPresentes().size(); i++) {
//            System.out.println(i + " - " + local.getPersonagensPresentes().get(i).getNome());
//        }
//
//        if (!sc.hasNextInt()) {
//            System.out.println("Opção inválida!");
//            sc.next(); // limpa o input
//            return;
//        }
//
//        int escolha = sc.nextInt();
//
//        if (escolha < 0 || escolha >= local.getPersonagensPresentes().size()) {
//            System.out.println("Opção inválida!");
//            return;
//        }
//
//        NPC npc = local.getPersonagensPresentes().get(escolha);
//        jogador.interagirComNPC(npc);
//    }
//
//
//    // ========== TEMPO ==========
//
//    private void avancarDia() {
//        exibirAvisoDeAmanha();
//
//        // verifica ANTES de incrementar
//        verificarEventosObrigatorios();
//
//        diaAtual++;
//        avisoHojeExibido = false;
//        gerenciadorDeEventosAL.sortearEventoAleatorio(jogador);
//        jogador.setFoiParaSalaHoje(false);
//    }
//
//    private void verificarEventosObrigatorios() {
//        int diaDoSemestre = (diaAtual - 1) % 21 + 1; // usa diaAtual atual, sem incremento
//        switch (diaDoSemestre) {
//            case 7  -> new EventoOBProva(1).aplicarEvento(jogador);
//            case 14 -> new EventoOBProva(2).aplicarEvento(jogador);
//            case 20 -> new EventoOBProva(3).aplicarEvento(jogador);
//            case 21 ->{
//                new EventoOBFimDeSemestre().aplicarEvento(jogador);
//                new EventoOBFormatura().aplicarEvento(jogador);
//            }
//        }
//    }
//
//
//    private void exibirAvisoInicioDoDia() {
//        int diaDoSemestre = (diaAtual - 1) % 21 + 1;
//
//        if (!avisoHojeExibido) {
//            if (diaDoSemestre == 7 || diaDoSemestre == 14 || diaDoSemestre == 20) {
//                System.out.println("📝 HOJE TEM PROVA! Vá para a Sala de Aula.");
//                avisoHojeExibido = true;
//            }
//        }
//    }
//
//    private void exibirAvisoDeAmanha() {
//        int diaDoSemestre = (diaAtual - 1) % 21 + 1;
//
//        if (diaDoSemestre == 6 || diaDoSemestre == 13 || diaDoSemestre == 19) {
//            System.out.println("⚠️  AMANHÃ TEM PROVA! Não se esqueça de ir para a Sala de Aula.");
//        }
//    }
//
//}


package jogo.controller;

import jogo.model.*;
import jogo.service.JogoService;
import java.util.Scanner;

public class JogoController {

    private JogoService jogoService;
    private Scanner sc;

    private int lerOpcaoValida(int min, int max) {
        while (true) {
            if (sc.hasNextInt()) {
                int opcao = sc.nextInt();
                if (opcao >= min && opcao <= max) return opcao;
                System.out.println("Digite um número entre " + min + " e " + max + "!");
            } else {
                System.out.println("Digite um número válido!");
                sc.next(); // descarta o input inválido
            }
        }
    }

    public void iniciarJogo() {
        sc = new Scanner(System.in);
        exibirMenuInicial();
    }

    private void exibirMenuInicial() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("   🎓 BIXOQUEST               ");
        System.out.println("   Da Matrícula à Formatura    ");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("1 - Novo Jogo");
        System.out.println("2 - Carregar Jogo");
        System.out.println("3 - Deletar Save");
        System.out.println("0 - Sair");

        int opcao = lerOpcaoValida(0, 3);
        switch (opcao) {
            case 1 -> iniciarNovoJogo();
            case 2 -> carregarJogo();
            case 3 -> deletarSave();
            case 0 -> System.out.println("Até logo!");
            default -> { System.out.println("Opção inválida!"); exibirMenuInicial(); }
        }
    }

    private void exibirSlots() {
        JogoService temp = new JogoService();
        System.out.println("=== SLOTS DE SAVE ===");
        for (int i = 1; i <= 3; i++) {
            EstadoDoJogo estado = temp.carregarEstado("slot" + i);
            if (estado == null) {
                System.out.println(i + " - [Vazio]");
            } else {
                System.out.println(i + " - " + estado.getNomeJogador()
                        + " | Dia " + estado.getDiaAtual()
                        + " | " + estado.getUltimoAcesso());
            }
        }
    }

    private void iniciarNovoJogo() {
        exibirSlots();
        System.out.println("Escolha um slot (1, 2 ou 3):");
        int slot = lerOpcaoValida(0, 3);
        if (slot == 0) { exibirMenuInicial(); return; }

        sc.nextLine(); // limpa o buffer após nextInt
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();
        System.out.println("Digite sua matrícula:");
        String matricula = sc.nextLine();

        jogoService = new JogoService();
        jogoService.novoJogo("slot" + slot, nome, matricula);
        loopPrincipal();
    }

    private void carregarJogo() {
        exibirSlots();
        System.out.println("Escolha um slot para carregar:");
        int slot = lerOpcaoValida(0, 3);
        if (slot == 0) { exibirMenuInicial(); return; }


        jogoService = new JogoService();
        EstadoDoJogo estado = jogoService.carregarEstado("slot" + slot);
        if (estado == null) {
            System.out.println("Slot vazio! Escolha outro.");
            carregarJogo(); // volta a pedir
            return;
        }
        jogoService.carregarJogo("slot" + slot);
        loopPrincipal();
    }

    private void deletarSave() {
        exibirSlots();
        System.out.println("Escolha um slot para deletar:");
        int slot = lerOpcaoValida(0, 3);
        if (slot == 0) { exibirMenuInicial(); return; }

        jogoService = new JogoService();
        jogoService.deletarJogo("slot" + slot);
        System.out.println("Save deletado!");
        exibirMenuInicial(); // volta pro menu
    }



    private void loopPrincipal() {
        int opcao;
        do {
            exibirAvisoInicioDoDia(); // consulta o service, não calcula nada
            exibirMenu();
            opcao = lerOpcaoValida(0, 10);
            executarAcao(opcao);
        } while (opcao != 0 && !jogoService.getJogador().isFormado());

        if (jogoService.getJogador().isFormado()) {
            System.out.println("\n🎓 Parabéns! Você concluiu o jogo!");
        }
    }

    private void executarAcao(int opcao) {
        Local local = jogoService.getJogador().getLocal();

        if (local instanceof LocalCasa)              executarAcaoCasa(opcao);
        else if (local instanceof LocalPontoDeOnibus) executarAcaoPonto(opcao);
        else                                          executarAcaoUEFS(opcao);
    }

    private void executarAcaoCasa(int opcao) {
        switch (opcao) {
            case 1 -> jogoService.irParaUEFS();
            case 7 -> mostrarStatus();
            case 0 -> System.out.println("Até logo!");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void executarAcaoPonto(int opcao) {
        switch (opcao) {
            case 1 -> jogoService.entrarNaUEFS(); // vai pra cantina ou último local
            case 2 -> jogoService.irParaCasa();
            case 3 -> jogoService.fazerCarinhoNoBichinho();
            case 7 -> mostrarStatus();
            case 0 -> System.out.println("Até logo!");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void executarAcaoUEFS(int opcao) {
        Local local = jogoService.getJogador().getLocal();

        switch (opcao) {
            case 1 -> jogoService.estudar();
            case 2 -> explorar(); // submenu de locais
            case 3 -> jogoService.irParaPonto();
            case 4 -> {
                if (local instanceof LocalCantina) jogoService.lanchar();
                else if (local instanceof LocalDA) jogoService.lazer();
                else System.out.println("Opção inválida aqui!");
            }
            case 5 -> jogoService.cursarDisciplina();
            case 6 -> jogoService.trabalhar();
            case 7 -> interagirNPC();
            case 8 -> mostrarStatus();
            case 9 -> {
                jogoService.salvarJogo(jogoService.getSlotAtual());
                System.out.println("Jogo salvo!");
            }
            case 0 -> System.out.println("Até logo!");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void explorar() {
        Jogador jogador = jogoService.getJogador();
        if (!jogador.podeExplorar()) {
            System.out.println("Você está muito cansado para explorar!");
            return;
        }
        if (jogador.getLocal() instanceof LocalCasa) {
            System.out.println("Você está em casa. Precisa pegar o ônibus primeiro!");
            return;
        }
        System.out.println("\nPara onde deseja ir?");
        System.out.println("1 - Cantina | 2 - Sala de Aula | 3 - Laboratório | 4 - Colegiado | 5 - DA de ECOMP");
        int escolha = sc.nextInt();
        jogoService.explorar(escolha); // controller só passa a escolha, service decide o que fazer
    }

    private void interagirNPC() {
        Local local = jogoService.getJogador().getLocal();
        if (local.getPersonagensPresentes().isEmpty()) {
            System.out.println("Não há NPCs aqui.");
            return;
        }
        System.out.println("Escolha um NPC:");
        for (int i = 0; i < local.getPersonagensPresentes().size(); i++) {
            System.out.println(i + " - " + local.getPersonagensPresentes().get(i).getNome());
        }
        int escolha = sc.nextInt();
        if (escolha < 0 || escolha >= local.getPersonagensPresentes().size()) {
            System.out.println("Opção inválida!");
            return;
        }
        jogoService.interagirComNPC(local.getPersonagensPresentes().get(escolha));
    }

    private void exibirAvisoInicioDoDia() {
        // Controller pergunta pro service — não calcula ele mesmo
        if (!jogoService.isAvisoHojeExibido() &&
                jogoService.getEventoService().hojeTemProva(jogoService.getDiaAtual())) {
            System.out.println("📝 HOJE TEM PROVA! Lembre de passar na Sala de Aula.");
            jogoService.setAvisoHojeExibido(true);
        }
    }

    private void mostrarStatus() {
        int dia = jogoService.getDiaAtual();
        int semestre = ((dia - 1) / 21) + 1;
        int diaDoSemestre = (dia - 1) % 21 + 1;

        Jogador j = jogoService.getJogador();
        System.out.println("\n========= STATUS =========");
        System.out.println("📅 Dia " + diaDoSemestre + " de 21 | Semestre " + semestre);
        System.out.println("👤 Nome: " + j.getNome());
        System.out.println("📍 Local: " + j.getLocal().getNomeLocal());
        System.out.println("⚡ Energia: " + j.getEnergia());
        System.out.println("❤️  Saúde: " + j.getSaude());
        System.out.println("💡 Motivação: " + j.getMotivacao());
        System.out.println("📚 Conhecimento: " + j.getNivelDeConhecimento());
        System.out.println("💰 Dinheiro: R$" + j.getDinheiro());
        System.out.println("🎓 Progresso: " + j.getProgresso() + "%");
        System.out.println("==========================");
    }

    private void exibirMenu() {
        Local local = jogoService.getJogador().getLocal();

        System.out.println("📍 " + jogoService.getJogador().getLocal().getNomeLocal());

        if (local instanceof LocalCasa)           exibirMenuCasa();
        else if (local instanceof LocalPontoDeOnibus) exibirMenuPonto();
        else                                       exibirMenuUEFS();
    }

    private void exibirMenuCasa() {
        int dia = jogoService.getDiaAtual();
        int semestre = ((dia - 1) / 21) + 1;
        int diaDoSemestre = (dia - 1) % 21 + 1;

        System.out.println("╔══════════════════════════════╗");
        System.out.println("  📅 Dia " + diaDoSemestre + " | 📆 Semestre " + semestre);
        System.out.println("╚══════════════════════════════╝");

        System.out.println("1 - Ir para UEFS");
        System.out.println("7 - Ver status | 0 - Sair");
    }

    private void exibirMenuPonto() {
        System.out.println("1 - Explorar o campus");
        System.out.println("2 - Ir para casa");
        System.out.println("3 - Fazer carinho nos bichinhos");
        System.out.println("7 - Ver status | 0 - Sair");
    }

    private void exibirMenuUEFS() {
        Local local = jogoService.getJogador().getLocal();

        // ações comuns a qualquer local da UEFS
        System.out.println("1 - Estudar");
        System.out.println("2 - Explorar outro local");
        System.out.println("3 - Ir para o ponto de ônibus");

        // ações específicas do local
        if (local instanceof LocalCantina)      System.out.println("4 - Lanchar");
        if (local instanceof LocalSalaDeAula)   System.out.println("5 - Cursar Disciplina");
        if (local instanceof LocalLaboratorio)  { System.out.println("5 - Cursar Disciplina"); System.out.println("6 - Trabalhar"); }
        if (local instanceof LocalDA)           System.out.println("4 - Jogar Dominó");

        // sempre disponível
        System.out.println("7 - Interagir com NPC");
        System.out.println("8 - Ver status");
        System.out.println("9 - Salvar jogo");
        System.out.println("0 - Sair");
    }
}