/*
 * Markeplace.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03;

import java.util.ArrayList;
import java.util.List;

import lab03.Clientes.Cliente;

/**
 * Representa o marketplace onde clientes podem listar e comprar ingressos
 * de outros clientes. O marketplace gerencia as ofertas, processa as transações
 * de compra e calcula a comissão.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class Marketplace {
    private List<OfertaIngresso> ingressosAVenda;
    private Double comissaoPorcentagem;
    private Double saldoComissao;

    /**
     * Construtor para criar uma nova instância de Marketplace.
     *
     * @param comissaoPorcentagem A porcentagem da comissão a ser cobrada (em valor inteiro, ex: 5 para 5%).
     */
    public Marketplace(Double comissaoPorcentagem){
        this.ingressosAVenda = new ArrayList<OfertaIngresso>();
        this.comissaoPorcentagem = comissaoPorcentagem/100;
        this.saldoComissao = 0.0;
    }

    /**
     * Retorna a lista de todas as ofertas de ingressos disponíveis para venda no marketplace.
     *
     * @return Uma lista de objetos OfertaIngresso.
     */
    public List<OfertaIngresso> listarOfertas(){
        return ingressosAVenda;
    }

    /**
     * Recebe uma nova oferta de ingresso de um vendedor e a adiciona ao marketplace.
     * O ingresso é removido da posse do vendedor.
     *
     * @param vendedor   O cliente que está oferecendo o ingresso.
     * @param ingresso   O ingresso a ser colocado à venda.
     * @param precoPedido O preço pedido pelo vendedor.
     */
    public void receberOFerta(Cliente vendedor, Ingresso ingresso, Double precoPedido){
        OfertaIngresso novaOfertaIngresso = new OfertaIngresso(ingresso, precoPedido, vendedor);
        ingressosAVenda.add(novaOfertaIngresso);
        vendedor.getIngressos().remove(ingresso);
    }

    /**
     * Processa a compra de uma oferta de ingresso por um cliente comprador.
     * Transfere o valor pago (deduzida a comissão) do comprador para o vendedor,
     * adiciona a comissão ao saldo do marketplace, transfere a posse do ingresso
     * para o comprador e remove a oferta do marketplace.
     *
     * @param comprador O cliente que está comprando o ingresso.
     * @param oferta    A oferta de ingresso que está sendo comprada.
     */
    public void processarCompra(Cliente comprador, OfertaIngresso oferta){
        Double valorComissao = oferta.getPrecoPedido()*comissaoPorcentagem;
        Cliente vendedor = oferta.getVendedor();
        
        comprador.setSaldo((comprador.getSaldo() - oferta.getPrecoPedido()));
        comprador.adicionarIngresso(oferta.getIngresso());
        this.saldoComissao += valorComissao;
        this.ingressosAVenda.remove(oferta);

        // O novo saldo do vendedor é o saldo antigo, somado ao valor da venda do ingresso, sem a parte que foi para a comissão;
        vendedor.setSaldo(vendedor.getSaldo() + (oferta.getPrecoPedido() - valorComissao));
    }

    /**
     * Retorna o saldo total acumulado de comissões pelo marketplace.
     *
     * @return O saldo atual das comissões.
     */
    public Double getSaldoComissao(){
        return saldoComissao;
    }
}
