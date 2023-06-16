package entidades;

public class Assento {
    private int numero;
    private char fila;

    public Assento(int numero, char fila) {
        this.numero = numero;
        this.fila = fila;
    }

    public int getNumero() {
        return this.numero;
    }

    public char getFila() {
        return this.fila;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.getFila()).append(":");
        sb.append(Integer.toString(this.getNumero()));

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Assento)) {
            return false;
        }
        Assento assento = (Assento) obj;
        return assento.numero == this.numero && assento.fila == this.fila;

    }

    public static Assento valueOf(String dadosAssento) {
        String[] partes = dadosAssento.split(":");
        int num = Integer.parseInt(partes[1]);
        char fil = partes[0].charAt(0);
        
        return new Assento(num, fil);
    }
}
