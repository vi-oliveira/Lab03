/*
 * Filtro.java
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
 * Interface que define um filtro para eventos.
 * 
 * Implementações desta interface devem fornecer uma lógica de filtragem
 * para selecionar eventos a partir de uma lista de eventos disponíveis.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public interface Filtro {
     /**
     * Filtra uma lista de eventos de acordo com critérios específicos.
     *
     * @param eventos a lista de eventos a ser filtrada
     * @return uma nova lista contendo apenas os eventos que atendem ao filtro
     */
     public List<Evento> filtrar(List<Evento> eventos);
}
