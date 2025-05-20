/*
 * OferecerIngressoController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03.GUI;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lab03.Gerenciadora;
import lab03.Ingresso;
import lab03.Clientes.Cliente;
import lab03.Exceptions.IngressoNaoPertenceAoClienteException;

/**
 * Controlador JavaFX para a tela de Oferecer Ingresso para Venda.
 * Responsável por exibir os ingressos que o usuário logado possui e permitir
 * que ele os liste no marketplace a um determinado preço.
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class OferecerIngressoController extends GeralController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public OferecerIngressoController(){}

    @FXML
    private Label labelErro;

    @FXML
    private Button botaoVender;

    @FXML
    private ListView<Ingresso> listIngressos;

    @FXML
    private TextField textFieldValor;

    /**
     * Método de inicialização chamado automaticamente após o FXML ter sido carregado.
     * Configura a tela, carregando e exibindo os ingressos que o usuário logado
     * possui na ListView.
     */
    @FXML
    private void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Cliente usuario = gerenciadora.getUsuarioAtual();

        List<Ingresso> ingressos = usuario.getIngressos();
        ObservableList<Ingresso> observableIngressos = FXCollections.observableArrayList(ingressos);
        listIngressos.setItems(observableIngressos);
    }
    
    /**
     * Manipula o evento de clique para voltar para a tela do Marketplace.
     * Reutiliza o método de navegação da classe pai {@link GeralController#handleAcessoMarketPlace(ActionEvent)}.
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na UI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws Exception Se ocorrer um erro durante a navegação (propagado do método da classe pai).
     */
    @FXML
    private void handleVoltarAoMarketplace(ActionEvent event) throws Exception {
        handleAcessoMarketPlace(event);
    }

    /**
     * Pega o ingresso selecionado na lista, o preço digitado no campo de texto,
     * valida a entrada do preço e tenta oferecer o ingresso para venda no marketplace
     * através da gerenciadora. Atualiza a GUI e exibe mensagens de erro em
     * caso de falha (input inválido, ingresso não pertencente ao usuário).
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro inesperado durante a execução.
     */
    @FXML
    private void handleOferecerIngresso(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Cliente vendedor = gerenciadora.getUsuarioAtual();

        Ingresso ingressoSelecionado = listIngressos.getSelectionModel().getSelectedItem();
        if (ingressoSelecionado == null) labelErro.setText("Nenhum ingresso selecionado");
        else {
            try {
                String precoDigitado = textFieldValor.getText();
                Double precoPedido = Double.parseDouble(precoDigitado);
                vendedor.oferecerIngressoParaVenda(
                    ingressoSelecionado, precoPedido, gerenciadora.getMarketplace());
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
