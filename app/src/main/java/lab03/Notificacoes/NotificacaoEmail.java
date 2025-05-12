/*
 * NotificacaoEmail.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso do
 * ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Notificacoes;

/**
 * Representa uma notificação do tipo e-mail.
 * 
 * Esta classe implementa {@link Notificavel} e inclui um campo adicional para o
 * assunto do e-mail. A notificação é exibida no console com um formato visual
 * simples.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class NotificacaoEmail implements Notificavel {
    private String mensagem;
    private String assunto;

    /**
     * Construtor da notificação por e-mail.
     *
     * @param mensagem a mensagem do e-mail
     * @param assunto o assunto do e-mail
     */
    public NotificacaoEmail(String mensagem, String assunto){
        this.mensagem = mensagem;
        this.assunto = assunto;
    }

    /**
     * Retorna a mensagem da notificação.
     * @return a mensagem da notificação
     */
    public String getMensagem(){
        return mensagem;
    }

    /**
     * Define uma nova mensagem para a notificação.
     * @param novaMensagem a nova mensagem a ser definida
     */
    public void setMensagem(String novaMensagem){
        this.mensagem = novaMensagem;
    }

    /**
     * Retorna o assunto do e-mail.
     * @return o assunto do e-mail
     */
    public String getAssunto(){
        return assunto;
    }

    /**
     * Exibe a notificação formatada no console.
     * 
     * A saída inclui o assunto e a mensagem, com linhas separadoras
     * para facilitar a leitura.
     */
    @Override
    public void notificar(){
        for (int i = 0; i < this.mensagem.length(); i++)
            System.out.printf("-");
        System.out.printf("\n");

        System.out.println("Assunto: " + this.assunto);

        for (int i = 0; i < this.mensagem.length(); i++)
            System.out.printf("-");
        System.out.printf("\n");

        System.out.println(this.mensagem);
    }
}
