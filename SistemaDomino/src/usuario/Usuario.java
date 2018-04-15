package usuario;

public class Usuario extends User {

    private String nome;
    private String email;
    private String senha;
    private long score;
    private boolean bloqueado;
    private long tempoBloqueado;

    /**
     * Constante que define o tempo de horas como padrão para bloqueio.
     */
    public static final long BLOQUEIO = 60*2*60000;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.score = 0;
        bloqueado = false;
        this.tempoBloqueado = 0;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String info) {
        this.nome = info;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String info) {
        this.email = info;
    }

    @Override
    public String getSenha() {
        return this.senha;
    }

    @Override
    public void setSenha(String info) {
        this.senha = info;
    }

    @Override
    public long getScore() {
        return this.score;
    }

    @Override
    public void setScore(long info) {
        this.score += info;
    }

    @Override
    public boolean getBloqueado() {
        return this.bloqueado;
    }

    @Override
    public void setBloqueado(boolean info) {
        this.bloqueado = info;
    }

    /**
     * Configura o tempo de bloqueio de um usuário.
     * @param tempoBloqueado Hora do bloqueio.
     */
    public void setTempoBloqueado(long tempoBloqueado) {
        this.tempoBloqueado = tempoBloqueado;
    }

    /**
     * Informa o horário que o usuário foi bloqueado.
     * @return Tempo em milisegundos.
     */
    public long getTempoBloqueado() {
        return tempoBloqueado;
    }

    /**
     * Bloqueia um usuário por 2 horas.
     * @param info Usuário a ser bloqueado.
     * @param hora Horário em milisegundos que o usuário foi bloqueado.
     * @return true caso tenha sido bloqueado e false caso o usuário já esteja bloqueado;
     */
    public boolean bloquearUser(User info, long hora) {
        boolean resp = false;

        if(!info.getBloqueado()) {
            setTempoBloqueado(hora);
            info.setBloqueado(true);
            resp = true;
        }
        return resp;
    }

    /**
     * Verifica se um usuário está bloqueado.
     * @param info Usuário.
     * @param hora Hora da verificação.
     * @return true se está bloqueado ou false caso não..
     */
    public boolean isBloqueado(Usuario info, long hora){
        boolean resp = false;
        long aux = hora - info.getTempoBloqueado();
        if (info.getBloqueado()){
            if (aux >= BLOQUEIO){
                info.setBloqueado(false);
            }else{
                resp = true;
            }
        }

        return resp;
    }

}

