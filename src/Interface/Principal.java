package Interface;

import usuario.Cadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Principal extends JFrame{
    private JMenu menu;
    private JMenuItem itemCadastro;
    private JMenuItem itemLogin;
    private JMenuItem itemJogar;
    private JMenuItem itemSair;
    private JMenuBar barra;
    private Cadastro banco;
    private JTabbedPane painel;
    private int qtdPainel;

    public Principal(Cadastro banco){
        super("Menu");
        this.banco=banco;
        gerar();
        iniciar();
        qtdPainel =0;
    }
    private void gerar(){
        painel = new JTabbedPane();
        this.menu = new JMenu("Opções");
        this.barra = new JMenuBar();
        setJMenuBar(barra);
        this.itemCadastro = new JMenuItem("Cadastrar usuario");
        this.itemLogin = new JMenuItem("2 jogadores");
        this.itemJogar = new JMenuItem("Jogar");
        this.itemSair = new JMenuItem("Sair");


        menu.add(itemCadastro);
        menu.add(itemLogin);
        menu.add(itemJogar);
        menu.add(itemSair);
        barra.add(menu);
        this.add(painel);

        JInternalFrame cadastro  = new JInternalFrame("Cadastro de usuários");
        cadastro.setContentPane(new TelaCadastro(banco).getPainelPrincipal());
        cadastro.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        cadastro.pack();
        cadastro.setVisible(true);
        painel.add("Cadastro",cadastro);
        painel.setMnemonicAt(0, KeyEvent.VK_1);

        JInternalFrame login = new JInternalFrame("Login");
        login.setContentPane(new Login(banco).getPainel());
        login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        login.pack();
        login.setVisible(true);
        painel.add("Jogar 1x1",login);
        painel.setMnemonicAt(1, KeyEvent.VK_1);

        itemCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setSelectedIndex(0);
            }

        });
        itemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setSelectedIndex(1);
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
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void removeInteral(){

    }
}