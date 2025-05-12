/*
 * EventoNaoEncontradoException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada quando se tenta vender um ingresso, ao chamar um
 * método de objeto evento, para um evento de outro objeto.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class EventoNaoEncontradoException extends Exception {
    /**
     * Construtor da exceção EventoNaoEncontradoException
     * @param mensagem a mensagem de erro
     */
    public EventoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
