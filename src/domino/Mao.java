package domino;

import java.util.ArrayList;

/**
 * Classe que representa a mão de um jogador.
 */
public class Mao {

    private ArrayList<Peca> pecas;
    private int quantia;

    /**
     * Construtor de uma mao para a alocacao de pecas.
     * @param qnt_pecas quantia de pecas que tera na mão.
     */
    public Mao(int qnt_pecas){

        if(qnt_pecas < 1 || qnt_pecas > 14)
            qnt_pecas = 7;
        quantia = qnt_pecas;

        pecas = new ArrayList<>(qnt_pecas);
    }

    /**
     * Metodo que recebe as pecas que a mão tera.
     * @param info peca a ser adicionada.
     * @return retorna True caso a peça tenha sido adicionada e False, caso contrário.
     */
    public boolean addPeca(Peca info){

        boolean status = false;

        if(pecas.size() <= quantia && info != null){
            pecas.add(info);
        }

        return status;
    }

    /**
     * Metodo devolve uma string com todas as peças da mão e suas respectivas posições.
     * @return String com todas as peças.
     */
    public String olharMao(){
        String txt = "";
        int i = 0;

        while(pecas.size() - 1 > i){
            if(pecas.get(i) != null)
                txt += " " + (i + 1) + " - " + pecas.get(i).toString();

            i++;
        }

        return txt;
    }

    /**
     * @return a quantia de peças que a mão suporta.
     */
    public int getQuantia(){
        return quantia;
    }

    /**
     * Metodo devolve a peça de uma determinada posicao da mão ou nulo.
     * @param info posição da peça na mão.
     * @return peça de uma posição ou nulo, caso a posição seja inexistente.
     */
    public Peca pegaPeca(int info){

        Peca devolv = null;

        if(info > 0 && info <= pecas.size())
            devolv = pecas.remove((info - 1));

        return devolv;
    }

}
