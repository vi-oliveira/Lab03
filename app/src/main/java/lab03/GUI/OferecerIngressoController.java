package lab03.GUI;

import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab03.Gerenciadora;
import lab03.Ingresso;
import lab03.Clientes.Cliente;
import lab03.Exceptions.IngressoNaoPertenceAoClienteException;

public class OferecerIngressoController extends GeralController {

    @FXML
    private Label labelErro;

    @FXML
    private Button botaoVender;

    @FXML
    private ListView<Ingresso> listIngressos;

    @FXML
    private TextField textFieldValor;

    @FXML
    public void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Cliente usuario = gerenciadora.getUsuarioAtual();

        List<Ingresso> ingressos = usuario.getIngressos();
        ObservableList<Ingresso> observableIngressos = FXCollections.observableArrayList(ingressos);
        listIngressos.setItems(observableIngressos);
    }

    private Stage getStage(ActionEvent event) {
        return (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
    }
    
    @FXML
    void handleVoltarAoMarketplace(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MarketplaceWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Vender ingresso");
    }

    @FXML
    void handleOferecerIngresso(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Cliente vendedor = gerenciadora.getUsuarioAtual();

        Ingresso ingressoSelecionado = listIngressos.getSelectionModel().getSelectedItem();
        if (ingressoSelecionado == null) labelErro.setText("Nenhum ingresso selecionado");
        else {
            try {
                String precoDigitado = textFieldValor.getText();
                Double precoPedido = Double.parseDouble(precoDigitado);
                vendedor.oferecerIngressoParaVenda(ingressoSelecionado, precoPedido, gerenciadora.getMarketplace());
                textFieldValor.setText("");
                initialize();
            } catch (NumberFormatException e) {
                labelErro.setText("Input inválido");
            } catch (IngressoNaoPertenceAoClienteException e){
                labelErro.setText(e.getMessage());
            }
        }
    }
}
