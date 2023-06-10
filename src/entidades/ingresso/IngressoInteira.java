package entidades.ingresso;

import entidades.Assento;
import entidades.Partida;

public class IngressoInteira extends Ingresso {

    public IngressoInteira(Partida partida, Assento assento) {
        super(partida, TipoIngresso.INTEIRA, assento);
        this.preco = partida.getValorIngresso();
    }

    public IngressoInteira(Partida partida) {
        super(partida, TipoIngresso.INTEIRA);
        this.preco = partida.getValorIngresso();
    }
}
