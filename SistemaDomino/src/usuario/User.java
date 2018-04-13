package usuario;

/**
 * Classe abstrata que representa um jogador.
 */
public abstract class User {

    /**
     * @return Retorna o nome do jogador.
     */
    public abstract String getNome();

    /**
     * Altera o nome do jogador.
     * @param info Novo nome.
     */
    public abstract void setNome(String info);

    /**
     * @return Retorna o email do jogador.
     */
    public abstract String getEmail();

    /**
     *Altera o email do jogador.
     * @param info Novo email.
     */
    public abstract void setEmail(String info);

    /**
     * @return Retorna a senha do jogador.
     */
    public abstract String getSenha();

    /**
     * Altera a senha do jogador.
     * @param info Nova senha.
     */
    public abstract void setSenha(String info);

    /**
     * @return Retorna o score do jogador.
     */
    public abstract long getScore();

    /**
     * Acrescenta ao score do jogador.
     * @param info Valor a ser somado ao antigo score.
     */
    public abstract void setScore(long info);

    /**
     * @return Retorna se o jogador está bloqueado(true) ou não(false).
     */
    public abstract boolean getBloqueado();

    /**
     * Altera o estado de bloqueio do jogador.
     * @param info Se receber um boolean TRUE o jogador sera bloqueado, caso receba FALSE ele será desbloqueado.
     */
    public abstract void setBloqueado(boolean info);
}
