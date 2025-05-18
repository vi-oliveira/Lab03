package lab03.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lab03.Gerenciadora;

public class MenuController extends NavegacaoController {

    @FXML
    private Label labelUsuario;

    @FXML
    private Button botaoEventos;

    @FXML
    private Button botaoMarketPlace;

    @FXML
    private Button botaoPerfil;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        String nomeUsuario = gerenciadora.getUsuarioAtual().getNome();
        labelUsuario.setText("BEM-VINDO, " + nomeUsuario.toUpperCase() + "!");
    }
}
