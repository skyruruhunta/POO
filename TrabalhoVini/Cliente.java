package TrabalhoVini;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente no sistema, que pode fazer e cancelar reservas.
 */
public class Cliente {
    private String nome;    
    private String email;   
    private String telefone;
    private List<Reserva> reservas;
    
    /**
     * Constrói um cliente com o nome, email e telefone especificados.
     *
     * @param nome O nome do cliente.
     * @param email O email do cliente.
     * @param telefone O telefone do cliente.
     */
    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.reservas = new ArrayList<>();
    }

    /**
     * Constrói um cliente com o nome especificado.
     *
     * @param nome O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o email do cliente.
     *
     * @return O email do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna o telefone do cliente.
     *
     * @return O telefone do cliente.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Retorna a lista de reservas do cliente.
     *
     * @return A lista de reservas do cliente.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Faz uma nova reserva para o cliente.
     *
     * @param reserva A reserva a ser feita.
     */
    public void fazerReserva(Reserva reserva) {
        reservas.add(reserva);
        reserva.confirmarReserva();
    }

    /**
     * Cancela uma reserva do cliente.
     *
     * @param reserva A reserva a ser cancelada.
     */
    public void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva);
        reserva.cancelarReserva();
    }

    /**
     * Lista as reservas do cliente.
     *
     * @param formatter O formatador de datas.
     */
    public void listarReservas(DateTimeFormatter formatter) {
        System.out.println("Reservas do cliente " + nome + ":");

        for (Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
    }
}