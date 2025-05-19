/*
 * IngressoNaoPertenceAoClienteException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada quando um cliente tenta vender no
 * marketplace um ingresso que não o pertence
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class IngressoNaoPertenceAoClienteException extends Exception {
    /**
     * Construtor da exceção IngressoNaoPertenceAoClienteException
     * @param mensagem a mensagem de erro
     */
    public IngressoNaoPertenceAoClienteException(String mensagem){
        super(mensagem);
    }
}
