package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jogo.model.EstadoDoJogo;
import jogo.service.JogoService;
import jogo.view.SceneManager;

public class TelaSlots {

    public static Scene criar(String modo, String telaOrigem) {

        String tituloPainel = switch (modo) {
            case "novo"      -> "Escolha um slot para o novo jogo:";
            case "continuar" -> "Qual save deseja carregar?";
            case "deletar"   -> "Qual save deseja deletar?";
            default          -> "Selecione um slot:";
        };

        Label titulo = new Label(tituloPainel);
        titulo.getStyleClass().add("titulo-slots");

        JogoService service = SceneManager.getJogoService();

        Button[] botoes = new Button[3];
        for (int i = 1; i <= 3; i++) {
            String slot = "slot" + i;
            EstadoDoJogo estado = service.carregarEstado(slot);

            String labelSlot;
            if (estado == null) {
                labelSlot = "Slot " + i + "  —  [Vazio]";
            } else {
                labelSlot = "Slot " + i + "  —  " + estado.getNomeJogador()
                        + "  |  Dia " + estado.getDiaAtual()
                        + "  |  " + estado.getUltimoAcesso().substring(0, 10);
            }

            Button btn = new Button(labelSlot);
            final int numeroSlot = i;
            final EstadoDoJogo estadoFinal = estado;

            btn.setOnAction(e -> executarAcaoSlot(modo, slot, numeroSlot, estadoFinal));
            botoes[i - 1] = btn;
        }

        Button voltar = new Button("← Voltar");
        voltar.getStyleClass().add("botao-sair");
        voltar.setOnAction(e -> SceneManager.irPara(telaOrigem));

        VBox coluna = new VBox(20, titulo,
                botoes[0], botoes[1], botoes[2], voltar);
        coluna.setAlignment(Pos.CENTER);

        StackPane raiz = new StackPane(coluna);
        raiz.getStyleClass().add("fundo-menu");

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                TelaSlots.class.getResource("/estilos/menu.css").toExternalForm()
        );

        return scene;
    }

    private static void executarAcaoSlot(String modo, String slot,
                                         int numero, EstadoDoJogo estado) {
        JogoService service = SceneManager.getJogoService();

        switch (modo) {
            case "novo" -> {
                if (estado != null) {
                    System.out.println("Slot ocupado! Delete antes.");
                    return;
                }
                // Por enquanto abre um diálogo simples — depois vira tela própria
                SceneManager.irPara("novo-jogo-" + slot);
            }
            case "continuar" -> {
                if (estado == null) {
                    System.out.println("Slot vazio!");
                    return;
                }
                service.carregarJogo(slot);
                SceneManager.irPara("jogo"); // tela do jogo — vem depois
                TelaJogo.atualizar();
            }
            case "deletar" -> {
                service.deletarJogo(slot);
                System.out.println("Save deletado!");
                SceneManager.irPara("menu");
            }
        }
    }
}