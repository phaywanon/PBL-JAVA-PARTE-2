package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jogo.model.Jogador;
import jogo.view.SceneManager;

public class TelaFormatura {

    public static Scene criar() {
        Jogador jogador = SceneManager.getJogoService().getJogador();

        Label emoji = new Label("🎓");
        emoji.setStyle("-fx-font-size: 80px;");

        Label titulo = new Label("PARABÉNS!");
        titulo.getStyleClass().add("titulo");

        Label mensagem = new Label(
                jogador.getNome() + ",\nvocê concluiu o curso de Engenharia de Computação\n" +
                        "na UEFS e se formou com sucesso!\n\n" +
                        "Progresso final: " + String.format("%.0f", jogador.getProgresso()) + "%"
        );
        mensagem.getStyleClass().add("popup-mensagem");
        mensagem.setWrapText(true);
        mensagem.setMaxWidth(700);

        Button btnMenu = new Button("🏠 Voltar ao Menu");
        btnMenu.setOnAction(e -> SceneManager.voltarAoMenu());

        VBox coluna = new VBox(25, emoji, titulo, mensagem, btnMenu);
        coluna.setAlignment(Pos.CENTER);

        StackPane raiz = new StackPane(coluna);
        raiz.getStyleClass().add("fundo-menu");

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                TelaFormatura.class.getResource("/estilos/menu.css").toExternalForm()
        );

        return scene;
    }
}