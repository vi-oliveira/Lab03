package lab03.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lab03.Gerenciadora;
import lab03.Clientes.Cliente;

public class DepositoController {
    // Este perfilController serve para poder atualizar o valor do saldo na tela de perfil em tempo real
    private PerfilController perfilController = null;

    public void setPerfilController(PerfilController perfilController){
        this.perfilController = perfilController;
    }

    @FXML
    private Label labelAviso;

    @FXML
    private Button botaoDepositar;

    @FXML
    private TextField textFieldValor;

    @FXML
    void handleDepositar() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Cliente usuario = gerenciadora.getUsuarioAtual();
        String valorDigitado = textFieldValor.getText();

        try{
            Double valorDeposito = Double.parseDouble(valorDigitado);
            if (valorDeposito < 0) throw new IllegalArgumentException();
            gerenciadora.getUsuarioAtual().setSaldo(usuario.getSaldo() + valorDeposito);
            perfilController.initialize(); //Atualiza o valor do saldo na tela de perfil
            labelAviso.setText("Valor de " + valorDeposito + " adicionado");
        } catch (NumberFormatException e) {
            labelAviso.setText("Input inválido");
        } catch (IllegalArgumentException e){
            labelAviso.setText("O valor deve ser positivo");
        }
    }
}
