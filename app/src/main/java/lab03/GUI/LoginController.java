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

public class LoginController extends GeralController {

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

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        String email = textFieldEmail.getText();
        String senha = passwordFieldSenha.getText();

        Cliente clienteLogin = gerenciadora.getCLientes().get(email);
        if (clienteLogin == null) labelAviso.setText("email ou senha incorretos");
        else {
            if (email.equals(clienteLogin.getEmail()) && senha.equals(clienteLogin.getSenha())){
                gerenciadora.setUsuarioAtual(clienteLogin);
                handleVoltarAoMenu(event);
            } else{
                labelAviso.setText("email ou senha incorretos");
            }
        }
    }

    @FXML
    private void handleNovoCliente(ActionEvent event) throws IOException {
        handleAcessoNovoCliente(event);
    }

}
