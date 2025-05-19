package lab03.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NavegacaoController {

    @FXML
    private Button botaoVoltarAoMenu;    

    private Stage getStage(ActionEvent event) {
        return (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
    }
    
    @FXML
    void handleVoltarAoMenu(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MenuWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Menu");
    }

    @FXML
    void handleAcessoEventos(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/EventosWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Eventos");
    }

    @FXML
    void handleAcessoMarketPlace(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MarketplaceWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Marketplace");
    }

    @FXML
    void handleAcessoPerfil(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/PerfilWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Perfil");
    }

    @FXML
    void handleAcessoDev(ActionEvent event) throws Exception {
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
