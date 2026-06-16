package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jogo.view.SceneManager;

public class MenuPrincipal {

    public static Scene criar() {
        // Título
        Label titulo = new Label("BixoQuest");
        titulo.getStyleClass().add("titulo");

        // Botões principais
        Button btnNovo = new Button("Novo Jogo");
        Button btnContinuar = new Button("Continuar");
        Button btnDeletar = new Button("Deletar Save");
        Button btnSair = new Button("Sair");

        btnSair.getStyleClass().add("botao-sair");

        // Ações
        btnNovo.setOnAction(e -> SceneManager.irPara("slots-novo"));
        btnContinuar.setOnAction(e -> SceneManager.irPara("slots-continuar"));
        btnDeletar.setOnAction(e -> SceneManager.irPara("slots-deletar"));
        btnSair.setOnAction(e -> System.exit(0));

        // Layout
        VBox coluna = new VBox(20, titulo, btnNovo, btnContinuar, btnDeletar, btnSair);
        coluna.setAlignment(Pos.CENTER);

        StackPane raiz = new StackPane(coluna);
        raiz.getStyleClass().add("fundo-menu");

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                MenuPrincipal.class.getResource("/estilos/menu.css").toExternalForm()
        );

        return scene;
    }
}