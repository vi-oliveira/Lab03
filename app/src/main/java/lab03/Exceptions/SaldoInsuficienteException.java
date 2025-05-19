/*
 * SaldoInsuficienteException.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab03.Exceptions;

/**
 * Exceção lançada quando um cliente tenta comprar
 * um ingresso, porém o saldo é insuficiente.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class SaldoInsuficienteException extends Exception {
    /**
     * Construtor da exceção SaldoInsuficienteException
     * @param mensagem a mensagem de erro
     */
    public SaldoInsuficienteException(String mensagem){
        super(mensagem);
    }
}
