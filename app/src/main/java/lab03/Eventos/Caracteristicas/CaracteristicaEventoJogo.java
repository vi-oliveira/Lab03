/*
 * CaracteristicaEventoJogo.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos.Caracteristicas;

import java.time.LocalDate;
import java.util.List;

import lab03.Eventos.Organizadora;

/**
 * Representa as características específicas de um evento jogo.
 * 
 * Estende {@link CaracteristicaDeEvento} adicionando uma lista de times
 * que participam do jogo.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CaracteristicaEventoJogo extends CaracteristicaDeEvento {
    private List<String> times;

    /**
     * Construtor da classe CaracteristicaEventoJogo.
     *
     * @param nome o nome do evento
     * @param precoIngresso o preço do ingresso do evento
     * @param organizadora a organizadora responsável pelo evento
     * @param data a data do evento
     * @param times a lista de times
     */
    public CaracteristicaEventoJogo(String nome, double precoIngresso, Organizadora organizadora, LocalDate data, List<String> times) {
        super(nome, precoIngresso, organizadora, data);
        this.times = times;
    }    

    /**
     * Retorna a lista com os nomes dos times que se enfrentam no Evento
     * @return a lista com os nomes dos times do Evento
     */
    public List<String> getTimes() {
        return times;
    }
}
