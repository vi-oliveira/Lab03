/*
 * EventoJogo.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos;

import lab03.Eventos.Caracteristicas.CaracteristicaEventoJogo;

/**
 * Representa um evento jogo.
 * 
 * Esta classe estende {@link Evento} e utiliza {@link CaracteristicaEventoJogo}
 * para armazenar informações específicas de um evneto jogo.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoJogo extends Evento {
    /**
     * Construtor da classe EventoJogo.
     * @param caracteristicas as características específicas do evento jogo
     */
    public EventoJogo(CaracteristicaEventoJogo caracteristicas) {
        super(caracteristicas);
    }
    
    /**
     * Retorna uma string contendo a descrição do Evento, com seu nome, times, local e data
     * @return uma string com a descrição do Evento
     */
    @Override
    public String descricao() {
        CaracteristicaEventoJogo caracteristicas = (CaracteristicaEventoJogo) super.getCaracteristicas();
        if (caracteristicas.getLocal() != null){
            return "Nome: " + caracteristicas.getNome() +
            " - Times: " + caracteristicas.getTimes() +
            " - Local: " + caracteristicas.getLocal().getNome() +
            " - Data: " + caracteristicas.getData();
        } else {
            return "Nome: " + caracteristicas.getNome() +
            " - Times: " + caracteristicas.getTimes() +
            " - Local: Indefinido" + 
            " - Data: " + caracteristicas.getData();
        }
    }
}
