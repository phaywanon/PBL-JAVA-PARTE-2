package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Region;
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

        Label aviso = new Label("");
        aviso.getStyleClass().add("label-erro");

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

            btn.setOnAction(e -> executarAcaoSlot(modo, slot, numeroSlot, estadoFinal, aviso));
            botoes[i - 1] = btn;
        }

        Button voltar = new Button("← Voltar");
        voltar.getStyleClass().add("botao-sair");
        voltar.setOnAction(e -> SceneManager.irPara(telaOrigem));

        VBox coluna = new VBox(18, titulo,
                botoes[0], botoes[1], botoes[2], aviso, voltar);

        coluna.setAlignment(Pos.CENTER);
        coluna.setTranslateY(90);
        coluna.setMaxWidth(460);
        coluna.setMaxHeight(Region.USE_PREF_SIZE);
        coluna.getStyleClass().add("painel-slots");

        ImageView fundo = new ImageView(
                new Image(
                        TelaSlots.class.getResource("/imagens/TelaMenuGpt2.png").toExternalForm()
                )
        );

        fundo.setFitWidth(1280);
        fundo.setFitHeight(720);
        fundo.setPreserveRatio(false);
        fundo.setMouseTransparent(true);

        Rectangle overlay = new Rectangle(1280, 720);
        overlay.setFill(Color.rgb(0, 0, 0, 0.55));
        overlay.setMouseTransparent(true);

        StackPane raiz = new StackPane(fundo, overlay, coluna);
        raiz.getStyleClass().add("fundo-menu");

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                TelaSlots.class.getResource("/estilos/menu.css").toExternalForm()
        );

        return scene;
    }

    private static void executarAcaoSlot(String modo, String slot,
                                         int numero, EstadoDoJogo estado,
                                         Label aviso) {
        JogoService service = SceneManager.getJogoService();

        switch (modo) {
            case "novo" -> {
                if (estado != null) {
                    aviso.setText("❌ Slot ocupado! Delete antes de criar um novo jogo.");
                    return;
                }
                SceneManager.irPara("novo-jogo-" + slot);
            }

            case "continuar" -> {
                if (estado == null) {
                    aviso.setText("⚠️ Slot vazio! Escolha um save existente.");
                    return;
                }
                service.carregarJogo(slot);
                SceneManager.irPara("jogo");
            }

            case "deletar" -> {
                if (estado == null) {
                    aviso.setText("⚠️ Esse slot já está vazio.");
                    return;
                }

                service.deletarJogo(slot);
                aviso.setText("🗑️ Save deletado!");

                SceneManager.irPara("slots-deletar");
            }
        }
    }
}