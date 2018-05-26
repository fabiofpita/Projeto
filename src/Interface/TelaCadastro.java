package Interface;

import usuario.Cadastro;
import usuario.User;
import usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class TelaCadastro {
    private JTextField fieldEmail;
    private JTextField fieldNome;
    private JPasswordField fieldSenha;
    private JLabel labelEmail;
    private JPanel painelPrincipal;
    private JLabel labelNome;
    private JLabel labelSenha;
    private JLabel labelCadastro;
    private JButton buttonCadastro;
    private JButton buttonLimpar;
    private JPanel painelBotao;

    public TelaCadastro(Cadastro banco){

        buttonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNome.getText();
                String email= fieldEmail.getText();
                String senha = new String(fieldSenha.getPassword());
                if(banco.autenticarEmail(email)){
                    if(banco.procuraEmail(email) == null) {
                        banco.newCadastro(new Usuario(nome, email, senha));
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                        limpaCampos();
                    }else{
                        JOptionPane.showMessageDialog(null,"E-mail ja cadastrado!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"E-mail invalido!");
                }
            }
        });
        buttonLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpaCampos();
            }
        });
    }
    public JPanel getPainelPrincipal(){
        return painelPrincipal;
    }

    public void limpaCampos(){
        fieldEmail.setText("");
        fieldNome.setText("");
        fieldSenha.setText("");
    }

}
