/*
 * EventoMusicaAoVivo.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos;

import lab03.Eventos.Caracteristicas.CaracteristicaEventoMusicaAoVivo;

/**
 * Representa um evento com música ao vivo.
 * 
 * Esta classe estende {@link Evento} e utiliza {@link CaracteristicaEventoMusicaAoVivo}
 * para armazenar informações específicas de eventos com música ao vivo.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoMusicaAoVivo extends Evento {    
    /**
     * Construtor da classe EventoMusicaAoVIvo.
     * @param caracteristicas as características específicas do evento com música ao vivo
     */
    public EventoMusicaAoVivo(CaracteristicaEventoMusicaAoVivo caracteristicas){
        super(caracteristicas);
    }

    /**
     * Retorna uma descrição do evento no bar, incluindo o nome do artista
     * e o seu gênero musical.
     * @return a descrição do evento
     */
    @Override
    public String descricao(){
        CaracteristicaEventoMusicaAoVivo caracteristicas = (CaracteristicaEventoMusicaAoVivo) super.getCaracteristicas();
        return "Música ao vivo com " + caracteristicas.getNomeDoArtista() +
        " (" + caracteristicas.getGeneroMusical() + ")";
    }
}
