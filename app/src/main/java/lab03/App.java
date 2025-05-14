/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class App {

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
        Gerenciadora gerenciadora = new Gerenciadora();
        gerenciadora.simularExemplo();

        
    }
}
