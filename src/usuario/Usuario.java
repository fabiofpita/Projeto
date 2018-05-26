package usuario;

public class Usuario extends User  implements Comparable<User> {

    private String nome;
    private String email;
    private String senha;
    private int score;
    private boolean bloqueado;
    private long tempoBloqueado;
    private boolean onli;
    private int tentativas;
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
        tentativas = 0;
        bloqueado = false;
        this.tempoBloqueado = 0;
    }
    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(long info) {
        this.score += info;
    }

    @Override
    public boolean equals(User info) {
        return false;
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
    @Override
    public int getTentativas(){
        return tentativas;
    }
    @Override
    public void consomeTentativa(){
        tentativas++;
    }
    @Override
    public void setOnli(boolean estado){
        this.onli = estado;
    }

    @Override
    public boolean isOnli(){
        return onli;
    }



    /**
     * Informa a quantidade de vitórias de um usuário.
     * @return vitórias.
     */
    public int getVitorias() {
        return vitorias;
    }

    /**
     * Adiciona uma vitória ao registro do usuário.
     */
    public void setVitorias() {
        this.vitorias = vitorias+1;
    }

    /**
     * Informa a quantidade de derrotas do usuário.
     * @return derrotas;
     */
    public int getDerrotas() {
        return derrotas;
    }

    /**
     * Adiciona uma derrota ao registro do usuário.
     */
    public void setDerrotas() {
        this.derrotas = derrotas+1;
    }

}

