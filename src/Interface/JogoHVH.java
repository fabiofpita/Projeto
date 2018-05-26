package Interface;
import usuario.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;


public class JogoHVH extends JFrame implements ActionListener {

    private JLabel labelPeca;
    private JLabel labelMesa;
    private JLabel pecasJ1;
    private JLabel pecasJ2;
    private JLabel labelJogador;
    private JLabel campoMesa;
    private JLabel labelMao;
    private JLabel campoMao;
    private JButton botaoLeft;
    private JButton botaoRight;
    private JButton botaoComprar;
    private JButton botaoPassar;
    private JPanel painelFunc;
    private JPanel painelMesa;
    private JPanel painelDados;
    private JPanel painelJogador;
    private User j1;
    private User j2;

    private GridBagLayout layout;
    private GridBagConstraints constraints;





    public JogoHVH(User j1, User j2) {
        this.j1 = j1;
        this.j2 = j2;
        criarJanela();
        iniciar();

    }
    public void criarJanela(){

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        setLayout(layout);
        Border border = BorderFactory.createLineBorder(Color.GRAY);

        painelFunc = new JPanel(layout);
        painelMesa = new JPanel(layout);
        painelDados = new JPanel(layout);
        painelJogador = new JPanel(layout);
        painelJogador.setBorder(border);
        painelDados.setBorder(border);
        painelMesa.setBorder(border);
        painelFunc.setBorder(border);

        pecasJ1 = new JLabel("Peças Jogador 1: X");
        pecasJ2 = new JLabel("Peças Jogador 2: X");

        labelPeca = new JLabel("4-2");
        labelMesa = new JLabel("5-1|1-3|3-6|6-8|");
        campoMesa = new JLabel("Mesa:");
        labelMao = new JLabel("4-2 ; 3-4 ; 6-6;");
        campoMao = new JLabel("Mão do jogador");
        labelJogador = new JLabel();

        botaoLeft = new JButton(">");
        botaoRight = new JButton("<");
        botaoComprar = new JButton("Comprar");
        botaoComprar.setEnabled(false);
        botaoPassar = new JButton("Passar");
        botaoPassar.setEnabled(false);

        //buttonEmprestar.addActionListener(this);


        adicionarComponente(painelFunc, botaoComprar, 0, 1, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelFunc, botaoRight, 0, 2, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelFunc, labelPeca, 0, 3, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelFunc, botaoLeft, 0, 4, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);

        adicionarComponente(painelFunc, botaoPassar, 0, 5, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelDados,pecasJ1, 2, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelDados,pecasJ2, 2, 1, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelDados,labelJogador, 0, 0, GridBagConstraints.SOUTH, 2, 1, GridBagConstraints.NONE);

        adicionarComponente(painelMesa, campoMesa, 0, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelMesa, labelMesa, 1, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);

        adicionarComponente(painelJogador,campoMao, 0, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelJogador,labelMao, 1, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);

        adicionarComponente(this, painelFunc, 3, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionarComponente(this, painelDados, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionarComponente(this, painelMesa, 2, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionarComponente(this, painelJogador, 4, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);



    }

    private void adicionarComponente(Container panelG, JComponent component, int y, int x, int pos, int cols, int lins, int preenche){
        constraints.gridy = y;
        constraints.gridx = x;

        constraints.insets = new Insets(10,10,10,10);
        constraints.anchor = pos;

        constraints.gridwidth = cols;
        constraints.gridheight = lins;

        constraints.fill = preenche;

        component.setFont(new Font("tahoma", Font.PLAIN, 20));

        layout.setConstraints(component, constraints);
        panelG.add(component);
    }
    private void iniciar(){
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void limparCampos(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
       /* if(e.getSource()){

        }*/

       // limparCampos();
    }
}