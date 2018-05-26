package rank;

import usuario.Banco_User;
import usuario.User;

public class Ranking {

    private Banco_User banco;

    /**
     * inicia o rankin guardando o banco de usuarios a ser utilizado.
     * @param banco
     */
    public Ranking(Banco_User banco) {
        this.banco = banco;
        this.banco.ordena();
    }


    /**
     *  O método busca os 5 primeiros jogadores do ranking global
     * @return uma string que contem todos os top jogadores classificados, caso não haja 5 jogadores é listado apenas os que estão registrados
     */
    public String getRankingTop5(){
        StringBuilder sb = new StringBuilder();
        User aux = null;
        int limite = banco.getSize();
        int i = 0,dif;
        this.banco.ordena();

        sb.append("Ranking Top 5:\n");

        while(i < 5 && i < limite ){
            aux = banco.mostrarUsuario(i);
            if(aux != null){
                sb.append("-------------\n");
                sb.append("Usuario: ");
                sb.append(aux.getNome());
                sb.append("\nScore:");
                sb.append(aux.getScore());
                sb.append("\n");
            }
            i++;
//            banco.pushBanco(aux);
        }

//        dif = (banco.getSize() - i)+1;

//        while(dif>=0){
//            banco.pushBanco(banco.pullBanco());
//            dif--;
//        }

        return sb.toString();
    }

    /** O método getRankingGlobal pega todos os jogadores cadastraos e os lista em uma string informano o
     * nome e o score;
     *
     * @return retorna uma string com todos os jogadores, caso esteja vazio retorna uma mensagem informando que não há
     * usuarios cadastrados.
     */
    public String getRankingGlobal(){
        StringBuilder sb = new StringBuilder();
        User aux = null;
        int limite = banco.getSize();
        int i = 0;
        this.banco.ordena();
        System.out.println("SIZEEEEEEEE " + banco.getSize());
        if(!banco.bancoIsEmpyt()) {
            sb.append("Ranking global:\n");
            while (i < limite) {
                aux = banco.mostrarUsuario(i);
                if (aux != null) {
                    sb.append(i + "-------------\n");
                    sb.append("Usuario: ");
                    sb.append(aux.getNome());
                    sb.append("\nScore:");
                    sb.append(aux.getScore());
                    sb.append("\n");
                }
                i++;
//                banco.pushBanco(aux);
            }
        }else{
            sb.append("Nenhum jogador registrado");
        }

        return sb.toString();
    }
}
