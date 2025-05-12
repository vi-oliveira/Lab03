/*
 * Local.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc de alguns métodos deste arquivo foi
 * feita com o uso do ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03;

import java.time.LocalDate;
import java.util.TreeSet;

import lab03.Eventos.Duravel;
import lab03.Eventos.Evento;
import lab03.Exceptions.CapacidadeInsuficienteException;
import lab03.Exceptions.LocalIndisponivelException;

/**
 * Contém a estrutura de implementação de um Local.
 * 
 * @author Gabriel Leite - 216180
 * @author Vinícius de Oliveira - 251527
 */
public class Local{
    private String nome;
    private double capacidadeMaxima;
    private TreeSet<LocalDate> datasAlocadas;
    /* Como é preciso analisar várias datas para saber se 
     * o local já está alocado para o dia do evento, resolvi
     * usar uma árvore binária para deixar a busca mais eficiente.
    */

    /**
     * Construtor da classe Local
     * @param nome o nome do local
     * @param capacidadeMaxima a capacidade máxima do local
     */
    public Local(String nome, double capacidadeMaxima){
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.datasAlocadas = new TreeSet<LocalDate>();
    }

    /**
     * Retorna o nome do local
     * @return o nome do local
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do local para `nome` 
     * @param nome o novo nome do local
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna a capacidade do local
     * @return a capacidade do local
     */
    public double getCapacidade(){
        return capacidadeMaxima;
    }
    
    /**
     * Altera a capacidade máxima do local para `capacidadeMaxima` 
     * @param capacidadeMaxima a nova capacidade máxima do local
     */
    public void setCapacidade(double capacidadeMaxima){
        this.capacidadeMaxima = capacidadeMaxima;
    }

    /**
    * Aloca um evento durável (com duração de vários dias) para o local,
    * verificando se todas as datas necessárias estão disponíveis.
    *
    * @param eventoDuravel o evento durável a ser alocado
    * @throws LocalIndisponivelException se alguma das datas já estiver ocupada
    */
    private void alocarEventoDuravel(Duravel eventoDuravel)
    throws LocalIndisponivelException {
        // Verificar se cada dia do evento está livre
        for (int i = 0; i < eventoDuravel.getDuracao(); i++){
            if (datasAlocadas.contains(eventoDuravel.getData().plusDays(i))) {
                throw new LocalIndisponivelException(
                    "Local não disponível nas datas do evento");
            }
        }
        // Se não teve erro, todas as datas estão disponíveis
        for (int i = 0; i < eventoDuravel.getDuracao(); i++){
            datasAlocadas.add(eventoDuravel.getData().plusDays(i));
        }
        Evento eventoSetLocal = (Evento) eventoDuravel;
        eventoSetLocal.setLocal(this);
    }

    /**
    * Aloca um evento de um único dia para o local, verificando se a data está disponível.
    *
    * @param evento o evento a ser alocado
    * @throws LocalIndisponivelException se a data já estiver ocupada
    */
    private void alocarEventoDeUmDia(Evento evento)
    throws LocalIndisponivelException{
        if (datasAlocadas.contains(evento.getData())){
            throw new LocalIndisponivelException(
                "Local não disponível na data do evento");
        }
        datasAlocadas.add(evento.getData());
        evento.setLocal(this);
    }

    /**
    * Aloca um evento (durável ou de um dia) para este local, verificando a disponibilidade
    * de datas e a capacidade do local.
    *
    * @param evento o evento a ser alocado
    * @throws CapacidadeInsuficienteException se a capacidade máxima do local for atingida
    * @throws LocalIndisponivelException se o local estiver indisponível na(s) data(s) do evento
    */
    public void alocarParaEvento(Evento evento)
    throws CapacidadeInsuficienteException, LocalIndisponivelException {
        if (evento.getIngressosVendidos().size() == this.capacidadeMaxima){
            throw new CapacidadeInsuficienteException(
                "Capacidade máxima do local atingida");
        } else if (evento instanceof Duravel){
            /* Caso o evento possua um atributo duração (dura alguns dias),
            é necessário ver a disponibilidade para mais de um dia.
            Para isso, esses eventos implementam a interface "Duravel"*/
            Duravel eventoDuravel = (Duravel) evento;
            alocarEventoDuravel(eventoDuravel);
        } else { 
            alocarEventoDeUmDia(evento);
        }
    }
}
