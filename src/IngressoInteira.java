public class IngressoInteira extends Ingresso {
 
    public IngressoInteira(Partida partida, TipoIngresso tipo, Assento assento, double preco) {
        super(partida, tipo = TipoIngresso.INTEIRA, assento, preco);
        this.preco = 100;
    }
}
