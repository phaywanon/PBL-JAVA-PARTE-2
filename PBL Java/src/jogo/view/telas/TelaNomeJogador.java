package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jogo.view.SceneManager;


public class TelaNomeJogador {

    public static Scene criar(String slot) {

        Label titulo = new Label("Novo Jogo - " + slot.replace("slot", "Slot "));
        titulo.getStyleClass().add("titulo-slots");

        Label nome = new Label("Nome:");
        nome.getStyleClass().add("label-campo");
        TextField campoNome = new TextField();
        campoNome.setPromptText("Ex: Pedro Haywanon");
        campoNome.setMaxWidth(400);

        Label matricula = new Label("Matricula:");
        matricula.getStyleClass().add("label-campo");
        TextField campoMatricula = new TextField();
        campoMatricula.setPromptText("Ex: 20131801");
        campoMatricula.setMaxWidth(400);

        Label labelErro = new Label("");
        labelErro.getStyleClass().add("label-erro");

        Button btnConfirmar = new Button("Iniciar Jornada");
        Button btnVoltar = new Button("Voltar");
        btnVoltar.getStyleClass().add("botao-sair");


        btnConfirmar.setOnAction(e -> {
            String nomeJogador = campoNome.getText().trim();
            String matriculaJogador = campoMatricula.getText().trim();

            if (nomeJogador.isEmpty() || matriculaJogador.isEmpty()) {
                labelErro.setText("Preencha todos os campos!");
                return;
            }

            boolean criou = SceneManager.getJogoService().novoJogo(slot, nomeJogador, matriculaJogador);

            if (criou) {
                SceneManager.irPara("jogo");
                TelaJogo.atualizar();
            } else {
                labelErro.setText("Erro ao criar jogo. Slot já ocupado?");
            }
        });

        btnVoltar.setOnAction(e -> SceneManager.irPara("slots-novo"));

        VBox coluna = new VBox(15,
                titulo,
                nome, campoNome,
                matricula, campoMatricula,
                labelErro,
                btnConfirmar, btnVoltar);
        coluna.setAlignment(Pos.CENTER);

        StackPane raiz = new StackPane(coluna);
        raiz.getStyleClass().add("fundo-menu");

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                TelaNomeJogador.class.getResource("/estilos/menu.css").toExternalForm()
        );

        return scene;

    }

}
