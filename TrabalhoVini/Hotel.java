package TrabalhoVini;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um hotel que possui uma lista de quartos.
 * Cada hotel tem um nome, endereço e quartos disponíveis para reserva.
 */
public class Hotel {
    private String nome;
    private String endereco;
    private List<Quarto> listaDeQuartos;

    /**
     * Constrói um hotel com o nome e endereço especificados.
     *
     * @param nome O nome do hotel.
     * @param endereco O endereço do hotel.
     */
    public Hotel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaDeQuartos = new ArrayList<>();
    }

    /**
     * Retorna o nome do hotel.
     *
     * @return O nome do hotel.
     */
    public String getNome() {
        return nome;
    }

    /**
     *retorna a lista de quartos do hotel.
     *
     * @return A lista de quartos do hotel.
     */
    public List<Quarto> getListaDeQuartos() {
        return listaDeQuartos;
    }

    /**
     * Adiciona um quarto à lista de quartos do hotel.
     *
     * @param quarto O quarto a ser adicionado.
     */
    public void adicionarQuarto(Quarto quarto) {
        listaDeQuartos.add(quarto);       
    }

    /**
     * lista todos os quartos do hotel.
     *
     * @param numero O número do quarto a ser listado.
     */
    public void listarQuartosDisponiveis() {
        System.out.println("Quartos disponíveis no hotel " + nome + ":");
        
        for (Quarto quarto : listaDeQuartos) {
            if (quarto.isDisponivel()) {
                System.out.println(quarto);
            }
        }
    }

    /**
     * Retorna uma representação em String do hotel.
     *
     * @return Uma representação em String do hotel.
     */
    @Override
    public String toString() {
        return nome + " - " + endereco;
    }
}