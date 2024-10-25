package TrabalhoVini;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Gerencia o sistema de reservas de quartos para múltiplos hotéis.
 * Permite a adição de clientes e a gestão de reservas e quartos.
 */
public class SistemaReservas {
    private List<Hotel> hoteis;
    private List<Cliente> clientes;
    private Scanner scanner;

    /**
     * Constrói um sistema de reservas com a lista de hotéis e clientes vazia.
     *
     * @param scanner Objeto Scanner para capturar entrada de dados.
     */
    public SistemaReservas(Scanner scanner) {
        this.hoteis = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.scanner = scanner;
    }
    

    /**
     * Retorna a lista de hoteis do sistema.
     *
     * @return A lista de hoteis.
     */
    public List<Hotel> getHoteis() {
        return hoteis;
    }
    
    /**
     * Adiciona um novo cliente ao sistema.
     * Se o cliente já estiver cadastrado, exibe uma mensagem de erro.
     */
    public void cadastrarCliente() {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();

        if (buscarClientePorNome(nome) != null) {
            System.out.println("Erro: Cliente com este nome já cadastrado.");
            return;
        }

        System.out.print("Email do Cliente: ");
        String email = scanner.nextLine();

        System.out.print("Telefone do Cliente: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(nome, email, telefone);
        clientes.add(cliente);
        System.out.println("Cliente " + nome + " cadastrado com sucesso.");
    }
    /**
     * Adiciona um quarto a um hotel específico.
     * Se o hotel não for encontrado, exibe uma mensagem de erro.
     */
    public void adicionarQuarto() {
        System.out.print("Nome do Hotel: ");
        String nomeHotel = scanner.nextLine();

        Hotel hotel = buscarHotelPorNome(nomeHotel);

        if (hotel == null) {
            System.out.println("Erro: Hotel não encontrado.");
            return;
        }

        try {
            System.out.print("Número do Quarto: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo do Quarto (SIMPLES, DUPLO, SUITE): ");
            TipoQuarto tipo = TipoQuarto.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Preço do Quarto: ");
            double preco = Double.parseDouble(scanner.nextLine());

            Quarto quarto = new Quarto(numero, tipo, preco);
            hotel.adicionarQuarto(quarto);

            System.out.println("Quarto adicionado com sucesso.");

        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida para número ou preço.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Tipo de quarto inválido.");
        }
    }

    /**
     * Lista todos os quartos disponíveis em um hotel específico.
     */
    public void listarQuartosDisponiveis() {
        System.out.print("Nome do Hotel: ");
        String nomeHotel = scanner.nextLine();

        Hotel hotel = buscarHotelPorNome(nomeHotel);
        if (hotel == null) {
            System.out.println("Erro: Hotel não encontrado.");
            return;
        }

        hotel.listarQuartosDisponiveis();
    }

     /**
     * Lista todos os hotéis cadastrados no sistema.
     * Se não houver hotéis cadastrados, exibe uma mensagem de erro.
     */
    public void listarHoteis() {
        if (hoteis.isEmpty()) {
            System.out.println("Nenhum hotel cadastrado.");

        } else {
            System.out.println("Lista de hotéis:");
            for (Hotel hotel : hoteis) {
                System.out.println(hotel);
            }
        }
    }

    /**
     * Faz uma reserva de quarto para um cliente.
     * Se o cliente não estiver cadastrado, ele é registrado no sistema.
     */
    public void fazerReserva() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Nome do Cliente: ");
            String nomeCliente = scanner.nextLine();

            Cliente cliente = buscarClientePorNome(nomeCliente);

            if (cliente == null) {
                System.out.print("Email do Cliente: ");
                String email = scanner.nextLine();

                System.out.print("Telefone do Cliente: ");
                String telefone = scanner.nextLine();

                cliente = new Cliente(nomeCliente, email, telefone);
                clientes.add(cliente);
            }

            System.out.print("Nome do Hotel: ");
            String nomeHotel = scanner.nextLine();

            Hotel hotel = buscarHotelPorNome(nomeHotel);

            if (hotel == null) {
                System.out.println("Hotel não encontrado.");
                return;
            }

            hotel.listarQuartosDisponiveis();

            System.out.print("Número do Quarto a reservar: ");
            int numeroQuarto = Integer.parseInt(scanner.nextLine());

            Quarto quarto = buscarQuartoPorNumero(hotel, numeroQuarto);

            if (quarto == null || !quarto.isDisponivel()) {
                System.out.println("Quarto não encontrado ou indisponível.");
                return;
            }

            System.out.print("Data de Check-in (dd/MM/yyyy): ");
            String checkInStr = scanner.nextLine();
            LocalDate checkIn = LocalDate.parse(checkInStr, formatter);

            System.out.print("Data de Check-out (dd/MM/yyyy): ");
            String checkOutStr = scanner.nextLine();
            LocalDate checkOut = LocalDate.parse(checkOutStr, formatter);

            Reserva reserva = new Reserva(cliente, quarto, checkIn, checkOut);
            cliente.fazerReserva(reserva);

        } catch (Exception e) {
            System.out.println("Erro: Entrada inválida. Por favor, tente novamente.");
        }
    }

    /**
     * Cancela uma reserva de quarto para um cliente.
     * Se a reserva não for encontrada, exibe uma mensagem de erro.
     */
    public void cancelarReserva() {
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();

        Cliente cliente = buscarClientePorNome(nomeCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        cliente.listarReservas(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Número do Quarto a cancelar: ");
        int numeroQuarto = Integer.parseInt(scanner.nextLine());

        for (Reserva reserva : cliente.getReservas()) {

            if (reserva.getQuarto().getNumero() == numeroQuarto) {
                cliente.cancelarReserva(reserva);
                System.out.println("Reserva cancelada com sucesso.");
                return;
            }
        }

        System.out.println("Reserva não encontrada.");
    }

    /**
     * Lista todas as reservas de um cliente específico.
     * Se o cliente não for encontrado, exibe uma mensagem de erro.
     */
    public void listarReservasCliente() {

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();

        Cliente cliente = buscarClientePorNome(nomeCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        cliente.listarReservas(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Busca um hotel pelo nome.
     * Se o hotel não for encontrado, retorna null.
     */
    private Hotel buscarHotelPorNome(String nome) {
        for (Hotel hotel : hoteis) {

            if (hotel.getNome().equalsIgnoreCase(nome)) {
                return hotel;
            }
        }
        return null;
    }

    /**
     * Busca um cliente pelo nome.
     * Se o cliente não for encontrado, retorna null.
     */
    private Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {

            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Busca um quarto pelo número em um hotel específico.
     * Se o quarto não for encontrado, retorna null.
     */
    private Quarto buscarQuartoPorNumero(Hotel hotel, int numero) {
        
        for (Quarto quarto : hotel.getListaDeQuartos()) {
            if (quarto.getNumero() == numero) {
                return quarto;
            }
        }
        return null;
    }
}