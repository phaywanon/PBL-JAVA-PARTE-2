package jogo.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Define o texto na barra da janela
        stage.setTitle("BixoQuest: Da Matrícula à Formatura");

        // Tela de 1280x720
        stage.setWidth(1280);
        stage.setHeight(720);

        // Impede o redimensionamento da tela, visando não perder/bugar sprites importantes
        stage.setResizable(false);

        // Entrega a tela para o SceneManager
        SceneManager.inicializar(stage);

        // Carrega a primeira tela
        SceneManager.irPara("menu");

        // Aparece a tela pra o usuário
        stage.show();
    }

    static void main(String[] args) {
        launch(args);
    }
}

