package usuario;

/**
 * Classe abstrata que representa um jogador.
 */
public abstract class User implements Comparable<User>{

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
    public abstract int getScore();

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

    /**
     * Configura o tempo de bloqueio de um usuário.
     * @param tempoBloqueado Hora do bloqueio.
     */
    public abstract void setTempoBloqueado(long tempoBloqueado);

    /**
     * Informa o horário que o usuário foi bloqueado.
     * @return Tempo em milisegundos.
     */
    public abstract long getTempoBloqueado();

    /**
     * Bloqueia um usuário por 2 horas.
     * @param hora Horário em milisegundos que o usuário foi bloqueado.
     * @return true caso tenha sido bloqueado e false caso o usuário já esteja bloqueado;
     */
    public abstract boolean bloquearUser(long hora);

    /**
     * Verifica se um usuário está bloqueado.
     * @param hora Hora da verificação.
     * @return true se está bloqueado ou false caso não..
     */
    public abstract boolean isBloqueado(long hora);

    /**
     * Verifica quanto tempo falta para o usuario ser desbloqueado.
     */
    public abstract int tempoBloqueio();

    public abstract boolean equals(User info);

    @Override
    public boolean equals(Object obj) {
        boolean iguais = false;
        if(obj != null && obj instanceof Usuario){
            iguais = Integer.toString(this.getScore()).equals(Integer.toString((((Usuario) obj).getScore())));
        }
        return iguais;
    }

    @Override
    public int compareTo(User user) {
        int retorno = 0;
        if(user != null){
            retorno = Integer.toString(this.getScore()).compareTo(Integer.toString(user.getScore()));
            retorno = retorno * -1;
        }
        return retorno;
    }

    public abstract int getTentativas();

    public abstract void consomeTentativa();

    public abstract void setOnli(boolean estado);

    public abstract boolean isOnli();
}
