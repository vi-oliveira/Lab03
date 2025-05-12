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

import lab03.Eventos.Evento;

/**
 * Filtro que seleciona eventos com base no tipo (classe).
 * 
 * Implementa a interface {@link Filtro} para filtrar uma lista de eventos,
 * retornando apenas aqueles cujo tipo corresponde exatamente ao tipo especificado.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoPorTipoFiltro implements Filtro {
    private Class<?> tipoFiltro;

    /**
    * Construtor da classe EventoPorTipoFiltro.
    *
    * @param tipoFiltro o tipo (classe) dos eventos que devem ser selecionados pelo filtro
    */
    public EventoPorTipoFiltro(Class<?> tipoFiltro){
        this.tipoFiltro = tipoFiltro;
    }

    /**
     * Filtra a lista de eventos, selecionando apenas os eventos que possuem o tipo igual ao tipo de filtro.
     *
     * @param eventos a lista de eventos a ser filtrada
     * @return uma lista contendo apenas os eventos cujo tipo é igual ao tipo de filtro
     */
    @Override
    public List<Evento> filtrar(List<Evento> eventos){
        List<Evento> eventosFiltrados = new ArrayList<Evento>();
        for (Evento evento : eventos){
            if (evento.getClass().equals(this.tipoFiltro)){
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
}
