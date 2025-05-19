/*
 * OfertaIngresso.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03;

import java.text.DecimalFormat;

import lab03.Clientes.Cliente;

/**
 * Representa uma oferta de um ingresso para venda no marketplace,
 * incluindo o ingresso em questão, o preço pedido pelo vendedor e o cliente vendedor.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class OfertaIngresso {
    private Ingresso ingresso;
    private Double precoPedido;
    private Cliente vendedor;

    /**
     * Construtor para criar uma nova oferta de ingresso.
     *
     * @param ingresso    O ingresso a ser oferecido.
     * @param precoPedido O preço pedido pelo vendedor.
     * @param vendedor    O cliente que está vendendo o ingresso.
     */
    public OfertaIngresso(Ingresso ingresso, Double precoPedido, Cliente vendedor){
        this.ingresso = ingresso;
        this.precoPedido = precoPedido;
        this.vendedor = vendedor;
    }

    /**
     * Retorna o ingresso associado a esta oferta.
     *
     * @return O ingresso que está sendo oferecido.
     */
    public Ingresso getIngresso(){
        return ingresso;
    }

    /**
     * Retorna o preço pedido pelo vendedor nesta oferta.
     *
     * @return O preço pedido.
     */
    public Double getPrecoPedido(){
        return precoPedido;
    }
    
    /**
     * Retorna o cliente que está oferecendo este ingresso para venda.
     *
     * @return O cliente vendedor.
     */
    public Cliente getVendedor(){
        return vendedor;
    }

    /**
     * Retorna uma representação em string desta oferta de ingresso,
     * formatada para exibição.
     *
     * @return Uma string descrevendo a oferta (vendedor, evento e preço).
     */
    @Override
    public String toString(){
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        return "Ingresso de " + this.vendedor.getNome() + " para: " + this.ingresso.getEvento().getNome() + "  -  R$" + decimalFormat.format(this.precoPedido);
    }
}
