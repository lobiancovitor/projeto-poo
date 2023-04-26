import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // Partida partida = cadastrarPartida();
        Partida partidaTeste = new Partida("Fla x Flu", "24/03/2022", "RJ", 100, 200);
        exibirInformacoesPartida(partidaTeste);
        exibirIngressosRestantes(partidaTeste);
        Ingresso ingresso = venderIngresso(partidaTeste);
        exibirUltimoIngresso(ingresso);
        // interfaceTexto();
 
    }
 
 
    static Partida cadastrarPartida() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o nome da partida: ");
        String nome = scanner.nextLine();
        
        System.out.print("Digite a data da partida: ");
        String data = scanner.nextLine();

        System.out.print("Digite o local da partida: ");
        String local = scanner.nextLine();

        System.out.print("Digite a quantidade de ingressos do tipo inteira disponíveis: ");
        int ingressosInteira = scanner.nextInt();

        System.out.print("Digite a quantidade de ingressos do tipo meia disponíveis: ");
        int ingressosMeia = scanner.nextInt();

        Partida partida = new Partida(nome, data, local, ingressosInteira, ingressosMeia);

        scanner.close();

        return partida;
    }
 
    static void exibirInformacoesPartida(Partida partida) {
        if(partida==null) {
            System.out.println("Ainda não existe uma partida, crie uma se necessário.");
        } else {
            System.out.println("----- Informações da Partida -----");
            System.out.println(partida);
            System.out.println("----------------------------------");
        }
    }
 
    static void exibirIngressosRestantes(Partida partida) {
        System.out.println("------ Ingressos Restantes -------");
        System.out.println("Inteira: " + partida.ingressosInteira);
        System.out.println("Meia: " + partida.ingressosMeia);
        System.out.println("----------------------------------");

    }
 
    static void exibirUltimoIngresso(Ingresso ingresso) {
        if(ingresso != null) {
            System.out.println("----- Informações do último ingresso -----");
            System.out.println(ingresso);
        }
    }
 
    static Ingresso venderIngresso(Partida partida) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Escolha seu assento -----");
        System.out.print("Informe a fila: ");
        char fila = scanner.next().charAt(0);
        System.out.print("Informe o número: ");
        int numero = scanner.nextInt();

        Assento assento = new Assento(numero, fila);

        System.out.println("Ingresso do tipo Meia ou Inteira? (M/I)");
        char tipoIngresso = scanner.next().charAt(0);
        System.out.println("----------------------------------");

        System.out.println("Informações do ingresso: ");
        System.out.println(partida + "\nFila: " + fila + " - Número: " + numero + " - Tipo: " + tipoIngresso);

        // antes de criar o ingresso confirmar informações

        if (tipoIngresso == 'M') {
            IngressoMeia ingresso = new IngressoMeia(partida, TipoIngresso.MEIA, assento, 50);
            return ingresso;
        } else {
            IngressoInteira ingresso = new IngressoInteira(partida, TipoIngresso.INTEIRA, assento, 100);
            return ingresso;
        }

    }
 
    static void interfaceTexto() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== SISTEMA PARA VENDA DE INGRESSOS =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar uma nova partida");
            System.out.println("2 - Vender um ingresso");
            System.out.println("3 - Exibir informações da partida");
            System.out.println("4 - Exibir o número de ingressos restantes");
            System.out.println("5 - Exibir informações do último ingresso vendido");
            System.out.println("6 - Encerrar o programa");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Método para cadastrar partida");
                    break;
                case 2:
                    System.out.println("Método para vender um ingresso");
                    break;
                case 3:
                    System.out.println("Método para exibir informações da partida");
                    break;
                case 4:
                    System.out.println("Método para exibir o número de ingressos restantes");
                    break;
                case 5:
                    System.out.println("Método para exibir informações do último ingresso vendido");
                    break;
                case 6:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida, digite outra opção.\n");
            }   

            if (opcao == 6) {
                scanner.close();
                break;
            }
        }
        
    }
 }
 
