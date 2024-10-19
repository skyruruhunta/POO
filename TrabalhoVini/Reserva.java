package TrabalhoVini;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma reserva de um quarto para um cliente em um determinado período.
 * Cada reserva está associada a um cliente e um quarto.
 */
public class Reserva {
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;

    /**
     * Constrói uma reserva com o cliente, quarto, data de check-in e check-out especificados.
     *
     * @param cliente O cliente que fez a reserva.
     * @param quarto O quarto reservado.
     * @param dataCheckIn A data de check-in.
     * @param dataCheckOut A data de check-out.
     */
    public Reserva(Cliente cliente, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
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
            System.out.println("Reserva confirmada para o cliente " + cliente.getNome());
        } else {
            System.out.println("Quarto não está disponível para reserva.");
        }
    }

    /**
     * Cancela a reserva, liberando o quarto.
     */
    public void cancelarReserva() {
        quarto.liberar();
        System.out.println("Reserva cancelada.");
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

        return "Reserva: " + cliente.getNome() + " - Quarto " + quarto + 
               " de " + checkInFormatado + " até " + checkOutFormatado;
    }
}
