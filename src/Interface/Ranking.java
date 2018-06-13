package Interface;

import usuario.Cadastro;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ranking extends JFrame implements ActionListener {
    private JPanel painel;
    private JTable tabela;
    private JScrollPane scroll;
    private DefaultTableModel modelDep;
    private JButton buttonRankingGlobal;
    private JButton buttonRanking5;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private Cadastro banco;

    public Ranking(Cadastro banco) {
        this.banco = banco;
        criarJanela();
        banco.getBanco().ordena();
    }
    public void criarJanela(){
        banco.getBanco().ordena();
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        setLayout(layout);
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        painel = new JPanel();
        buttonRankingGlobal= new JButton("Ranking global");
        buttonRanking5 = new JButton("Top 5");
        buttonRankingGlobal.addActionListener(this);
        buttonRanking5.addActionListener(this);

        modelDep = new DefaultTableModel();

        tabela = new JTable(modelDep);

        modelDep.addColumn("Nome");
        modelDep.addColumn("Pontos");


        tabela.setFillsViewportHeight(true);
        scroll = new JScrollPane();
        scroll.setViewportView(tabela);


        adicionarComponente(painel,scroll, 0, 0, GridBagConstraints.SOUTH, 1, 2, GridBagConstraints.NONE);
        adicionarComponente(painel,buttonRanking5, 2, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(painel,buttonRankingGlobal, 3, 0, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);
        adicionarComponente(this, painel, 0, 1, GridBagConstraints.SOUTH, 1, 1, GridBagConstraints.NONE);

        modelDep.setNumRows(0);

    }

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
    public JPanel getPainel(){
        return painel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        banco.getBanco().ordena();

        if(e.getSource() == buttonRanking5){
            modelDep.setNumRows(0);
            for(int i =0; i < 5; i++){
                if(banco.getBanco().getSize() >i) {
                    Object[] linha = new Object[]{banco.getBanco().mostrarUsuario(i).getNome(),
                            banco.getBanco().mostrarUsuario(i).getScore()};

                    modelDep.addRow(linha);
                }
            }
        }else{
            if(e.getSource() == buttonRankingGlobal){
                modelDep.setNumRows(0);
                for(int i =0; i < banco.getBanco().getSize(); i++){
                    Object[] linha = new Object[]{banco.getBanco().mostrarUsuario(i).getNome(),
                            banco.getBanco().mostrarUsuario(i).getScore()};

                    modelDep.addRow(linha);
                }
            }
        }

    }

    /**
     * Atualiza tela.
     */
    public void atualiza(){
        modelDep.setNumRows(0);
        for(int i =0; i < 5; i++){
            if(banco.getBanco().getSize() >i) {
                Object[] linha = new Object[]{banco.getBanco().mostrarUsuario(i).getNome(),
                        banco.getBanco().mostrarUsuario(i).getScore()};

                modelDep.addRow(linha);
            }
        }
    }
}
