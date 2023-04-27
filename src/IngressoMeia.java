public class IngressoMeia extends Ingresso {
    
    public IngressoMeia(Partida partida, TipoIngresso tipo, Assento assento, double preco) {
        super(partida, tipo, assento, preco);
        this.tipo = TipoIngresso.MEIA;
        this.preco = 50;
    }
}