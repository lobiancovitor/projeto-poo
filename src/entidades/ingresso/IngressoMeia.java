package entidades.ingresso;

import entidades.Assento;
import entidades.Partida;

public class IngressoMeia extends Ingresso {

    public IngressoMeia(Partida partida, Assento assento) {
        super(partida, TipoIngresso.MEIA, assento);
        this.preco = partida.getValorIngresso() / 2;
    }

    public IngressoMeia(Partida partida) {
        super(partida, TipoIngresso.MEIA);
        this.preco = partida.getValorIngresso() / 2;
    }
}