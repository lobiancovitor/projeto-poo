package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entidades.ingresso.Ingresso;
import entidades.ingresso.TipoIngresso;

public class Partida {
    private String nome;
    private LocalDate data;
    private String local;
    private int ingressosInteira;
    private int ingressosMeia;
    private double valorIngresso;
    private List<Ingresso> ingressos = new ArrayList<>();

    public Partida(String nome, LocalDate data, String local, int ingressosInteira, int ingressosMeia, double valorIngresso) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.ingressosInteira = ingressosInteira;
        this.ingressosMeia = ingressosMeia;
        this.valorIngresso = valorIngresso;
    }

    public Partida(String nome) {
        this.nome = nome;
    }

    public double getValorIngresso() {
        return this.valorIngresso;
    }

    public String getNome() {
        return this.nome;
    }

    public LocalDate getData() {
        return this.data;
    }

    public String getLocal() {
        return this.local;
    }

     public int getIngressos() {
        return this.ingressosInteira + this.ingressosMeia;
    }

    public void setIngresso(ArrayList<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public void addIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }

    public void removeIngresso(Ingresso ingresso) {
        this.ingressos.remove(ingresso);
    }

    public void atualizaInfo(LocalDate data, String local, double valorIngresso) {
        this.data = data;
        this.local = local;
        this.valorIngresso = valorIngresso;
    }

    public boolean isIngressoDisponivel(TipoIngresso tipo, int quantidade) {
        if (tipo == TipoIngresso.INTEIRA) {
            return quantidade <= this.ingressosInteira;
        }

        return quantidade <= this.ingressosMeia;
    }

    public void venderIngresso(TipoIngresso tipo) {
        if (this.isIngressoDisponivel(tipo, 1)) {
            if (tipo == TipoIngresso.INTEIRA) {
                this.ingressosInteira--;
            } else {
                this.ingressosMeia--;
            }
            System.out.println("Ingresso vendido!");
        } else {
            System.out.println("Não há ingressos disponíveis!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Partida)) {
            return false;
        }
        Partida partida = (Partida) obj;
        return partida.nome.equals(this.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, data, local, ingressosInteira, ingressosMeia, valorIngresso);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Partida: ").append(this.getNome()).append("\nData: ").append(this.getData()).append("\nLocal: ").append(this.getLocal()).append("\nValor (R$): ").append(this.getValorIngresso()).append("\nIngressos disponíveis: ").append(this.getIngressos());

        return sb.toString();
    }

    public static Partida valueOf(String string) {
        return null;
    }
}
