package TrabalhoVini;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaReservas sistema = new SistemaReservas();
        int opcao = 0;

        do {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Adicionar Novo Hotel");
            System.out.println("2. Listar Todos os Hotéis");
            System.out.println("3. Adicionar Quarto a um Hotel");
            System.out.println("4. Listar Quartos Disponíveis em um Hotel");
            System.out.println("5. Fazer Reserva");
            System.out.println("6. Cancelar Reserva");
            System.out.println("7. Listar Reservas de um Cliente");
            System.out.println("8. Cadastrar Cliente");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                String entrada = scanner.nextLine(); 
                opcao = Integer.parseInt(entrada);   
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                continue; 
            }

            
            switch (opcao) {
                case 1:
                    sistema.adicionarHotel();
                    break;
                case 2:
                    sistema.listarHoteis();
                    break;
                case 3:
                    sistema.adicionarQuarto();
                    break;
                case 4:
                    sistema.listarQuartosDisponiveis();
                    break;
                case 5:
                    sistema.fazerReserva();
                    break;
                case 6:
                    sistema.cancelarReserva();
                    break;
                case 7:
                    sistema.listarReservasCliente();
                    break;
                case 8:
                    sistema.cadastrarCliente();
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 9);

        scanner.close();
    }
}
