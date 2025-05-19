/*
 * OfertaNaoEncontradaException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada quando um cliente tenta comprar uma
 * oferta no marketplace, porém a oferta não foi encontrada.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class OfertaNaoEncontradaException extends Exception {
    /**
     * Construtor da exceção OfertaNaoEncontradaException
     * @param mensagem a mensagem de erro
     */
    public OfertaNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
