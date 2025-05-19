/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Construtor padrão da classe App.
     * Construtor vazio, pois a classe é utilizada apenas para a main.
     */
    public App(){}

    /**
     * Aplicação principal
     * @param args argumentos para execução
     */
    public static void main(String[] args) {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        gerenciadora.simularExemplo();
        launch(args);
    }
}
