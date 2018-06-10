package Interface;

import computador.PCEasy;
import domino.Mao;
import domino.Mesa;
import usuario.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JogoHVB extends JFrame implements ActionListener {

    private JLabel labelPeca;
    private JLabel labelMesa;
    private JLabel pecasJ1;
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
    private User jogador;
    private List<Mao> maos;
    private int escolha;
    private int vez;
    private PCEasy mente;
    private boolean venceu;
    private Mesa jogo;

    private GridBagLayout layout;
    private GridBagConstraints constraints;


    public JogoHVB(User j1) {

        mente = new PCEasy();

        jogador = j1;

        jogo = new Mesa();

        maos = new ArrayList();

        maos.add(new Mao(7));
        maos.add(new Mao(7));
        maos.add(new Mao(7));
        maos.add(new Mao(7));
        venceu = false;
        jogo.distribuirPecas(maos);
        criarJanela();
        iniciar();
        escolha = 0;
        vez = jogo.vez();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        atualizaJanela();
        atualizaBotoes();

        if(vez > 0)
            jogaBots();
    }

    /**
     * Este método serve para instanciar os atributos que compoem a tela vizual.
     */
    public void criarJanela() {

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

        pecasJ1 = new JLabel("Peças Jogador: " + jogador.getNome());


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
        botaoPassar = new JButton("Passar/Jogar");
        botaoPassar.setEnabled(true);
        botaoLeft.addActionListener(this);
        botaoRight.addActionListener(this);
        botaoComprar.addActionListener(this);
        botaoPassar.addActionListener(this);

        //buttonEmprestar.addActionListener(this);


        adicionarComponente(painelFunc, botaoComprar, 0, 1, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelFunc, botaoRight, 0, 2, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelFunc, labelPeca, 0, 3, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelFunc, botaoLeft, 0, 4, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);

        adicionarComponente(painelFunc, botaoPassar, 0, 5, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelDados, pecasJ1, 2, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelDados, labelJogador, 0, 0, GridBagConstraints.SOUTH, 2, 1, GridBagConstraints.NONE);

        adicionarComponente(painelMesa, campoMesa, 0, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelMesa, labelMesa, 1, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);

        adicionarComponente(painelJogador, campoMao, 0, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painelJogador, labelMao, 1, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);

        adicionarComponente(this, painelFunc, 3, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionarComponente(this, painelDados, 1, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionarComponente(this, painelMesa, 2, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);
        adicionarComponente(this, painelJogador, 4, 0, GridBagConstraints.CENTER, 1, 1, GridBagConstraints.BOTH);

        atualizaJanela();
        atualizaBotoes();

    }

    /**
     * Atualiza os componentes conforme as jogadas
     */
    private void atualizaJanela() {

        if (escolha >= maos.get(vez).size())
            escolha = maos.get(vez).size() - 1;

        if(vez != 0)
            campoMao.setText("Vez do jogador: Caio Bot " + vez);
        else
            campoMao.setText("Vez do jogador: " + jogador.getNome());
        labelPeca.setText(maos.get(0).olhaPeca(escolha));
        labelMesa.setText(jogo.toString());
        labelMao.setText(maos.get(0).olharMao());

    }

    /**
     * Adiciona componentes em forma ordenada no painel conforme uma estrutura de plano cartesiano.
     *
     * @param panelG    painel "pai" a ser inserido.
     * @param component componente a ser inserido no painel
     * @param y         eixo y no plano
     * @param x         representa o eixo x no plano.
     * @param pos       representa a posicao
     * @param cols      numero de colunas a ser ocupado
     * @param lins      numero de linhas a ser ocupado
     * @param preenche  a posicao a ser preenchida.
     */
    private void adicionarComponente(Container panelG, JComponent component, int y, int x, int pos, int cols, int lins, int preenche) {
        constraints.gridy = y;
        constraints.gridx = x;

        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = pos;

        constraints.gridwidth = cols;
        constraints.gridheight = lins;

        constraints.fill = preenche;

        component.setFont(new Font("tahoma", Font.PLAIN, 20));

        layout.setConstraints(component, constraints);
        panelG.add(component);
    }

    /**
     * iniciar os parametros para o jframe
     */
    private void iniciar() {
        this.pack();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Eventos dos botões
     *
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == botaoLeft) {
            escolha++;
        } else {
            if (actionEvent.getSource() == botaoRight) {
                escolha--;
            } else {
                if (actionEvent.getSource() == botaoPassar) {

                    if (maos.get(0).fazJogada(maos.get(0).copiaPeca(escolha), jogo) == 0) {
                        maos.get(vez).pegaPeca(escolha);
                        atualizaJanela();

                        if (jogo.acabou(maos.get(vez))) {
                            JOptionPane.showMessageDialog(null, jogador.getNome() + " VENCEU!!");
                            jogador.setScore(1);
                            venceu = true;
                            //FINALIZA O GAME
                            dispose();
                        } else {
                            if (jogo.taFechado()) {
                                vez = jogo.fechou(maos);

                                if(vez > 0){
                                    JOptionPane.showMessageDialog(null, "Caio Bot " + vez + " VENCEU!!\n Com " + maos.get(vez).pontoTotal() + " pontos");
                                }else{
                                    JOptionPane.showMessageDialog(null, jogador.getNome() + " VENCEU!!\n Com " + maos.get(0).pontoTotal() + " pontos");
                                    jogador.setScore(1);
                                }
                                venceu = true;
                                //FINALIZA O GAME
                                dispose();
                            }
                        }

                        if (vez < maos.size() - 1) {
                            vez++;
                        } else {
                            vez = 0;
                        }
                        escolha = 0;
                        atualizaJanela();
                        if(!venceu)
                            jogaBots();

                    }else {
                        if(!maos.get(vez).podeJogar(jogo) && !jogo.temCompra()){
                            if (vez < maos.size() - 1) {
                                vez++;
                            } else {
                                vez = 0;
                            }

                            escolha = 0;
                            atualizaJanela();
                            jogaBots();
                        }else{
                            JOptionPane.showMessageDialog(null, "Jogada inválida!");
                        }
                    }

                } else {
                    if (actionEvent.getSource() == botaoComprar) {
                        maos.get(vez).addPeca(jogo.comprar());
                    }
                }
            }
        }

        atualizaJanela();
        atualizaBotoes();

    }

    private void jogaBots(){
        int cont = 0;
        while(cont < 2){
            try {
                Thread.sleep(500);
                }catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            cont ++;
        }


        while(vez != 0 && !venceu){

            mente.jogadaAutomatica(maos.get(vez).getMao(), jogo);
            atualizaJanela();
            if (jogo.acabou(maos.get(vez))) {
                JOptionPane.showMessageDialog(null, "Caio Bot " + vez + " VENCEU!!");
                venceu = true;
                //FINALIZA O GAME
                dispose();
            } else {
                if (jogo.taFechado()) {
                    vez = jogo.fechou(maos);

                    if (vez > 0) {
                        JOptionPane.showMessageDialog(null, "Computador " + vez + " VENCEU!!\n Com " + maos.get(0).pontoTotal() + " pontos");
                    } else {
                        JOptionPane.showMessageDialog(null, jogador.getNome() + " VENCEU!!\n Com " + maos.get(0).pontoTotal() + " pontos");
                        jogador.setScore(1);
                    }
                    venceu = true;
                    //FINALIZA O GAME
                    dispose();
                }
            }

            if(!venceu)
                if (vez < maos.size() - 1) {
                    vez++;
                } else {
                    vez = 0;
                }

            escolha = 0;
            atualizaJanela();

        }
    }

    /**
     * atualiza os textos dos botões
     */
    private void atualizaBotoes() {

        if (escolha == maos.get(0).size() - 1) {
            escolha = maos.get(0).size() - 1;
            botaoLeft.setEnabled(false);
        } else {
            botaoLeft.setEnabled(true);
        }

        if (escolha <= 0) {
            escolha = 0;
            botaoRight.setEnabled(false);
        } else {
            botaoRight.setEnabled(true);
        }

    }
}

