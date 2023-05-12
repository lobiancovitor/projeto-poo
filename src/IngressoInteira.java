public class IngressoInteira extends Ingresso {
 
    public IngressoInteira(Partida partida, Assento assento, double preco) {
        super(partida, TipoIngresso.INTEIRA, assento, preco);
        this.preco = 100;
    }
}
