/*
 * Gerenciadora.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi com o uso de Ia
 * e posteriormente revisada e/ou corrigida.
 */
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

/**
 * A classe Gerenciadora atua como o ponto central de controle e comunicação
 * entre a interface gráfica (GUI) e o backend da aplicação. Ela mantém as
 * referências aos principais componentes do sistema, como o histórico de eventos,
 * o marketplace, clientes cadastrados, ingressos disponíveis e entidades
 * organizadoras e imobiliárias. A GUI deve interagir com o backend
 * chamando os métodos disponíveis nesta classe. Implementa o padrão Singleton
 * para garantir uma única instância global.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class Gerenciadora {
    private static Gerenciadora INSTANCE;
    private HistoricoEventos historico; // A lista de todos os eventos está aqui
    private Marketplace marketplace;
    private Cliente usuarioAtual;
    private List<Ingresso> ingressosComunsDisponiveis;
    private List<ImobiliariaDeEventos> imobiliarias; // A lista de todos os locais está aqui
    private List<Organizadora> organizadoras;
    private HashMap<String, Cliente> clientes; // HashMap para que o login seja mais eficiente

    /**
     * Construtor privado para a classe Gerenciadora, seguindo o padrão Singleton.
     * Inicializa os componentes do sistema.
     */
    private Gerenciadora(){
        this.historico = new HistoricoEventos();
        this.marketplace = new Marketplace(20.0);
        this.usuarioAtual = null;
        this.ingressosComunsDisponiveis = new ArrayList<Ingresso>();
        this.imobiliarias = new ArrayList<ImobiliariaDeEventos>();
        this.organizadoras = new ArrayList<Organizadora>();
        this.clientes = new HashMap<String, Cliente>();
    }
    
    /**
     * Retorna a única instância da classe Gerenciadora (padrão Singleton).
     * Se a instância ainda não existir, ela é criada.
     *
     * @return A instância única de Gerenciadora.
     */
    public static Gerenciadora getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Gerenciadora();
        }
        return INSTANCE;
    }

    /**
     * Processa a venda de um único ingresso comum para um cliente.
     * Remove o ingresso da lista de ingressos comuns disponíveis.
     *
     * @param evento  O evento ao qual o ingresso pertence.
     * @param ingresso O ingresso a ser vendido.
     * @param cliente O cliente que está comprando o ingresso.
     * @throws IngressoEsgotadoException se a capacidade máxima do local já foi atingida.
     * @throws EventoNaoEncontradoException Se o evento não for encontrado.
     * @throws SaldoInsuficienteException Se o cliente não tiver saldo suficiente para a compra.
     */
    public void venderIngressoFormaComum(Evento evento, Ingresso ingresso, Cliente cliente)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
            evento.venderIngresso(cliente, ingresso);
            this.ingressosComunsDisponiveis.remove(ingresso);
    }
    
    /**
     * Processa a venda de uma lista de ingressos comuns para um cliente.
     * Remove os ingressos da lista de ingressos comuns disponíveis.
     *
     * @param evento  O evento ao qual os ingressos pertencem.
     * @param ingressos A lista de ingressos a serem vendidos.
     * @param cliente O cliente que está comprando os ingressos.
     * @throws IngressoEsgotadoException se a capacidade máxima do local já foi atingida.
     * @throws EventoNaoEncontradoException Se o evento não for encontrado.
     * @throws SaldoInsuficienteException Se o cliente não tiver saldo suficiente para a compra.
     */
    public void venderIngressoFormaComum(Evento evento, List<Ingresso> ingressos, Cliente cliente)
    throws IngressoEsgotadoException, EventoNaoEncontradoException, SaldoInsuficienteException {
            evento.venderIngresso(cliente, ingressos);
            this.ingressosComunsDisponiveis.removeAll(ingressos);
    }

    /**
     * Cria um cenário fictício com exemplos de eventos, imobiliárias, locais,
     * clientes e vendas, populando a gerenciadora com dados de simulação.
     */
    public void simularExemplo(){
        Simulador simulador = new Simulador();
        simulador.Simular(this);
    }

    /**
     * Define o cliente que está utilizando a aplicação no momento.
     *
     * @param usuario O cliente que está logado, ou null se deslogado.
     */
    public void setUsuarioAtual(Cliente usuario) {
        this.usuarioAtual = usuario;
    }

    /**
     * Retorna o mapa de todos os clientes cadastrados no sistema.
     *
     * @return Um HashMap onde a chave é o email do cliente e o valor é o objeto Cliente.
     */
    public HashMap<String, Cliente> getCLientes(){
        return clientes;
    }

    /**
     * Retorna o histórico de eventos do sistema.
     *
     * @return O objeto HistoricoEventos.
     */
    public HistoricoEventos getHistorico(){
        return historico;
    }

    /**
     * Retorna a instância do marketplace do sistema.
     *
     * @return O objeto Marketplace.
     */
    public Marketplace getMarketplace(){
        return marketplace;
    }

    /**
     * Retorna o cliente que está logado no momento.
     *
     * @return O cliente atualmente logado, ou null.
     */
    public Cliente getUsuarioAtual(){
        return usuarioAtual;
    }

    /**
     * Retorna a lista de ingressos comuns que estão disponíveis para venda
     * direta.
     *
     * @return Uma lista de objetos Ingresso.
     */
    public List<Ingresso> getIngressosComunsDisponiveis(){
        return ingressosComunsDisponiveis;
    }

    /**
     * Retorna a lista de imobiliárias cadastradas no sistema.
     *
     * @return Uma lista de objetos ImobiliariaDeEventos.
     */
    public List<ImobiliariaDeEventos> getImobiliarias(){
        return imobiliarias;
    }
    
    /**
     * Retorna a lista de organizadoras cadastradas no sistema.
     *
     * @return Uma lista de objetos Organizadora.
     */
    public List<Organizadora> getOrganizadoras(){
        return organizadoras;
    }
}
