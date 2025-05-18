package lab03.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lab03.Gerenciadora;

public class MenuController {

    @FXML
    private Label labelUsuario;

    @FXML
    private Button botaoEventos;

    @FXML
    private Button botaoMarketPlace;

    @FXML
    private Button botaoPerfil;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        String nomeUsuario = gerenciadora.getUsuarioAtual().getNome();
        labelUsuario.setText("BEM-VINDO, " + nomeUsuario.toUpperCase() + "!");
    }

    private Stage getStage(ActionEvent event) {
        return (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
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
}
