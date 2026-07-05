///*Autor: Pedro Haywanon Santos Araujo
//        Componente Curricular: Algoritmos e Programação II
//        Concluido em: 04/07/2026
//        Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
//        trecho de código de outro colega ou de outro autor, tais como provindos de livros e
//        apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
//        de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
//        do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
//

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

