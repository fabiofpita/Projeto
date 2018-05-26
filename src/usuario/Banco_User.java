package usuario;

import java.util.*;

/**
 * Classe responsável por armazenar os usuarios.
 */
public class Banco_User {

    private List<User> banco;

    /**
     * Construtor do banco de usuarios.
     */
    public Banco_User(){

        banco = new ArrayList<>();
    }

    /**
     * Metodo coloca um usuario no fim do banco.
     * @param info usuario a ser adcionado.
     */
    public void pushBanco(User info){

        if(info != null)
            banco.add(info);
    }

    /**
     * @param info Mostra o usuario do banco na posicao "INFO" informada.
     * @return Retorna o usuario ou null, caso este não exista.
     */
    public User mostrarUsuario(int info){

        User usuario;

        try{
            usuario = banco.get(info);
        }catch (NullPointerException e){
            throw new NullPointerException("Valor inexistente. Size " + banco.size() + ", Info " + info);
        }

        return usuario;
    }

    /**
     * @return Retorna o primeiro usuario do banco.
     */
    public User pullBanco(){

        User usuario;

        try{
            usuario = banco.get(0);
        }catch (NullPointerException e){
            throw new NullPointerException("Valor informado é inexistente. Size " + banco.size());
        }

        return usuario;

    }

    /**
     * Metodo informa se o banco está vazio.
     * @return TRUE se estiver sem usuarios e FALSE caso tenha ao menos um usuario.
     */
    public boolean bancoIsEmpyt(){
        return banco.isEmpty();
    }

    /**
     * Metodo informa se o banco está cheio.
     * @return TRUE se estiver cheio e FALSE caso não esteja cheio.
     */
    public boolean bancoIsFull(){
        return false;
    }

    /**
     * FINALIDADE DE TESTE ORDENAÇÃO DO BANCO EM DECRESCENTE
     */
    public void ordena(){

        Collections.sort(banco);

    }

    /**
     * @return Retorna a quantia de usuarios no banco.
     */
    public int getSize(){
        return banco.size();
    }

}