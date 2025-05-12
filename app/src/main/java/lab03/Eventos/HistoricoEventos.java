/*
 * HistoricoEventos.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * Eu reimplementei o histórico de eventos por conta da busca usando filtros.
 * Caso a busca fosse realizada na classe "Organizadora", seria possível apenas
 * filtrar os eventos criados por ela.
 * Usando o histórico de eventos, é possível buscar por qualquer evento que já
 * foi criado. Caso seja necessário buscar eventos específicos de alguma
 * organizadora, basta utilizar a classe "AndFiltro" e combinar os filtros
 * necessários.
 */
package lab03.Eventos;

import java.util.ArrayList;
import java.util.List;

import lab03.Filtros.Filtro;

/**
 * Classe que representa um histórico de eventos
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class HistoricoEventos {
    private List<Evento> eventos;

    /**
     * Construtor que inicializa a lista de eventos
     */
    public HistoricoEventos(){
        this.eventos = new ArrayList<Evento>();
    }

    /**
     * Adiciona um evento ao histórico
     * @param evento O evento a ser adicionado
     */
    public void adicionarEvento(Evento evento){
        this.eventos.add(evento);
    }

    /**
     * Retorna a lista de eventos registrados
     * @return Lista de eventos
     */
    public List<Evento> getEventos(){
        return eventos;
    }
    
    /**
     * Realiza uma busca nos eventos e retorna aqueles que
     * passam no filtro. ALém disso, é possível combinar
     * diferentes filtros.
     * @param filtro o filtro ou grupo de filtros
     * @return Lista de eventos que passam no filtro
     */
    public List<Evento> buscarEventos(Filtro filtro){
        List<Evento> eventosFiltrados = filtro.filtrar(this.eventos);
        return eventosFiltrados;
    }
}
