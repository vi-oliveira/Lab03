/*
 * Organizadora.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Eventos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lab03.Eventos.Caracteristicas.CaracteristicaEventoEmBar;
import lab03.Eventos.Caracteristicas.CaracteristicaEventoFestival;
import lab03.Eventos.Caracteristicas.CaracteristicaEventoFestivalDeShows;
import lab03.Eventos.Caracteristicas.CaracteristicaEventoJogo;
import lab03.Eventos.Caracteristicas.CaracteristicaEventoMusicaAoVivo;
import lab03.Eventos.Caracteristicas.CaracteristicaEventoShow;

/**
 * Contém a estrutura de implementação de uma organizadora.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class Organizadora {
    private String nome;
    private int cnpj;
    private String endereco;
    private List<Evento> eventosOrganizados;

    /**
     * Construtor da classe Organizadora
     * @param nome o nome da organizadora
     * @param cnpj o CNPJ da organizadora
     * @param endereco o endereço da organizadora
     */
    public Organizadora(String nome, int cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.eventosOrganizados = new ArrayList<Evento>();
    }

    /**
     * Retorna o nome da organizadora
     * @return o nome da organizadora
     */
    public String getNome(){
        return nome;
    }

    /**
     * Retorna o CNPJ da organizadora
     * @return o CNPJ da organizadora
     */
    public int getCnpj(){
        return cnpj;
    }

    /**
     * Retorna o endereço da organizadora
     * @return o endereço da organizadora
     */
    public String getEndereco(){
        return endereco;
    }

    /**
     * Retorna os eventos organizados pela organizadora
     * @return os eventos organizados pela organizadora
     */
    public List<Evento> getEventosOrganizados(){
        return eventosOrganizados;
    }

    /**
    * Criador de eventos organizados por esta organizadora
    * @param nome o nome do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param data a data do Evento
    * @param artista o artista do Evento
    * @param historico o historico que contém todos os eventos
    * @return o evento criado
    */
    public EventoShow criarEvento(String nome, double precoIngresso,
    LocalDate data, String artista, HistoricoEventos historico){
        CaracteristicaEventoShow caracteristicas = new CaracteristicaEventoShow(
            nome, precoIngresso, this, data, artista);
        EventoShow eventoCriado = new EventoShow(caracteristicas);
        eventosOrganizados.add(eventoCriado);
        historico.adicionarEvento(eventoCriado);
        return eventoCriado;
    }

    /**
    * Criador de eventos organizados por esta organizadora
    * @param nome o nome do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param data a data do Evento
    * @param lineup o lineup do festival
    * @param duracao a duracao do Evento
    * @param historico o historico que contém todos os eventos
    * @return o evento criado
    */
    public EventoFestival criarEvento(String nome, double precoIngresso,
    LocalDate data, List<String> lineup, int duracao, HistoricoEventos historico){
        CaracteristicaEventoFestival caracteristicas = new CaracteristicaEventoFestival(
            nome, precoIngresso, this, data, lineup, duracao);
        EventoFestival eventoCriado = new EventoFestival(caracteristicas);
        eventosOrganizados.add(eventoCriado);
        historico.adicionarEvento(eventoCriado);
        return eventoCriado;
    }

    /**
    * Criador de eventos organizados por esta organizadora
    * @param nome o nome do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param data a data do Evento
    * @param lineup o lineup do festival
    * @param duracao a duracao do Evento
    * @param shows a lista de shows do festival
    * @param historico o historico que contém todos os eventos
    * @return o evento criado
    */
    public EventoFestivalDeShows criarEvento(String nome, double precoIngresso,
    LocalDate data, List<String> lineup, int duracao, List<EventoShow> shows, HistoricoEventos historico){
        CaracteristicaEventoFestival caracteristicas = new CaracteristicaEventoFestival(
            nome, precoIngresso, this, data, lineup, duracao);
        CaracteristicaEventoFestivalDeShows caracteristicasShows = new CaracteristicaEventoFestivalDeShows(shows);
        EventoFestivalDeShows eventoCriado = new EventoFestivalDeShows(caracteristicas, caracteristicasShows);
        eventosOrganizados.add(eventoCriado);
        historico.adicionarEvento(eventoCriado);
        return eventoCriado;
    }
    
    /**
    * Criador de eventos organizados por esta organizadora
    * @param nome o nome do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param data a data do Evento
    * @param times os times do jogo
    * @param historico o historico que contém todos os eventos
    * @return o evento criado
    */
    public EventoJogo criarEvento(String nome, double precoIngresso,
    LocalDate data, List<String> times, HistoricoEventos historico){
        CaracteristicaEventoJogo caracteristicas = new CaracteristicaEventoJogo(
            nome, precoIngresso, this, data, times);
        EventoJogo eventoCriado = new EventoJogo(caracteristicas);
        eventosOrganizados.add(eventoCriado);
        historico.adicionarEvento(eventoCriado);
        return eventoCriado;
    }
    
    /**
    * Criador de eventos organizados por esta organizadora
    * @param nome o nome do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param data a data do Evento
    * @param nomeDoBar o nome do bar onde ocorre o Evento
    * @param inicioHappyHour a hora de início do HappyHour
    * @param duracaoHappyHour a duração do HappyHour em horas
    * @param historico o historico que contém todos os eventos
    * @return o evento criado
    */
    public EventoEmBar criarEvento(String nome, double precoIngresso, LocalDate data, String nomeDoBar,
    String inicioHappyHour, int duracaoHappyHour, HistoricoEventos historico){
        CaracteristicaEventoEmBar caracteristicas = new CaracteristicaEventoEmBar(nome, precoIngresso, this, data, nomeDoBar, inicioHappyHour, duracaoHappyHour);
        EventoEmBar eventoCriado = new EventoEmBar(caracteristicas);
        eventosOrganizados.add(eventoCriado);
        historico.adicionarEvento(eventoCriado);
        return eventoCriado;
    }

    /**
    * Criador de eventos organizados por esta organizadora
    * @param nome o nome do Evento
    * @param precoIngresso o preço do Ingresso do Evento
    * @param data a data do Evento
    * @param nomeDoArtista o nome do artista
    * @param generoMusical o gênero músical
    * @param historico o historico que contém todos os eventos
    * @return o evento criado
    */
    public EventoMusicaAoVivo criarEvento(String nome, double precoIngresso,
    LocalDate data, String nomeDoArtista, String generoMusical, HistoricoEventos historico){
        CaracteristicaEventoMusicaAoVivo caracteristicas = new CaracteristicaEventoMusicaAoVivo(nome, precoIngresso, this, data, nomeDoArtista, generoMusical);
        EventoMusicaAoVivo eventoCriado = new EventoMusicaAoVivo(caracteristicas);
        eventosOrganizados.add(eventoCriado);
        historico.adicionarEvento(eventoCriado);
        return eventoCriado;
    }
}
