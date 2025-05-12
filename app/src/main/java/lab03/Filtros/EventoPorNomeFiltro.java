/*
 * EventoPorNomeFiltro.java
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
 * Filtro que seleciona eventos com base no nome.
 * 
 * Implementa a interface {@link Filtro} para filtrar uma lista de eventos,
 * retornando apenas aqueles cujo nome corresponde exatamente ao nome especificado.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoPorNomeFiltro implements Filtro {
    private String nomeFiltro;

    /**
    * Construtor da classe EventoPorNomeFiltro.
    *
    * @param nomeFiltro o nome dos eventos que devem ser selecionados pelo filtro
    */
    public EventoPorNomeFiltro(String nomeFiltro){
        this.nomeFiltro = nomeFiltro;
    }

    /**
     * Filtra a lista de eventos, selecionando apenas os eventos que possuem o nome igual ao nome de filtro.
     *
     * @param eventos a lista de eventos a ser filtrada
     * @return uma lista contendo apenas os eventos cujo nome é igual ao nome de filtro
     */
    @Override
    public List<Evento> filtrar(List<Evento> eventos){
        List<Evento> eventosFiltrados = new ArrayList<Evento>();
        for (Evento evento : eventos){
            if (evento.getNome().equals(this.nomeFiltro)){
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
}
