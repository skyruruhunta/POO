package TrabalhoVini;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaReservas sistema = new SistemaReservas(scanner);
        Administrador admin = new Administrador("Admin", 1);
        int opcao = 0;

        do {            
            System.out.println("\n===============================");
            System.out.println("       SISTEMA DE RESERVAS      ");
            System.out.println("===============================");
            System.out.println("1. Adicionar Novo Hotel (Admin)");
            System.out.println("2. Remover Hotel (Admin)");
            System.out.println("3. Listar Todos os Hotéis");
            System.out.println("4. Adicionar Quarto a um Hotel");
            System.out.println("5. Listar Quartos Disponíveis em um Hotel");
            System.out.println("6. Fazer Reserva");
            System.out.println("7. Cancelar Reserva");
            System.out.println("8. Listar Reservas de um Cliente");
            System.out.println("9. Cadastrar Cliente");
            System.out.println("10. Sair");
            System.out.println("===============================");
            System.out.print("Escolha uma opção (1-10): ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("\n-- Adicionar Novo Hotel --");
                        admin.adicionarHotel(sistema.getHoteis(), scanner);
                        break;

                    case 2:
                        System.out.println("\n-- Remover Hotel --");
                        admin.removerHotel(sistema.getHoteis(), scanner);
                        break;

                    case 3:
                        System.out.println("\n-- Listar Todos os Hotéis --");
                        sistema.listarHoteis();
                        break;

                    case 4:
                        System.out.println("\n-- Adicionar Quarto a um Hotel --");
                        sistema.adicionarQuarto();
                        break;

                    case 5:
                        System.out.println("\n-- Listar Quartos Disponíveis em um Hotel --");
                        sistema.listarQuartosDisponiveis();
                        break;

                    case 6:
                        System.out.println("\n-- Fazer Reserva --");
                        sistema.fazerReserva();
                        break;

                    case 7:
                        System.out.println("\n-- Cancelar Reserva --");
                        sistema.cancelarReserva();
                        break;

                    case 8:
                        System.out.println("\n-- Listar Reservas de um Cliente --");
                        sistema.listarReservasCliente();
                        break;

                    case 9:
                        System.out.println("\n-- Cadastrar Cliente --");
                        sistema.cadastrarCliente();
                        break;

                    case 10:
                        System.out.println("\nSaindo do sistema... Até logo!");
                        break;
                        
                    default:
                        System.out.println("\nOpção inválida. Por favor, escolha um número entre 1 e 10.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("\nErro: Entrada inválida. Por favor, insira um número.");
            }
                        
            System.out.println("\n===============================");
        } while (opcao != 10);

        scanner.close();
    }
}
