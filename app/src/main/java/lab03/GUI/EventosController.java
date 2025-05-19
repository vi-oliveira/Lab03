/*
 * EventosController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feitacom o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
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

/**
 * Controlador JavaFX para a tela de listagem de Eventos.
 * Responsável por exibir a lista de todos os eventos disponíveis no sistema
 * e permitir que o usuário selecione um evento para ver seus detalhes em uma
 * tela específica.
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventosController extends GeralController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public EventosController(){}

    @FXML
    private Button BotaoSelecionar;

    @FXML
    private Label labelErro;

    @FXML
    private ListView<Evento> listEventos;

    /**
     * Método de inicialização chamado automaticamente após o FXML ter sido carregado.
     * Obtém a lista de eventos do histórico através da {@link Gerenciadora}
     * e popula a {@link #listEventos} com esses eventos.
     */
    @FXML
    private void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        List<Evento> eventos = gerenciadora.getHistorico().getEventos();
        ObservableList<Evento> observableEventos = FXCollections.observableArrayList(eventos);
        listEventos.setItems(observableEventos);
    }
    
    /**
     * Manipula o evento de clique no botão "Selecionar".
     * Obtém o evento selecionado na {@link #listEventos}. Se nenhum evento for selecionado,
     * exibe uma mensagem de erro. Caso contrário, carrega a tela de "Evento Específico",
     * passa o evento selecionado para o controlador dessa tela e navega para ela.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela de evento específico.
     */
    @FXML
    private void handleAcessarEventoEspecifico(ActionEvent event) throws IOException {
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
