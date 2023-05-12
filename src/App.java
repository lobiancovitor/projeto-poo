import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partida partida = null;
        TipoIngresso tipo = null;
        ArrayList<Partida> partidas = new ArrayList<Partida>();
        Ingresso ingresso = null;

        interfaceTexto(partida, ingresso, scanner, partidas, tipo);
    }
 
    static Partida cadastrarPartida(ArrayList<Partida> partidas) {
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
        partidas.add(partida);

        System.out.println("\nPartida criada!");
        
        return partida;
    }
 
    static void exibirInformacoesPartida(ArrayList<Partida> partidas) {
        if(partidas.size() <= 0) {
            System.out.println("\nAinda não existe uma partida, crie uma se necessário.");
        } else {
            System.out.println("\n----- Informações da Partida -----");
            for (Partida partida2 : partidas) {
                System.out.println(partida2 + "\n");
            }
            System.out.println("----------------------------------");
        }
    }
 
    static void exibirIngressosRestantes(ArrayList<Partida> partidas) {
        if (partidas.size() > 0) {
            System.out.println("\n------ Ingressos Restantes -------");
            for (Partida partida2 : partidas) {
                System.out.println("Partida - " + partida2.nome + ": " + partida2.getIngressos());
            };
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
 
    static Ingresso venderIngresso(Partida partida, ArrayList<Partida> partidas, TipoIngresso tipo) {
        Scanner scanner = new Scanner(System.in);
        Ingresso ingresso = null;
        boolean partidaDisponivel = false;

        if (partidas.size() <= 0) {
            System.out.println("\nUm ingresso deve pertencer a uma partida. Crie uma partida.");
            return ingresso;
        }

        System.out.print("Informe o nome da partida: ");
        boolean controle = false;

        String nomePartida = scanner.nextLine();

        for (Partida partida2: partidas) {
            if (partida2.nome.equals(nomePartida)) {
                System.out.println("----- Escolha seu assento -----");
                System.out.print("Informe a fila: ");
                char fila = scanner.next().charAt(0);
                System.out.print("Informe o número: ");
                int numero = scanner.nextInt();
    
                Assento assento = new Assento(numero, fila);
    
                System.out.println("Ingresso do tipo Meia ou Inteira? (M/I)");
                char tipoIngresso = scanner.next().toUpperCase().charAt(0);
                System.out.println("----------------------------------");
    
                if (tipoIngresso == 'I') {
                    tipo = TipoIngresso.INTEIRA;
                    partidaDisponivel = partida2.isIngressoDisponivel(TipoIngresso.INTEIRA);
                } else if (tipoIngresso == 'M') {
                    tipo = TipoIngresso.MEIA;
                    partidaDisponivel = partida2.isIngressoDisponivel(TipoIngresso.MEIA);
                } else {
                    System.out.println("Opção inválida.");
                    break;
                }
    
                if (partidaDisponivel != true) {
                    return ingresso;
                }

                controle = true;
    
                System.out.println("Informações do ingresso: ");
                System.out.println("Partida: " + partida2.nome + "\nData: " + partida2.data + " - Local: " + partida2.local + "\nFila: " + fila + " - Número: " + numero + " - Tipo: " + tipoIngresso + "\n");
  
                System.out.println("Confirma informações do ingresso? (S/N)");
                System.out.println("Digite outra coisa para voltar para a escolha de opções.");
                char confirma = scanner.next().toUpperCase().charAt(0);
    
                if (confirma == 'S') {
                    if (tipoIngresso == 'I') {
                        ingresso = new IngressoMeia(partida2, assento, 50);
                    } else {
                        ingresso = new IngressoInteira(partida2, assento, 100);
                    }
                } else {
                    break;
                }

                partida2.venderIngresso(tipo, 1);
                controle = true;
                
            } else if (!controle) {
                System.out.println("Não existe uma partida com esse nome.");
                return ingresso;
            }
        }

        return ingresso;
    }
 
    static void interfaceTexto(Partida partida, Ingresso ingresso, Scanner scanner, ArrayList<Partida> partidas, TipoIngresso tipo) {
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
                    partida = cadastrarPartida(partidas);
                    break;
                case 2:
                    ingresso = venderIngresso(partida, partidas, tipo);
                    break;
                case 3:
                    exibirInformacoesPartida(partidas);
                    break;
                case 4:
                    exibirIngressosRestantes(partidas);
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
