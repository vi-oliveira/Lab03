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

public class EventoEspecificoController extends GeralController {
    private Evento eventoAtual = null;

    public Evento getEventoAtual(){
        return eventoAtual;
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

    private List<Ingresso> getIngressosDoEvento(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        List<Ingresso> ingressosDoEvento = new ArrayList<Ingresso>();

        for (Ingresso ingresso : gerenciadora.getIngressosComunsDisponiveis()){
            if (ingresso.getEvento() == this.eventoAtual) ingressosDoEvento.add(ingresso);
        }

        return ingressosDoEvento;
    }

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

        List<Ingresso> ingressosDoEvento = getIngressosDoEvento();
        ObservableList<Ingresso> observableIngressos = FXCollections.observableArrayList(ingressosDoEvento);
        listIngressos.setItems(observableIngressos);
    }

    @FXML
    void handleComprarIngresso(ActionEvent event) throws IOException {
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

    @FXML
    public void initialize(Evento evento) {

    }
    
}
