public class Bispo extends Peca {
    
    /**
     * Construtor para criar um Bispo.
     * 
     * @param cor     a cor do Bispo ("Branco" ou "Preto").
     * @param posicao a posição inicial do Bispo.
     */
    public Bispo(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    /**
     * Verifica se o movimento do Bispo é válido.
     * 
     * @param destino   a posição de destino do movimento.
     * @param tabuleiro o tabuleiro de jogo.
     * @return true se o movimento for válido, false caso contrário.
     */
    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        if (!tabuleiro.posicaoValida(destino)) return false;

        int deltaLinha = Math.abs(destino.getLinha() - posicao.getLinha());
        int deltaColuna = Math.abs(destino.getColuna() - posicao.getColuna());

        return (deltaLinha == deltaColuna) && !tabuleiro.temObstaculos(posicao, destino);
    }
}