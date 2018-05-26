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
     * Metodo distribui as peças aleatoriamente para um jogador.
     * @param jogadores List de jogadores que receberão as peças.
     * @return true se foi distribuido as peças corretamente e false, caso contrário.
     */
    public boolean distribuirPecas(List jogadores){
        boolean deu = false;
        Random diller = new Random();
        int i = 0;
        int roda = 0;
        Mao mao;
        Peca peca;
        int comparar = -1;

        if(jogadores != null){

            while(roda < jogadores.size()) {

                mao = (Mao)jogadores.get(roda);


                while (i < mao.getQuantia()) {
                    peca = compra.remove(diller.nextInt(compra.size()));
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
        System.out.println("Olha ai " + comeca);
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

        while(i < compra.size()){

            if(compra.get(i) != null)
                txt += compra.get(i).toString();

            i++;
        }

        return txt;
    }

    /**
     * Metodo desenvolve o jogo e da a vez aos jogadores.
     * @return retorna a casa que o vencedor está no list.
     */
    public int play(List<Mao> mao){

        int vencedor = - 1;
        boolean acabou = false;

        while(!acabou){

         //   mao.get(comeca).jogar; // Dou a jogada para o primeiro a jogar

            if(acabou(mao, comeca)){
                vencedor = comeca;
                acabou = true;
            }else{
                if(taFechado()){
                    vencedor = fechou(mao);
                    acabou = true;
                }
            }

            if(comeca == mao.size() - 1) {
                comeca = 0;
            }else{
                comeca ++;
            }
        }

        return vencedor;
    }

    /**
     * Verifica se as peças do jogador terminaram.
     * @param info Lista de mãos.
     * @param posicao Posição da mão a ser processada.
     * @return True se o jogador não tem mais peças e false caso contrário.
     */
    private boolean acabou(List<Mao> info, int posicao){

        boolean retorno = false;

        if(info.get(posicao).size() == 0)
            retorno = true;

        return retorno;
    }

    /**
     * Metodo verifica a quantia de pontos de todas as mãos e devolve a que tem menos pontos(vencedor).
     * @param info Lista de mãos.
     * @return Um inteiro com a posição da mão ganhadora.
     */
    private int fechou(List<Mao> info){

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
    private boolean taFechado(){
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
}
