public class Tabuleiro {
    
    private Peca[][] tabuleiro;

    /**
     * Construtor para criar o tabuleiro vazio.
     */
    public Tabuleiro() {
        tabuleiro = new Peca[8][8];
    }

    /**
     * Inicializa o tabuleiro com todas as peças em suas posições padrão.
     */
    public void inicializarTabuleiro() {
        tabuleiro[0][0] = new Torre("Preto", new Posicao(0, 0));
        tabuleiro[0][1] = new Cavalo("Preto", new Posicao(0, 1));
        tabuleiro[0][2] = new Bispo("Preto", new Posicao(0, 2));
        tabuleiro[0][3] = new Rainha("Preto", new Posicao(0, 3));
        tabuleiro[0][4] = new Rei("Preto", new Posicao(0, 4));
        tabuleiro[0][5] = new Bispo("Preto", new Posicao(0, 5));
        tabuleiro[0][6] = new Cavalo("Preto", new Posicao(0, 6));
        tabuleiro[0][7] = new Torre("Preto", new Posicao(0, 7));

        for (int coluna = 0; coluna < 8; coluna++) {
            tabuleiro[1][coluna] = new Peao("Preto", new Posicao(1, coluna));
        }

        tabuleiro[7][0] = new Torre("Branco", new Posicao(7, 0));
        tabuleiro[7][1] = new Cavalo("Branco", new Posicao(7, 1));
        tabuleiro[7][2] = new Bispo("Branco", new Posicao(7, 2));
        tabuleiro[7][3] = new Rainha("Branco", new Posicao(7, 3));
        tabuleiro[7][4] = new Rei("Branco", new Posicao(7, 4));
        tabuleiro[7][5] = new Bispo("Branco", new Posicao(7, 5));
        tabuleiro[7][6] = new Cavalo("Branco", new Posicao(7, 6));
        tabuleiro[7][7] = new Torre("Branco", new Posicao(7, 7));

        for (int coluna = 0; coluna < 8; coluna++) {
            tabuleiro[6][coluna] = new Peao("Branco", new Posicao(6, coluna));
        }
    }

    /**
     * Verifica se há obstáculos no caminho entre duas posições.
     * 
     * @param origem  a posição inicial.
     * @param destino a posição final.
     * @return true se houver obstáculos, false caso contrário.
     */
    public boolean temObstaculos(Posicao origem, Posicao destino) {
        int linhaAtual = origem.getLinha();
        int colunaAtual = origem.getColuna();

        int deltaLinha = Integer.compare(destino.getLinha(), origem.getLinha());
        int deltaColuna = Integer.compare(destino.getColuna(), origem.getColuna());

        // Avançar no caminho até alcançar o destino
        linhaAtual += deltaLinha;
        colunaAtual += deltaColuna;

        // Verificar se há peças no caminho
        while (linhaAtual != destino.getLinha() || colunaAtual != destino.getColuna()) {
            if (getPeca(new Posicao(linhaAtual, colunaAtual)) != null) {
                return true;
            }
            linhaAtual += deltaLinha;
            colunaAtual += deltaColuna;
        }

        return false;
    }

    /**
     * Obtém a peça em uma posição específica.
     *
     * @param posicao a posição da peça a ser recuperada.
     * @return a peça encontrada ou null se a posição estiver vazia.
     */
    public Peca getPeca(Posicao posicao) {
        return tabuleiro[posicao.getLinha()][posicao.getColuna()];
    }

    /**
     * Move uma peça de uma posição para outra.
     *
     * @param origem  a posição de origem da peça.
     * @param destino a posição de destino da peça.
     * @throws IllegalArgumentException se a posição de origem não contiver uma peça.
     */
    public void moverPeca(Posicao origem, Posicao destino) {
        Peca peca = getPeca(origem);

        if (peca == null) {
            throw new IllegalArgumentException("Não há peça na posição de origem.");
        }

        tabuleiro[destino.getLinha()][destino.getColuna()] = peca;
        tabuleiro[origem.getLinha()][origem.getColuna()] = null;
        peca.setPosicao(destino);
    }

    /**
     * Verifica se uma posição é válida dentro do tabuleiro.
     *
     * @param posicao a posição a ser verificada.
     * @return true se a posição estiver dentro dos limites do tabuleiro, false caso contrário.
     */
    public boolean posicaoValida(Posicao posicao) {
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }

