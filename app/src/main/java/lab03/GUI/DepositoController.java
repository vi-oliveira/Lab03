/*
 * DepositoController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feitacom o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lab03.Gerenciadora;
import lab03.Clientes.Cliente;

/**
 * Controlador JavaFX para a janela de Depósito.
 * Responsável por permitir que o usuário logado adicione fundos ao seu saldo.
 * Comunica-se com o {@link PerfilController} para atualizar a tela de perfil
 * após um depósito bem-sucedido.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class DepositoController {
    /**
     * Referência ao controlador da tela de perfil. Usada para chamar o método
     * de atualização da tela de perfil após um depósito.
     */
    private PerfilController perfilController;

    /**
     * Construtor padrão da classe DepositoController.
     */
    public DepositoController(){
        this.perfilController = null;
    }

    /**
     * Define a referência para o controlador da tela de perfil.
     * Este método é chamado pela tela de perfil ao abrir a janela de depósito.
     *
     * @param perfilController O controlador da tela de perfil que abriu esta janela.
     */
    public void setPerfilController(PerfilController perfilController){
        this.perfilController = perfilController;
    }

    @FXML
    private Label labelAviso;

    @FXML
    private Button botaoDepositar;

    @FXML
    private TextField textFieldValor;

    /**
     * Obtém o valor digitado no campo de texto, valida se é um número positivo,
     * adiciona o valor ao saldo do usuário logado através da {@link Gerenciadora}
     * e chama o método no {@link #perfilController} para atualizar a tela de perfil.
     * Exibe feedback de sucesso ou mensagens de erro no {@link #labelAviso}.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     */
    @FXML
    private void handleDepositar() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Cliente usuario = gerenciadora.getUsuarioAtual();
        String valorDigitado = textFieldValor.getText();

        try{
            Double valorDeposito = Double.parseDouble(valorDigitado);
            if (valorDeposito < 0) throw new IllegalArgumentException();
            gerenciadora.getUsuarioAtual().setSaldo(usuario.getSaldo() + valorDeposito);
            perfilController.atualizarTelaPerfil(); //Atualiza o valor do saldo na tela de perfil
            labelAviso.setText("Valor de " + valorDeposito + " adicionado");
        } catch (NumberFormatException e) {
            labelAviso.setText("Input inválido");
        } catch (IllegalArgumentException e){
            labelAviso.setText("O valor deve ser positivo");
        }
    }
}
