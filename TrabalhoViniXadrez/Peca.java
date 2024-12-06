public abstract class Peca {
    
    protected String cor;
    protected Posicao posicao;

    /**
     * Construtor para criar uma peça.
     * 
     * @param cor     a cor da peça ("Branco" ou "Preto").
     * @param posicao a posição inicial da peça.
     */
    public Peca(String cor, Posicao posicao) {
        this.cor = cor;
        this.posicao = posicao;
    }

    /**
     * Obtém a cor da peça.
     * 
     * @return a cor da peça.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Obtém a posição atual da peça.
     * 
     * @return a posição da peça.
     */
    public Posicao getPosicao() {
        return posicao;
    }

    /**
     * Define a nova posição da peça.
     * 
     * @param posicao a nova posição da peça.
     */
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    /**
     * Método abstrato que verifica se um movimento é válido para a peça.
     * 
     * @param destino   a posição de destino do movimento.
     * @param tabuleiro o tabuleiro de jogo.
     * @return true se o movimento for válido, false caso contrário.
     */
    public abstract boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro);        
}