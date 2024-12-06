public class Posicao {
    
    private int linha;
    private int coluna;

    /**
     * Construtor para criar uma posição.
     * 
     * @param linha  a linha da posição.
     * @param coluna a coluna da posição.
     */
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    /**
     * Obtém a linha da posição.
     * 
     * @return a linha da posição.
     */
    public int getLinha() {
        return linha;
    }

    /**
     * Obtém a coluna da posição.
     * 
     * @return a coluna da posição.
     */
    public int getColuna() {
        return coluna;
    }

    /**
     * Verifica se esta posição é igual a outra.
     * 
     * @param outra a posição a ser comparada.
     * @return true se forem iguais, false caso contrário.
     */
    public boolean equals(Posicao outra) {
        return this.linha == outra.linha && this.coluna == outra.coluna;
    }
}