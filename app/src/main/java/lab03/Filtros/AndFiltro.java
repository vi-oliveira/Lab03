/*
 * AndFiltro.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso do
 * ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Filtros;

import java.util.List;

import lab03.Eventos.Evento;

/**
 * Filtro que seleciona eventos com base em outros filtros.
 * 
 * Implementa a interface {@link Filtro} para filtrar uma lista de eventos,
 * retornando apenas aqueles cujo tipo corresponde exatamente à todos os filtros.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class AndFiltro implements Filtro {
    private List<Filtro> filtros;
    
    /**
    * Construtor da classe AndFiltro.
    *
    * @param filtros a lista de filtros que os eventos devem passar
    */
    public AndFiltro(List<Filtro> filtros){
        this.filtros = filtros;
    }


    /**
     * Filtra a lista de eventos, selecionando apenas os eventos que passem na lista de filtros.
     *
     * @param eventos a lista de eventos a ser filtrada
     * @return uma lista contendo apenas os eventos que passam em todos os filtros
     */
    @Override
    public List<Evento> filtrar(List<Evento> eventos){
        List<Evento> eventosFiltrados = eventos;
        /* A cada iteração nos filtros, apenas os eventos que
        *  atendem aos critérios de cada filtro continua armazenado.*/
        for (Filtro filtro : this.filtros){
            eventosFiltrados = filtro.filtrar(eventosFiltrados);
        }
        return eventosFiltrados;
    }
}
