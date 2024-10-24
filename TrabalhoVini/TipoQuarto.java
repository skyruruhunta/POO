package TrabalhoVini;

/**
 * Enumeração que define os tipos de quarto disponíveis.
 */
public enum TipoQuarto {
    SIMPLES,
    DUPLO,
    SUITE;

    /**
     * Retorna uma representação textual do tipo de quarto.
     *
     * @return Uma string com o nome do tipo de quarto.
     */
    @Override
    public String toString() {
        switch (this) {
            case SIMPLES: return "Simples";
            case DUPLO: return "Duplo";
            case SUITE: return "Suíte";
            default: throw new IllegalArgumentException("Tipo de quarto inválido." );
        }
    }
}
