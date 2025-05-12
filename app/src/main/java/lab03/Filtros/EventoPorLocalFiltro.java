/*
 * EventoPorLocalFiltro.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso do
 * ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Filtros;

import java.util.ArrayList;
import java.util.List;

import lab03.Local;
import lab03.Eventos.Evento;

/**
 * Filtro que seleciona eventos com base no local.
 * 
 * Implementa a interface {@link Filtro} para filtrar uma lista de eventos,
 * retornando apenas aqueles cujo local corresponde exatamente ao local especificado.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoPorLocalFiltro implements Filtro {
    private Local localFiltro;

    /**
    * Construtor da classe EvenoPorLocalFiltro.
    *
    * @param localFiltro o local dos eventos que devem ser selecionados pelo filtro
    */
    public EventoPorLocalFiltro(Local localFiltro){
        this.localFiltro = localFiltro;
    }

    /**
     * Filtra a lista de eventos, selecionando apenas os eventos que possuem o local igual ao local de filtro.
     *
     * @param eventos a lista de eventos a ser filtrada
     * @return uma lista contendo apenas os eventos cujo local é igual ao local de filtro
     */
    @Override
    public List<Evento> filtrar(List<Evento> eventos){
        List<Evento> eventosFiltrados = new ArrayList<Evento>();
        for (Evento evento : eventos){
            if (evento.getLocal() != null && evento.getLocal().equals(this.localFiltro)){
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
}
