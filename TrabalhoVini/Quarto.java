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
        this.estaDisponivel = true;  // Por padrão, o quarto é criado como disponível.
    }
   
    public int getNumero() {
        return numero;
    }
   
    public TipoQuarto getTipo() {
        return tipo;
    }
    
    public boolean isDisponivel() {
        return estaDisponivel;
    }

    /**
     * Reserva o quarto, se ele estiver disponível.
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
     * Libera o quarto, tornando-o disponível.
     */
    public void liberar() {
        estaDisponivel = true;
        System.out.println("Quarto " + numero + " liberado.");
    }

    /**
     * Retorna uma representação em String do quarto.
     *
     * @return Uma representação em String do quarto.
     */
    @Override
    public String toString() {
        return "Quarto " + numero + " (" + tipo + "), Preço: R$" + preco;
    }
}
