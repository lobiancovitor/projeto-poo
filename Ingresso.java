public abstract class Ingresso{
    double preco;
    TipoIngresso tipo;
    Assento assento;

    Ingresso(double preco, TipoIngresso tipo, Assento assento){
        this.preco = preco;
        this.tipo = tipo;
        this.assento = assento;
    }

}