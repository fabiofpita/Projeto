package computador;

/**
 * Classe que representa um objeto do tipo computador.
 */
public abstract class Computador{
    private static int i = 0;
    private String nome;

    /**
     * Construtor de um objeto do tipo computador.
     */
    public Computador(){
        nome = "Computador "+ i++;
    }

    /**
     * @return Metodo retorna o nome do computador.
     */
    public String getNome() {
        return nome;
    }

}
