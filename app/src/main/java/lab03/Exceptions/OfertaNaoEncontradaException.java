package lab03.Exceptions;

public class OfertaNaoEncontradaException extends Exception {
    public OfertaNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
