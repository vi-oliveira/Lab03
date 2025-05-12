/*
 * EventoShow.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03.Eventos;

import lab03.Eventos.Caracteristicas.CaracteristicaEventoShow;

/**
 * Representa um evento show.
 * 
 * Esta classe estende {@link Evento} e utiliza {@link CaracteristicaEventoShow}
 * para armazenar informações específicas de eventos show.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoShow extends Evento {
    /**
     * Construtor da classe EventoShow.
     * @param caracteristicas as características específicas do evento show
     */
    public EventoShow(CaracteristicaEventoShow caracteristicas) {
        super(caracteristicas);
    }

    /**
     * Retorna uma descrição do evento sgiw, incluindo o nome do evento,
     * o/a artista, local e data.
     * @return a descrição do evento
     */
    @Override
    public String descricao() {
        CaracteristicaEventoShow caracteristicas = (CaracteristicaEventoShow) super.getCaracteristicas();
        if (caracteristicas.getLocal() != null){
            return "Show: " + caracteristicas.getNome() +
            " - Artista: " + caracteristicas.getArtista()  +
            " - Local: " + caracteristicas.getLocal().getNome() +
            " - Data: " + caracteristicas.getData();
        } else {
            return "Show: " + caracteristicas.getNome() +
            " - Artista: " + caracteristicas.getArtista()  +
            " - Local: Indefinido - Data: " + caracteristicas.getData();
        }
    }
}
