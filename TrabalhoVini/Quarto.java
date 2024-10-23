package TrabalhoVini;

/**
 * Representa um quarto de hotel, com um número, tipo e preço.
 * Um quarto pode estar disponível ou reservado.
 */
public class Quarto {
    private int numero;
    private TipoQuarto tipo;
    private double preco;
    private boolean estaDisponivel;

    /**
     * Constrói um quarto com o número, tipo e preço especificados.
     *
     * @param numero O número do quarto.
     * @param tipo O tipo do quarto.
     * @param preco O preço por noite do quarto.
     */
    public Quarto(int numero, TipoQuarto tipo, double preco) {
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.estaDisponivel = true;
    }

    /**
     * Retorna o número do quarto.
     *
     * @return O número do quarto.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna o tipo do quarto.
     *
     * @return O tipo do quarto.
     */
    public TipoQuarto getTipo() {
        return tipo;
    }

    /**
     * Verifica se o quarto está disponível para reserva.
     *
     * @return true se o quarto estiver disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return estaDisponivel;
    }

    /**
     * Reserva o quarto, tornando-o indisponível.
     */
    public void reservar() {
        if (estaDisponivel) {
            estaDisponivel = false;

            System.out.println("Quarto " + numero + " reservado com sucesso!");
        } else {
            System.out.println("Quarto já está reservado.");
        }
    }

    /**
     * Libera o quarto, tornando-o disponível novamente.
     */
    public void liberar() {
        estaDisponivel = true;

        System.out.println("Quarto " + numero + " liberado.");
    }

    /**
     * Retorna uma representação textual do quarto, incluindo o número, tipo e preço.
     *
     * @return A representação textual do quarto.
     */
    @Override
    public String toString() {
        return "Quarto " + numero + " (" + tipo + "), Preço: R$" + preco;
    }
}