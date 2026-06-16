package jogo.view.telas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import jogo.model.*;
import jogo.service.JogoService;
import jogo.view.SceneManager;

public class TelaJogo {

    // painéis que serão atualizados dinamicamente
    private static Label labelLocal    = new Label();
    private static Label labelEnergia  = new Label();
    private static Label labelDinheiro = new Label();
    private static Label labelDia      = new Label();
    private static Label labelLog      = new Label(); // mensagens de feedback
    private static VBox painelBotoes   = new VBox(12);

    public static Scene criar() {

        // ── TOPO: status do jogador ──
        labelLocal.getStyleClass().add("status-local");
        labelEnergia.getStyleClass().add("status-item");
        labelDinheiro.getStyleClass().add("status-item");
        labelDia.getStyleClass().add("status-item");

        HBox topo = new HBox(30, labelLocal, labelDia,
                labelEnergia, labelDinheiro);
        topo.setAlignment(Pos.CENTER_LEFT);
        topo.setPadding(new Insets(15, 20, 15, 20));
        topo.getStyleClass().add("painel-topo");

        // ── DIREITA: botões de ação ──
        painelBotoes.setAlignment(Pos.TOP_CENTER);
        painelBotoes.setPadding(new Insets(20));
        painelBotoes.setMinWidth(280);
        painelBotoes.getStyleClass().add("painel-botoes");

        // ── CENTRO: área do local (sprite virá aqui) ──
        StackPane centro = new StackPane();
        centro.getStyleClass().add("painel-centro");

        // ── LOG: mensagens de feedback ──
        labelLog.getStyleClass().add("label-log");
        labelLog.setWrapText(true);
        HBox rodape = new HBox(labelLog);
        rodape.setPadding(new Insets(10, 20, 10, 20));
        rodape.getStyleClass().add("painel-rodape");

        // ── LAYOUT GERAL ──
        BorderPane jogo = new BorderPane();
        jogo.setTop(topo);
        jogo.setCenter(centro);
        jogo.setRight(painelBotoes);
        jogo.setBottom(rodape);
        jogo.getStyleClass().add("fundo-jogo");

        // popup começa invisível
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

    // chamado sempre que algo muda no jogo
    public static void atualizar() {
        JogoService service = SceneManager.getJogoService();
        Jogador jogador = service.getJogador();

        if (jogador == null) return;

        int dia = service.getDiaAtual();
        int diaDoSemestre = (dia - 1) % 21 + 1;
        int semestre = ((dia - 1) / 21) + 1;

        labelLocal.setText("📍 " + jogador.getLocal().getNomeLocal());
        labelDia.setText("📅 Dia " + diaDoSemestre + "/21  |  Sem. " + semestre);
        labelEnergia.setText("⚡ " + jogador.getEnergia());
        labelDinheiro.setText("💰 R$" + String.format("%.0f", jogador.getDinheiro()));

        atualizarBotoes(service, jogador);

        // aviso de prova
        if (service.getEventoService().hojeTemProva(service.getDiaAtual())
                && !service.isAvisoHojeExibido()) {
            mostrarPopup("📝 HOJE TEM PROVA!",
                    "Não esqueça de passar pela Sala de Aula antes de dormir!\n" +
                            "Se você não estiver lá quando a prova acontecer, vai tirar zero.");
            service.setAvisoHojeExibido(true);
        }

        // verifica vitória
        if (service.getJogador().isFormado()) {
            SceneManager.irPara("formatura");
            return;
        }
    }

    private static void atualizarBotoes(JogoService service, Jogador jogador) {
        painelBotoes.getChildren().clear();
        Local local = jogador.getLocal();

        if (local instanceof LocalCasa)              botoesCasa(service);
        else if (local instanceof LocalPontoDeOnibus) botoesPonto(service);
        else                                          botoesUEFS(service, local);
    }

    private static void botoesCasa(JogoService service) {
        Button irUEFS = botao("🚌 Ir para a UEFS", () -> {
            log(service.irParaUEFS());
            atualizar();
        });

        Button status = botao("📊 Ver status", () -> log(statusCompleto(service)));

        Button sair = botao("🚪 Sair para o menu", SceneManager::voltarAoMenu);
        sair.getStyleClass().add("botao-sair");

        painelBotoes.getChildren().addAll(irUEFS, status, sair);
    }

    private static void botoesPonto(JogoService service) {
        Button entrar = botao("🏫 Entrar na UEFS", () -> {
            service.entrarNaUEFS();
            atualizar();
        });
        Button voltar = botao("🏠 Ir para casa", () -> {
            log(service.irParaCasa());
            atualizar();
        });

        Button npc = botao("👤 Interagir com NPC", () -> SceneManager.irPara("npc"));

        Button status = botao("📊 Ver status", () -> log(statusCompleto(service)));

        Button sair = botao("🚪 Sair para o menu", SceneManager::voltarAoMenu);
        sair.getStyleClass().add("botao-sair");

        painelBotoes.getChildren().addAll(entrar, voltar, npc, status, sair);
    }

    private static void botoesUEFS(JogoService service, Local local) {
        Button estudar = botao("📖 Estudar", () -> {
            log(service.estudar());
            atualizar();
        });
        Button ponto = botao("🚌 Ir ao ponto", () -> {
            log(service.irParaPonto());
            atualizar();
        });
        Button salvar = botao("💾 Salvar", () -> {
            service.salvarJogo(service.getSlotAtual());
            log("Jogo salvo!");
        });

        Button status = botao("📊 Ver status", () -> log(statusCompleto(service)));

        Button sair = botao("🚪 Sair para o menu", SceneManager::voltarAoMenu);
        sair.getStyleClass().add("botao-sair");

        painelBotoes.getChildren().addAll(salvar, sair, status, ponto, estudar);

        if (local instanceof LocalCantina) {
            painelBotoes.getChildren().add(botao("🍽️ Lanchar", () -> {
                log(service.lanchar());
                atualizar();
            }));
        }
        if (local instanceof LocalSalaDeAula || local instanceof LocalLaboratorio) {
            painelBotoes.getChildren().add(botao("🎓 Cursar disciplina", () -> {
                log(service.cursarDisciplina());
                atualizar();
            }));
        }
        if (local instanceof LocalLaboratorio) {
            painelBotoes.getChildren().add(botao("💼 Trabalhar", () -> {
                log(service.trabalhar());
                atualizar();
            }));
        }
        if (local instanceof LocalDA) {
            painelBotoes.getChildren().add(botao("🎲 Jogar Dominó", () -> {
                log(service.lazer());
                atualizar();
            }));
        }

        painelBotoes.getChildren().add(botao("👤 Interagir com NPC", () -> SceneManager.irPara("npc")));

        // Explorar — submenu simples por enquanto
        painelBotoes.getChildren().add(botao("🗺️ Explorar", () -> {
            SceneManager.irPara("explorar");
        }));
    }

    // helper para criar botão com ação
    private static Button botao(String texto, Runnable acao) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setOnAction(e -> acao.run());
        return btn;
    }

    public static void log(String mensagem) {
        labelLog.setText(mensagem);
    }

    private static String statusCompleto(JogoService s) {
        Jogador j = s.getJogador();
        return String.format("⚡%d  ❤️%d  💡%d  📚%.1f  💰R$%.0f  🎓%.0f%%",
                j.getEnergia(), j.getSaude(), j.getMotivacao(),
                j.getNivelDeConhecimento(), j.getDinheiro(), j.getProgresso());
    }

    private static StackPane painelPopup = new StackPane();

    public static void mostrarPopup(String titulo, String mensagem) {
        painelPopup.getChildren().clear();

        // fundo escuro semitransparente
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

}
