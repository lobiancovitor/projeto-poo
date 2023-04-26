public class IngressoInteira extends Ingresso {
 
    public IngressoInteira(Partida partida, TipoIngresso tipo, Assento assento, double preco) {
        super(partida, tipo, assento, preco);
        this.tipo = TipoIngresso.INTEIRA;
        this.preco = 100;
    }
}
