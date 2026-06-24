package jogo.view.telas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import jogo.controller.AcaoDisponivel;
import jogo.controller.JogoSceneController;
import jogo.view.SceneManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaJogo {

    private static ImageView fundoLocal;
    private static Label labelLocal = new Label();
    private static Label labelEnergia = new Label();
    private static Label labelDinheiro = new Label();
    private static Label labelDia = new Label();
    private static Label labelLog = new Label();
    private static VBox painelBotoes = new VBox(12);
    private static StackPane painelPopup = new StackPane();

    private static Pane camadaNPCs;
    private static Pane camadaJogador;
    private static ImageView spriteGato;
    private static ImageView spriteCachorro;
    private static ImageView spriteJogador;

    private static JogoSceneController controller;

    public static Scene criar() {
        controller = new JogoSceneController(SceneManager.getJogoService());

        labelLocal.getStyleClass().add("status-local");
        labelEnergia.getStyleClass().add("status-item");
        labelDinheiro.getStyleClass().add("status-item");
        labelDia.getStyleClass().add("status-item");

        HBox topo = new HBox(30, labelLocal, labelDia, labelEnergia, labelDinheiro);
        topo.setAlignment(Pos.CENTER_LEFT);
        topo.setPadding(new Insets(15, 20, 15, 20));
        topo.getStyleClass().add("painel-topo");

        painelBotoes.setAlignment(Pos.TOP_CENTER);
        painelBotoes.setPadding(new Insets(20));
        painelBotoes.setMinWidth(280);
        painelBotoes.getStyleClass().add("painel-botoes");

        StackPane centro = new StackPane();
        centro.getStyleClass().add("painel-centro");

        fundoLocal = new ImageView();
        fundoLocal.setFitWidth(900);
        fundoLocal.setFitHeight(520);
        fundoLocal.setPreserveRatio(false);

        camadaNPCs = new Pane();
        camadaNPCs.setPrefSize(900, 520);
        camadaNPCs.setPickOnBounds(false);

        camadaJogador = new Pane();
        camadaJogador.setPrefSize(900, 520);
        camadaJogador.setPickOnBounds(false);


        spriteGato = new ImageView(
                new Image(TelaJogo.class.getResource("/imagens/GatinhoGPT.png").toExternalForm())
        );

        spriteCachorro = new ImageView(
                new Image(TelaJogo.class.getResource("/imagens/CachorrinhoGPT.png").toExternalForm())
        );

        spriteJogador = new ImageView(
                new Image(TelaJogo.class.getResource("/imagens/JogadorGPT.png").toExternalForm())
        );

        spriteGato.setFitWidth(60);
        spriteGato.setPreserveRatio(true);
        spriteGato.setLayoutX(560);
        spriteGato.setLayoutY(300);

        spriteCachorro.setFitWidth(90);
        spriteCachorro.setPreserveRatio(true);
        spriteCachorro.setLayoutX(435);
        spriteCachorro.setLayoutY(230);

        spriteJogador.setFitWidth(250);
        spriteJogador.setPreserveRatio(true);


        camadaNPCs.getChildren().addAll(spriteGato, spriteCachorro);
        camadaJogador.getChildren().add(spriteJogador);

        centro.getChildren().addAll(fundoLocal, camadaNPCs, camadaJogador);

        labelLog.getStyleClass().add("label-log");
        labelLog.setWrapText(true);
        HBox rodape = new HBox(labelLog);
        rodape.setPadding(new Insets(10, 20, 10, 20));
        rodape.getStyleClass().add("painel-rodape");

        BorderPane jogo = new BorderPane();
        jogo.setTop(topo);
        jogo.setCenter(centro);
        jogo.setRight(painelBotoes);
        jogo.setBottom(rodape);
        jogo.getStyleClass().add("fundo-jogo");

        painelPopup.setVisible(false);
        painelPopup.getStyleClass().add("overlay-popup");

        StackPane raiz = new StackPane(jogo, painelPopup);

        Scene scene = new Scene(raiz, 1280, 720);
        scene.getStylesheets().add(
                TelaJogo.class.getResource("/estilos/jogo.css").toExternalForm()
        );

        atualizar();
        return scene;
    }

    public static void atualizar() {
        if (controller.jogadorSeFormou()) {
            SceneManager.irPara("formatura");
            return;
        }

        atualizarFundo();
        atualizarNPCs();
        atualizarJogador();

        labelLocal.setText("📍 " + controller.getNomeLocalAtual());
        labelDia.setText("📅 Dia " + controller.getDiaDoSemestre() + "/21  |  Sem. " + controller.getSemestre());
        labelEnergia.setText("⚡ " + controller.getEnergia());
        labelDinheiro.setText("💰 R$" + String.format("%.0f", controller.getDinheiro()));

        atualizarBotoes();

        if (controller.deveAvisarProva()) {
            mostrarPopup("📝 HOJE TEM PROVA!",
                    "Não esqueça de passar pela Sala de Aula antes de dormir!\n" +
                            "Se você não estiver lá quando a prova acontecer, vai tirar zero.");
            controller.marcarAvisoExibido();
        }
    }

    private static void atualizarBotoes() {
        painelBotoes.getChildren().clear();

        for (AcaoDisponivel acao : controller.getAcoesParaLocalAtual()) {
            Button btn = botao(acao.getLabel(), () -> {
                log(acao.executar());
                atualizar();
            });
            painelBotoes.getChildren().add(btn);
        }

        if (controller.podeExplorar()) {
            painelBotoes.getChildren().add(
                    botao("🗺️ Explorar", () -> SceneManager.irPara("explorar"))
            );
        }

        // ações fixas que não dependem de regra de negócio
        painelBotoes.getChildren().add(
                botao("👤 Interagir com NPC", () -> SceneManager.irPara("npc"))
        );

        painelBotoes.getChildren().add(
                botao("📊 Ver status", () -> log(controller.statusCompleto()))
        );

        Button sair = botao("🚪 Sair para o menu", SceneManager::voltarAoMenu);
        sair.getStyleClass().add("botao-sair");
        painelBotoes.getChildren().add(sair);
    }

    private static Button botao(String texto, Runnable acao) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setOnAction(e -> acao.run());
        return btn;
    }

    public static void log(String mensagem) {
        labelLog.setText(mensagem);
    }

    public static void mostrarPopup(String titulo, String mensagem) {
        painelPopup.getChildren().clear();
        painelPopup.setStyle("-fx-background-color: rgba(0,0,0,0.75);");

        Label lblTitulo = new Label(titulo);
        lblTitulo.getStyleClass().add("popup-titulo");

        Label lblMensagem = new Label(mensagem);
        lblMensagem.getStyleClass().add("popup-mensagem");
        lblMensagem.setWrapText(true);
        lblMensagem.setMaxWidth(600);

        Button btnOk = new Button("OK, entendi!");
        btnOk.setOnAction(e -> painelPopup.setVisible(false));

        VBox caixa = new VBox(20, lblTitulo, lblMensagem, btnOk);
        caixa.setAlignment(Pos.CENTER);
        caixa.setMaxWidth(650);
        caixa.setMaxHeight(350);
        caixa.getStyleClass().add("caixa-popup");

        painelPopup.getChildren().add(caixa);
        painelPopup.setVisible(true);
    }

    private static void atualizarFundo() {
        String caminho = controller.getCaminhoImagemLocalAtual();

        var url = TelaJogo.class.getResource(caminho);

        if (url == null) {
            System.err.println("Imagem não encontrada: " + caminho);
            return;
        }

        fundoLocal.setImage(new Image(url.toExternalForm()));
    }

    private static void atualizarNPCs() {
        boolean noPonto = controller.getNomeLocalAtual().equals("Ponto de ônibus da UEFS");

        spriteGato.setVisible(noPonto);
        spriteCachorro.setVisible(noPonto);
    }

    private static void trocarSpriteJogador(String caminho, double largura) {
        var url = TelaJogo.class.getResource(caminho);

        if (url == null) {
            System.err.println("Sprite do jogador não encontrado: " + caminho);
            return;
        }

        spriteJogador.setImage(new Image(url.toExternalForm()));

        spriteJogador.setFitWidth(largura);
    }

    private static void atualizarJogador() {
        String local = controller.getNomeLocalAtual();

        switch (local) {
            case "Casa" -> {
                trocarSpriteJogador("/imagens/JogadorMirrorGPT.png", 270);
                spriteJogador.setLayoutX(250);
                spriteJogador.setLayoutY(200);
            }

            case "Ponto de ônibus da UEFS" -> {
                trocarSpriteJogador("/imagens/JogadorGPT.png", 270);
                spriteJogador.setLayoutX(600);
                spriteJogador.setLayoutY(250);
            }

            case "Cantina" -> {
                trocarSpriteJogador("/imagens/JogadorDireitaGPT.png", 270);
                spriteJogador.setLayoutX(300);
                spriteJogador.setLayoutY(180);
            }

            case "Sala de Aula" -> {
                trocarSpriteJogador("/imagens/JogadorEsquerdaGPT.png", 270);
                spriteJogador.setLayoutX(720);
                spriteJogador.setLayoutY(305);
            }

            case "Laboratório" -> {
                trocarSpriteJogador("/imagens/JogadorNordesteGPT.png", 110);
                spriteJogador.setLayoutX(560);
                spriteJogador.setLayoutY(200);
            }

            case "Colegiado de ECOMP" -> {
                trocarSpriteJogador("/imagens/JogadorCostasGPT.png", 300);
                spriteJogador.setLayoutX(320);
                spriteJogador.setLayoutY(180);
            }

            case "DA de ECOMP" -> {
                trocarSpriteJogador("/imagens/JogadorNordesteGPT.png", 120);
                spriteJogador.setLayoutX(320);
                spriteJogador.setLayoutY(280);
            }
        }
    }
}