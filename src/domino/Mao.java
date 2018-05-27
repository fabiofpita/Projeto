package domino;

import Interface.JogoHVH;

import javax.swing.*;
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
            status = true;
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

        while(pecas.size() > i){
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

        if(info >= 0 && info < pecas.size())
            devolv = pecas.remove(info);

        return devolv;
    }

    /**
     * @return Retorna a quantia de pecas na mao.
     */
    public int size(){
        return pecas.size();
    }


    public int pontoTotal(){

        int pontos = 0, anda = 0;

        while(anda < size()){
            pontos += pecas.get(anda).pesoPeça();
        }

        return pontos;
    }

    /**
     * Metodo que executa uma jogada na mesa.
     * @param peca Peca que o jogador selecionou.
     * @param mesa Mesa do jogo.
     * @return 0 - Caso a jogada tenha sido executada com sucesso;
     *         1 - Caso a peça selecionada pelo jogador, na primeira jogada,
     *         não seja a maior bucha de sua mão ou a peça de maior peso.
     *         2 - Caso a peça selecionada pelo jogador, seja inválida,
     *         ou seja, não se encaixe em nenhuma das pontas.
     */

    public int fazJogada(Peca peca, Mesa mesa){
        int retorno = 0;
        Peca maior;
        if(mesa.isEmpty()){
            if (peca.isBucha()){
                maior = peca;
                for (int i=0; i<pecas.size(); i++){
                    if (maior.pesoPeça()<pecas.get(i).pesoPeça()){
                        retorno = 1;
                    }
                }

                if (retorno == 0){
                    mesa.addPecaNaMesa(peca);
                }
            }else{
                maior = peca;
                for (int i=0; i<pecas.size(); i++){
                    if (maior.pesoPeça()<pecas.get(i).pesoPeça()){
                        retorno =1;
                    }
                }

                if(retorno==0){
                    mesa.addPecaNaMesa(peca);
                }
            }
        }else{
            if (peca.getDireito() == mesa.verificaPontaDir()){
                peca.arrumarPeca(peca.getDireito(), peca.getEsquerdo());
                mesa.addPecaNaMesa(peca,0);
            }else{
                if (peca.getEsquerdo() == mesa.verificaPontaDir()){
                    mesa.addPecaNaMesa(peca,0);
                }else{
                    if(peca.getDireito() == mesa.verificaPontaEsq()){
                        mesa.addPecaNaMesa(peca);
                    }else{
                        if(peca.getEsquerdo() == mesa.verificaPontaDir()){
                            peca.arrumarPeca(peca.getDireito(), peca.getEsquerdo());
                            mesa.addPecaNaMesa(peca);
                        }else{
                            retorno = 2;
                        }
                    }
                }
            }
        }

        return retorno;

    }

    /**
     * Metodo que executa uma jogada.
     * @param peca Peca a ser colocada na mesa.
     * @param ponta Ponta que o jogador deseja coloca-la (0 = ponta esquerda, 1 = ponta direita)
     * @param mesa Mesa do jogo;
     * @return 0 - Caso a jogada tenha sido executada com sucesso;
     */
    public int fazJogada(Peca peca, int ponta, Mesa mesa){
        int retorno=0;

        if (ponta == 0){
            if (peca.getEsquerdo()== mesa.verificaPontaEsq()){
                peca.arrumarPeca(peca.getDireito(), peca.getEsquerdo());
                mesa.addPecaNaMesa(peca,0);
            }else{
                if (peca.getDireito() == mesa.verificaPontaEsq()){
                    mesa.addPecaNaMesa(peca,0);
                }
            }
        }else{
            if (peca.getDireito()== mesa.verificaPontaDir()){
                peca.arrumarPeca(peca.getDireito(), peca.getEsquerdo());
                mesa.addPecaNaMesa(peca);
            }else{
                if (peca.getEsquerdo() == mesa.verificaPontaDir()){
                    mesa.addPecaNaMesa(peca);
                }
            }
        }

        return retorno;
    }

    public void jogar(){

            //this.fazJogada(//metodo que busca peça selecionada da interface, Mesa mesa);
    }
}