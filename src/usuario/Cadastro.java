package usuario;

import criptografia.MD5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável pelo cadastro e a autenticação dos usuários.
 */
public class Cadastro {

    private Banco_User banco;
    private int tamanho_banco;
    private MD5 criptar;

    /**
     * Construtor da classe cadastro, onde se define o tamanho do banco de usuarios.
     * @param info define a quantia max de usuarios que o banco comportarám, caso a quantia seja menor que 1
     *             o valor padrão será 100.
     */
    public Cadastro(int info) {

        if(info < 1)
            info = 100;

        tamanho_banco = info;
        banco = new Banco_User();
        criptar = new MD5();
    }

    /**
     * Método responsável por cadastrar um usuario e criptografar sua senha.
     * @param info usuario a ser cadastrado.
     * @return devolve true caso tenha sido cadastrado com sucesso, caso contrário devolverá false.
     */
    public boolean newCadastro(User info){

        boolean deuCerto = false;

        if(!banco.bancoIsFull()){
            info.setSenha(criptar.gerarMD5(info.getSenha()));
            banco.pushBanco(info);
            deuCerto = true;
        }

        return deuCerto;
    }

    /**
     *  O metodo autenticarEmail recebe uma string e confere se conteudo digitado tem um formato de e-mail valido.
     *
     * @param info String que vai ser conferida.
     * @return retorna true se o e-mail for valido e false se o e-mail for invalido.
     */
    public boolean autenticarEmail(String info){
        boolean confere;
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(info);
        confere = m.matches();
        return confere;
    }
    /**
     *  O método procuraEmail recebe uma string que verifica se o e-email existe no banco de usuarios.
     *  qtdUser é a quantidade de usuarios cadastrados no banco.
     *  vetorUser é o vetor de usuarios cadastrados.
     * @param info e-mail a ser conferido
     * @return caso o usuario exista retorna o usuario caso contrario retorna null.
     */
    public User procuraEmail(String info){
        User retorno = null;
        boolean achou = false;
        int i = 0;

        while(i < banco.getSize() && !achou){

            if(banco.mostrarUsuario(i).getEmail().equals(info)){
                retorno = banco.mostrarUsuario(i);
                achou = true;
            }
            i++;
        }

        return retorno;
    }

    /**
     * Método retorna o banco de usuários para sua edição.
     * @return banco de usuarios.
     */
    public Banco_User getBanco() {
        return banco;
    }

    /**
     * Método responsável por verificar se a senha é correspondente a do usuario.
     * @param info usuario a se verificar a senha.
     * @param senha senha para se verificar.
     * @return true caso a senha seja a mesma e false caso contrário.
     */
    public boolean autenticarSenha(User info, String senha){


        boolean confirma;

        if(info.getSenha().equals(criptar.gerarMD5(senha)) && !info.isOnli()) {
            confirma = true;
            User user = procuraEmail(info.getEmail());
            user.setOnli(true);
        }else{
            info.consomeTentativa();
            if(info.getTentativas() == 3){
                info.bloquearUser(System.currentTimeMillis());
            }
            confirma = false;
        }
        return confirma;
    }
}
