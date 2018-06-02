package Interface;

import usuario.Cadastro;
import usuario.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginHVH {


    private JLabel labelEmail;
    private JTextField fieldEmail1;
    private JPasswordField fieldSenha1;
    private JButton buttonLogin1;
    private JPanel painel;
    private JButton buttonPlay;
    private JTextField fieldEmail2;
    private JPasswordField fieldSenha2;
    private JButton buttonLogin2;
    private JLabel labelJ1;
    private JLabel labelJ2;
    private Cadastro banco;
    private JTabbedPane painelTabbed;

    private boolean loginJ1;
    private boolean loginJ2;

    public LoginHVH(Cadastro banco){
        this.banco = banco;
        this.loginJ1 = false;
        this.loginJ2 = false;
        this.painelTabbed = painelTabbed;
        buttonPlay.setEnabled(false);
        buttonLogin1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginJ1 = logaUser(fieldEmail1,fieldSenha1);
                confereLogados();
                if(loginJ1){
                    labelJ1.setText("<html><font color=green>Pronto!</font></html>");
                    buttonLogin1.setEnabled(false);
                }else{
                    labelJ1.setText("<html><font color=red>Pendente!</font></html>");
                }
            }
        });
        buttonLogin2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginJ2 = logaUser(fieldEmail2,fieldSenha2);
                confereLogados();
                if(loginJ2){
                    labelJ2.setText("<html><font color=green>Pronto!</font></html>");
                    buttonLogin2.setEnabled(false);
                }else{
                    labelJ2.setText("<html><font color=red>Pendente!</font></html>");
                }
            }
        });
        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User j1 = banco.procuraEmail(fieldEmail1.getText());
                User j2 = banco.procuraEmail(fieldEmail2.getText());
                JogoHVH jhvh = new JogoHVH(j1,j2);
                banco.logoutUser(fieldEmail1.getText());
                banco.logoutUser(fieldEmail2.getText());
                reinicia();


            }
        });

  }

  public JPanel getPainel(){
        return painel;
  }
  public void reinicia(){
      labelJ1.setText("<html><font color=red>Pendente!</font></html>");
      labelJ2.setText("<html><font color=red>Pendente!</font></html>");
      fieldEmail1.setText("");
      fieldEmail2.setText("");
      fieldSenha1.setText("");
      fieldSenha2.setText("");
      buttonLogin1.setEnabled(true);
      buttonLogin2.setEnabled(true);
      buttonPlay.setEnabled(false);
      loginJ1 = false;
      loginJ2 = false;
  }

    /**
     * confere se os dois jogadores estão logados para habilitar o botao de jogo
     */
  public void confereLogados(){
      if(loginJ1 && loginJ2){
          buttonPlay.setEnabled(true);

      }
  }

  public boolean logaUser(JTextField fieldEmail, JPasswordField fieldSenha){
        boolean deuCerto = false;
        String email = fieldEmail.getText();
        String senha =  new String(fieldSenha.getPassword());
        if(banco.autenticarEmail(email)){
            User user = banco.procuraEmail(email);
            if(user!= null) {
                if (banco.autenticarSenha(user, senha) && !user.isBloqueado(user.tempoBloqueio())) {
                    //JOptionPane.showMessageDialog(null,"Logado");
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