import programa.Gestor;
import util.LeitoraDados;

public class Main {

    static LeitoraDados leitoraDados = new LeitoraDados();
    public static void main(String[] args) throws Exception {
        Gestor gestor = new Gestor("partidas.txt");
        String opcao = "0";

        while (!(opcao.equals(""))) {
            System.out.println(gestor.exibeOpcoes()); 
            opcao = gestor.processaOpcoes();
        } 
    }
}