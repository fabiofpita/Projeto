package Interface;

import usuario.Cadastro;
import usuario.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHVB{

    private JPanel principal;
    private JTextField fieldEmail;
    private JButton login;
    private JPasswordField fieldSenha;
    private JLabel labelEmail;
    private JLabel labelSenha;
    private Cadastro banco;

    public LoginHVB(Cadastro banco){
        this.banco = banco;

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = fieldEmail.getText();
                String senha = new String(fieldSenha.getPassword());
                if(login(email,senha)){
                    JOptionPane.showMessageDialog(null,"Usuario confirmado\nBoa sorte!");
                    User j1 = banco.procuraEmail(fieldEmail.getText());
                    new JogoHVB(j1);
                    banco.logoutUser(fieldEmail.getText());
                    //reinicia();
                }

            }
        });
    }

    /**
     * Limpa os campos e desloga o usuario.
     */
    public void reinicia(){
        //
        fieldEmail.setText("");
        fieldSenha.setText("");
    }

    /**
     *  pega o painel do GUI
     * @return painel
     */
    public JPanel getPainel(){
        return principal;
    }

    /**
     * Trata o login do usuario.
     * @param email
     * @param senha
     * @return
     */
    public boolean login(String email, String senha){
        boolean deuCerto = false;
        if(banco.autenticarEmail(email)){
            User user = banco.procuraEmail(email);
            if(user!= null) {
                if (banco.autenticarSenha(user, senha) && !user.isBloqueado(user.tempoBloqueio())) {
                    deuCerto = true;
                } else {
                    if (user.isBloqueado(user.tempoBloqueio())) {
                        JOptionPane.showMessageDialog(null, "Usuario bloqueado por :" + user.tempoBloqueio() + " minutos", "Dominó", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Senha Invalida!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"E-mail não cadastrado!");
            }
        }else{
            JOptionPane.showMessageDialog(null,"E-mail invalido!");
        }
        return deuCerto;
    }

}

