/*
 * Duravel.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso do
 * ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Eventos;

import java.time.LocalDate;

/**
 * Interface que define o comportamento de eventos duráveis,
 * ou seja, eventos que possuem uma duração de mais de um dia.
 */
public interface Duravel {
    /**
     * Retorna a duração do evento em dias.
     * @return a quantidade de dias que o evento dura
     */
    public int getDuracao();

    /**
     * Retorna a data de início do evento.
     * @return a data de início do evento
     */
    public LocalDate getData();
}
