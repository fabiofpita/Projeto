package domino;

import static main.Main.*;

/**
 * Classe responsável por gerenciar uma peça de dominó.
 */
public class Peca {

    private int esquerdo;
    private int direito;
    private boolean isBucha;

    /**
     * Construtor de uma peça de dominó.
     * @param esquerdo valor a ser adcionado do lado esquerdo da peça.
     * @param direito valor a ser adcionado do lado direito da peça.
     */
    public Peca(int esquerdo, int direito){

        if(esquerdo < 0 || esquerdo > 6)
            esquerdo = 1;

        if(direito < 0 || direito > 6)
            direito = 1;

        if(esquerdo == direito)
            isBucha = true;
        else
            isBucha = false;

        setEsquerdo(esquerdo);
        setDireito(direito);
    }

    /**
     * @return Devolve o peso da peça.
     */
    public int pesoPeça(){
        return direito + esquerdo;
    }

    /**
     * Metodo retorna que a peca é bucha ou não.
     * @return se retornar True é bucha e False, caso contrário.
     */
    public boolean isBucha(){
        return isBucha;
    }

    /**
     * Método informa se existe o valor na peça.
     * @param info valor a ser comparado.
     * @return Devolve True(1) caso o valor exista na peça e False(0) caso não exista.
     */
    public boolean existeValor(int info) {

        boolean tem = false;

        if(esquerdo == info || direito == info)
            tem = true;

        return tem;
    }

    /**
     * Metodo altera o valor do lado esquerdo da peça.
     * @param esquerdo valor a ser colocado na peça.
     */
    private void setEsquerdo(int esquerdo) {
        this.esquerdo = esquerdo;
    }

    /**
     * Metodo devolve o valor do lado direito da peça.
     * @return valor inteiro do lado direito.
     */
    public int getDireito() {
        return direito;
    }

    /**
     * Metodo devolve o valor do lado esquerdo da peça.
     * @return valor inteiro do lado esquerdo.
     */
    public int getEsquerdo() {
        return esquerdo;
    }

    /**
     * Metodo altera o valor do lado direito da peça.
     * @param direito valor a ser colocado na peça.
     */
    private void setDireito(int direito) {
        this.direito = direito;
    }

    /**
     * Método arruma a peça.
     * @param lado Lado que o valor deve estar.
     * @param valor Valor que deve ser realocado, caso precise.
     */
    public void arrumarPeca(int lado, int valor){

        int troca;

        if(ESQUERDO == lado){
            if(valor == direito){
                troca = esquerdo;
                esquerdo = direito;
                direito = troca;
            }
        }else{
            if(valor == esquerdo){
                troca = esquerdo;
                esquerdo = direito;
                direito = troca;
            }
        }
    }

    /**
     * Funcao reescreve o método toString para que se adeque ao jogo.
     * @return uma String representando uma peca.
     */
    @Override
    public String toString(){
        String txt;
        txt = "[" + esquerdo + "|" + direito + "]";
        return txt;
    }
}
