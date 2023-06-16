package entidades.ingresso;

import java.util.Objects;

import entidades.Assento;
import entidades.Partida;

public abstract class Ingresso {
    protected Partida partida;
    protected TipoIngresso tipo;
    protected Assento assento;
    protected double preco;

    public Ingresso(Partida partida, TipoIngresso tipo, Assento assento) {
        this.partida = partida;
        this.tipo = tipo;
        this.assento = assento;
    }

    public Ingresso(Partida partida, TipoIngresso tipo) {
        this.partida = partida;
        this.tipo = tipo;
    }

    public Ingresso(Partida partida) {
        this.partida = partida;
    }

    public double getPreco() {
        return this.preco;
    }

    public Partida getPartida() {
        return this.partida;
    }

    public Assento getAssento() {
        return this.assento;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Partida)) {
            return false;
        }
        Ingresso ingresso = (Ingresso) obj;
        return ingresso.partida.equals(this.partida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partida, tipo, assento, preco);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Partida: ").append(this.partida.getNome()).append("\n");
        sb.append("Assento: ").append(this.assento.toString()).append("\n");
        sb.append("Tipo: ").append(this.tipo).append("\n");
        sb.append("Valor (R$): ").append(this.getPreco()).append("\n");

        return sb.toString();
    }

    
}