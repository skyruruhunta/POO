public class Rainha extends Peca {
    /**
     * Construtor para criar uma Rainha.
     * 
     * @param cor     a cor da Rainha ("Branco" ou "Preto").
     * @param posicao a posição inicial da Rainha.
     */
    public Rainha(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    /**
     * Verifica se o movimento da Rainha é válido.
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

        // A Rainha pode se mover em linha reta (vertical ou horizontal) ou em diagonal,
        boolean movimentoReto = (deltaLinha == 0 || deltaColuna == 0) && !tabuleiro.temObstaculos(posicao, destino);
        boolean movimentoDiagonal = (deltaLinha == deltaColuna) && !tabuleiro.temObstaculos(posicao, destino);
        
        boolean capturaOuVazio = tabuleiro.getPeca(destino) == null || 
            !tabuleiro.getPeca(destino).getCor().equals(this.cor);

        return (movimentoReto || movimentoDiagonal) && capturaOuVazio;
    }
}