    /**
     * Encontra a posição do Rei de uma determinada cor.
     *
     * @param cor a cor do Rei a ser encontrada ("Branco" ou "Preto").
     * @return a posição do Rei.
     * @throws IllegalStateException se o Rei não for encontrado no tabuleiro.
     */
    public Posicao encontrarRei(String cor) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = tabuleiro[linha][coluna];

                if (peca instanceof Rei && peca.getCor().equals(cor)) {
                    return peca.getPosicao();
                }
            }
        }
        throw new IllegalStateException("Rei não encontrado!");
    }

    /**
     * Verifica se o Rei de uma determinada cor está em xeque.
     *
     * @param cor a cor do Rei a ser verificada ("Branco" ou "Preto").
     * @return true se o Rei estiver em xeque, false caso contrário.
     */
    public boolean estaEmXeque(String cor) {
        Posicao posicaoRei = encontrarRei(cor);

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = getPeca(new Posicao(linha, coluna));

                if (peca != null && !peca.getCor().equals(cor) &&
                        peca.movimentoValido(posicaoRei, this)) {
                    return true; 
                }
            }
        }
        return false;
    }



    /**
     * Cria uma cópia do tabuleiro, com todas as peças nas mesmas posições.
     * 
     * @return uma nova instância de Tabuleiro com as mesmas peças.
     */
    public Tabuleiro copiar() {
        Tabuleiro copia = new Tabuleiro();

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca pecaOriginal = this.tabuleiro[linha][coluna];

                if (pecaOriginal != null) {
                    Posicao novaPosicao = new Posicao(linha, coluna);
                    
                    if (pecaOriginal instanceof Torre) {
                        copia.tabuleiro[linha][coluna] = new Torre(pecaOriginal.getCor(), novaPosicao);
                    } else if (pecaOriginal instanceof Cavalo) {
                        copia.tabuleiro[linha][coluna] = new Cavalo(pecaOriginal.getCor(), novaPosicao);

                    } else if (pecaOriginal instanceof Bispo) {
                        copia.tabuleiro[linha][coluna] = new Bispo(pecaOriginal.getCor(), novaPosicao);
                    } else if (pecaOriginal instanceof Rainha) {
                        copia.tabuleiro[linha][coluna] = new Rainha(pecaOriginal.getCor(), novaPosicao);

                    } else if (pecaOriginal instanceof Rei) {
                        copia.tabuleiro[linha][coluna] = new Rei(pecaOriginal.getCor(), novaPosicao);
                    } else if (pecaOriginal instanceof Peao) {
                        copia.tabuleiro[linha][coluna] = new Peao(pecaOriginal.getCor(), novaPosicao);
                    }
                }
            }
        }

        return copia;
    }

    /**
     * Representa o estado do tabuleiro como uma string.
     * 
     * @return uma string que representa o tabuleiro.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    A   B   C   D   E   F   G   H\n");
        sb.append("  +---+---+---+---+---+---+---+---+\n");

        for (int linha = 0; linha < 8; linha++) {
            sb.append(8 - linha).append(" |");
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = tabuleiro[linha][coluna];

                if (peca == null) {
                    sb.append(" . ");
                } else {
                    sb.append(" ").append(obterSimboloPeca(peca)).append(" ");
                }
                sb.append("|");
            }
            sb.append(" ").append(8 - linha).append("\n");
            sb.append("  +---+---+---+---+---+---+---+---+\n");
        }

        sb.append("    A   B   C   D   E   F   G   H\n");
        return sb.toString();
    }

    /**
     * Obtém o símbolo representativo de uma peça.
     * 
     * @param peca a peça a ser representada.
     * @return o símbolo da peça.
     */
    private char obterSimboloPeca(Peca peca) {
        char simbolo;
        
        if (peca instanceof Rei) {
            simbolo = 'R';
        } else if (peca instanceof Rainha) {
            simbolo = 'D';
        } else if (peca instanceof Bispo) {
            simbolo = 'B';
        } else if (peca instanceof Cavalo) {
            simbolo = 'C';
        } else if (peca instanceof Torre) {
            simbolo = 'T';
        } else if (peca instanceof Peao) {
            simbolo = 'P';
        } else {
            throw new IllegalStateException("Tipo de peça desconhecido: " + peca.getClass().getSimpleName());
        }
        return peca.getCor().equals("Branco") ? simbolo : Character.toLowerCase(simbolo);
    }
}