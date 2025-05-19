package lab03.GUI.Dev;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lab03.Gerenciadora;
import lab03.Marketplace;
import lab03.Clientes.Cliente;

public class DevController {

    @FXML
    private Label labelInfoMarketplace;

    @FXML
    private ListView<String> listInformacoes;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Marketplace marketplace = gerenciadora.getMarketplace();

        String infoMarketplace = " - comissões: " + marketplace.getSaldoComissao() + "; qtdOFertasDisponíveis: " +  marketplace.listarOfertas().size();
        labelInfoMarketplace.setText(infoMarketplace);

        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        List<String> infoClientes = new ArrayList<String>();
        String infoCliente = null;
        for (Cliente cliente : gerenciadora.getCLientes().values()){
            infoCliente = cliente.getNome() + " - Saldo: " + decimalFormat.format(cliente.getSaldo()) + " - qtdIngressos: " + cliente.getIngressos().size();
            infoClientes.add(infoCliente);
        }

        ObservableList<String> observableInfoClientes = FXCollections.observableArrayList(infoClientes);
        listInformacoes.setItems(observableInfoClientes);
    }
}
