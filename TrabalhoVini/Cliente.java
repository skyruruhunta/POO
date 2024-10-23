package TrabalhoVini;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente no sistema, que pode fazer e cancelar reservas.
 */
public class Cliente {
    private String nome;
    @SuppressWarnings("unused")
    private String email;
    @SuppressWarnings("unused")
    private String telefone;
    private List<Reserva> reservas;

    /**
     * Constrói um cliente com o nome, e-mail e telefone especificados.
     *
     * @param nome O nome do cliente.
     * @param email O e-mail do cliente.
     * @param telefone O telefone do cliente.
     */
    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.reservas = new ArrayList<>();
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a lista de reservas do cliente.
     *
     * @return A lista de reservas feitas pelo cliente.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Adiciona uma nova reserva para o cliente e a confirma.
     *
     * @param reserva A reserva a ser adicionada.
     */
    public void fazerReserva(Reserva reserva) {
        reservas.add(reserva);
        reserva.confirmarReserva();
    }

    /**
     * Cancela uma reserva existente para o cliente.
     *
     * @param reserva A reserva a ser cancelada.
     */
    public void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva);
        reserva.cancelarReserva();
    }

    /**
     * Lista todas as reservas do cliente, formatando as datas conforme o formato fornecido.
     *
     * @param formatter O formato de data a ser utilizado na listagem.
     */
    public void listarReservas(DateTimeFormatter formatter) {
        System.out.println("Reservas do cliente " + nome + ":");
        for (Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
    }
}