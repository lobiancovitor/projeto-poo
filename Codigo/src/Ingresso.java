public abstract class Ingresso{
    Partida partida;
    TipoIngresso tipo;
    Assento assento;
    double preco;

    public Ingresso(Partida partida, TipoIngresso tipo, Assento assento, double preco) {
        this.partida = partida;
        this.tipo = tipo;
        this.assento = assento;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return
            "Partida: " + this.partida + "\n" +
            "Tipo: " + this.tipo + "\n" +
            "Assento: " + this.assento + "\n" +
            "Preço: " + this.preco + "\n"
            ;
    }

    public double getPreco() {
        return this.preco;
    }

}