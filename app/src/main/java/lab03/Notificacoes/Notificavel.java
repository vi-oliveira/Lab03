/*
 * Notificavel.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc de alguns métodos deste arquivo foi
 * feita com o uso do ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Notificacoes;

/**
 * Interface que define um comportamento notificável.
 * 
 * Classes que implementam esta interface devem fornecer uma implementação
 * do método {@code notificar}, que será chamado para realizar alguma forma
 * de notificação (ex: envio de e-mail, SMS, etc.).
 * 
 * @author Vinícius de Oliveira - 251527
 */
public interface Notificavel {
    /**
     * Realiza a notificação ao cliente.
     */
    public void notificar();
}
