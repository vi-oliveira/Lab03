/*
 * NovoClienteController.java
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import lab03.Gerenciadora;
import lab03.Clientes.Cliente;

/**
 * Controlador JavaFX para a tela de Cadastro de Novo Cliente.
 * Responsável por coletar as informações de um novo cliente, validar os dados,
 * criar o cliente no sistema e navegar de volta para a tela de login.
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class NovoClienteController extends GeralController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public NovoClienteController(){}

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

    /**
     * Exibe um alerta informando que o cliente foi criado com sucesso
     * e, após o usuário clicar em OK, navega de volta para a tela de login.
     *
     * @param event O ActionEvent que disparou a ação (usado para navegação).
     * @throws IOException Se ocorrer um erro durante a navegação para a tela de login.
     */
    private void mostrarAlertaClienteCriado(ActionEvent event) throws IOException {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Novo cliente");
        alerta.setHeaderText("Cliente cadastrado com sucesso!");
        alerta.setContentText("Ao clicar em Ok você será voltará à tela de login");
        alerta.showAndWait();
        System.out.println("eita");
        handleAcessoLogin(event);
    }


    /* Na função abaixo, foi utilizado IA dentro na expressão do if: "nome == null || nome.trim().isEmpty()".
     * Isso foi feito para corrigir um problema em que, mesmo com campos vazios, as excessões não eram lançadas.
    */

    /**
     * Manipula o evento de clique no botão para criar um novo cliente.
     * Valida se todos os campos obrigatórios foram preenchidos, valida o formato
     * do saldo (deve ser um número positivo), cria uma nova instância de Cliente,
     * adiciona-o à gerenciadora e, se bem-sucedido, chama {@link #mostrarAlertaClienteCriado(ActionEvent)}.
     * Exibe mensagens de erro no {@link #labelAviso} em caso de falha na validação
     * ou criação.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro durante a navegação acionada pelo alerta de sucesso.
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
            
            // Verifica se o email já existe
            if (gerenciadora.getCLientes().containsKey(email)) {
                throw new IllegalArgumentException("Já existe um cliente cadastrado com este email.");
            }

            Cliente novoCliente = new Cliente(nome, email, senha, telefone, saldo);
            gerenciadora.getCLientes().put(novoCliente.getEmail(), novoCliente);

            mostrarAlertaClienteCriado(event);
        } catch (NumberFormatException e) {
            labelAviso.setText("Saldo inválido");
        } catch (IllegalArgumentException e) {
            labelAviso.setText(e.getMessage());
        }
    }

    /**
     * Manipula o evento de clique no botão para voltar para a tela de Login.
     * Navega para a tela de login, utilizando o método de navegação da classe pai
     * {@link GeralController#handleAcessoLogin(ActionEvent)}.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro durante a navegação para a tela de login.
     */
    @FXML
    void handleVoltarLogin(ActionEvent event) throws IOException {
        handleAcessoLogin(event);
    }

}