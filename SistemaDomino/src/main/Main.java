package main;

import rank.Ranking;
import usuario.Cadastro;
import usuario.User;
import usuario.Usuario;
import javax.swing.*;

public class Main {

    static String TITULO = "Dominó";
    static int TAMANHO_MAX = 300;

    public static void main(String[] args) {

        User usuario;
        Cadastro cadastro = new Cadastro(TAMANHO_MAX);
        Main main = new Main();

        boolean confirm = false;

        int escolhas;
        int tentativas;

        String email;
        String senha;

        do{

            escolhas = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Cadastro\n2 - Login\n0 - Sair", TITULO, JOptionPane.INFORMATION_MESSAGE));

            /** Cadastro **/

            while(escolhas == 1){

                email = JOptionPane.showInputDialog(null, "Digite o e-mail a ser cadastrado:", TITULO, JOptionPane.INFORMATION_MESSAGE);
                if(email != null)
                    if(cadastro.autenticarEmail(email)){

                        if(cadastro.procuraEmail(email) == null){

                            usuario = main.readUsuario(email);
                            if(cadastro.newCadastro(usuario))
                                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!", TITULO, JOptionPane.INFORMATION_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar-se, favor refazer a operação!", TITULO, JOptionPane.INFORMATION_MESSAGE);

                        }else{
                            JOptionPane.showMessageDialog(null, "E-mail já cadastrado!", TITULO, JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "E-mail inválido!", TITULO, JOptionPane.INFORMATION_MESSAGE);
                    }
                escolhas = -1;
            }

            /** Login  **/

            while(escolhas == 2){

                tentativas = 0;
                confirm = false;
                email = JOptionPane.showInputDialog(null, "Digite o e-mail para login:", TITULO, JOptionPane.INFORMATION_MESSAGE);
                usuario = cadastro.procuraEmail(email);

                if(usuario != null){

                    if(!usuario.isBloqueado(System.currentTimeMillis())){
                        while (tentativas < 3 && !confirm) {

                            senha = JOptionPane.showInputDialog(null, "Tentativa " + (tentativas + 1) + " de 3\n\nSenha:", TITULO, JOptionPane.INFORMATION_MESSAGE);
                            if (cadastro.autenticarSenha(usuario, senha)) {
                                confirm = true;
                                JOptionPane.showMessageDialog(null, "Olá, " + usuario.getNome(), TITULO, JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                tentativas++;
                            }
                        }

                        if (!confirm) {
                            if (usuario.bloquearUser(System.currentTimeMillis()))
                                JOptionPane.showMessageDialog(null, "Usuario bloqueado por 2hrs\n\n" + usuario.getEmail(), TITULO, JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Usuario bloqueado por " + usuario.tempoBloqueio() +" minutos", TITULO, JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "E-mail não encontrado!", TITULO, JOptionPane.INFORMATION_MESSAGE);
                }

                escolhas = -1;
            }

            while(confirm){
                escolhas = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - ?\n2 - logout", TITULO, JOptionPane.INFORMATION_MESSAGE));

                if(escolhas == 2)
                    confirm = false;
            }

        }while(escolhas != 0);
        /*
        -----------------------------------------------------------------TESTES CAIO-------------------------------------------------
        User user = new Usuario("python","caio@g.com","caio");
        User user2 = new Usuario(">","caio@g2.com","caio");
        User user3 = new Usuario("all","caiwo@gq.com","caio");
        User user4 = new Usuario("alle","caaio@gq.com","caio");
        User user5 = new Usuario("alal","caeio@gq.com","caio");
        User user6 = new Usuario("allf","cio@gq.com","caio");
        User user7 = new Usuario("bartijano","cio@gq.com","caio");
        user.setScore(4);
        user2.setScore(1);
        user3.setScore(7);
        cadastro.newCadastro(user);
        cadastro.newCadastro(user2);
        cadastro.newCadastro(user3);
        cadastro.newCadastro(user4);
        cadastro.newCadastro(user5);
        cadastro.newCadastro(user6);
        cadastro.newCadastro(user7);
        Ranking rank = new Ranking(cadastro.getBanco());
        //System.out.println(rank.getRankingGlobal());
         System.out.println(rank.getRankingTop5());
        System.out.println(rank.getRankingGlobal());
        */
    }

    public static Usuario readUsuario(String email){

        String nome, senha;

        nome = JOptionPane.showInputDialog(null, "Nome: ", TITULO, JOptionPane.INFORMATION_MESSAGE);
        senha = JOptionPane.showInputDialog(null, "Senha: ", TITULO, JOptionPane.INFORMATION_MESSAGE);

        return new Usuario(nome, email, senha);
    }

}
