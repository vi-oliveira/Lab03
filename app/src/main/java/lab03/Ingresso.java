/*
 * Ingresso.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import java.text.DecimalFormat;

import lab03.Eventos.Evento;

/**
 * Contém a estrutura de implementação de um Ingresso.
 */
public class Ingresso implements Vendivel {

    private Evento evento;
    private Double preco;

    /**
     * Construtor da classe Ingresso
     * @param evento o evento associado ao Ingresso
     * @param preco o preço do Ingresso
     */
    public Ingresso(Evento evento, Double preco) {
        this.evento = evento;
        this.preco = preco;
    }

    /**
     * Retorna o preço do Ingresso
     * @return o preço do Ingresso
     */
    public Double getPreco() {
        return this.preco;
    }

    /**
     * Retorna o evento associado ao ingresso
     * @return o evento associado ao ingresso
     */
    public Evento getEvento(){
        return evento;
    }

    /**
     * Define o evento associado ao Ingresso
     * @param evento o evento associado ao Ingresso
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString(){
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
        return "Ingresso comum para: " + this.evento.getNome() + "  -  R$" + decimalFormat.format(this.preco);
    }
}
