/*
 * EventoFestivalDeShows.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos;

import java.util.List;

import lab03.Eventos.Caracteristicas.CaracteristicaEventoFestival;
import lab03.Eventos.Caracteristicas.CaracteristicaEventoFestivalDeShows;

/**
 * Representa um evento realizado em um bar.
 * 
 * Esta classe estende {@link Evento} e implementa {@link Duravel},
 * além de utilizar {@link CaracteristicaEventoFestival} para
 * armazenar informações específicas de um festival e 
 * {@link CaracteristicaEventoFestivalDeShows} para armazenar
 * características secundárias de um festival especializado
 * em música.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoFestivalDeShows extends Evento implements Duravel {
    private CaracteristicaEventoFestivalDeShows caracteristicasShows;

    /**
    * Construtor da classe EventoFestivalDeShows
    * @param caracteristicas as características específicas do festival
    * @param caracteristicasShows as características secundárias do festival de shows.
    */
    public EventoFestivalDeShows(CaracteristicaEventoFestival caracteristicas,
    CaracteristicaEventoFestivalDeShows caracteristicasShows){
        super(caracteristicas);
        this.caracteristicasShows = caracteristicasShows;
    }

    /**
    * Retorna a dura o do Festival em dias
    * @return a dura o do Festival em dias
    */
    public int getDuracao() {
        CaracteristicaEventoFestival caracteristicas = (CaracteristicaEventoFestival) super.getCaracteristicas();
        return caracteristicas.getDuracao();
    }

    /**
    * Retorna a lista de shows do Festival
    * @return a lista de shows do Festival
    */
    public List<EventoShow> getShows(){
        return caracteristicasShows.getShows();
    }

    /**
     * Retorna uma string contendo a descri o do Festival, com seu nome, lineup, local e duração,
     * além da lista de shows.
     * @return uma string com a descrição do Festival
     */
    @Override
    public String descricao() {
        CaracteristicaEventoFestival caracteristicas = (CaracteristicaEventoFestival) super.getCaracteristicas();
        return "Festival: " + caracteristicas.getNome() +
        " - Lineup: " + caracteristicas.getLineup() +
        " - Duração: " + caracteristicas.getDuracao() +
        "Shows: " + caracteristicasShows.getShows();
    }
}
