public class Peao extends Peca {
    
    /**
     * Construtor para criar um Peão.
     * 
     * @param cor     a cor do Peão ("Branco" ou "Preto").
     * @param posicao a posição inicial do Peão.
     */
    public Peao(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    /**
     * Verifica se o movimento do Peão é válido.
     * 
     * @param destino   a posição de destino do movimento.
     * @param tabuleiro o tabuleiro de jogo.
     * @return true se o movimento for válido, false caso contrário.
     */
    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        if (!tabuleiro.posicaoValida(destino)) return false;

        int direcao = cor.equals("Branco") ? -1 : 1;
        int deltaLinha = destino.getLinha() - posicao.getLinha();
        int deltaColuna = Math.abs(destino.getColuna() - posicao.getColuna());

        if (deltaColuna == 0 && deltaLinha == direcao && tabuleiro.getPeca(destino) == null) {
            return true;
        }

        if (deltaColuna == 0 && deltaLinha == 2 * direcao && posicao.getLinha() == (cor.equals("Branco") ? 6 : 1) && tabuleiro.getPeca(destino) == null && 
            tabuleiro.getPeca(new Posicao(posicao.getLinha() + direcao, posicao.getColuna())) == null) {
            return true;
        }

        if (deltaColuna == 1 && deltaLinha == direcao && tabuleiro.getPeca(destino) != null && !tabuleiro.getPeca(destino).getCor().equals(this.cor)) {
            return true;
        }

        return false;
    }
}