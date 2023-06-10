import programa.Gestor;

public class Main {
    public static void main(String[] args) throws Exception {
        Gestor gestor = new Gestor("informacoes.txt");
        String opcao = "0";

        while (!(opcao.equals(""))) {
            System.out.println(gestor.exibeOpcoes()); 
            opcao = gestor.processaOpcoes();
        }
    }
}