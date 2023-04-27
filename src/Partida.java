public class Partida {
    String nome;
    String data;
    String local;
    int ingressosInteira;
    int ingressosMeia;

    public Partida(String nome, String data, String local, int ingressosInteira, int ingressosMeia) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.ingressosInteira = ingressosInteira;
        this.ingressosMeia = ingressosMeia;
    }

    @Override
    public String toString() {
        return
            "Nome: " + getNome() + "\n" +
            "Data: " + getData() + "\n" +
            "Local: " + getLocal() + "\n" +
            "Ingressos inteira: " + this.ingressosInteira + "\n" +
            "Ingressos meia: " + this.ingressosMeia
            ;
    }

    public String getNome() {
        return this.nome;
    }

    public String getData() {
        return this.data;
    }

    public String getLocal() {
        return this.local;
    }

    public int getIngressos() {
        return this.ingressosInteira + this.ingressosMeia;
    }

    public boolean isIngressoDisponivel(TipoIngresso tipo, int quantidade) {
        if (tipo == TipoIngresso.INTEIRA && this.ingressosInteira >= quantidade) {
            return true;
        } else if (tipo == TipoIngresso.MEIA && this.ingressosMeia >= quantidade) {
            return true;
        } else {
            return false;
        }
    }

    public double venderIngresso(TipoIngresso tipo, int quantidade) {
        double preco = 0;
        if (isIngressoDisponivel(tipo, quantidade)) {
            if (tipo == TipoIngresso.INTEIRA) {
                this.ingressosInteira -= quantidade;
                preco = 100;
            } else if (tipo == TipoIngresso.MEIA) {
                this.ingressosMeia -= quantidade;
                preco = 50;
            }
        }
        System.out.println("Compra realizada!");
        return preco * quantidade;
    }
}
