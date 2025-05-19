/*
 * Cliente.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc de alguns métodos deste arquivo foi
 * feita com o uso do ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Clientes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lab03.Ingresso;
import lab03.Marketplace;
import lab03.OfertaIngresso;
import lab03.Eventos.Evento;
import lab03.Exceptions.CancelamentoNaoPermitidoException;
import lab03.Exceptions.IngressoNaoEncontradoException;
import lab03.Exceptions.IngressoNaoPertenceAoClienteException;
import lab03.Exceptions.OfertaNaoEncontradaException;
import lab03.Exceptions.SaldoInsuficienteException;
import lab03.Notificacoes.Notificavel;

/**
 * Representa um cliente que pode comprar ingressos para eventos e receber notificações.
 * 
 * Um cliente possui nome, e-mail, senha, telefone, saldo, uma lista de
 * ingressos adquiridos e listas de notificações pendentes e enviadas.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class Cliente implements CompararA {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private Double saldo;
    private List<Ingresso> ingressos;
    private List<Notificavel> notificacoesPendentes;
    private List<Notificavel> notificacoesEnviadas;

    /**
     * Construtor da classe cliente
     * @param nome o nome do cliente
     * @param email o email do cliente
     * @param senha a senha do cliente
     * @param telefone o telefone do cliente
     * @param saldo o saldo do cliente
     */
    public Cliente(String nome, String email, String senha, String telefone, Double saldo){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.saldo = saldo;
        this.ingressos = new ArrayList<Ingresso>();
        this.notificacoesPendentes = new ArrayList<Notificavel>();
        this.notificacoesEnviadas = new ArrayList<Notificavel>();
    }

    /**
    * Oferece um ingresso para venda em um marketplace.
    * O cliente deve possuir o ingresso para poder colocá-lo à venda.
    *
    * @param ingresso O ingresso a ser oferecido para venda.
    * @param precoPedido O preço pelo qual o cliente deseja vender o ingresso.
    * @param marketplace O marketplace onde o ingresso será oferecido.
    * @throws IngressoNaoPertenceAoClienteException Se o cliente não possuir o ingresso especificado.
    */
    public void oferecerIngressoParaVenda(Ingresso ingresso, double precoPedido, Marketplace marketplace) 
    throws IngressoNaoPertenceAoClienteException {
        if(!this.ingressos.contains(ingresso)) throw new IngressoNaoPertenceAoClienteException("Você não tem esse ingresso");
        marketplace.receberOFerta(this, ingresso, precoPedido);
    }

    /**
    * Realiza a compra de um ingresso listado como oferta em um marketplace.
    * A compra só é possível se o cliente tiver saldo suficiente e a oferta existir no marketplace.
    *
    * @param oferta A oferta de ingresso que o cliente deseja comprar.
    * @param marketplace O marketplace onde a oferta está listada.
    * @throws SaldoInsuficienteException Se o cliente não possuir saldo suficiente para a compra.
    * @throws OfertaNaoEncontradaException Se a oferta especificada não for encontrada no marketplace.
    */
    public void comprarIngressoNoMarketplace(OfertaIngresso oferta, Marketplace marketplace)
    throws SaldoInsuficienteException, OfertaNaoEncontradaException {
        if(this.saldo < oferta.getPrecoPedido()) throw new SaldoInsuficienteException("Saldo insuficiente");
        if (!marketplace.listarOfertas().contains(oferta)) throw new OfertaNaoEncontradaException("Essa oferta não existe");
        marketplace.processarCompra(this, oferta);
    }

    /**
     * Cancela um ingresso comprado
     * Um ingresso só será removido com até 24 horas de antecedência
     * do início do evento. Caso contrário, será lançada uma exceção.
     * @param ingresso o ingresso a ser removido dos ingressos.
     * @throws IngressoNaoEncontradoException Exceção para quando o
     * ingresso não foi comprado pelo cliente
     * @throws CancelamentoNaoPermitidoException Exceção para quando a
     * data limite para o cancelamento do ingresso já foi ultrapassada
     */
    public void cancelarIngresso(Ingresso ingresso)
    throws IngressoNaoEncontradoException, CancelamentoNaoPermitidoException {
        if (ingressos.contains(ingresso)){
            LocalDate dataLimiteCancelamento =
            ingresso.getEvento()
            .getData()
            .minusDays(2);

            if (LocalDate.now().isAfter(dataLimiteCancelamento)){
                throw new CancelamentoNaoPermitidoException(
                    "A data limite para o cancelamento já foi ultrapassada");
            }
            ingressos.remove(ingresso);

        } else {
            throw new IngressoNaoEncontradoException(
                "O ingresso não foi encontrado");
        }
    }

    /**
     * Adiciona uma nova notificação à lista de notificações pendentes do cliente.
     *
     * @param notificacao a notificação a ser adicionada
     */
    public void adiconarNotificacao(Notificavel notificacao){
        this.notificacoesPendentes.add(notificacao);
    }

    /**
     * Envia todas as notificações pendentes para o cliente.
     * 
     * Após o envio, as notificações são movidas para a lista de notificações enviadas.
     */
    public void enviarNotificacoes(){
        for (Notificavel notificacao : notificacoesPendentes){
            notificacao.notificar();
            notificacoesEnviadas.add(notificacao);
        }
        notificacoesPendentes.clear();
    }

    /**
     * Compara este cliente com outro cliente e identifica os eventos em comum
     * baseados nos ingressos adquiridos.
     *
     * @param outroCliente o outro cliente a ser comparado
     * @return um conjunto de eventos que ambos os clientes possuem ingressos
     */
    public Set<Evento> compararA(Cliente outroCliente){
        Set<Evento> eventosEmComum = new HashSet<Evento>();

        for (Ingresso ingressoCliente : this.ingressos){
            Evento eventoAtual = ingressoCliente.getEvento();
            for (Ingresso ingressoOutroCliente : outroCliente.getIngressos()){
                if (eventoAtual.equals(ingressoOutroCliente.getEvento())){
                    eventosEmComum.add(eventoAtual);
                }
            }
        }
        return eventosEmComum;
    }

    /**
     * Retorna o nome do cliente
     * @return o nome do cliente
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do cliente para `nome` 
     * @param nome o novo nome do cliente
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna o email do cliente
     * @return o email do cliente
     */
    public String getEmail(){
        return email;
    }

    /**
     * Altera o email do cliente para `email` 
     * @param email o novo email do cliente
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * Retorna o telefone do cliente
     * @return o telefone do cliente
     */
    public String getTelefone(){
        return telefone;
    }
    
    /**
     * Altera o telefone do cliente para `telefone` 
     * @param telefone o novo telefone do cliente
     */
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    /**
     * Retorna o senha do cliente
     * @return o senha do cliente
     */
    public String getSenha(){
        return senha;
    }
    
    /**
     * Altera o senha do cliente para `senha` 
     * @param senha o novo senha do cliente
     */
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    /**
     * Retorna o saldo do cliente
     * @return o saldo do cliente
     */
    public Double getSaldo(){
        return saldo;
    }
    
    /**
     * Altera o saldo do cliente para 'saldo' 
     * @param saldo o novo saldo do cliente
     */
    public void setSaldo(Double saldo){
        this.saldo = saldo;
    }
    
    /**
     * Adiciona dinheiro ao saldo do cliente 
     * @param deposito o valor a ser depositado no saldo
     */
    public void depositarDinheiro(Double deposito){
        this.saldo += deposito;
    }

    /**
     * Adiciona um ingresso à lista;
     * @param ingresso ingresso a ser adicionado.
     */
    public void adicionarIngresso(Ingresso ingresso){
        this.ingressos.add(ingresso);
    }
    
    /**
     * Adiciona uma lista ingressos à outra;
     * @param ingressos a lista de ingressos a serem adicionados.
     */
    public void adicionarIngresso(List<Ingresso> ingressos){
        this.ingressos.addAll(ingressos);
    }
    
    /**
     * Remove um ingresso da lista;
     * @param ingresso o ingresso a ser removido.
     */
    public void removerIngresso(Ingresso ingresso){
        this.ingressos.remove(ingresso);
    }

    /**
     * Retorna a lista de ingressos registrados.
     * @return lista de ingressos.
     */
    public List<Ingresso> getIngressos(){
        return ingressos;
    }

    /**
     * Retorna a lista de notificações pendentes.
     * @return lista de notificações pendentes.
     */
    public List<Notificavel> getNotificaoPendentes(){
        return notificacoesPendentes;
    }
    
    /**
     * Retorna a lista de notificações enviadas.
     * @return lista de notificações enviadas.
     */
    public List<Notificavel> getNotificaoEnviadas(){
        return notificacoesEnviadas;
    }

}