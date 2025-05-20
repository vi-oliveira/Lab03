/*
 * PerfilController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feitacom o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03.GUI;

import java.io.IOException;
import java.text.DecimalFormat;
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
import lab03.Ingresso;

/**
 * Controlador JavaFX para a tela de Perfil do Usuário.
 * Responsável por exibir as informações do usuário logado (nome, email, telefone, saldo)
 * e a lista de ingressos que ele possui. Permite ao usuário fazer um depósito
 * e sair da aplicação (logout).
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class PerfilController extends GeralController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public PerfilController(){}

    @FXML
    private Label labelUsuario;
    
    @FXML
    private Label labelEmail;
    
    @FXML
    private Label labelTelefone;
    
    @FXML
    private Label labelSaldo;
    
    @FXML
    private Button botaoFazerDeposito;

    @FXML
    private ListView<Ingresso> listIngressos;

    /**
     * Método para atualizar a exibição da tela de perfil.
     * Simplesmente chama o método {@link #initialize()} para recarregar os dados do usuário
     * e atualizar os elementos da UI.
     */
    public void atualizarTelaPerfil(){
        initialize();
    }

    /**
     * Método de inicialização chamado automaticamente após o FXML ter sido carregado.
     * Obtém as informações do usuário logado através da {@link Gerenciadora}
     * e as exibe nos labels correspondentes. Também carrega e exibe a lista
     * de ingressos que o usuário possui na ListView.
     */
    @FXML
    private void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        String nomeUsuario = gerenciadora.getUsuarioAtual().getNome();
        labelUsuario.setText(nomeUsuario);
        String email = gerenciadora.getUsuarioAtual().getEmail();
        labelEmail.setText("Email: " + email);
        String telefone = gerenciadora.getUsuarioAtual().getTelefone();
        labelTelefone.setText("Telefone: " + telefone);
        Double saldoUsuario = gerenciadora.getUsuarioAtual().getSaldo();
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        labelSaldo.setText("Saldo: R$" + decimalFormat.format(saldoUsuario));

        List<Ingresso> ingressosUsuario = gerenciadora.getUsuarioAtual().getIngressos();
        ObservableList<Ingresso> observableIngressos = FXCollections.observableArrayList(ingressosUsuario);
        listIngressos.setItems(observableIngressos);
    }
    
    /**
     * Manipula o evento de clique no botão "Fazer Depósito".
     * Carrega e exibe a janela de depósito em uma *nova* janela.
     * Passa uma referência deste {@code PerfilController} para o {@code DepositoController}
     * para permitir a atualização da tela de perfil após o depósito.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da janela de depósito.
     */
    @FXML
    private void handleJanelaDepositar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/DepositoWindow.fxml"));
        Parent root = loader.load();
        DepositoController depositoController = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add(
            getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Depositar");
        stage.show();
        depositoController.setPerfilController(this);
    }

    /**
     * Manipula o evento de clique para fazer logout.
     * Define o usuário atual na {@link Gerenciadora} como null
     * e navega de volta para a tela de login, utilizando o método
     * de navegação da classe pai {@link GeralController#handleAcessoLogin(ActionEvent)}.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro durante a navegação para a tela de login.
     */
    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        gerenciadora.setUsuarioAtual(null);
        handleAcessoLogin(event);
    }
}
