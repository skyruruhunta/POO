package TrabalhoVini;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Gerencia o sistema de reservas de quartos para múltiplos hotéis.
 * Permite a adição de hotéis, a gestão de quartos e reservas.
 */
public class SistemaReservas {
    private List<Hotel> hoteis;
    private List<Cliente> clientes;
    private Scanner scanner;

    /**
     * Construtor para inicializar o sistema de reservas.
     * Inicializa as listas de hotéis e clientes.
     * 
     * @param hoteis   Lista de hotéis a ser inicializada.
     * @param clientes Lista de clientes a ser inicializada.
     * @param scanner  Scanner a ser utilizado para entrada de dados.
     */
    public SistemaReservas(Scanner scanner) {
        this.hoteis = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.scanner = scanner;
    }

    /**
     * Adiciona um novo hotel ao sistema.
     * Verifica se já existe um hotel com o mesmo nome antes de adicionar.
     * Se já existir, exibe uma mensagem de erro.
     */
    public void adicionarHotel() {

        System.out.print("Nome do Hotel: ");
        String nome = scanner.nextLine();

        /**
         * Verifica se já existe um hotel com o mesmo nome.
         * Se já existir, exibe uma mensagem de erro e retorna.
         */
        if (buscarHotelPorNome(nome) != null) {
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

            System.out.print("Tipo do Quarto (Simples, Duplo, Suíte): ");
            TipoQuarto tipo = TipoQuarto.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Preço do Quarto: ");
            double preco = Double.parseDouble(scanner.nextLine());

            Quarto quarto = new Quarto(numero, tipo, preco);
            hotel.adicionarQuarto(quarto);

            System.out.println("Quarto adicionado com sucesso.");

        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida para número ou preço.");
        }

    }

    /**
     * Lista todos os quartos disponíveis em um hotel específico.
     * Se o hotel não for encontrado, exibe uma mensagem de erro.
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
     * Cadastra um novo cliente no sistema.
     * Verifica se o cliente já está cadastrado antes de adicionar.
     * Se o cliente já estiver cadastrado, exibe uma mensagem de erro.
     */
    public void cadastrarCliente() {

        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();

        if (buscarClientePorNome(nome) != null) {
            System.out.println("Erro: Cliente já cadastrado.");
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
            int numeroQuarto = scanner.nextInt();
            scanner.nextLine();

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
        int numeroQuarto = scanner.nextInt();

        scanner.nextLine();

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
     * Fecha o scanner utilizado para entrada de dados.
     */
    public void fecharScanner() {
        
        if (scanner != null)
            scanner.close();
    }

    /**
     * Busca um hotel pelo nome na lista de hotéis.
     *
     * @param nome O nome do hotel a ser buscado.
     * @return O hotel encontrado, ou null se não for encontrado.
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
     * Busca um cliente pelo nome na lista de clientes.
     *
     * @param nome O nome do cliente a ser buscado.
     * @return O cliente encontrado, ou null se não for encontrado.
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
     *
     * @param hotel  O hotel onde o quarto está localizado.
     * @param numero O número do quarto a ser buscado.
     * @return O quarto encontrado, ou null se não for encontrado.
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