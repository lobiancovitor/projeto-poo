public class IngressoMeia extends Ingresso {
    
    public IngressoMeia(Partida partida, Assento assento, double preco) {
        super(partida, TipoIngresso.MEIA, assento, preco);
        this.preco = 50;
    }
}