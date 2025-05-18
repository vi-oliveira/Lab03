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

public class PerfilController extends NavegacaoController {

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

    @FXML
    public void initialize() {
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
    
    @FXML
    public void handleJanelaDepositar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DepositoWindow.fxml"));
        Parent root = loader.load();
        DepositoController depositoController = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Depositar");
        stage.show();
        depositoController.setPerfilController(this);
    }
}
