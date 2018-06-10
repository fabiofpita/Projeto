package usuario;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    /**
     * Inicializa o banco para a memória.
     * @return Lista de usuários.
     */
    public static List<User> iniciaBanco(){
        String[] conteudo;
        List<User> retorno = null;
        try {
            FileReader arq = new FileReader("data.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha;
            try {
                retorno = new ArrayList<>();
                linha = lerArq.readLine();
                while (linha!=null){
                    conteudo = linha.split(";");
                    retorno.add(new Usuario(conteudo[0],conteudo[1], conteudo[2], conteudo[3], conteudo[4]));
                    linha = lerArq.readLine();
                }

                arq.close();

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");

        }
        return retorno;
    }

    /**
     * Faz a atualizações no banco de dados.
     * @param banco Banco.
     */
    public static void atualizaBanco(List<User> banco){
        boolean resp = false;
        int line=0;

        try{
            FileWriter fileWriter = new FileWriter("data.txt", false);
            BufferedWriter printWriter = new BufferedWriter(fileWriter);
            StringBuilder stringBuilder = new StringBuilder();
            String [] linha;

            for(int i=0; i<banco.size(); i++) {
                stringBuilder.append(banco.get(i).getEmail()+";"+banco.get(i).getNome()+";"+banco.get(i).getSenha()+";"+
                        banco.get(i).getScore()+";"+banco.get(i).getTempoBloqueado());
                stringBuilder.append("\n");
            }

            printWriter.write(stringBuilder.toString());

            printWriter.close();

        }catch (IOException e){
            JOptionPane.showMessageDialog(null, e, "Domino", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Verifica se o arquivo se esta vazio.
     * @return true caso esteja.
     */
    public static boolean verificaArquivo(){
        boolean resp = false;


        try {
            FileReader arq = new FileReader("data.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha;
            try {
                linha = lerArq.readLine();

                if (linha!=null){
                    resp = true;
                }

                arq.close();

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");

        }

        return resp;
    }

}


