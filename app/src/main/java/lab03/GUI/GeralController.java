/*
 * GeralController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feitacom o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03.GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe base para controladores JavaFX que fornece métodos comuns de navegação
 * entre diferentes janelas da aplicação.
 * Esta classe é projetada para ser herdada por controladores específicos de cada tela
 * para evitar a duplicação de código relacionado à transição de telas.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class GeralController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public GeralController(){}

    @FXML
    private Button botaoVoltarAoMenu;    

    /**
     * Obtém o objeto Stage a partir de um ActionEvent.
     * Útil para acessar a janela atual onde o evento ocorreu.
     *
     * @param event O ActionEvent disparado por um elemento da UI.
     * @return O Stage associado ao evento.
     */
    protected Stage getStage(ActionEvent event) {
        return (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
    }
    
    /**
     * Manipula o evento de voltar ao menu principal. Carrega a tela do menu.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela do menu.
     */
    @FXML
    protected void handleVoltarAoMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/MenuWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(
            getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Menu");
    }

    /**
     * Manipula o evento de acesso à tela de eventos. Carrega a tela de eventos.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela de eventos.
     */
    @FXML
    protected void handleAcessoEventos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/EventosWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(
            getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Eventos");
    }

    /**
     * Manipula o evento de acesso à tela do marketplace. Carrega a tela do marketplace.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela do marketplace.
     */
    @FXML
    protected void handleAcessoMarketPlace(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/MarketplaceWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(
            getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Marketplace");
    }

    /**
     * Manipula o evento de acesso à tela de perfil do usuário. Carrega a tela de perfil.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela de perfil.
     */
    @FXML
    protected void handleAcessoPerfil(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/PerfilWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(
            getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Perfil");
    }

    /**
     * Manipula o evento de acesso à tela de login. Carrega a tela de login.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela de login.
     */
    @FXML
    protected void handleAcessoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(
            getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Eventos");
    }
    
    /**
     * Manipula o evento de acesso à tela de cadastro de novo cliente. Carrega a tela de novo cliente.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela de novo cliente.
     */
    @FXML
    protected void handleAcessoNovoCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/NovoClienteWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(
            getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Eventos");
    }

    /**
     * Manipula o evento de acesso à tela de desenvolvimento (Dev).
     * Carrega a tela de desenvolvimento em uma *nova* janela.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela de desenvolvimento.
     */
    @FXML
    protected void handleAcessoDev(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/Dev/DevWindow.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add(
            getClass().getResource("/Dev/EstiloDev.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Menu de desenvolvimento");
        stage.show();
    }
}
