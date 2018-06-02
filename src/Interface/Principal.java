package Interface;

import usuario.Cadastro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Principal extends JFrame{
    private JMenu menu;
    private JMenuItem itemCadastro;
    private JMenuItem itemLogin;
    private JMenuItem itemJogar;
    private JMenuItem itemRanking;
    private JMenuItem itemJvsBot;
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
        this.itemJogar = new JMenuItem("Contra o computador");
        this.itemRanking = new JMenuItem("Ranking");
        this.itemJvsBot = new JMenuItem("Jogar contra BOT");

        menu.add(itemCadastro);
        menu.add(itemLogin);
        menu.add(itemJogar);
        menu.add(itemRanking);
        menu.add(itemJvsBot);
        barra.add(menu);
        this.add(painel);

        JInternalFrame cadastro  = new JInternalFrame("Cadastro de usuários");
        cadastro.setContentPane(new TelaCadastro(banco).getPainelPrincipal());
        cadastro.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        cadastro.pack();
        cadastro.setVisible(true);
        painel.add("Cadastro",cadastro);
        painel.setMnemonicAt(0, KeyEvent.VK_1);

        JInternalFrame login = new JInternalFrame("LoginHVH");
        login.setContentPane(new LoginHVH(banco).getPainel());
        login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        login.pack();
        login.setVisible(true);
        painel.add("Jogar 1x1",login);
        painel.setMnemonicAt(1, KeyEvent.VK_1);

        JInternalFrame ranking = new JInternalFrame("Ranking");
        ranking.setContentPane(new Ranking().getPainel());
        ranking.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ranking.pack();
        painel.add("Ranking",ranking);
        painel.setMnemonicAt(2, KeyEvent.VK_1);

        JInternalFrame bot = new JInternalFrame();
        bot.setContentPane(new LoginHVB(banco).getPainel());
        bot.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bot.pack();
        painel.add("Jogar contra BOT",bot);
        painel.setMnemonicAt(3,KeyEvent.VK_1);

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
        itemRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setSelectedIndex(2);
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