/*
 * EventoEspecificoController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feitacom o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03.GUI;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lab03.Gerenciadora;
import lab03.Ingresso;
import lab03.Eventos.Evento;

/**
 * Controlador JavaFX para a tela de detalhes de um Evento Específico.
 * Responsável por exibir informações detalhadas de um evento selecionado
 * e permitir que o usuário compre ingressos disponíveis para ele.
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoEspecificoController extends GeralController {
    private Evento eventoAtual;

    /**
     * Construtor padrão da classe EventoEspecificoController.
     */
    public EventoEspecificoController(){
        this.eventoAtual = null;
    }

    @FXML
    private Label labelNomeEvento;
    
    @FXML
    private Label labelData;
    
    @FXML
    private Label labelLocal;
    
    @FXML
    private Label labelOrganizadora;
    
    @FXML
    private Label labelSaldo;
    
    @FXML
    private Label labelErro;
    
    @FXML
    private Label botaoComprarIngresso;

    @FXML
    private ListView<Ingresso> listIngressos;

    /**
     * Retorna o evento cujos detalhes estão sendo exibidos nesta tela.
     *
     * @return O objeto Evento atualmente carregado.
     */
    public Evento getEventoAtual(){
        return eventoAtual;
    }

    /**
     * Carrega os detalhes de um evento específico na UI desta tela.
     * Atualiza os labels com as informações do evento, exibe o saldo do usuário
     * e popula a lista de ingressos disponíveis para este evento.
     * Este método é chamado externamente (ex: de {@link EventosController})
     * após a navegação para esta tela.
     *
     * @param evento O objeto Evento cujas informações devem ser exibidas.
     */
    public void carregarEvento(Evento evento){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        this.eventoAtual = evento;

        String nomeEvento = eventoAtual.getNome();
        labelNomeEvento.setText(nomeEvento);
        LocalDate dataEvento = eventoAtual.getData();
        labelData.setText("Data: " + dataEvento);
        String localEvento = eventoAtual.getLocal().getNome();
        labelLocal.setText("Local: " + localEvento);
        String nomeOrganizadora = eventoAtual.getOrganizadora().getNome();
        labelOrganizadora.setText("Organizadora: " + nomeOrganizadora);
        Double saldoUsuario = gerenciadora.getUsuarioAtual().getSaldo();
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        labelSaldo.setText("Saldo: R$" + decimalFormat.format(saldoUsuario));

        List<Ingresso> ingressosDoEvento = SelecionarIngressosDoEvento();
        ObservableList<Ingresso> observableIngressos = FXCollections.observableArrayList(ingressosDoEvento);
        listIngressos.setItems(observableIngressos);
    }

    /**
     * Filtra a lista global de ingressos comuns disponíveis na gerenciadora
     * para encontrar apenas aqueles que pertencem ao {@link #eventoAtual}.
     *
     * @return Uma lista de ingressos disponíveis para o evento atual.
     */
    private List<Ingresso> SelecionarIngressosDoEvento(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        List<Ingresso> ingressosDoEvento = new ArrayList<Ingresso>();

        for (Ingresso ingresso : gerenciadora.getIngressosComunsDisponiveis()){
            if (ingresso.getEvento() == this.eventoAtual) ingressosDoEvento.add(ingresso);
        }

        return ingressosDoEvento;
    }

    /**
     * Obtém o ingresso selecionado na {@link #listIngressos}. Se nenhum ingresso
     * for selecionado, exibe uma mensagem de erro. Caso contrário, tenta processar
     * a venda do ingresso comum através da {@link Gerenciadora}. Atualiza a tela
     * (lista de ingressos e saldo) após a tentativa e exibe mensagens de erro
     * se a compra falhar (ex: saldo insuficiente, ingresso esgotado).
     * Este método é anotado com @FXML e deve ser vinculado a um elemento na GUI.
     *
     * @param event O ActionEvent que disparou este manipulador.
     * @throws IOException Se ocorrer um erro inesperado (embora as exceções de negócio sejam capturadas).
     */
    @FXML
    private void handleComprarIngresso(ActionEvent event) throws IOException {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Ingresso ingressoSelecionado = listIngressos.getSelectionModel().getSelectedItem();
        if (ingressoSelecionado == null) labelErro.setText("Nenhum ingresso selecionado");
        else {
            try {
                gerenciadora.venderIngressoFormaComum(eventoAtual, ingressoSelecionado, gerenciadora.getUsuarioAtual());
            } catch (Exception e){
                labelErro.setText(e.getMessage());
            }
    
            carregarEvento(eventoAtual);
        }
    }
    
}
