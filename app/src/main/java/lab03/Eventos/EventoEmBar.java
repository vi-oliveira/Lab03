/*
 * EventoEmBar.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos;

import lab03.Eventos.Caracteristicas.CaracteristicaEventoEmBar;

/**
 * Representa um evento realizado em um bar.
 * 
 * Esta classe estende {@link Evento} e utiliza {@link CaracteristicaEventoEmBar}
 * para armazenar informações específicas de eventos em bares.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoEmBar extends Evento {
    /**
     * Construtor da classe EventoEmBar.
     * @param caracteristicas as características específicas do evento em bar
     */
    public EventoEmBar(CaracteristicaEventoEmBar caracteristicas){
        super(caracteristicas);
    }
    
    /**
     * Retorna uma descrição do evento no bar, incluindo o nome do bar,
     * o horário de início do happy hour e sua duração.
     * @return a descrição do evento
     */
    @Override
    public String descricao(){
        CaracteristicaEventoEmBar caracteristicas = (CaracteristicaEventoEmBar) super.getCaracteristicas();
        return "Evento no bar: " + caracteristicas.getNomeDoBar() + 
        ", Happy Hour Inicio: " + caracteristicas.getInicioHappyHour() +
        ", Duração: " + caracteristicas.getDuracaoHappyHour();
    }
}
