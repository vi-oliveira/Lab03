/*
 * Evento.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * O atributo data foi alterado de String para LocalDate para que seja possível
 * analisar mais facilmente se o local estará disponível para ser alocado para o
 * evento na data ou, principalmente, nas datas em que determinado evento ocorre.
 * 
 * Para criar um objeto de algum tipo de evento, não é mais necessário fornecer o local,
 * visto que ele será alocado para o evento posteriormente. Pela implementação original,
 * o local era fornecido na criação do evento, agora a relação entre os dois objetos se
 * dá no caso da disponibilidade do local na data do evento.
 * 
 * A documentação para javadoc de alguns métodos deste arquivo foi
 * feita com o uso do ChatGPT e posteriormente revisada e/ou corrigida.
 */

package lab03.Eventos;

import java.time.LocalDate;
import java.util.List;

import lab03.Ingresso;
import lab03.Local;
import lab03.Clientes.Cliente;
import lab03.Eventos.Caracteristicas.CaracteristicaDeEvento;
import lab03.Exceptions.EventoNaoEncontradoException;
import lab03.Exceptions.IngressoEsgotadoException;

/**
 * Classe abstrata que representa um evento genérico.
 * 
 * Um evento possui características definidas por um objeto {@link CaracteristicaDeEvento}.
 * Esta classe permite a venda de ingressos para clientes, tratando possíveis exceções
 * como ingressos esgotados ou eventos incorretos.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public abstract class Evento {
    /**
     * Características do evento, como local, ingressos vendidos, etc.
     */
    protected CaracteristicaDeEvento caracteristicas;

    /**
     * Construtor da classe Evento
     * @param caracteristicas as caracteristicas do Evento
     */
    public Evento(CaracteristicaDeEvento caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    /**
     * Retorna o nome do Evento
     * @return o nome do Evento
     */
    public String getNome(){
        return caracteristicas.getNome();
    }

    /**
     * Altera o nome do Evento para `nome` 
     * @param nome o novo nome do Evento
     */
    public void setNome(String nome){
        this.caracteristicas.setNome(nome);
    }

    /**
     * Retorna o Local do Evento
     * @return o local do Evento
     */
    public Local getLocal() {
        return caracteristicas.getLocal();
    }

    /**
     * Altera o local do Evento para `local` 
     * @param local o novo local do Evento
     */
    public void setLocal(Local local) {
        this.caracteristicas.setLocal(local);
    }

    /**
     * Retorna o preço do ingresso do Evento
     * @return o precoIngresso do Evento
     */
    public double getPrecoIngresso(){
        return caracteristicas.getPrecoIngresso();
    }

    /**
     * Altera o precoIngresso do Evento para `precoIngresso` 
     * @param precoIngresso o novo precoIngresso do Evento
     */
    public void setPrecoIngresso(double precoIngresso){
        this.caracteristicas.setPrecoIngresso(precoIngresso);
    }

    /**
     * Retorna as características do Evento
     * @return as características do Evento
     */
    public CaracteristicaDeEvento getCaracteristicas(){
        return caracteristicas;
    }

    /**
     * Altera as características do Evento para 'caracteristicas'
     * @param caracteristicas as novas características do Evento
     */
    public void setCaracteristicas(CaracteristicaDeEvento caracteristicas){
        this.caracteristicas = caracteristicas;
    }

    /**
     * Retorna a data do Evento
     * @return a data do Evento
     */
    public LocalDate getData() {
        return caracteristicas.getData();
    }

    /**
     * Retorna a lista de ingressos vendidos
     * @return a a lista de ingressos vendidos
     */
    public List<Ingresso> getIngressosVendidos(){
        return caracteristicas.getIngressosVendidos();
    }

    /**
     * Retorna a organizadora do Evento
     * @return a organizadora do Evento
     */
    public Organizadora getOrganizadora(){
        return caracteristicas.getOrganizadora();
    }
    
    /**
     * Gera uma descrição genérica de um evneto,
     * com nome e data.
     * @return uma String com a descrição do evento
     */
    public String descricao(){
        return "Evento: " + this.caracteristicas.getNome() + " - Data: " + this.caracteristicas.getData();
    }

    /**
     * Realiza a venda de um único ingresso para um cliente.
     *
     * @param cliente o cliente que está comprando o ingresso
     * @param ingresso o ingresso a ser vendido
     * @throws IngressoEsgotadoException se a capacidade máxima do local já foi atingida
     * @throws EventoNaoEncontradoException se o ingresso fornecido não pertence a este evento
     */
    public void venderIngresso(Cliente cliente, Ingresso ingresso)
    throws IngressoEsgotadoException, EventoNaoEncontradoException {
        if (this.caracteristicas.getIngressosVendidos().size() + 1 >
        this.caracteristicas.getLocal().getCapacidade()){
            throw new IngressoEsgotadoException("Os ingressos esgotaram");
        }
        if (!(ingresso.getEvento().equals(this))){
            throw new EventoNaoEncontradoException(
                "O evento do ingresso e o evento fornecido são diferentes: "
                + ingresso.getEvento().getNome() + " != " + this.getNome());
        }
        this.caracteristicas.getIngressosVendidos().add(ingresso);
        cliente.adicionarIngresso(ingresso);
    }

    /**
     * Realiza a venda de múltiplos ingressos para um cliente.
     *
     * @param cliente o cliente que está comprando os ingressos
     * @param ingressos a lista de ingressos a serem vendidos
     * @throws IngressoEsgotadoException se a venda dos ingressos ultrapassar a capacidade do local
     * @throws EventoNaoEncontradoException se algum ingresso da lista não pertence a este evento
     */
    public void venderIngresso(Cliente cliente, List<Ingresso> ingressos)
    throws IngressoEsgotadoException, EventoNaoEncontradoException {
        if (this.caracteristicas.getIngressosVendidos().size() + ingressos.size() >
        this.caracteristicas.getLocal().getCapacidade()){
            throw new IngressoEsgotadoException("Os ingressos esgotaram");
        }
        for (Ingresso ingresso : ingressos){
            if (!(ingresso.getEvento().equals(this))){
                throw new EventoNaoEncontradoException(
                    "O evento de algum ingresso e o evento fornecido são " +
                    "diferentes (Nenhum ingresso vendido)");
            }
        }
        this.caracteristicas.getIngressosVendidos().addAll(ingressos);
        cliente.adicionarIngresso(ingressos);

    }
}