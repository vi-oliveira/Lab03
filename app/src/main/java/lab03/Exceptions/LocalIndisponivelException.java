/*
 * LocalIndisponivelException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada ao tentar alocar um local para um evento
 * em uma data em que o local já foi alocado para outro evento.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class LocalIndisponivelException extends Exception {
    /**
     * Construtor da exceção LocalIndisponivelException
     * @param mensagem a mensagem de erro
     */
    public LocalIndisponivelException(String mensagem){
        super(mensagem);
    }
}
