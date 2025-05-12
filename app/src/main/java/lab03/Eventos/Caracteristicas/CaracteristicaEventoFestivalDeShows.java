/*
 * CaracteristicaEventoFestivalDeShows.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos.Caracteristicas;

import java.util.List;

import lab03.Eventos.EventoShow;

/**
 * Representa as características adicionais para um evento festival de shows.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CaracteristicaEventoFestivalDeShows {
    private List<EventoShow> shows;
    
    /**
     * Construtor da classe CaracteristicaEventoFestivalDeShows.
     *
     * @param shows a lista de shows do festival
     */
    public CaracteristicaEventoFestivalDeShows(List<EventoShow> shows) {
        this.shows = shows;
    } 

    /**
    * Retorna a lista de shows do Festival
    * @return a lista de shows do Festival
    */
    public List<EventoShow> getShows(){
        return shows;
    }
}
