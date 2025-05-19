/*
 * MarkeplaceController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
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
import lab03.OfertaIngresso;

/**
 * Controlador JavaFX para a tela do Marketplace.
 * Responsável por exibir as ofertas de ingressos, mostrar o saldo do usuário
 * logado e lidar com as ações de compra e navegação para a tela de venda de ingressos.
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class MarketplaceController extends GeralController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public MarketplaceController(){}

    @FXML
    private Label labelSaldo;

    @FXML
    private Label labelErro;

    @FXML
    private Button BotaoComprarIngressos;

    @FXML
    private Button buttonVender;

    @FXML
    private ListView<OfertaIngresso> listOfertaIngressos;

    /**
     * Método de inicialização chamado automaticamente após o FXML ter sido carregado.
     * Configura a tela, exibindo o saldo do usuário logado e carregando as ofertas
     * de ingressos disponíveis no marketplace na ListView.
     */
    @FXML
    private void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        Double saldoUsuario = gerenciadora.getUsuarioAtual().getSaldo();
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        labelSaldo.setText("Saldo: R$" + decimalFormat.format(saldoUsuario));

        List<OfertaIngresso> ingressos = gerenciadora.getMarketplace().listarOfertas();
        ObservableList<OfertaIngresso> observableIngressos = FXCollections.observableArrayList(ingressos);
        listOfertaIngressos.setItems(observableIngressos);
    }
    
    /**
     * Manipula o evento de clique no botão de comprar ingresso.
     * Verifica se um ingresso foi selecionado, tenta processar a compra
     * através da gerenciadora e atualiza a GUI (saldo e lista de ofertas)
     * após a tentativa de compra. Exibe mensagens de erro se a compra falhar.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro inesperado durante a execução (embora não diretamente relacionado a carga de FXML aqui).
     */
    @FXML
    private void handleComprarIngresso(ActionEvent event) throws IOException {
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

    /**
     * Manipula o evento de clique no botão para acessar a tela de venda de ingresso.
     * Carrega e exibe a tela onde o usuário pode listar um ingresso para venda no marketplace.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML da tela de oferta de ingresso.
     */
    @FXML
    private void handleAcessarVendaIngresso(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/OferecerIngressoWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = getStage(event);
        scene.getStylesheets().add(getClass().getResource("/EstiloPadrao.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Vender ingresso");
    }
}
