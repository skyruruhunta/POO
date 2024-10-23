package TrabalhoVini;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma reserva de um quarto para um cliente em um determinado período.
 * Cada reserva está associada a um cliente e um quarto.
 */
public class Reserva {
    private static int contador = 0;
    private int id;
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;

    /**
     * Constrói uma reserva com o cliente, quarto, data de check-in e check-out especificados.
     * Assegura que a data de check-out seja posterior à data de check-in.
     *
     * @param cliente O cliente que fez a reserva.
     * @param quarto O quarto reservado.
     * @param dataCheckIn A data de check-in.
     * @param dataCheckOut A data de check-out.
     */
    public Reserva(Cliente cliente, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut) {

        if (dataCheckOut.isBefore(dataCheckIn)) {
            throw new IllegalArgumentException("A data de check-out não pode ser anterior à de check-in.");
        } 
        else if (dataCheckIn.isAfter(dataCheckOut)) {
            throw new IllegalArgumentException("A data de check-in não pode ser posterior à de check-out.");
        }

        this.id = ++contador;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
    }

    /**
     * Retorna o ID da reserva.
     *
     * @return O ID da reserva.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o quarto associado à reserva.
     *
     * @return O quarto reservado.
     */
    public Quarto getQuarto() {
        return quarto;
    }

    /**
     * Confirma a reserva, marcando o quarto como reservado.
     */
    public void confirmarReserva() {

        if (quarto.isDisponivel()) {
            quarto.reservar();
            System.out.println("Reserva " + id + " confirmada para o cliente " + cliente.getNome());
        } else {
            System.out.println("Quarto não está disponível para reserva.");
        }
    }

    /**
     * Cancela a reserva, liberando o quarto.
     */
    public void cancelarReserva() {
        quarto.liberar();

        System.out.println("Reserva " + id + " cancelada.");
    }

    /**
     * Retorna a representação textual da reserva, incluindo o cliente, o quarto, 
     * e o período da reserva (check-in e check-out).
     *
     * @return A representação textual da reserva.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String checkInFormatado = dataCheckIn.format(formatter);
        String checkOutFormatado = dataCheckOut.format(formatter);

        return "Reserva " + id + ": " + cliente.getNome() + " - Quarto " + quarto + 
               " de " + checkInFormatado + " até " + checkOutFormatado;
    }
}