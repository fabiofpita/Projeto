package computador;

/**
 * Classe que representa um objeto do tipo computador.
 */
public class Computador {

    private long score;
    private int vitorias;
    private int derrotas;
    private String nome;

    /**
     * Construtor de um objeto do tipo computador.
     */
    public Computador(){
        vitorias = 0;
        derrotas = 0;
        score = 0;
        nome = "Caio";
    }

    /**
     * @return Metodo retorna o nome do computador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return Retorna o score do jogador.
     */
    public long getScore() {
        return this.score;
    }

    /**
     * Acrescenta ao score do jogador.
     * @param info Valor a ser somado ao antigo score.
     */
    public void setScore(long info) {
        this.score += info;
    }
}
