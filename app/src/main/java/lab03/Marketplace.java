package lab03;

import java.util.ArrayList;
import java.util.List;

import lab03.Clientes.Cliente;

public class Marketplace {
    private List<OfertaIngresso> ingressosAVenda;
    private int comissaoPorcentagem;
    private Double saldoComissao;

    public Marketplace(int comissaoPorcentagem){
        this.ingressosAVenda = new ArrayList<OfertaIngresso>();
        this.comissaoPorcentagem = comissaoPorcentagem/100;
        this.saldoComissao = 0.0;
    }

    public List<OfertaIngresso> listarOfertas(){
        return ingressosAVenda;
    }

    public void receberOFerta(Cliente vendedor, Ingresso ingresso, Double precoPedido){
        OfertaIngresso novaOfertaIngresso = new OfertaIngresso(ingresso, precoPedido, vendedor);
        ingressosAVenda.add(novaOfertaIngresso);
        vendedor.getIngressos().remove(ingresso);
    }

    public void processarCompra(Cliente comprador, OfertaIngresso oferta){
        Double valorComissao = oferta.getPrecoPedido()*comissaoPorcentagem;
        Cliente vendedor = oferta.getVendedor();
        
        comprador.setSaldo((comprador.getSaldo() - oferta.getPrecoPedido()));
        comprador.adicionarIngresso(oferta.getIngresso());
        this.saldoComissao += valorComissao;

        // O novo saldo do vendedor é o saldo antigo, somado ao valor da venda do ingresso, sem a parte que foi para a comissão;
        vendedor.setSaldo(vendedor.getSaldo() + (oferta.getPrecoPedido() - valorComissao));
    }

    public Double getSaldoComissao(){
        return saldoComissao;
    }
}
