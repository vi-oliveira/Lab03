/*
 * EventoFestival.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03.Eventos;

import lab03.Eventos.Caracteristicas.CaracteristicaEventoFestival;

/**
 * Representa um evento festival.
 * 
 * Esta classe estende {@link Evento} e implementa {@link Duravel},
 * além de utilizar {@link CaracteristicaEventoFestival} para
 * armazenar informações específicas de um festival.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoFestival extends Evento implements Duravel {     
    /**
    * Construtor da classe EventoFestival
    * @param caracteristicas as características específicas do festival
    */
    public EventoFestival(CaracteristicaEventoFestival caracteristicas) {
        super(caracteristicas);
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
     * Retorna uma string contendo a descri o do Festival, com seu nome, lineup, local e duração
     * @return uma string com a descrição do Festival
     */
    @Override
    public String descricao() {
        CaracteristicaEventoFestival caracteristicas = (CaracteristicaEventoFestival) super.getCaracteristicas();
        if (caracteristicas.getLocal() != null){
            return "Festival: " + caracteristicas.getNome() +
            " - Lineup: " + caracteristicas.getLineup() +
            " - Local: " + caracteristicas.getLocal().getNome() +
            " - Duração: " + caracteristicas.getDuracao();
        } else {
            return "Festival: " + caracteristicas.getNome() +
            " - Lineup: " + caracteristicas.getLineup() +
            " - Local: Indefinido - Duração: " + caracteristicas.getDuracao();
        }
    }
}
