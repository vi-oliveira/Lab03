/*
 * CaracteristicaDeEvento.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 */
package lab03.Eventos.Caracteristicas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lab03.Ingresso;
import lab03.Local;
import lab03.Eventos.Organizadora;

/**
 * Classe que define um comportamento para características de eventos.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public abstract class CaracteristicaDeEvento {
    /**
     * O nome do evento.
     */
    protected String nome;
    
    /**
     * O local onde o evento será realizado.
     */
    protected Local local;

    /**
     * O preço de cada ingresso para o evento.
     */
    protected double precoIngresso;

    /**
     * A organizadora responsável pelo evento.
     */
    protected Organizadora organizadora;

    /**
     * A data em que o evento ocorrerá.
     */
    protected LocalDate data;
    private List<Ingresso> ingressosVendidos;

    /**
     * Construtor da classe Evento
     * @param nome o nome do Evento
     * @param precoIngresso o preço do Ingresso do Evento
     * @param organizadora a organizadora do Evento
     * @param data a data do Evento
     */
    public CaracteristicaDeEvento(String nome, double precoIngresso,
    Organizadora organizadora, LocalDate data) {
        this.nome = nome;
        this.local = null;
        this.precoIngresso = precoIngresso;
        this.organizadora = organizadora;
        this.data = data;
        this.ingressosVendidos = new ArrayList<Ingresso>();
    }

    /**
     * Retorna o nome do Evento
     * @return o nome do Evento
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do Evento para `nome` 
     * @param nome o novo nome do Evento
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna o Local do Evento
     * @return o local do Evento
     */
    public Local getLocal() {
        return local;
    }

    /**
     * Altera o local do Evento para `local` 
     * @param local o novo local do Evento
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Retorna o preço do ingresso do Evento
     * @return o precoIngresso do Evento
     */
    public double getPrecoIngresso(){
        return this.precoIngresso;
    }

    /**
     * Altera o precoIngresso do Evento para `precoIngresso` 
     * @param precoIngresso o novo precoIngresso do Evento
     */
    public void setPrecoIngresso(double precoIngresso){
        this.precoIngresso = precoIngresso;
    }

    /**
     * Retorna a data do Evento
     * @return a data do Evento
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Retorna a lista de ingressos vendidos
     * @return a a lista de ingressos vendidos
     */
    public List<Ingresso> getIngressosVendidos(){
        return ingressosVendidos;
    }

    /**
     * Retorna a organizadora do Evento
     * @return a organizadora do Evento
     */
    public Organizadora getOrganizadora(){
        return this.organizadora;
    }
}
