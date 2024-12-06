import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Partida partida = new Partida();
        partida.iniciarJogo();

        try (Scanner scanner = new Scanner(System.in)) {

        while (true) {
            try {
                System.out.println("\nTabuleiro Atual:");
                System.out.println(partida.getTabuleiro());

                System.out.println("Turno do jogador: " + (partida.isTurnoJogador1() ? "Branco" : "Preto"));

                System.out.println("Digite a posição de origem (ex: E2):");
                String origemInput = scanner.nextLine();
                
                System.out.println("Digite a posição de destino (ex: E4):");
                String destinoInput = scanner.nextLine();

                Posicao origem = textoParaPosicao(origemInput);
                Posicao destino = textoParaPosicao(destinoInput);

                partida.executarTurno(origem, destino);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                }
            }
        }
    }

    /*
     * Converte um texto no formato 'E2' para uma posição no tabuleiro.
     * @param texto Texto no formato 'E2'.
     * @return Posição correspondente no tabuleiro.
     * @throws IllegalArgumentException Se o texto não estiver no formato correto ou se a posição estiver fora do tabuleiro.
     */
    private static Posicao textoParaPosicao(String texto) {
        if (texto.length() != 2) {
            throw new IllegalArgumentException("Formato inválido. Use algo como 'E2'.");
        }

        char coluna = texto.toUpperCase().charAt(0);
        int linha = Character.getNumericValue(texto.charAt(1));

        int colunaIndex = coluna - 'A';
        int linhaIndex = 8 - linha;

        if (colunaIndex < 0 || colunaIndex > 7 || linhaIndex < 0 || linhaIndex > 7) {
            throw new IllegalArgumentException("Posição fora do tabuleiro.");
        }

        return new Posicao(linhaIndex, colunaIndex);
    }
}