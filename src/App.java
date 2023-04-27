import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partida partida = null;
        Ingresso ingresso = null;

        interfaceTexto(partida, ingresso, scanner);
    }
 
    static Partida cadastrarPartida() {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o nome da partida: ");
        String nome = input.nextLine();
        
        System.out.print("Digite a data da partida: ");
        String data = input.nextLine();

        System.out.print("Digite o local da partida: ");
        String local = input.nextLine();

        System.out.print("Digite a quantidade de ingressos do tipo inteira disponíveis: ");
        int ingressosInteira = input.nextInt();

        System.out.print("Digite a quantidade de ingressos do tipo meia disponíveis: ");
        int ingressosMeia = input.nextInt();

        Partida partida = new Partida(nome, data, local, ingressosInteira, ingressosMeia);

        System.out.println("\nPartida criada!");
        
        return partida;
    }
 
    static void exibirInformacoesPartida(Partida partida) {
        if(partida==null) {
            System.out.println("\nAinda não existe uma partida, crie uma se necessário.");
        } else {
            System.out.println("\n----- Informações da Partida -----");
            System.out.println(partida);
            System.out.println("----------------------------------");
        }
    }
 
    static void exibirIngressosRestantes(Partida partida) {
        if (partida != null) {
            System.out.println("\n------ Ingressos Restantes -------");
            System.out.println("Inteira: " + partida.ingressosInteira);
            System.out.println("Meia: " + partida.ingressosMeia);
            System.out.println("----------------------------------");
        } else {
            System.out.println("\nUm ingresso deve pertencer a uma partida. Crie uma partida.");
        }
    }
 
    static void exibirUltimoIngresso(Ingresso ingresso) {
        if(ingresso != null) {
            System.out.println("\n----- Informações do último ingresso -----");
            System.out.println(ingresso);
        } else {
            System.out.println("\nUm ingresso ainda não foi vendido.");
        }
    }
 
    static Ingresso venderIngresso(Partida partida) {
        Scanner scanner = new Scanner(System.in);
        Ingresso ingresso = null;

        if (partida == null) {
            System.out.println("\nUm ingresso deve pertencer a uma partida. Crie uma partida.");
            return ingresso;
        }

        while (true) {
            System.out.println("----- Escolha seu assento -----");
            System.out.print("Informe a fila: ");
            char fila = scanner.next().charAt(0);
            System.out.print("Informe o número: ");
            int numero = scanner.nextInt();

            Assento assento = new Assento(numero, fila);

            System.out.println("Ingresso do tipo Meia ou Inteira? (M/I)");
            char tipoIngresso = scanner.next().toUpperCase().charAt(0);
            System.out.println("----------------------------------");

            System.out.println("Informações do ingresso: ");
            System.out.println("Partida: " + partida.nome + "\nData: " + partida.data + " - Local: " + partida.local + "\nFila: " + fila + " - Número: " + numero + " - Tipo: " + tipoIngresso + "\n");

            // antes de criar o ingresso confirmar informações
            System.out.println("Confirma informações do ingresso? (S/N)");
            System.out.println("Digite outra coisa para voltar para a escolha de opções.");
            char confirma = scanner.next().toUpperCase().charAt(0);

            if (confirma == 'S') {
                if (tipoIngresso == 'M') {
                ingresso = new IngressoMeia(partida, TipoIngresso.MEIA, assento, 50);
                partida.venderIngresso(TipoIngresso.MEIA, 1);
                return ingresso;
                } else {
                ingresso = new IngressoInteira(partida, TipoIngresso.INTEIRA, assento, 100);
                partida.venderIngresso(TipoIngresso.INTEIRA, 1);
                return ingresso;
                }
            } else if (confirma == 'N') {
                continue;
            } else {
                //Ingresso ingresso = null;
                return ingresso;
            }
        }
    }
 
    static void interfaceTexto(Partida partida, Ingresso ingresso, Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("===== SISTEMA PARA VENDA DE INGRESSOS =====");
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Cadastrar uma nova partida");
            System.out.println("2 - Vender um ingresso");
            System.out.println("3 - Exibir informações da partida");
            System.out.println("4 - Exibir o número de ingressos restantes");
            System.out.println("5 - Exibir informações do último ingresso vendido");
            System.out.println("6 - Encerrar o programa");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    partida = cadastrarPartida();
                    break;
                case 2:
                    ingresso = venderIngresso(partida);
                    break;
                case 3:
                    exibirInformacoesPartida(partida);
                    break;
                case 4:
                    exibirIngressosRestantes(partida);
                    break;
                case 5:
                    exibirUltimoIngresso(ingresso);
                    break;
                case 6:
                    System.out.println("\nEncerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida, digite outra opção.");
            }   
            if (opcao == 6) {
                scanner.close();
                break;
            }
        }
    }
}
