package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jogo.view.SceneManager;



public class TelaExplorar {

    public static Scene criar() {

        Label titulo = new Label("Escolha seu próximo destino");
        titulo.getStyleClass().clear();
        titulo.getStyleClass().add("titulo-explorar");

        Button cantina = botao("🍽️ Cantina", 1);
        Button sala = botao("📚 Sala de Aula", 2);
        Button lab = botao("💻 Laboratório", 3);
        Button colegiado = botao("🏛️ Colegiado de ECOMP", 4);
        Button da = botao("🎲 DA de ECOMP", 5);

        cantina.getStyleClass().add("botao-explorar");
        sala.getStyleClass().add("botao-explorar");
        lab.getStyleClass().add("botao-explorar");
        colegiado.getStyleClass().add("botao-explorar");
        da.getStyleClass().add("botao-explorar");

        Button voltar = new Button("← Voltar");
        voltar.getStyleClass().add("botao-sair");
        voltar.setOnAction(e -> SceneManager.irPara("jogo"));

        VBox coluna = new VBox(15,
                titulo, cantina, sala, lab, colegiado, da, voltar);
        coluna.setAlignment(Pos.CENTER);
        coluna.setTranslateY(50);
        coluna.setMaxWidth(460);
        coluna.setMaxHeight(Region.USE_PREF_SIZE);
        coluna.getStyleClass().add("painel-slots");

        ImageView fundo = new ImageView(
                new Image(
                        TelaExplorar.class
                                .getResource("/imagens/LocaisGPT.png").toExternalForm()
                )
        );

        fundo.setFitWidth(1280);
        fundo.setFitHeight(720);
        fundo.setPreserveRatio(false);
        fundo.setMouseTransparent(true);

        Rectangle overlay = new Rectangle(1280, 720);
        overlay.setFill(Color.rgb(0, 0, 0, 0.45));
        overlay.setMouseTransparent(true);

        StackPane raiz = new StackPane(fundo, overlay, coluna);
        raiz.getStyleClass().add("fundo-menu");

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                TelaExplorar.class.getResource("/estilos/menu.css").toExternalForm()
        );

        return scene;
    }

    private static Button botao(String texto, int escolha) {
        Button btn = new Button(texto);
        btn.setOnAction(e -> {
            String msg = SceneManager.getJogoService().explorar(escolha);
            SceneManager.irPara("jogo");
            TelaJogo.exibirResultado(msg);
        });
        return btn;
    }
}
