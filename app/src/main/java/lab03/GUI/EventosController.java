package lab03.GUI;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lab03.Gerenciadora;
import lab03.Eventos.Evento;

public class EventosController {

    @FXML
    private Label labelUsuario;

    @FXML
    private Button BotaoSelecionar;

    @FXML
    private ListView<Evento> listEventos;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        String nomeUsuario = gerenciadora.getUsuarioAtual().getNome();
        labelUsuario.setText(nomeUsuario);

        List<Evento> eventos = gerenciadora.getHistorico().getEventos();
        ObservableList<Evento> observableEventos = FXCollections.observableArrayList(eventos);
        listEventos.setItems(observableEventos);
    }
    
}
