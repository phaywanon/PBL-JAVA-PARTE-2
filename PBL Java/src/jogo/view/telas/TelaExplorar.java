package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jogo.view.SceneManager;


public class TelaExplorar {

    public static Scene criar() {

        Label titulo = new Label("Para onde você quer ir agora?");
        titulo.getStyleClass().add("titulo");

        Button cantina = botao("🍽️ Cantina", 1);
        Button sala = botao("📚 Sala de Aula", 2);
        Button lab = botao("💻 Laboratório", 3);
        Button colegiado = botao("🏛️ Colegiado de ECOMP", 4);
        Button da = botao("🎲 DA de ECOMP", 5);

        Button voltar = new Button("← Voltar");
        voltar.getStyleClass().add("botao-sair");
        voltar.setOnAction(e -> SceneManager.irPara("jogo"));

        VBox coluna = new VBox(15,
                titulo, cantina, sala, lab, colegiado, da, voltar);
        coluna.setAlignment(Pos.CENTER);

        StackPane raiz = new StackPane(coluna);
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
            TelaJogo.log(msg);
            TelaJogo.atualizar();
        });
        return btn;
    }
}
