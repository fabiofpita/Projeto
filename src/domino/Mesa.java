package domino;

import java.util.ArrayList;
import java.util.Random;

// FALTA MÉTODOS A SEREM CONSTRUIDOS NESTA CLASSE

/**
 * Classe representa uma mesa com as peças do jogo e o seu gerenciamento.
 */
public class Mesa {

    private ArrayList<Peca> mesa;

    /**
     * Construtor de uma mesa com pecas de dominó.
     */
    public Mesa(){
        mesa = new ArrayList<>(28);
        gerarPecas();
    }

    /**
     * Metodo distribui as peças aleatoriamente para um jogador.
     * @param jogador jogador que recebera as peças.
     * @return true se foi distribuido as peças corretamente e false, caso contrário.
     */
    public boolean distribuirPecas(Mao jogador){
        boolean deu = false;
        Random diller = new Random();
        int i = 0;

        if(jogador != null){

            while(jogador.getQuantia() >= i){
                jogador.addPeca(mesa.remove(diller.nextInt(mesa.size())));
                i++;
            }

            deu = true;

        }

        return deu;
    }

    /**
     * Método gera todas as peças e as coloca na mesa.
     */
    private void gerarPecas(){
        int esquerdo = 0;
        int direito = 0;

        boolean concluido = false;

        while(!concluido){
            mesa.add(new Peca(esquerdo, direito));

            if(direito == 6){
                esquerdo++;
                direito = esquerdo;
            }else{
                direito ++;
            }

            if(esquerdo == 7)
                concluido = true;
        }
    }

    /**
     * Método reescreve o método toString para mostrar a mesa com suas peças.
     * @return uma String com as peças.
     */
    @Override
    public String toString(){
        String txt = "";
        int i = 0;

        while(i < mesa.size()){

            if(mesa.get(i) != null)
                txt += mesa.get(i).toString();

            i++;
        }

        return txt;
    }
}
