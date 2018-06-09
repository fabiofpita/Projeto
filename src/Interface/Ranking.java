package Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ranking extends JFrame implements ActionListener  {
    private JPanel painel;
    private JTable tabela;
    private JScrollPane scroll;
    private DefaultTableModel modelDep;
    private JButton buttonRankingGlobal;
    private JButton buttonRanking5;


    public Ranking() {
        criarJanela();
    }
    public void criarJanela(){
        painel = new JPanel();
        buttonRankingGlobal= new JButton("Ranking global");
        buttonRanking5 = new JButton("Top 5");
        //buttonPesquisar.addActionListener(this);

        modelDep = new DefaultTableModel();
        tabela = new JTable(modelDep);

        modelDep.addColumn("Nome");
        modelDep.addColumn("Pontos");


        tabela.setFillsViewportHeight(true);
        scroll = new JScrollPane();
        scroll.setViewportView(tabela);

        painel.add(scroll);
        painel.add(buttonRankingGlobal);
        painel.add(buttonRanking5);

        modelDep.setNumRows(0);
    }
    public JPanel getPainel(){
        return painel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
