/*
 * CaracteristicaEventoFestival.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos.Caracteristicas;

import java.time.LocalDate;
import java.util.List;

import lab03.Eventos.Organizadora;

/**
 * Representa as características específicas de um evento festival.
 * 
 * Estende {@link CaracteristicaDeEvento} adicionando informações como lineup e
 * a duração em dias do festival.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CaracteristicaEventoFestival extends CaracteristicaDeEvento {
    private List<String> lineup;
    private int duracao;
    
    /**
     * Construtor da classe CaracteristicaEventoFestival.
     *
     * @param nome o nome do evento
     * @param precoIngresso o preço do ingresso do evento
     * @param organizadora a organizadora responsável pelo evento
     * @param data a data do evento
     * @param lineup o lineup do festival
     * @param duracao a duração em dias do evento
     */
    public CaracteristicaEventoFestival(String nome, double precoIngresso,
    Organizadora organizadora, LocalDate data, List<String> lineup, int duracao) {
        super(nome, precoIngresso, organizadora, data);
        this.lineup = lineup;
        this.duracao = duracao;
    } 

    /**
    * Retorna o lineup do Festival
    * @return o lineup do Festival
    */
    public List<String> getLineup() {
        return this.lineup;
    }
    
    /**
    * Retorna a duração do Festival em dias
    * @return a duração do Festival em dias
    */
    public int getDuracao() {
        return this.duracao;
    }
}
