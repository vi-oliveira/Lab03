package lab03;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import lab03.Clientes.Cliente;
import lab03.Eventos.HistoricoEventos;
import lab03.Eventos.ImobiliariaDeEventos;
import lab03.Eventos.Organizadora;

public class Gerenciadora {
    private HistoricoEventos historico;
    private List<ImobiliariaDeEventos> imobiliarias; // A lista de todos os locais está aqui
    private List<Organizadora> organizadoras;
    private TreeSet<Cliente> clientes;

    public Gerenciadora(){
        this.historico = new HistoricoEventos();
        this.imobiliarias = new ArrayList<ImobiliariaDeEventos>();
        this.organizadoras = new ArrayList<Organizadora>();
        this.clientes = new TreeSet<Cliente>();
    }

    

    /*Cria um cenário fictício com exemplos de eventos e outras coisas */
    public void simularExemplo(){

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
