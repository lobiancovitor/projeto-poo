public class IngressoMeia extends Ingresso {
    
    public IngressoMeia(Partida partida, TipoIngresso tipo, Assento assento, double preco) {
        super(partida, tipo = TipoIngresso.MEIA, assento, preco);
        this.preco = 50;
    }
}