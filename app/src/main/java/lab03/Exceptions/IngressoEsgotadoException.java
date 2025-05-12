/*
 * IngressoEsgotadoException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada ao tentar vender ingressos para um evento
 * que já vendeu todos os ingressos possíveis.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class IngressoEsgotadoException extends Exception {
    /**
     * Construtor da exceção IngressoEsgotadoException
     * @param mensagem a mensagem de erro
     */
    public IngressoEsgotadoException(String mensagem){
        super(mensagem);
    }
}
