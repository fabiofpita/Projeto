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
    public void jogadaAutomatica (Mao mao, Mesa mesa) {
        Peca maior;
        boolean para = false;
        if (mao.podeJogar(mesa)) {
            if (mesa.isEmpty()) {
                for (int i = 0; i < mao.size() && !para; i++) {
                    if (mao.pegaPeca(i).isBucha()) {
                        maior = mao.pegaPeca(i);
                        para = true;
                        for (int j = 0; i < mao.size(); j++) {
                            if (maior.pesoPeça() < mao.pegaPeca(j).pesoPeça() && mao.pegaPeca(j).isBucha()) {
                                maior = mao.pegaPeca(j);
                            }
                        }
                        mao.fazJogadaPC(maior, mesa);
                    }
                }

                if (!para) {
                    mao.fazJogadaPC(mao.pegaPeca(0), mesa);
                }

            } else {
                for (int i = 0; i < mao.size() && !para; i++) {
                    if (mao.pegaPeca(i).isBucha()) {
                        if (mao.pegaPeca(i).getDireito() == mesa.verificaPontaDir() || mao.pegaPeca(i).getDireito() == mesa.verificaPontaEsq()) {
                            mao.fazJogadaPC(mao.pegaPeca(i), mesa);
                            para = true;
                        }
                    }
                }

                if (!para) {
                    for (int i = 0; i < mao.size() && !para; i++) {
                        if (mao.pegaPeca(i).getEsquerdo() == mesa.verificaPontaEsq() || mao.pegaPeca(i).getDireito() == mesa.verificaPontaEsq()) {
                            mao.fazJogada(mao.pegaPeca(i), 0, mesa);
                            para = true;
                        } else {
                            if (mao.pegaPeca(i).getEsquerdo() == mesa.verificaPontaDir() || mao.pegaPeca(i).getDireito() == mesa.verificaPontaDir()) {
                                mao.fazJogadaPC(mao.pegaPeca(i), mesa);
                                para = true;
                            }
                        }
                    }
                }
            }
        }else{
            mao.addPeca(mesa.comprar());
        }
    }

}
