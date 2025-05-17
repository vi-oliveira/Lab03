package lab03;

import java.util.ArrayList;
import java.util.List;

import lab03.Clientes.Cliente;
import lab03.Eventos.Evento;
import lab03.Exceptions.EventoNaoEncontradoException;
import lab03.Exceptions.IngressoEsgotadoException;
import lab03.Exceptions.SaldoInsuficienteException;

public class Marketplace {
    private List<Vendivel> ingressosAVenda;
    private int comissaoPorcentagem;
    private Double saldoComissao;

    public Marketplace(int comissaoPorcentagem){
        this.ingressosAVenda = new ArrayList<Vendivel>();
        this.comissaoPorcentagem = comissaoPorcentagem/100;
        this.saldoComissao = 0.0;
    }

    public List<Vendivel> listarOfertas(){
        return ingressosAVenda;
    }

    public void receberOFerta(Cliente vendedor, Ingresso ingresso, Double precoPedido){
        OfertaIngresso novaOfertaIngresso = new OfertaIngresso(ingresso, precoPedido, vendedor);
        ingressosAVenda.add(novaOfertaIngresso);
        vendedor.getIngressos().remove(ingresso);
    }

    private void processarCompraComum(Cliente comprador, Ingresso ingresso)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
        System.out.println("ingresso");
        Evento evento = ingresso.getEvento();
        evento.venderIngresso(comprador, ingresso);

        // Se não houver erro, o valor do ingresso é descontado do cliente
        comprador.setSaldo(comprador.getSaldo() - ingresso.getPreco());
    }

    private void processarCompraDeCliente(Cliente comprador, OfertaIngresso oferta)
    throws SaldoInsuficienteException {
        if (comprador.getSaldo() < oferta.getPreco()) throw new SaldoInsuficienteException("Salado insuficiente");
        Double valorComissao = oferta.getPreco() * comissaoPorcentagem;
        Cliente vendedor = oferta.getVendedor();
        
        comprador.setSaldo((comprador.getSaldo() - oferta.getPreco()));
        comprador.adicionarIngresso(oferta.getIngresso());
        this.saldoComissao += valorComissao;

        // O novo saldo do vendedor é o saldo antigo, somado ao valor da venda do ingresso, sem a parte que foi para a comissão;
        vendedor.setSaldo(vendedor.getSaldo() + (oferta.getPreco() - valorComissao));
    }

    public void processarCompra(Cliente comprador, Vendivel oferta)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
        if (oferta instanceof OfertaIngresso){
            OfertaIngresso ofertaDeCliente = (OfertaIngresso) oferta;
            processarCompraDeCliente(comprador, ofertaDeCliente);
        } else if (oferta instanceof Ingresso) {
            Ingresso ofertaComum = (Ingresso) oferta;
            processarCompraComum(comprador, ofertaComum);
        }

        // Se não for lançado erro, a oferta é removida
        this.ingressosAVenda.remove(oferta);
    }

    public Double getSaldoComissao(){
        return saldoComissao;
    }
}
