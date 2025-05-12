/*
 * EventoPorDataFiltro.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso do
 * ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Filtros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lab03.Eventos.Evento;

/**
 * Filtro que seleciona eventos com base na data.
 * 
 * Implementa a interface {@link Filtro} para filtrar uma lista de eventos,
 * retornando apenas aqueles cuja data corresponde exatamente à data especificada.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoPorDataFiltro implements Filtro {
    private LocalDate dataFiltro;

    /**
    * Construtor da classe EventoPorDataFiltro.
    *
    * @param dataFiltro a data dos eventos que devem ser selecionados pelo filtro
    */
    public EventoPorDataFiltro(LocalDate dataFiltro){
        this.dataFiltro = dataFiltro;
    }

    /**
     * Filtra a lista de eventos, selecionando apenas os eventos que possuem a data igual à data de filtro.
     *
     * @param eventos a lista de eventos a ser filtrada
     * @return uma lista contendo apenas os eventos cuja data é igual à data de filtro
     */
    @Override
    public List<Evento> filtrar(List<Evento> eventos){
        List<Evento> eventosFiltrados = new ArrayList<Evento>();
        for (Evento evento : eventos){
            if (evento.getData().equals(this.dataFiltro)){
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
}
