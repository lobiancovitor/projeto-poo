public class Assento {
    int numero;
    char fila;

    public Assento(int numero, char fila) {
        this.numero = numero;
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return 
            "Número: " + getNumero() + ", " +
            "Fila: " + getFila()
            ;
    }

    public int getNumero() {
        return this.numero;
    }

    public char getFila() {
        return this.fila;
    }
    
}
