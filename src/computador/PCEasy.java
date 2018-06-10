package computador;

import domino.Mao;
import domino.Mesa;
import domino.Peca;

/**
* Classe que representa um computador no nivel fácil.
*/
public class PCEasy extends Computador {

      /**
      * Construtor.
      */
      public PCEasy() {
          super();
      }

      /**
      *
      * @return nome do computador.
      */
      @Override
      public String getNome() {
               return super.getNome();
      }

      /**
      * Executa uma jogada automatica para um computador.
      * @param mao Mão do computador.
      * @param mesa Mesa do jogo;
      */
      public void jogadaAutomatica (Mao mao, Mesa mesa){

           Peca maior;
           boolean para = false;

           if(mesa.isEmpty() && !mao.podeJogar(mesa)){
               mao.addPeca(mesa.comprar());
           }else{
               while(mesa.temCompra() && !mesa.isEmpty() && !mao.podeJogar(mesa)){
                   mao.addPeca(mesa.comprar());
               }
           }

           if (mao.podeJogar(mesa)) {
                   if (mesa.isEmpty()) {
                           for (int i = 0; i < mao.size() && !para; i++) {
                                   if (mao.copiaPeca(i).isBucha()) {
                                           maior = mao.copiaPeca(i);
                                           para = true;
                                           for (int j = 0; j < mao.size(); j++) {
                                                   if (maior.pesoPeça() < mao.copiaPeca(j).pesoPeça() && mao.copiaPeca(j).isBucha()) {
                                                           maior = mao.copiaPeca(j);
                                                   }
                                           }
                                           mao.fazJogadaPC(maior, mesa);
                                           mao.soltaPeca(maior);
                                   }
                           }

                   } else {
                           for (int i = 0; i < mao.size() && !para; i++) {
                                     if (mao.copiaPeca(i).isBucha()) {
                                            if (mao.copiaPeca(i).getDireito() == mesa.verificaPontaDir() || mao.copiaPeca(i).getDireito() == mesa.verificaPontaEsq()) {
                                                   mao.fazJogadaPC(mao.pegaPeca(i), mesa);
                                                   para = true;
                                            }
                                     }
                           }
                           if (!para) {
                                     for (int i = 0; i < mao.size() && !para; i++) {
                                            if (mao.copiaPeca(i).getEsquerdo() == mesa.verificaPontaEsq() || mao.copiaPeca(i).getDireito() == mesa.verificaPontaEsq()) {
                                                    mao.fazJogada(mao.pegaPeca(i), 0, mesa);
                                                    para = true;
                                            } else {
                                                    if (mao.copiaPeca(i).getEsquerdo() == mesa.verificaPontaDir() || mao.copiaPeca(i).getDireito() == mesa.verificaPontaDir()) {
                                                          mao.fazJogada(mao.pegaPeca(i), 1, mesa);
                                                          para = true;
                                                    }
                                            }
                                     }
                           }
                   }
           }

      }
}