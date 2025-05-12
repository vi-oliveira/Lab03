/*
 * CompararA.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso do
 * ChatGPT e posteriormente revisada e/ou corrigida.
 */
package lab03.Clientes;

import java.util.Set;

import lab03.Eventos.Evento;

/**
 * Interface que define um contrato para comparar clientes
 * com base nos eventos que eles frequentam.
 * 
 * Implementações desta interface devem fornecer um método
 * que retorna os eventos em comum entre dois clientes.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public interface CompararA {
     /**
     * Compara este cliente com outro cliente e retorna os eventos
     * que ambos frequentam.
     *
     * @param outroCliente o cliente a ser comparado
     * @return um conjunto de eventos que os dois clientes têm em comum
     */
    public Set<Evento> compararA(Cliente outroCliente);
}
