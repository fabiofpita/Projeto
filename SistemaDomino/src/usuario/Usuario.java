package usuario;

public class Usuario extends User {

    private String nome;
    private String email;
    private String senha;
    private long score;
    private boolean bloqueado;
    private long tempoBloqueado;
    private int vitorias;
    private int derrotas;

    /**
     * Constante que define o tempo de horas como padrão para bloqueio.
     */
    public static final long BLOQUEIO = 60*2*60000;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        vitorias = 0;
        derrotas = 0;
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

    @Override
    public void setTempoBloqueado(long tempoBloqueado) {
        this.tempoBloqueado = tempoBloqueado;
    }

    @Override
    public long getTempoBloqueado() {
        return tempoBloqueado;
    }

    @Override
    public boolean bloquearUser(long hora) {
        boolean resp = false;

        if(!getBloqueado()) {
            setTempoBloqueado(hora);
            setBloqueado(true);
            resp = true;
        }
        return resp;
    }

    @Override
    public boolean isBloqueado(long hora){
        boolean resp = false;
        long aux = hora - getTempoBloqueado();
        if (getBloqueado()){
            if (aux >= BLOQUEIO){
                setBloqueado(false);
            }else{
                resp = true;
            }
        }

        return resp;
    }

    @Override
    public int tempoBloqueio(){
        int tempo = 0;
        if(getBloqueado())
            tempo = (int) ((72000000 - (System.currentTimeMillis() - getTempoBloqueado()))/1000/60%1000*0.6);

        return tempo;
    }

}

