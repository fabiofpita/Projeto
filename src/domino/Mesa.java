package domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// FALTA MÉTODOS A SEREM CONSTRUIDOS NESTA CLASSE

/**
 * Classe representa uma mesa com as peças do jogo e o seu gerenciamento.
 */
public class Mesa {

    private ArrayList<Peca> compra;
    private ArrayList<Peca> jogo;
    private int comeca;

    /**
     * Construtor de uma mesa com pecas de dominó.
     */
    public Mesa(){
        compra = new ArrayList<>(28);
        jogo = new ArrayList<>();
        gerarPecas();
        comeca = 0;
    }

    /**
     * Metodo faz a compra de uma peça.
     * @return Uma peça da mesa.
     */
    public Peca comprar(){
        Peca compra = null;
        Random dealer = new Random();

        if(this.compra.size() > 0)
            compra = this.compra.remove(dealer.nextInt(this.compra.size()));

        return compra;
    }

    /**
     * Metodo distribui as peças aleatoriamente para um jogador.
     * @param jogadores List de jogadores que receberão as peças.
     * @return true se foi distribuido as peças corretamente e false, caso contrário.
     */
    public boolean distribuirPecas(List<Mao> jogadores){
        boolean deu = false;
        int i = 0;
        int roda = 0;
        Mao mao;
        Peca peca;
        int comparar = -1;

        if(jogadores != null){

            while(roda < jogadores.size()) {

                mao = jogadores.get(roda);


                while (i < 7) {
                    peca = comprar();
                    mao.addPeca(peca);
                    i++;

                    if(peca.isBucha()){
                        if(comparar < peca.pesoPeça()){
                            comparar = peca.pesoPeça();
                            this.comeca = roda;
                        }
                    }

                }
                i = 0;
                roda ++;
            }
            deu = true;
        }

        return deu;
    }

    /**
     * @return Devolve a quantia de peças na mesa.
     */
    public int size(){
        return jogo.size();
    }

    /**
     * Método gera todas as peças e as coloca na mesa.
     */
    private void gerarPecas(){
        int esquerdo = 0;
        int direito = 0;

        boolean concluido = false;

        while(!concluido){
            compra.add(new Peca(esquerdo, direito));

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

        while(i < jogo.size()){

            if(jogo.get(i) != null)
                txt += jogo.get(i).toString();

            i++;
        }

        return txt;
    }

    /**
     * @return o jogador da vez.
     */
    public int vez(){
        return comeca;
    }

    /**
     * Verifica se as peças do jogador terminaram.
     * @param info Lista de mãos.
     * @return True se o jogador não tem mais peças e false caso contrário.
     */
    public boolean acabou(Mao info){

        boolean retorno = false;

        if(info.size() == 0)
            retorno = true;

        return retorno;
    }

    /**
     * Metodo verifica a quantia de pontos de todas as mãos e devolve a que tem menos pontos(vencedor).
     * @param info Lista de mãos.
     * @return Um inteiro com a posição da mão ganhadora.
     */
    public int fechou(List<Mao> info){

        int vencedor = 0;
        int compara = 0, pontos;

        if(info != null) {
            pontos = info.get(compara).pontoTotal();

            while (compara < info.size()) {

                if(info.get(compara).pontoTotal() < pontos){
                    pontos = info.get(compara).pontoTotal();
                    vencedor = compara;
                }

                compara ++;
            }
        }

        return vencedor;
    }

    /**
     * Metodo verifica se o jogo está fechado.
     * @return True caso esteja fechado e Fals, caso contrario.
     */
    public boolean taFechado(){
        int pecas = 1, valor;
        int caminhando = 1;
        boolean retorno = false;

        if(jogo.size() > 0) {

            valor = jogo.get(0).getEsquerdo();

            while (caminhando < jogo.size()){
                if(jogo.get(caminhando).existeValor(valor)){
                    pecas ++;
                }
                caminhando ++;
            }

            if(pecas == 7)
                retorno = true;

        }

        return retorno;
    }


    /**
     * Verifica se a mesa do jogo está vazia.
     * @return true caso esteja e false caso não esteja.
     */
    public boolean isEmpty(){
        boolean retorno = false;

        if (jogo.isEmpty()){
            retorno = true;
        }

        return retorno;
    }

    /**
     * Metodo que verifica o valor da ponta direita da mesa.
     * @return valor da ponta ou -1, caso ela não exista.
     */
    public int verificaPontaDir(){
        int retorno = -1;

        if (jogo.size() > 0){
            retorno = jogo.get(jogo.size()-1).getDireito();
        }

        return retorno;
    }

    /**
     * Metodo que verifica o valor da ponta esquerda da mesa.
     * @return valor da ponta ou -1, caso ela não exista.
     */
    public int verificaPontaEsq(){

        int resp = -1;

        if(jogo.size() > 0)
            resp = jogo.get(0).getEsquerdo();

        return resp;
    }

    /**
     * Adiciona uma peça na ponta esquerda mesa.
     * @param peca Peça a ser adicionada.
     * @param index posição inicial do array (0)
     */
    public void addPecaNaMesa(Peca peca, int index){
        jogo.add(0, peca);
    }

    /**
     * Adiciona uma peça na ponta direita da mesa.
     * @param peca Peça a ser adicionada.
     */
    public void addPecaNaMesa (Peca peca){
        jogo.add(peca);
    }

    /**
     * @return Retorna true caso tenha peças para comprar e false, caso contrario.
     */
    public boolean temCompra(){
        boolean tem = false;

        if(compra.size() > 0)
            tem = true;

        return tem;
    }
}



