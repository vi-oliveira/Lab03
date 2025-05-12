/*
 * LocalNaoEncontradoException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada ao tentar buscar por um local,
 * mas a bucas não encontra nenhum local.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class LocalNaoEncontradoException extends Exception{
    /**
     * Construtor da exceção LocalNaoEncontradoException
     * @param mensagem a mensagem de erro
     */
    public LocalNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
