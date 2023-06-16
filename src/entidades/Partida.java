package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public int getIngressoMeia() {
        return this.ingressosMeia;
    }

    public int getIngressoInteira() {
        return this.ingressosInteira;
    }

    public List<Ingresso> getAllIngressos() {
        return this.ingressos;
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

    public boolean venderIngresso(TipoIngresso tipo) {
        if (this.isIngressoDisponivel(tipo, 1)) {
            if (tipo == TipoIngresso.INTEIRA) {
                this.ingressosInteira--;
            } else {
                this.ingressosMeia--;
            }
            System.out.println("Ingresso vendido!");
            return true;
        } else {
            System.out.println("Não há ingressos disponíveis!");
            return false;
        }
    }

    public boolean assentoOcupado(Assento assento) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getAssento().equals(assento)) {
                return true;
            }
        }
        return false;
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

        sb.append("Partida: ").append(this.getNome()).append("\n");
        sb.append("Data: ").append(this.getData()).append("\n");
        sb.append("Local: ").append(this.getLocal()).append("\n");
        sb.append("Valor: R$ ").append(this.getValorIngresso()).append("\n");
        sb.append("Ingressos meia: ").append(this.getIngressoMeia()).append("\n");
        sb.append("Ingressos inteira: ").append(this.getIngressoInteira()).append("\n");

        List<Ingresso> ingressos = this.getAllIngressos();
        if (ingressos.isEmpty()) {
            sb.append("Ingressos ainda não foram vendidos para esta partida\n");
        } else {
            sb.append("\nIngressos vendidos:\n\n");

            for (Ingresso ingresso : ingressos) {
                sb.append(ingresso.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public static Partida valueOf(String dadosPartida) {
        String[] separa = dadosPartida.split("\n");

        String nome = "";
        LocalDate dataL = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String local = "";
        double valor = 0.0;
        int ingressosMeia = 0;
        int ingressosInteira = 0;
        int contador = 0;

        for (String linha: separa) {
            if (contador >= 5) {
            break;
            }

            String[] partes = linha.split(":");
            String chave = partes[0].trim();

            if (partes.length > 1) {
                String valorStr = partes[1].trim();

                switch (chave) {
                    case "Partida":
                        nome = valorStr;
                        break;
                    case "Data":
                        dataL = LocalDate.parse(valorStr, formatter);
                        break;
                    case "Local":
                        local = valorStr;
                        break;
                    case "Valor (R$)":
                        valor = Double.parseDouble(valorStr);
                        break;
                    case "Ingressos meia":
                        ingressosMeia = Integer.parseInt(valorStr);
                        break;
                    case "Ingressos inteira":
                        ingressosInteira = Integer.parseInt(valorStr);
                        break;
                    default:
                        break;
                }
            }
            contador++;
        }

        return new Partida(nome, dataL, local, ingressosInteira, ingressosMeia, valor);
    }
}
