package lab03.GUI;

import java.text.DecimalFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    
}
