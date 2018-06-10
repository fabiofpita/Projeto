package Interface;

import usuario.Cadastro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Principal extends JFrame{
    private JMenu menu;
    private JMenuItem itemCadastro;
    private JMenuItem itemPvP;
    private JMenuItem itemPvB;
    private JMenuItem itemRanking;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * gera os componentes visuais
     */
    private void gerar(){
        painel = new JTabbedPane();
        this.menu = new JMenu("Opções");
        this.barra = new JMenuBar();
        setJMenuBar(barra);
        this.itemCadastro = new JMenuItem("Cadastrar usuario");
        this.itemPvP = new JMenuItem("2 jogadores");
        this.itemPvB = new JMenuItem("Contra o computador");
        this.itemRanking = new JMenuItem("Ranking");


        menu.add(itemCadastro);
        menu.add(itemPvP);
        menu.add(itemPvB);
        menu.add(itemRanking);
        barra.add(menu);
        this.add(painel);

        JInternalFrame cadastro  = new JInternalFrame("Cadastro de usuários");
        cadastro.setContentPane(new TelaCadastro(banco).getPainelPrincipal());
        cadastro.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        cadastro.setVisible(true);
        cadastro.pack();
        painel.add("Cadastro",cadastro);
        painel.setMnemonicAt(0, KeyEvent.VK_1);


        JInternalFrame pvp = new JInternalFrame("LoginHVH");
        pvp.setContentPane(new LoginHVH(banco).getPainel());
        pvp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pvp.setVisible(true);
        pvp.pack();
        painel.add("Jogar 1x1",pvp);
        painel.setMnemonicAt(1, KeyEvent.VK_1);


        JInternalFrame login = new JInternalFrame("LoginHVH");
        login.setContentPane(new LoginHVH(banco).getPainel());
        login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        login.setVisible(true);
        login.pack();
        painel.add("Jogar 1x1",login);
        painel.setMnemonicAt(1, KeyEvent.VK_1);

        JInternalFrame ranking = new JInternalFrame("Ranking");
        ranking.setContentPane(new Ranking().getPainel());
        ranking.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ranking.setVisible(true);
        ranking.pack();
        painel.add("Ranking",ranking);
        painel.setMnemonicAt(2, KeyEvent.VK_1);


        JInternalFrame bot = new JInternalFrame("Bot");
        bot.setContentPane(new LoginHVB(banco).getPainel());
        bot.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bot.setVisible(true);
        bot.pack();
        painel.add("Jogar contra BOT",bot);
        painel.setMnemonicAt(2,KeyEvent.VK_1);

        JInternalFrame ranking = new JInternalFrame("Ranking");
        ranking.setContentPane(new Ranking(banco).getPainel());
        ranking.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ranking.setVisible(true);
        ranking.pack();
        painel.add("Ranking",ranking);
        painel.setMnemonicAt(3, KeyEvent.VK_1);


        itemCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setSelectedIndex(0);
            }

        });
        itemPvP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setSelectedIndex(1);
            }
        });
        itemRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setSelectedIndex(3);
            }
        });
        itemPvB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setSelectedIndex(2);
            }
        });
    }

    /**
     * Inicia a tela
     */
    private void iniciar(){
        this.setSize(560,600);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

}