package usuario;

public class Usuario extends User{

    private String nome;
    private String email;
    private String senha;
    private long score;
    private boolean bloqueado;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.score = 0;
        bloqueado = false;
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
}

