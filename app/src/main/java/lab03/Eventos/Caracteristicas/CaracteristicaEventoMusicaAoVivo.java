/*
 * CaracteristicaEventoMusicaAoVivo.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos.Caracteristicas;

import java.time.LocalDate;

import lab03.Eventos.Organizadora;

/**
 * Representa as características específicas de um evento de música ao vivo.
 * 
 * Estende {@link CaracteristicaDeEvento} adicionando informações como nome do artista
 * e o gênero musical.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CaracteristicaEventoMusicaAoVivo extends CaracteristicaDeEvento {
    private String nomeDoArtista;
    private String generoMusical;

    /**
     * Construtor da classe CaracteristicaEventoMusicaAoVivo.
     *
     * @param nome o nome do evento
     * @param precoIngresso o preço do ingresso do evento
     * @param organizadora a organizadora responsável pelo evento
     * @param data a data do evento
     * @param nomeDoArtista o nome do artista
     * @param generoMusical o gênero musical da apresentação
     */
    public CaracteristicaEventoMusicaAoVivo(String nome, double precoIngresso,
    Organizadora organizadora, LocalDate data, String nomeDoArtista, String generoMusical) {
        super(nome, precoIngresso, organizadora, data);
        this.nomeDoArtista = nomeDoArtista;
        this.generoMusical = generoMusical;
    }

    /**
    * Retorna o nome do artista
    * @return o nome do artista
    */
    public String getNomeDoArtista(){
        return nomeDoArtista;
    }
    
    /**
    * Retorna o gênero musical
    * @return o gênero musical
    */
    public String getGeneroMusical(){
        return generoMusical;
    }
}
