//package jogo.main;
//
//import jogo.model.*;
//
////public class Jogo {
//
////    public static void main(String[] args) {
////        Local pontoOnibus = new Local("Ponto de ônibus");
////        Jogador jogador = new Jogador("PH", pontoOnibus);
////
////        jogador.mostrarStatus(); // do Personagem
////        System.out.println("Local do jogador: " + jogador.getLocal().getNomeLocal());
////
////        jogador.explorar();
////        jogador.estudar();
////        jogador.lanchar();
////        jogador.pegarOnibus();
////        jogador.mostrarStatus();
////    }
////}
//
//public class Jogo {
//    public static void main(String[] args) {
//
//        // Criando jogador
//        Jogador jogador = new Jogador("PH", new LocalCantina());
//
//        // Evento ao entrar
//        jogador.getLocal().eventoAoEntrar(jogador);
//
//        // Listar NPCs do local
//        System.out.println("\nNPCs presentes:");
//        for (NPC npc : jogador.getLocal().getPersonagensPresentes()) {
//            System.out.println("- " + npc.getNome());
//        }
//
//        // Interagir com o primeiro NPC
//        if (!jogador.getLocal().getPersonagensPresentes().isEmpty()) {
//            NPC npc = jogador.getLocal().getPersonagensPresentes().get(0);
//
//            System.out.println("\nInteragindo com: " + npc.getNome());
//            jogador.interagirComNPC(npc);
//        }
//
//        // Mostrar status depois da interação
//        System.out.println("\nStatus do jogador:");
//        jogador.mostrarStatus();
//    }
//}
//
//


package jogo.main;

import jogo.controller.JogoController;

public class Jogo {
    public static void main(String[] args) {
        JogoController controller = new JogoController();
        controller.iniciarJogo();
    }
}