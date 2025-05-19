package lab03.GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GeralController {

    @FXML
    private Button botaoVoltarAoMenu;    

    private Stage getStage(ActionEvent event) {
        return (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
    }
    
    @FXML
    protected void handleVoltarAoMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MenuWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Menu");
    }

    @FXML
    protected void handleAcessoEventos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EventosWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Eventos");
    }

    @FXML
    protected void handleAcessoMarketPlace(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MarketplaceWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Marketplace");
    }

    @FXML
    protected void handleAcessoPerfil(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/PerfilWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Perfil");
    }

    @FXML
    protected void handleAcessoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Eventos");
    }
    
    @FXML
    protected void handleAcessoNovoCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/NovoClienteWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Eventos");
    }

    @FXML
    protected void handleAcessoDev(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dev/DevWindow.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("/Dev/EstiloDev.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Menu de desenvolvimento");
        stage.show();
    }
}
