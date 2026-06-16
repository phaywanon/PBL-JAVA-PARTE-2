package jogo.view.telas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jogo.model.NPC;
import jogo.service.JogoService;
import jogo.view.SceneManager;

import java.util.List;

public class TelaNPC {

    public static Scene criar() {
        JogoService service = SceneManager.getJogoService();
        List<NPC> npcs = service.getJogador().getLocal().getPersonagensPresentes();

        Label titulo = new Label("Com quem deseja interagir?");
        titulo.getStyleClass().add("titulo-slots");

        VBox coluna = new VBox(15, titulo);
        coluna.setAlignment(Pos.CENTER);

        if (npcs.isEmpty()) {
            Label vazio = new Label("Não há ninguém aqui no momento.");
            vazio.getStyleClass().add("label-campo");
            coluna.getChildren().add(vazio);
        } else {
            for (NPC npc : npcs) {
                Button btn = new Button(npc.getNome());
                btn.setOnAction(e -> {
                    service.interagirComNPC(npc);
                    SceneManager.irPara("jogo");
                    TelaJogo.atualizar();
                });
                coluna.getChildren().add(btn);
            }
        }

        Button voltar = new Button("← Voltar");
        voltar.getStyleClass().add("botao-sair");
        voltar.setOnAction(e -> SceneManager.irPara("jogo"));
        coluna.getChildren().add(voltar);

        StackPane raiz = new StackPane(coluna);
        raiz.getStyleClass().add("fundo-menu");

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                TelaNPC.class.getResource("/estilos/menu.css").toExternalForm()
        );

        return scene;
    }
}