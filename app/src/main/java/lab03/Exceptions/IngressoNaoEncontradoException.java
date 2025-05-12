/*
 * IngressoNaoEncontradoException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada ao tentar cancelar um ingresso que o cliente não tem.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class IngressoNaoEncontradoException extends Exception {
    /**
     * Construtor da exceção IngressoNaoEncontradoException
     * @param mensagem a mensagem de erro
     */
    public IngressoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
