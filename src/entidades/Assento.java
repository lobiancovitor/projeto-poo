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
        
        sb.append(this.getFila()).append(Integer.toString(this.getNumero()));

        return sb.toString();
    }

    public static Assento valueOf(String string) {
        return null;
    }
}
