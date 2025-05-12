/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação de alguns métodos desta classe foi feita com auxílio de IA
 */

package lab03.Eventos;

import java.util.ArrayList;
import java.util.List;

import lab03.Local;
import lab03.Exceptions.LocalNaoEncontradoException;

/**
 * Classe que representa uma imobiliária de eventos
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class ImobiliariaDeEventos {    
    private List<Local> locais;
    private String nome;

    /**
     * Construtor da classe ImobiliariaDeEventos
     * @param nome o nome da imobiliária de eventos
     */
    public ImobiliariaDeEventos(String nome) {
        this.nome = nome;
        this.locais = new ArrayList<Local>();
    }

    /**
     * Retorna o nome da imobiliária
     * @return o nome da imobiliária
     */
    public String getNome(){
        return nome;
    }

    /**
     * Adiciona um local à lista de locais disponíveis
     * @param local o local a ser adicionado
     */
    public void adicionarLocal(Local local) {
        this.locais.add(local);
    }
    
    /**
     * Busca um local na lista de locais cadastrados pelo seu nome.
     * @param nome o nome do local a ser buscado.
     * @return o local correspondente ao nome fornecido,
     * @throws LocalNaoEncontradoException Se nenhum local com o nome especificado
     * for encontrado na lista.
     */
    public Local buscarLocal(String nome) throws LocalNaoEncontradoException{
        for (Local local : locais){
            if (local.getNome().equals(nome)){
                return local;
            }
        }
        throw new LocalNaoEncontradoException(
            "O local especificado não foi encontrado");
    }
    
    /**
     * Busca locais na lista de locais cadastrados pelas suas capacidades.
     * @param capacidade A capacidade dos locais a serem buscados.
     * @return o local correspondente ao nome fornecido,
     * @throws LocalNaoEncontradoException se nenhum local for encontrado na lista.
     */
    public List<Local> buscarLocal(int capacidade) throws LocalNaoEncontradoException{
        List<Local> locaisEncontrados = new ArrayList<Local>();
        for (Local local : locais){
            if (local.getCapacidade() >= capacidade){
                locaisEncontrados.add(local);
            }
        }
        if (!locaisEncontrados.isEmpty()){
            return locaisEncontrados;
        } else{
            throw new LocalNaoEncontradoException(
                "Nenhum local foi encontrado");
        }
    }
}
