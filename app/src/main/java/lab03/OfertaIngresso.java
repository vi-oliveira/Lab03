package lab03;

import lab03.Clientes.Cliente;

public class OfertaIngresso {
    private Ingresso ingresso;
    private Double precoPedido;
    private Cliente vendedor;

    public OfertaIngresso(Ingresso ingresso, Double precoPedido, Cliente vendedor){
        this.ingresso = ingresso;
        this.precoPedido = precoPedido;
        this.vendedor = vendedor;
    }

    public Ingresso getIngresso(){
        return ingresso;
    }

    public Double getPrecoPedido(){
        return precoPedido;
    }
    
    public Cliente getVendedor(){
        return vendedor;
    }
}
