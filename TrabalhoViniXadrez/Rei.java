public class Rei extends Peca {

    /**
     * Construtor para criar um Rei.
     * 
     * @param cor     a cor do Rei ("Branco" ou "Preto").
     * @param posicao a posição inicial do Rei.
     */
    public Rei(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    /**
     * Verifica se o movimento do Rei é válido.
     * 
     * @param destino   a posição de destino do movimento.
     * @param tabuleiro o tabuleiro de jogo.
     * @return true se o movimento for válido, false caso contrário.
     */
    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        if (!tabuleiro.posicaoValida(destino)) return false;

        // Verifica se a posição de destino é uma casa adjacente
        int deltaLinha = Math.abs(destino.getLinha() - posicao.getLinha());
        int deltaColuna = Math.abs(destino.getColuna() - posicao.getColuna());

        // O Rei pode mover-se uma casa em qualquer direção
        return deltaLinha <= 1 && deltaColuna <= 1;
    }
}