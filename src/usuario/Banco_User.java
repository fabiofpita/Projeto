package usuario;

/**
 * Classe responsável por armazenar os usuarios.
 */
public class Banco_User {

    protected int tamanhobanco;
    private User[] banco;
    private int lugar;

    /**
     * Construtor do banco de usuarios.
     * @param tamanhobanco quantia MAX de usuarios do jogo, caso o valor seja menor que 1 o novo valor será 10;
     */
    public Banco_User(int tamanhobanco){

        if(tamanhobanco < 1)
            tamanhobanco = 10;

        this.tamanhobanco = tamanhobanco;
        banco = new User[this.tamanhobanco];
        lugar = -1;
    }

    /**
     * Metodo coloca um usuario no fim do banco.
     * @param info usuario a ser adcionado.
     */
    public void pushBanco(User info){

        if(!bancoIsFull()){
            lugar ++;
            banco[lugar] = info;
        }
    }

    /**
     * @param info Mostra o usuario do banco na posicao "INFO" informada.
     * @return Retorna o usuario ou null, caso este não exista.
     */
    public User mostrarUsuario(int info){

        User usuario = null;

        if(!bancoIsEmpyt() && info > -1 && info < tamanhobanco){
            usuario = banco[info];
        }

        return usuario;
    }

    /**
     * @return Retorna o primeiro usuario do banco.
     */
    public User pullBanco(){

        User usuario = null;

        if(!bancoIsEmpyt()){
            usuario = banco[0];
            moverbanco();
            lugar --;
        }

        return usuario;

    }

    /**
     * Metodo reorganiza os usuarios no banco para não ocorrer erros.
     */
    private void moverbanco(){

        int i = 0;

        if(!bancoIsEmpyt()){

            while(i < lugar){
                banco[i] = banco[i+1];
                i++;
            }

        }
    }

    /**
     * Metodo informa se o banco está vazio.
     * @return TRUE se estiver sem usuarios e FALSE caso tenha ao menos um usuario.
     */
    public boolean bancoIsEmpyt(){

        boolean resp = true;

        if(lugar > -1)
            resp = false;

        return resp;
    }

    /**
     * Metodo informa se o banco está cheio.
     * @return TRUE se estiver cheio e FALSE caso não esteja cheio.
     */
    public boolean bancoIsFull(){

        boolean resp = true;

        if(lugar < this.tamanhobanco - 1)
            resp = false;

        return resp;
    }
    /**
     * retorna o lugar do ultimo usuario colocado no banco.
     * @return ultima posição
     */
    public int getLugar(){
        return lugar;
    }

    /**
     * FINALIDADE DE TESTE ORDENAÇÃO DO BANCO EM DECRESCENTE
     */
    public void ordena(){
        for(int i=0;i<lugar;i++){
            for(int j = i; j<lugar+1;j++){
                if(banco[i] != null && banco[j] != null)
                if(banco[i].getScore() < banco[j].getScore()){
                    User aux = banco[j];
                    banco[j]= banco[i];
                    banco[i] = aux;
                }
            }
        }
    }
}