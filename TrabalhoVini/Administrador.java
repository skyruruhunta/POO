package TrabalhoVini;

import java.util.List;
import java.util.Scanner;

/**
 * Representa um administrador no sistema, que pode adicionar e remover hotéis.
 */
public class Administrador {
    private String nome;
    private int id;

    /**
     * Constrói um administrador com o nome e id especificados.
     *
     * @param nome O nome do administrador.
     * @param id O ID do administrador.
     */
    public Administrador(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    /**
     * Adiciona um novo hotel ao sistema.
     *
     * @param hoteis Lista de hotéis do sistema.
     * @param scanner Objeto Scanner para capturar entrada de dados.
     */
    public void adicionarHotel(List<Hotel> hoteis, Scanner scanner) {

        System.out.print("Nome do Hotel: ");
        String nome = scanner.nextLine();
        
        if (buscarHotelPorNome(hoteis, nome) != null) {
            System.out.println("Erro: Já existe um hotel com este nome.");
            return;
        }

        System.out.print("Endereço do Hotel: ");
        String endereco = scanner.nextLine();

        Hotel hotel = new Hotel(nome, endereco);
        hoteis.add(hotel);
        
        System.out.println("Hotel " + nome + " adicionado com sucesso.");
    }

    /**
     * Remove um hotel do sistema.
     *
     * @param hoteis Lista de hotéis do sistema.
     * @param scanner Objeto Scanner para capturar entrada de dados.
     */
    public void removerHotel(List<Hotel> hoteis, Scanner scanner) {

        System.out.print("Nome do Hotel a remover: ");
        String nome = scanner.nextLine();

        Hotel hotel = buscarHotelPorNome(hoteis, nome);

        if (hotel == null) {
            System.out.println("Erro: Hotel não encontrado.");
            return;
        }

        hoteis.remove(hotel);

        System.out.println("Hotel " + nome + " removido com sucesso.");
    }

    /**
     * Busca um hotel pelo nome.
     *
     * @param hoteis Lista de hotéis do sistema.
     * @param nome O nome do hotel a ser buscado.
     * @return O hotel encontrado ou null se não existir.
     */
    private Hotel buscarHotelPorNome(List<Hotel> hoteis, String nome) {

        for (Hotel hotel : hoteis) {

            if (hotel.getNome().equalsIgnoreCase(nome)) {
                return hotel;
            }
        }
        return null;
    }
      
    /**
     * Retorna o nome do administrador.
     *
     * @return O nome do administrador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o ID do administrador.
     *
     * @return O ID do administrador.
     */
    public int getId() {
        return id;
    }
}