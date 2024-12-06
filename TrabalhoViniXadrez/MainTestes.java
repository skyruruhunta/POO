/**
 * Classe de testes para verificar a funcionalidade completa do jogo de xadrez.
 * Executa um cenário com movimentos válidos, captura de peças, xeque, e xeque-mate.
 */
public class MainTestes {
    public static void main(String[] args) {
        Partida partida = new Partida();
        partida.iniciarJogo();

        System.out.println("Estado inicial do tabuleiro:");
        System.out.println(partida.getTabuleiro());

        try {            
            System.out.println("\nMovimento 1: Peão branco avança 2 casas (E2 para E4).");
                partida.executarTurno(new Posicao(6, 4), new Posicao(4, 4));
            System.out.println(partida.getTabuleiro());

            System.out.println("\nMovimento 2: Peão preto avança 2 casas (E7 para E5).");
                partida.executarTurno(new Posicao(1, 4), new Posicao(3, 4));
            System.out.println(partida.getTabuleiro());
            
            System.out.println("\nMovimento 3: Bispo branco avança na diagonal (F1 para C4).");
                partida.executarTurno(new Posicao(7, 5), new Posicao(4, 2));
            System.out.println(partida.getTabuleiro());
            
            System.out.println("\nMovimento 4: Cavalo preto se reposiciona (B8 para A6).");
                partida.executarTurno(new Posicao(0, 1), new Posicao(2, 0));
            System.out.println(partida.getTabuleiro());

            System.out.println("\nMovimento 5: Rainha branca avança (D1 para H5).");
                partida.executarTurno(new Posicao(7, 3), new Posicao(3, 7));
            System.out.println(partida.getTabuleiro());

            System.out.println("\nMovimento 6: Peão preto avança (F7 para F6).");
                partida.executarTurno(new Posicao(1, 5), new Posicao(2, 5));
            System.out.println(partida.getTabuleiro());

            System.out.println("\nMovimento 7: Rainha branca captura Peão preto (H5 para E5).");
                partida.executarTurno(new Posicao(3, 7), new Posicao(3, 4));
            System.out.println(partida.getTabuleiro());            

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}