package lab03.GUI;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lab03.Gerenciadora;
import lab03.Eventos.Evento;

public class EventosController extends NavegacaoController {

    @FXML
    private Button BotaoSelecionar;

    @FXML
    private Label labelErro;

    @FXML
    private ListView<Evento> listEventos;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        List<Evento> eventos = gerenciadora.getHistorico().getEventos();
        ObservableList<Evento> observableEventos = FXCollections.observableArrayList(eventos);
        listEventos.setItems(observableEventos);
    }
    
    private Stage getStage(ActionEvent event) {
        return (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
    }
    
    @FXML
    void handleAcessarEventoEspecifico(ActionEvent event) throws IOException {
        Evento eventoSelEvento = listEventos.getSelectionModel().getSelectedItem();
        if (eventoSelEvento == null) labelErro.setText("Nenhum evento selecionado");
        else {     
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EventoEspecificoWindow.fxml"));
            Parent root = loader.load();
    
            EventoEspecificoController eventoEspecificoController = loader.getController();
            eventoEspecificoController.carregarEvento(eventoSelEvento);
    
            Scene scene = new Scene(root);
            Stage stage = getStage(event);
            scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Menu");
        }
    }
}
