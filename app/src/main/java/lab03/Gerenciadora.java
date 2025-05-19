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
    private static Gerenciadora INSTANCE;
    private HistoricoEventos historico;
    private Marketplace marketplace;
    private Cliente usuarioAtual;
    private List<Ingresso> ingressosComunsDisponiveis;
    private List<ImobiliariaDeEventos> imobiliarias; // A lista de todos os locais está aqui
    private List<Organizadora> organizadoras;
    private HashMap<String, Cliente> clientes;

    private Gerenciadora(){
        this.historico = new HistoricoEventos();
        this.marketplace = new Marketplace(20.0);
        this.usuarioAtual = null; // Na simulação de clientes, alterar para que seja feito por login
        this.ingressosComunsDisponiveis = new ArrayList<Ingresso>();
        this.imobiliarias = new ArrayList<ImobiliariaDeEventos>();
        this.organizadoras = new ArrayList<Organizadora>();
        this.clientes = new HashMap<String, Cliente>();
    }
    
    public static Gerenciadora getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Gerenciadora();
        }
        return INSTANCE;
    }

    public void venderIngressoFormaComum(Evento evento, Ingresso ingresso, Cliente cliente)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
            evento.venderIngresso(cliente, ingresso);
            this.ingressosComunsDisponiveis.remove(ingresso);
    }
    
    public void venderIngressoFormaComum(Evento evento, List<Ingresso> ingressos, Cliente cliente)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
            evento.venderIngresso(cliente, ingressos);
            this.ingressosComunsDisponiveis.removeAll(ingressos);
    }

    /*Cria um cenário fictício com exemplos de eventos e outras coisas */
    public void simularExemplo(){
        Simulador simulador = new Simulador();
        simulador.Simular(this);
    }

    public void setUsuario(Cliente usuario) {
        this.usuarioAtual = usuario;
    }

    public HashMap<String, Cliente> getCLientes(){
        return clientes;
    }

    public HistoricoEventos getHistorico(){
        return historico;
    }

    public Marketplace getMarketplace(){
        return marketplace;
    }

    public Cliente getUsuarioAtual(){
        return usuarioAtual;
    }

    public List<Ingresso> getIngressosComunsDisponiveis(){
        return ingressosComunsDisponiveis;
    }

    public List<ImobiliariaDeEventos> getImobiliarias(){
        return imobiliarias;
    }
    
    public List<Organizadora> getOrganizadoras(){
        return organizadoras;
    }
}
