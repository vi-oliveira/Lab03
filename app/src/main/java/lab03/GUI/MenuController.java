/*
 * MenuController.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feitacom o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lab03.Gerenciadora;

/**
 * Controlador JavaFX para a tela do Menu Principal.
 * Responsável por exibir uma mensagem de boas-vindas ao usuário logado
 * e fornecer botões para navegar para outras seções da aplicação (Eventos, Marketplace, Perfil).
 * Herda de {@link GeralController} para funcionalidades de navegação comuns.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class MenuController extends GeralController {

    /**
     * Construtor padrão da classe. (Para remover aviso do javadoc)
     */
    public MenuController(){}

    @FXML
    private Label labelUsuario;

    @FXML
    private Button botaoEventos;

    @FXML
    private Button botaoMarketPlace;

    @FXML
    private Button botaoPerfil;

    /**
     * Método de inicialização chamado automaticamente após o FXML ter sido carregado.
     * Obtém o nome do usuário logado através da {@link Gerenciadora} e atualiza
     * o texto do {@link #labelUsuario} para exibir uma mensagem de boas-vindas.
     */
    @FXML
    private void initialize() {
        Gerenciadora gerenciadora = Gerenciadora.getInstance();

        String nomeUsuario = gerenciadora.getUsuarioAtual().getNome();
        labelUsuario.setText("BOAS-VINDAS, " + nomeUsuario.toUpperCase() + "!");
    }
}
