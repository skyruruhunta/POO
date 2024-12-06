public class Partida {
    
    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;
    private boolean turnoJogador1;

    /**
     * Construtor para inicializar a partida.
     */
    public Partida() {

        this.tabuleiro = new Tabuleiro();
        this.jogador1 = new Jogador("Branco");
        this.jogador2 = new Jogador("Preto");
        this.turnoJogador1 = true;
    }

    /**
     * Inicia o jogo, colocando todas as peças no tabuleiro e associando-as aos jogadores.
     */
    public void iniciarJogo() {
        tabuleiro.inicializarTabuleiro();

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = tabuleiro.getPeca(new Posicao(linha, coluna));

                if (peca != null) {
                    if (peca.getCor().equals("Branco")) {
                        jogador1.adicionarPeca(peca);
                    } else {
                        jogador2.adicionarPeca(peca);
                    }
                }
            }
        }

        System.out.println("O jogo começou!");
    }

    /**
     * Executa o turno de um jogador, movendo uma peça.
     * 
     * @param origem  a posição de origem da peça.
     * @param destino a posição de destino da peça.
     */
    public void executarTurno(Posicao origem, Posicao destino) {

        Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;

        System.out.println("Turno do jogador: " + jogadorAtual.getCor());
        Peca peca = tabuleiro.getPeca(origem);

        if (peca == null) {
            throw new IllegalArgumentException("Não há peça na posição de origem.");
        }

        if (!peca.getCor().equals(jogadorAtual.getCor())) {
            throw new IllegalArgumentException("Essa peça não pertence ao jogador atual.");
        }

        if (!peca.movimentoValido(destino, tabuleiro)) {
            throw new IllegalArgumentException("Movimento inválido para a peça.");
        }

        tabuleiro.moverPeca(origem, destino);

        if (estaEmXeque(oponente(jogadorAtual.getCor()))) {
            System.out.println("Atenção: Rei do jogador " + oponente(jogadorAtual.getCor()) + " está em xeque!");

            if (estaEmXequeMate(oponente(jogadorAtual.getCor()))) {
                System.out.println("Xeque-mate! " + jogadorAtual.getCor() + " venceu!");
                System.exit(0);
            }
        }
        
        turnoJogador1 = !turnoJogador1;
    }

    /**
     * Verifica se o Rei de uma cor está em xeque.
     * 
     * @param cor a cor do Rei a ser verificada.
     * @return true se o Rei estiver em xeque, false caso contrário.
     */
    public boolean estaEmXeque(String cor) {
        Posicao posicaoRei = tabuleiro.encontrarRei(cor);

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = tabuleiro.getPeca(new Posicao(linha, coluna));

                if (peca != null && !peca.getCor().equals(cor) &&
                    peca.movimentoValido(posicaoRei, tabuleiro)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se a cor está em xeque-mate.
     * 
     * @param cor a cor a ser verificada.
     * @return true se a cor estiver em xeque-mate, false caso contrário.
     */
 /**
 * Verifica se o jogador com a cor especificada está em xeque-mate.
 * 
 * @param cor a cor do jogador a ser verificada ("Branco" ou "Preto").
 * @return true se estiver em xeque-mate, false caso contrário.
 */
public boolean estaEmXequeMate(String cor) {
    Posicao posicaoRei = tabuleiro.encontrarRei(cor);
    
    for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
            if (i == 0 && j == 0) continue;

            Posicao destino = new Posicao(posicaoRei.getLinha() + i, posicaoRei.getColuna() + j);

            if (tabuleiro.posicaoValida(destino) && (tabuleiro.getPeca(destino) == null || !tabuleiro.getPeca(destino).getCor().equals(cor))) {
                Tabuleiro copia = tabuleiro.copiar();
                copia.moverPeca(posicaoRei, destino);

                if (!estaEmXeque(cor)) {
                    return false;
                }
            }
        }
    }    
    
    return true;
}

    /**
     * Obtém a cor do oponente.
     * 
     * @param cor a cor do jogador atual.
     * @return a cor do oponente.
     */
    private String oponente(String cor) {
        return cor.equals("Branco") ? "Preto" : "Branco";
    }

    /**
     * Obtém o tabuleiro da partida.
     * 
     * @return o tabuleiro.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Verifica se é o turno do jogador 1.
     * 
     * @return true se for o turno do jogador 1, false caso contrário.
     */
    public boolean isTurnoJogador1() {
        return turnoJogador1;
    }
}