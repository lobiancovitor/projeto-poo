package entidades.ingresso;

import entidades.Assento;
import entidades.Partida;

public class IngressoFactory {

    public Ingresso novoIngresso(Partida partida, TipoIngresso tipoIngresso, Assento assento) {
        if (tipoIngresso == null) {
            return null;
        }
        switch(tipoIngresso) {
            case MEIA:
                return new IngressoMeia(partida, assento);
            case INTEIRA:
                return new IngressoInteira(partida, assento);
            default:
                throw new IllegalArgumentException();
        }
    }

    public Ingresso novoIngresso(Partida partida, TipoIngresso tipoIngresso) {
        if (tipoIngresso == null) {
            return null;
        }
        switch(tipoIngresso) {
            case MEIA:
                return new IngressoMeia(partida);
            case INTEIRA:
                return new IngressoInteira(partida);
            default:
                throw new IllegalArgumentException();
        }
    }
}

/*
Implementação:
IngressoFactory ingressoFactory = new IngressoFactory();
Ingresso ingresso = ingressoFactory.novoIngresso(tipoIngresso, partida, assento);
...
...
...
*/
