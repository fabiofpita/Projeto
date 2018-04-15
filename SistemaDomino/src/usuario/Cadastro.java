package usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cadastro {
    private Banco_User banco;

    public Cadastro(Banco_User banco) {
        this.banco = banco;
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
        User aux = null;
        int i = 0,dif;
        while(i<banco.getLugar()+1 && !achou){
            aux = banco.pullBanco();
            i++;
            if(aux.getEmail() == info){
                retorno = aux;
                achou = true;
            }else{
                banco.pushBanco(aux);
            }
        }
        dif = (banco.getLugar()+1- i)+1;
        while(dif>=0){
            banco.pushBanco(banco.pullBanco());
            dif--;
        }
        return retorno;
    }
}
