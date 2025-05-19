package lab03.GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import lab03.Gerenciadora;
import lab03.Clientes.Cliente;

public class NovoClienteController extends GeralController {

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
    private TextField textFieldNome;

    @FXML
    private TextField textFieldSaldo;

    @FXML
    private TextField textFieldTelefone;

    private void mostrarAlertaClienteCriado(ActionEvent event) throws IOException {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Novo cliente");
        alerta.setHeaderText("Cliente cadastrado com sucesso!");
        alerta.setContentText("Ao clicar em Ok você será voltará à tela de login");
        alerta.showAndWait();
        System.out.println("eita");
        handleAcessoLogin(event);
    }

    /* Na função abaixo foi utilizado IA dentro na expressão do if: "nome == null || nome.trim().isEmpty()".
     * Isso foi feito para corrigir um problema em que, mesmo com campos vazios, as excessões não eram lançadas.
    */
    @FXML
    void handleCriarNovoCliente(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        // Todos os campos devem estar preenchidos
        try {
            String nome = textFieldNome.getText();
            if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Existe(m) campo(s) vazio(s)");

            String email = textFieldEmail.getText();
            if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Existe(m) campo(s) vazio(s)");

            String telefone = textFieldTelefone.getText();
            if (telefone == null || telefone.trim().isEmpty()) throw new IllegalArgumentException("Existe(m) campo(s) vazio(s)");

            String senha = passwordFieldSenha.getText();
            if (senha == null || senha.trim().isEmpty()) throw new IllegalArgumentException("Existe(m) campo(s) vazio(s)");

            String saldoDigitado = textFieldSaldo.getText();
            Double saldo = Double.parseDouble(saldoDigitado);
            if (saldo < 0) throw new IllegalArgumentException("O valor do saldo deve ser positivo");

            Cliente novoCliente = new Cliente(nome, email, senha, telefone, saldo);
            gerenciadora.getCLientes().put(novoCliente.getEmail(), novoCliente);

            mostrarAlertaClienteCriado(event);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            labelAviso.setText(e.getMessage());
        }
    }

    @FXML
    void handleVoltarLogin(ActionEvent event) throws IOException {
        handleAcessoLogin(event);
    }

}