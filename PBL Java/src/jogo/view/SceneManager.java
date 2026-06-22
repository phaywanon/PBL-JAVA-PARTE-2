package jogo.view;

import jogo.service.JogoService;

import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.view.telas.*;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private static Stage stage;

    // Dicionário nome -> telas
    private static Map<String, Scene> telas = new HashMap<>();

    public static void inicializar(Stage stagePrincipal) {
        stage = stagePrincipal;
        registrarTelas();
    }

    // Cadastra as telas do jogo
    private static void registrarTelas() {
        telas.put("menu",            MenuPrincipal.criar());
        telas.put("novo-jogo-slot1", TelaNomeJogador.criar("slot1"));
        telas.put("novo-jogo-slot2", TelaNomeJogador.criar("slot2"));
        telas.put("novo-jogo-slot3", TelaNomeJogador.criar("slot3"));
        telas.put("explorar", TelaExplorar.criar());

    }



    // Busca a scene no mapa e coloca no stage
    public static void irPara(String nomeTela) {
        Scene cena = switch (nomeTela) {
            case "slots-novo"      -> TelaSlots.criar("novo",      "menu");
            case "slots-continuar" -> TelaSlots.criar("continuar", "menu");
            case "slots-deletar"   -> TelaSlots.criar("deletar",   "menu");
            case "npc" -> TelaNPC.criar();
            case "formatura" -> TelaFormatura.criar();
            case "jogo"            -> TelaJogo.criar();
            default                -> telas.get(nomeTela);
        };

        if (cena == null) {
            System.err.println("Tela não encontrada: " + nomeTela);
            return;
        }

        stage.setScene(cena);
    }

    public static Stage getStage() {
        return stage;
    }

    private static JogoService jogoService = new JogoService();

    public static JogoService getJogoService() {
        return jogoService;
    }

    public static void voltarAoMenu() {
        jogoService = new JogoService(); // reseta o service — limpa estado em memória
        irPara("menu");
    }
}