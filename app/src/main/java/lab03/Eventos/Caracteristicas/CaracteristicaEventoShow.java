/*
 * CaracteristicaEventoShow.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos.Caracteristicas;

import java.time.LocalDate;

import lab03.Eventos.Organizadora;

/**
 * Representa as características específicas de um evento show.
 * 
 * Estende {@link CaracteristicaDeEvento} adicionando informações como
 * o nome do artista.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CaracteristicaEventoShow extends CaracteristicaDeEvento {
    private String artista;
    
    /**
     * Construtor da classe CaracteristicaEventoShow.
     *
     * @param nome o nome do evento
     * @param precoIngresso o preço do ingresso do evento
     * @param organizadora a organizadora responsável pelo evento
     * @param data a data do evento
     * @param artista o artista do show
     */
    public CaracteristicaEventoShow(String nome, double precoIngresso,
    Organizadora organizadora, LocalDate data, String artista) {
        super(nome, precoIngresso, organizadora, data);
        this.artista = artista;
    }    

    /**
    * Retorna o artista do show
    * @return o artista do show
    */
    public String getArtista(){
        return artista;
    }
}
