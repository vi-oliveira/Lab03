package lab03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lab03.Clientes.Cliente;
import lab03.Eventos.Evento;
import lab03.Eventos.HistoricoEventos;
import lab03.Eventos.ImobiliariaDeEventos;
import lab03.Eventos.Organizadora;

public class Gerenciadora {
    private HistoricoEventos historico;
    private Double saldoComissao;
    private List<Ingresso> ingressosDisponiveis;
    private List<Ingresso> ingressosVendidos;
    private List<ImobiliariaDeEventos> imobiliarias; // A lista de todos os locais está aqui
    private List<Organizadora> organizadoras;
    private HashMap<String, Cliente> clientes;

    public Gerenciadora(){
        this.historico = new HistoricoEventos();
        this.saldoComissao = 0.0;
        this.ingressosDisponiveis = new ArrayList<Ingresso>();
        this.ingressosVendidos = new ArrayList<Ingresso>();
        this.imobiliarias = new ArrayList<ImobiliariaDeEventos>();
        this.organizadoras = new ArrayList<Organizadora>();
        this.clientes = new HashMap<String, Cliente>();
    }

    // ALTERAR PARA TER EXCEÇÃO DE SALDO E OUTROS LÇKDSJFALDKJF, ELE VAI LANÇAR E NÃO TEM TRY CATCH
    public void venderIngresso(Evento evento, Ingresso ingresso, Cliente cliente){
        try{
            evento.venderIngresso(cliente, ingresso);
            this.ingressosDisponiveis.remove(ingresso);
            this.ingressosVendidos.add(ingresso);
        } catch (Exception e) {
            // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHH
        }
    }

    /*Cria um cenário fictício com exemplos de eventos e outras coisas */
    public void simularExemplo(){
        Simulador simulador = new Simulador();
        simulador.Simular(this);
    }

    public Double getSaldoComissao(){
        return saldoComissao;
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

    public List<ImobiliariaDeEventos> getImobiliarias(){
        return imobiliarias;
    }
    
    public List<Organizadora> getOrganizadoras(){
        return organizadoras;
    }
}
