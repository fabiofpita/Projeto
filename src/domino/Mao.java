package domino;
import Interface.JogoHVH;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Classe que representa a mão de um jogador.
 */
public class Mao {

    private ArrayList<Peca> pecas;

    /**
     * Construtor de uma mao para a alocacao de pecas.
     * @param qnt_pecas quantia de pecas que tera na mão.
     */
    public Mao(int qnt_pecas){

        if(qnt_pecas < 1 || qnt_pecas > 14)
            qnt_pecas = 7;

        pecas = new ArrayList<>(qnt_pecas);
    }

    /**
     * Metodo verifica se a mao tem alguma peca que possa ser jogada na mesa.
     * @param mesa Mesa que está acontecendo o jogo.
     * @return Retorna true se é possivel fazer uma jogada e false, caso contrário.
     */
    public boolean podeJogar(Mesa mesa){

        boolean tem = false;
        int cont = 0;

        if(mesa.size() > 0);
            while(cont < pecas.size() && !tem){

                if(pecas.get(cont).existeValor(mesa.verificaPontaEsq()))
                    tem = true;
                if(pecas.get(cont).existeValor(mesa.verificaPontaDir()))
                    tem = true;

                cont ++;
            }

        return tem;
    }

    /**
     * Metodo que recebe as pecas que a mão tera.
     * @param info peca a ser adicionada.
     * @return retorna True caso a peça tenha sido adicionada e False, caso contrário.
     */
    public boolean addPeca(Peca info){

        boolean status = false;

        if(info != null){
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
     * Metodo devolve uma copia da peça de uma determinada posicao da mão ou nulo.
     * @param info posição da peça na mão.
     * @return peça de uma posição ou nulo, caso a posição seja inexistente.
     */
    public String olhaPeca(int info){

        String devolv = "";

        if(info >= 0 && info < pecas.size())
            devolv +="[" + pecas.get(info).getEsquerdo() + "|" + pecas.get(info).getDireito() + "]";

        return devolv;
    }

    /**
     * Metodo devolve uma copia da peça de uma determinada posicao da mão ou nulo.
     * @param info posição da peça na mão.
     * @return peça de uma posição ou nulo, caso a posição seja inexistente.
     */
    public Peca copiaPeca(int info){

        Peca devolv = null;

        if(info >= 0 && info < pecas.size())
            devolv = pecas.get(info);

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

        int retorno = -1;

        Peca maior;

        if(mesa.isEmpty()){
            if (peca.isBucha()){
                maior = peca;
                for (int i=0; i < pecas.size(); i++){
                    if (maior.pesoPeça() < pecas.get(i).pesoPeça() && pecas.get(i).isBucha()){
                        retorno = 1;
                    }
                }

                if (retorno == -1){
                    mesa.addPecaNaMesa(peca);
                    retorno = 0;
                }
            }//else{
            //    maior = peca;
            //    for (int i=0; i<pecas.size(); i++){
            //        if (maior.pesoPeça()<pecas.get(i).pesoPeça()){
            //            retorno =1;
            //        }
            //    }

            //    if(retorno==0){
            //        mesa.addPecaNaMesa(peca);
            //    }
            //}
        }else{
            if (peca.getDireito() == mesa.verificaPontaDir()){
                peca.arrumarPeca(1, peca.getEsquerdo());
                mesa.addPecaNaMesa(peca);
                retorno = 0;
            }else{
                if (peca.getEsquerdo() == mesa.verificaPontaDir()){
                    mesa.addPecaNaMesa(peca);
                    retorno = 0;
                }else{
                    if(peca.getDireito() == mesa.verificaPontaEsq()){
                        mesa.addPecaNaMesa(peca, 0);
                        retorno = 0;
                    }else{
                        if(peca.getEsquerdo() == mesa.verificaPontaEsq()){
                            peca.arrumarPeca(-1, peca.getDireito());
                            mesa.addPecaNaMesa(peca, 0);
                            retorno = 0;
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


