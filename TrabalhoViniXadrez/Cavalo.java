public class Cavalo extends Peca {
    
    /**
     * Construtor para criar um Cavalo.
     * 
     * @param cor     a cor do Cavalo ("Branco" ou "Preto").
     * @param posicao a posição inicial do Cavalo.
     */
    public Cavalo(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    /**
     * Verifica se o movimento do Cavalo é válido.
     * 
     * @param destino   a posição de destino do movimento.
     * @param tabuleiro o tabuleiro de jogo.
     * @return true se o movimento for válido, false caso contrário.
     */
    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        if (!tabuleiro.posicaoValida(destino)) return false;

        // Calcula a diferença entre as posições de origem e destino.
        int deltaLinha = Math.abs(destino.getLinha() - posicao.getLinha());
        int deltaColuna = Math.abs(destino.getColuna() - posicao.getColuna());

        
        boolean movimentoEmL = (deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2);
        boolean capturaOuVazio = tabuleiro.getPeca(destino) == null || !tabuleiro.getPeca(destino).getCor().equals(this.cor);
        
        return movimentoEmL && capturaOuVazio;
    }
}