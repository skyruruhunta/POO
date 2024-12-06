public class Torre extends Peca {

    /**
     * Construtor para criar uma Torre.
     * 
     * @param cor     a cor da Torre ("Branco" ou "Preto").
     * @param posicao a posição inicial da Torre no tabuleiro.
     */
    public Torre(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    /**
     * Verifica se o movimento da Torre é válido.
     * A Torre pode se mover em linha reta (vertical ou horizontal), desde que não 
     * haja obstáculos no caminho até a posição de destino.
     * 
     * @param destino   a posição de destino do movimento.
     * @param tabuleiro o tabuleiro de jogo, necessário para verificar obstáculos.
     * @return true se o movimento for válido, false caso contrário.
     */
    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        // Verifica se o destino está dentro dos limites do tabuleiro
        if (!tabuleiro.posicaoValida(destino)) return false;

        // Calcula a diferença entre as linhas e colunas
        int deltaLinha = Math.abs(destino.getLinha() - posicao.getLinha());
        int deltaColuna = Math.abs(destino.getColuna() - posicao.getColuna());
       
        return (deltaLinha == 0 || deltaColuna == 0) && !tabuleiro.temObstaculos(posicao, destino);
    }
}