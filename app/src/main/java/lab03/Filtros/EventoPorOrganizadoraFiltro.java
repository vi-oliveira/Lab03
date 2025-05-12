/*
 * EventoPorOrganizadora.java
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
import lab03.Eventos.Organizadora;

/**
 * Filtro que seleciona eventos com base na organizadora.
 * 
 * Implementa a interface {@link Filtro} para filtrar uma lista de eventos,
 * retornando apenas aqueles cuja organizadora corresponde exatamente à organizadora especificada.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoPorOrganizadoraFiltro implements Filtro {
    private Organizadora organizadoraFiltro;

    /**
    * Construtor da classe EvenoPorOrganizadoraFiltro.
    *
    * @param organizadoraFiltro a organizadora dos eventos que devem ser selecionados pelo filtro
    */
    public EventoPorOrganizadoraFiltro(Organizadora organizadoraFiltro){
        this.organizadoraFiltro = organizadoraFiltro;
    }

    /**
     * Filtra a lista de eventos, selecionando apenas os eventos que possuem a organizadora igual à organizadora de filtro.
     *
     * @param eventos a lista de eventos a ser filtrada
     * @return uma lista contendo apenas os eventos cuja organizadora é igual à organizadora de filtro
     */
    @Override
    public List<Evento> filtrar(List<Evento> eventos){
        List<Evento> eventosFiltrados = new ArrayList<Evento>();
        for (Evento evento : eventos){
            if (evento.getOrganizadora().equals(this.organizadoraFiltro)){
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
}
