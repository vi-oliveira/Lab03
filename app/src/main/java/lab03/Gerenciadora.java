package lab03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lab03.Clientes.Cliente;
import lab03.Eventos.Evento;
import lab03.Eventos.HistoricoEventos;
import lab03.Eventos.ImobiliariaDeEventos;
import lab03.Eventos.Organizadora;
import lab03.Exceptions.EventoNaoEncontradoException;
import lab03.Exceptions.IngressoEsgotadoException;
import lab03.Exceptions.SaldoInsuficienteException;

public class Gerenciadora {
    private HistoricoEventos historico;
    private Marketplace marketplace;
    private List<Ingresso> ingressosDisponiveis;
    private List<Ingresso> ingressosVendidos;
    private List<ImobiliariaDeEventos> imobiliarias; // A lista de todos os locais está aqui
    private List<Organizadora> organizadoras;
    private HashMap<String, Cliente> clientes;

    public Gerenciadora(){
        this.historico = new HistoricoEventos();
        this.marketplace = new Marketplace(20);
        this.ingressosDisponiveis = new ArrayList<Ingresso>();
        this.ingressosVendidos = new ArrayList<Ingresso>();
        this.imobiliarias = new ArrayList<ImobiliariaDeEventos>();
        this.organizadoras = new ArrayList<Organizadora>();
        this.clientes = new HashMap<String, Cliente>();
    }

    public void venderIngressoFormaComum(Evento evento, Ingresso ingresso, Cliente cliente)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
            evento.venderIngresso(cliente, ingresso);
            this.ingressosDisponiveis.remove(ingresso);
            this.ingressosVendidos.add(ingresso);
    }
    
    public void venderIngressoFormaComum(Evento evento, List<Ingresso> ingressos, Cliente cliente)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
            evento.venderIngresso(cliente, ingressos);
            this.ingressosDisponiveis.removeAll(ingressos);
            this.ingressosVendidos.addAll(ingressos);
    }
    
    public void venderIngressoDeCLiente(Cliente cliente, OfertaIngresso ofertaIngresso, Marketplace marketplace)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
            marketplace.processarCompra(cliente, ofertaIngresso);
            this.ingressosDisponiveis.remove(ofertaIngresso.getIngresso());
            this.ingressosVendidos.add(ofertaIngresso.getIngresso());
    }

    /*Cria um cenário fictício com exemplos de eventos e outras coisas */
    public void simularExemplo(){
        Simulador simulador = new Simulador();
        simulador.Simular(this);
    }

    public List<Ingresso> getIngressosDisponiveis(){
        return ingressosDisponiveis;
    }

    public List<Ingresso> getIngressosVendidos(){
        return ingressosVendidos;
    }

    public HashMap<String, Cliente> getCLientes(){
        return clientes;
    }

    public HistoricoEventos getHistorico(){
        return historico;
    }

    public Marketplace getmMarketplace(){
        return marketplace;
    }

    public List<ImobiliariaDeEventos> getImobiliarias(){
        return imobiliarias;
    }
    
    public List<Organizadora> getOrganizadoras(){
        return organizadoras;
    }
}
