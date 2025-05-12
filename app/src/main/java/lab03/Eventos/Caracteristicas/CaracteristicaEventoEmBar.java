/*
 * CaracteristicaEventoEmBar.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso do
 * ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Eventos.Caracteristicas;

import java.time.LocalDate;

import lab03.Eventos.Organizadora;

/**
 * Representa as características específicas de um evento realizado em um bar.
 * 
 * Estende {@link CaracteristicaDeEvento} adicionando informações como o nome do bar,
 * o horário de início e a duração do happy hour.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CaracteristicaEventoEmBar extends CaracteristicaDeEvento {
    private String nomeDoBar;
    private String inicioHappyHour;
    private int duracaoHappyHour;

    /**
     * Construtor da classe CaracteristicaEventoEmBar.
     *
     * @param nome o nome do evento
     * @param precoIngresso o preço do ingresso do evento
     * @param organizadora a organizadora responsável pelo evento
     * @param data a data do evento
     * @param nomeDoBar o nome do bar onde o evento ocorrerá
     * @param inicioHappyHour o horário de início do happy hour
     * @param duracaoHappyHour a duração do happy hour em horas
     */
    public CaracteristicaEventoEmBar(String nome, double precoIngresso, Organizadora organizadora,
     LocalDate data, String nomeDoBar, String inicioHappyHour, int duracaoHappyHour){
        super(nome, precoIngresso, organizadora, data);
        this.nomeDoBar = nomeDoBar;
        this.inicioHappyHour = inicioHappyHour;
        this.duracaoHappyHour = duracaoHappyHour;
    }

    /**
     * Retorna o nome do bar onde o evento ocorrerá.
     * @return o nome do bar
     */
    public String getNomeDoBar(){
        return nomeDoBar;
    }

    /**
     * Retorna o horário de início do happy hour.
     * @return o horário de início do happy hour
     */
    public String getInicioHappyHour(){
        return inicioHappyHour;
    }

    /**
     * Retorna a duração do happy hour em horas.
     * @return a duração do happy hour
     */
    public int getDuracaoHappyHour(){
        return duracaoHappyHour;
    }
}
