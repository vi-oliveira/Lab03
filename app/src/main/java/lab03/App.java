/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab03.Clientes.Cliente;

/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));

        // Cria a cena com o nó raiz
        Scene scene = new Scene(root, 300, 200);

        // Configura o Stage
        primaryStage.setTitle("Minha Primeira Aplicação JavaFX");
        primaryStage.setScene(scene);

        // Exibe o Stage
        primaryStage.show();
    }

    /**
     * Construtor padrão da classe App.
     * Construtor vazio, pois a classe é utilizada apenas para a main.
     */
    public App(){}

    /**
     * Aplicação principal
     * @param args argumentos para execução
     */
    public static void main(String[] args) {
         launch(args); // Inicia a aplicação JavaFX
        // Testar forma de singleton
        /*Gerenciadora gerenciadora = new Gerenciadora();
        gerenciadora.simularExemplo();

        Cliente vendedor = gerenciadora.getCLientes().get("Alonso@gmail.com");
        Ingresso ingressoASerVendido = vendedor.getIngressos().get(0);

        System.out.println("Ingressos Antes: " + vendedor.getIngressos());
        
        try {
            vendedor.oferecerIngressoParaVenda(ingressoASerVendido, ingressoASerVendido.getPreco() - 20.0 , gerenciadora.getMarketplace());
        } catch (Exception e){}
        
        System.out.println("Ingressos Depois: " + vendedor.getIngressos());*/

    }
}
