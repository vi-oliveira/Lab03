/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import lab03.Clientes.Cliente;

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

        Cliente vendedor = gerenciadora.getCLientes().get("Alonso@gmail.com");
        Ingresso ingressoASerVendido = vendedor.getIngressos().get(0);

        System.out.println("Ingressos Antes: " + vendedor.getIngressos());
        // System.out.println("Marketplace antes: " + gerenciadora.getmMarketplace().);
        
        try {
            vendedor.oferecerIngressoParaVenda(ingressoASerVendido, ingressoASerVendido.getPreco() - 20.0 , gerenciadora.getmMarketplace());
        } catch (Exception e){}
        
        System.out.println("Ingressos Depois: " + vendedor.getIngressos());

    }
}
