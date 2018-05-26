package Interface;

import usuario.Cadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JMenu menu;
    private JMenuItem itemCadastro;
    private JMenuItem itemLogin;
    private JMenuItem itemJogar;
    private JMenuItem itemSair;
    private JMenuBar barra;
    private Cadastro banco;

    public Principal(Cadastro banco){
        super("Menu");
        this.banco=banco;
        gerar();
        iniciar();

    }
    private void gerar(){
        this.menu = new JMenu("Opções");
        this.barra = new JMenuBar();
        setJMenuBar(barra);
        this.itemCadastro = new JMenuItem("Cadastrar usuario");
        this.itemLogin = new JMenuItem("Login");
        this.itemJogar = new JMenuItem("Jogar");
        this.itemSair = new JMenuItem("Sair");


        menu.add(itemCadastro);
        menu.add(itemLogin);
        menu.add(itemJogar);
        menu.add(itemSair);
        barra.add(menu);
        itemCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cadastro  = new JFrame("Cadastro de usuários");
                cadastro.setContentPane(new TelaCadastro(banco).getPainelPrincipal());
                cadastro.setLocationRelativeTo(null);
                cadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cadastro.pack();
                cadastro.setVisible(true);
            }
        });
        itemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame login = new JFrame("Login");
                login.setContentPane(new Login(banco).getPainel());
                login.setLocationRelativeTo(null);
                login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                login.pack();
                login.setVisible(true);
            }
        });
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        itemJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JogoHVH jhvh = new JogoHVH();
            }
        });
    }
    private void iniciar(){
        this.setSize(900,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void ajustaFundo(String arquivo) {
        JLabel fundo = new JLabel(new ImageIcon(arquivo));
        fundo.setLayout(new FlowLayout());
        setContentPane(fundo);
    }




}
