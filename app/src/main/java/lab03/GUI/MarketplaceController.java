package lab03.GUI;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lab03.Gerenciadora;
import lab03.OfertaIngresso;

public class MarketplaceController extends NavegacaoController {

    @FXML
    private Label labelSaldo;

    @FXML
    private Label labelErro;

    @FXML
    private Button BotaoComprarIngressos;

    @FXML
    private ListView<OfertaIngresso> listOfertaIngressos;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        Double saldoUsuario = gerenciadora.getUsuarioAtual().getSaldo();
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        labelSaldo.setText("Saldo: R$" + decimalFormat.format(saldoUsuario));

        List<OfertaIngresso> ingressos = gerenciadora.getMarketplace().listarOfertas();
        ObservableList<OfertaIngresso> observableIngressos = FXCollections.observableArrayList(ingressos);
        listOfertaIngressos.setItems(observableIngressos);
    }
    
    @FXML
    void handleComprarIngresso(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        OfertaIngresso ingressoSelecionado = listOfertaIngressos.getSelectionModel().getSelectedItem();
        if (ingressoSelecionado == null) labelErro.setText("Nenhum ingresso selecionado");
        else {
            try {
                gerenciadora.getUsuarioAtual().comprarIngressoNoMarketplace(ingressoSelecionado, gerenciadora.getMarketplace());
            } catch (Exception e){
                labelErro.setText(e.getMessage());
            }
        }
        initialize();
    }
}
