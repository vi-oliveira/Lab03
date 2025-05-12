/*
 * CancelamentoNaoPermitidoException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada quando toda a capacidade de um local já está
 * reservada.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class CapacidadeInsuficienteException extends Exception {
    /**
     * Construtor da exceção CapacidadeInsuficienteException
     * @param mensagem a mensagem de erro
     */
    public CapacidadeInsuficienteException(String mensagem){
        super(mensagem);
    }
}
