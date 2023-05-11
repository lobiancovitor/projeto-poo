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

    public boolean isIngressoDisponivel(TipoIngresso tipo) {
        if (tipo == TipoIngresso.INTEIRA && this.ingressosInteira >= 1) {
            return true;
        } else if (tipo == TipoIngresso.MEIA && this.ingressosMeia >= 1) {
            return true;
        } else {
            System.out.println("Não há ingressos disponíveis para este tipo.");
            System.out.println("----------------------------------");
            return false;
        }
    }
     
    public double venderIngresso(TipoIngresso tipo, int quantidade) {
        double preco = 0;
        if (isIngressoDisponivel(tipo)) {
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
}
