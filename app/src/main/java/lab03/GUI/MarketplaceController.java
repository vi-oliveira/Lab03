package lab03.GUI;

import java.text.DecimalFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lab03.Gerenciadora;
import lab03.OfertaIngresso;

public class MarketplaceController {

    @FXML
    private Label labelUsuario;

    @FXML
    private Label labelSaldo;

    @FXML
    private Button BotaoComprarIngressos;

    @FXML
    private ListView<OfertaIngresso> listVendiveis;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        String nomeUsuario = gerenciadora.getUsuarioAtual().getNome();
        labelUsuario.setText(nomeUsuario);
        Double saldoUsuario = gerenciadora.getUsuarioAtual().getSaldo();
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        labelSaldo.setText("Saldo: R$" + decimalFormat.format(saldoUsuario));

        List<OfertaIngresso> ingressos = gerenciadora.getMarketplace().listarOfertas();
        ObservableList<OfertaIngresso> observableIngressos = FXCollections.observableArrayList(ingressos);
        listVendiveis.setItems(observableIngressos);
    }
    
}
