import java.util.ArrayList;

public class Jogador {

    private String cor;
    private ArrayList<Peca> pecas;

    /**
     * Construtor para criar um jogador.
     * 
     * @param cor a cor das peças do jogador ("Branco" ou "Preto").
     */
    public Jogador(String cor) {
        this.cor = cor;
        this.pecas = new ArrayList<>();
    }

    /**
     * Obtém a cor das peças do jogador.
     * 
     * @return a cor do jogador.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Obtém as peças do jogador.
     * 
     * @return a lista de peças do jogador.
     */
    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    /**
     * Adiciona uma peça à lista de peças do jogador.
     * 
     * @param peca a peça a ser adicionada.
     */
    public void adicionarPeca(Peca peca) {
        pecas.add(peca);
    }

    /**
     * Remove uma peça da lista de peças do jogador.
     * 
     * @param peca a peça a ser removida.
     */
    public void removerPeca(Peca peca) {
        pecas.remove(peca);
    }
}