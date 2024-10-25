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
     * Constrói uma reserva com o cliente, quarto e datas de check-in e check-out especificados.
     *
     * @param cliente O cliente que fez a reserva.
     * @param quarto O quarto reservado.
     * @param dataCheckIn A data de check-in da reserva.
     * @param dataCheckOut A data de check-out da reserva.
     */
    public Reserva(Cliente cliente, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        if (dataCheckOut.isBefore(dataCheckIn)) {
            throw new IllegalArgumentException("A data de check-out não pode ser anterior a de check-in.");

        } else if (dataCheckIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de check-in não pode ser anterior a data atual.");
        }
         
        this.id = ++contador;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
    }
    
    public int getId() {
        return id;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    /**
     * Retorna a data de check-in da reserva.
     *
     * @return A data de check-in da reserva.
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
     * Retorna uma representação em String da reserva.
     *
     * @return Uma representação em String da reserva.
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
