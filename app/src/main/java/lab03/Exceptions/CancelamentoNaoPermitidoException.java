/*
 * CancelamentoNaoPermitidoException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada quando uma operação de cancelamento de ingresso não é permitida.
 *
 * Esta exceção ocoorre quando se tenta cancelar um evento após uma data limite
 * (incluindo a data lmite). A data limite é o dia anterior ao Evento.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CancelamentoNaoPermitidoException extends Exception {
    /**
     * Construtor da exceção CancelamentoNaoPermitidoException
     * @param mensagem a mensagem de erro
     */
    public CancelamentoNaoPermitidoException(String mensagem){
        super(mensagem);
    }
}
