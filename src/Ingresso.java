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

    public double getPreco() {
        return this.preco;
    }

    @Override
    public String toString() {
        return
            "Nome: " + this.partida.nome + "\n" +
            "Data: " + this.partida.data + "\n" +
            "Local: " + this.partida.local + "\n" +
            "Tipo: " + this.tipo + "\n" +
            "Assento: " + this.assento + "\n" +
            "Preço: " + this.preco + "\n"
            ;
    }

}