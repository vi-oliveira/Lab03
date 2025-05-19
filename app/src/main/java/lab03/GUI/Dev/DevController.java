/*
 * DevController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feitacom o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03.GUI.Dev;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lab03.Gerenciadora;
import lab03.Marketplace;
import lab03.Clientes.Cliente;
import lab03.GUI.GeralController;

/**
 * Controlador JavaFX para a janela de Desenvolvimento (Dev).
 * Esta janela é exclusiva para desenvolvedores e tem como objetivo principal
 * demonstrar o funcionamento interno do software, exibindo informações sobre
 * o estado atual do sistema, como dados do marketplace e informações dos clientes.
 * Note: Esta classe não herda de {@link GeralController}, pois abre em uma janela separada
 * e não compartilha a mesma navegação principal.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class DevController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public DevController(){}

    @FXML
    private Label labelInfoMarketplace;

    @FXML
    private ListView<String> listInformacoes;

    /**
     * Método de inicialização chamado automaticamente após o FXML ter sido carregado.
     * Obtém as instâncias da {@link Gerenciadora} e do {@link Marketplace},
     * coleta informações relevantes sobre o sistema (marketplace, clientes)
     * e popula os elementos da UI ({@link #labelInfoMarketplace} e {@link #listInformacoes})
     * com esses dados para demonstração.
     */
    @FXML
    private void initialize() {
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
