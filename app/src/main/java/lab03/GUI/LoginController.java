/*
 * LoginController.java
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lab03.Gerenciadora;
import lab03.Clientes.Cliente;

/**
 * Controlador JavaFX para a tela de Login.
 * Responsável por permitir que usuários existentes façam login no sistema
 * e navegar para a tela de cadastro de novo cliente ou para o menu principal
 * após um login bem-sucedido.
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class LoginController extends GeralController {
    
    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public LoginController(){}

    @FXML
    private Button botaoLogin;

    @FXML
    private Button botaoNovoCliente;

    @FXML
    private Label labelAviso;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private TextField textFieldEmail;

    /**
     * Obtém o email e a senha dos campos de texto, tenta encontrar o cliente
     * correspondente através da {@link Gerenciadora}. Se o cliente for encontrado
     * e a senha estiver correta, define o usuário atual na gerenciadora e navega
     * para o menu principal. Caso contrário, exibe uma mensagem de erro.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro durante a navegação para o menu principal.
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        String email = textFieldEmail.getText();
        String senha = passwordFieldSenha.getText();

        Cliente clienteLogin = gerenciadora.getCLientes().get(email);
        if (clienteLogin == null) labelAviso.setText("email ou senha incorretos");
        else {
            if (email.equals(clienteLogin.getEmail())
            && senha.equals(clienteLogin.getSenha())) {
                gerenciadora.setUsuarioAtual(clienteLogin);
                handleVoltarAoMenu(event);
            } else{
                labelAviso.setText("email ou senha incorretos");
            }
        }
    }

    /**
     * Manipula o evento de clique no botão "Novo Cliente".
     * Navega para a tela de cadastro de um novo cliente, utilizando o método
     * de navegação da classe pai {@link GeralController#handleAcessoNovoCliente(ActionEvent)}.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro durante a navegação para a tela de novo cliente.
     */
    @FXML
    private void handleNovoCliente(ActionEvent event) throws IOException {
        handleAcessoNovoCliente(event);
    }

}
