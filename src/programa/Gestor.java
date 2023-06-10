package programa;

import java.io.IOException;

import dao.IngressoDAO;
import dao.PartidaDAO;
import entidades.Partida;
import entidades.ingresso.Ingresso;
import util.LeitoraDados;

public class Gestor {
    LeitoraDados leitora;
    PartidaDAO partidaDAO;
    IngressoDAO ingressoDAO;

    public Gestor(String caminho) throws IOException {
        this.leitora = new LeitoraDados();
        this.ingressoDAO = new IngressoDAO(caminho);
        this.partidaDAO = new PartidaDAO(caminho);
    }

    public String exibeOpcoes() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n------\n");
        sb.append("Insira a opção desejada conforme o menu abaixo, ou digite outra mensagem para sair do programa:\n");
        sb.append("01 - Cadastrar uma nova partida;\n");
        sb.append("02 - Remover uma partida;\n");
        sb.append("03 - Exibir todas as partidas;\n");
        sb.append("04 - Atualizar uma partida;\n");
        sb.append("05 - Exibir informações sobre uma partida específica;\n");
        sb.append("06 - Realizar a venda de um ingresso;\n");
        sb.append("07 - Exibir o número de ingressos restantes para todas as partidas;\n");
        sb.append("08 - Exibir o número de ingressos restantes para uma partida;\n");
        sb.append("09 - Lista todos os ingressos vendiddos de uma partida;\n");
        sb.append("10 - Exibe os dados do último ingresso vendido;\n");

        return sb.toString();
    }

    public String processaOpcoes() throws IOException {
        String opcao = this.leitora.lerTexto();
        switch (opcao) {
            case "1":
                partidaDAO.adicionar(this.leitora.lerNovaPartida());
                break;
            case "2":
                System.out.print("Digite o nome da partida a ser removida: ");
                String nomeRemover = this.leitora.lerTexto();
                if (partidaDAO.consultar(nomeRemover) == null) {
                    System.out.println("Partida não encontrada");
                } else {
                    partidaDAO.excluir(nomeRemover);
                    System.out.println("Partida removida com sucesso");
                }
                break;
            case "3":
                System.out.println(partidaDAO);
                break;
            case "4":
                partidaDAO.alterar(this.leitora.lerNovaPartida());
            case "5":
                System.out.print("Digite o nome da partida a ser consultada: ");
                String partidaPesquisada = this.leitora.lerTexto();
                partidaDAO.consultar(partidaPesquisada);
            case "6":
                System.out.println("Digite o nome da Partida: ");
                Partida partidaProcurada = partidaDAO.consultar(this.leitora.lerTexto());
                if (partidaProcurada == null) {
                    System.out.println("Partida não encontrada");
                } else {
                    ingressoDAO.adicionar(this.leitora.lerNovoIngresso(partidaProcurada));
                }
                break;
            case "7":
                if (partidaDAO.listar() != null) {
                    System.out.println("Ingressos disponíveis:");
                    for (Partida partida : partidaDAO.listar()) {
                        System.out.println(partida.getNome() + ": " + partida.getIngressos());
                    } 
                } else {
                    System.out.println("Você precisa primeiro cadastrar uma partida!");
                } 
                break;
            case "8":
                System.out.println("Digite o nome da Partida: ");
                Partida partidaIngresso = partidaDAO.consultar(this.leitora.lerTexto());
                if (partidaIngresso == null) {
                    System.out.println("Partida não encontrada");
                } else {
                    partidaIngresso.getIngressos();
                }
                break;
            case "9":
                System.out.println("Digite o nome da Partida: ");
                Partida partidaIngressosVendidos = partidaDAO.consultar(this.leitora.lerTexto());
                if (partidaIngressosVendidos == null) {
                    System.out.println("Partida não encontrada");
                } else {
                    System.out.println(ingressoDAO);
                }
                break;
            case "10":
                Ingresso ultimoIngresso = ingressoDAO.listar().get(ingressoDAO.listar().size() -1);
                System.out.println(ultimoIngresso);
            default:
                ingressoDAO.exportar();
                partidaDAO.exportar();
                System.out.println("Volte sempre!");
                opcao = ""; 
        }
        return opcao;
    }
}

// case 1:
//                     System.out.println("Insira as informações da partida:");
//                     System.out.print("Nome da partida: ");
//                     nome = scanner.nextLine();
//                     if (procuraPartida(partidas, nome) != null) {
//                         System.out.println("Erro! Partida já foi criada!");
//                     } else {
//                         System.out.print("Data da partida [dd. MMM. yyyy]: ");
//                         String str = scanner.nextLine();
//                         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
//                         data = LocalDate.parse(str, dtf);
//                         System.out.print("Local da partida: ");
//                         local = scanner.nextLine();
//                         System.out.print("Número de ingressos tipo inteira: ");
//                         ingressosInt = scanner.nextInt();
//                         scanner.nextLine();
//                         System.out.print("Número de ingressos tipo meia: ");
//                         ingressosMeia = scanner.nextInt();
//                         scanner.nextLine();
//                         System.out.print("Valor do ingresso: ");
//                         valor = scanner.nextDouble();
//                         scanner.nextLine();
//                         partidas.add(new Partida(nome, data, local, ingressosInt, ingressosMeia, valor));
//                         System.out.println("Partida criada!");
//                     }
//                     break;
//                 case 2:
//                     if (partidas.size() > 0) {
//                         System.out.println("Vendendo um ingresso!");
//                         System.out.print("Informe o nome da partida que deseja comprar ingresso: ");
//                         nomeBusca = scanner.nextLine();

//                         partidaVenda = procuraPartida(partidas, nomeBusca);
//                         if (partidaVenda != null) {
//                             ingresso = venderIngresso(scanner, partidaVenda);
//                         } else {
//                             System.out.println("Erro! Partida com nome " + nomeBusca + " não encontrada!");
//                         }

//                     } else {
//                         System.out.println("Você precisa primeiro cadastrar uma partida!");
//                     }
//                     break;
//                 case 3:
//                     if (partidas.size() > 0) {
//                         System.out.println("Informações das partidas:");
//                         for (Partida partida : partidas) {
//                             System.out.println(partida);
//                             System.out.println("");
//                         }
//                     } else {
//                         System.out.println("Você precisa primeiro cadastrar uma partida!");
//                     }
//                     break;
//                 case 4:
//                     if (partidas.size() > 0) {
//                         System.out.println("Ingressos disponíveis:");
//                         for (Partida partida : partidas) {
//                             System.out.println(partida.getNome() + ": " + partida.getIngressos());
//                         }
//                     } else {
//                         System.out.println("Você precisa primeiro cadastrar uma partida!");
//                     }
//                     break;
//                 case 5:
//                     if (ingresso != null) {
//                         System.out.println("Informações sobre o último ingresso vendido:");
//                         System.out.println(ingresso);
//                     } else {
//                         System.out.println("Você precisa primeiro vender um ingresso!");
//                     }
//                     break;
//                 case 6:
//                     if (partidas.size() > 0) {
//                         System.out.print("Informe o nome da partida a ser excluída: ");
//                         nome = scanner.nextLine();
//                         partidaProcurada = procuraPartida(partidas, nome);
//                         if (partidaProcurada != null) {
//                             partidas.remove(partidaProcurada);
//                             System.out.println("Partida removida!");
//                         } else {
//                             System.out.println("Partida não localizada!");
//                         }
//                     } else {
//                         System.out.println("Você precisa primeiro cadastrar uma partida!");
//                     }
//                     break;
//                 case 7:
//                     if (partidas.size() > 0) {
//                         System.out.print("Informe o nome da partida a ser editada: ");
//                         nome = scanner.nextLine();
//                         partidaProcurada = procuraPartida(partidas, nome);
//                         if (partidaProcurada != null) {
//                             System.out.print("Data da partida [dd. MMM. yyyy]: ");
//                             String str = scanner.nextLine();
//                             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
//                             data = LocalDate.parse(str, dtf);
//                             System.out.print("Local da partida: ");
//                             local = scanner.nextLine();
//                             System.out.print("Valor do ingresso: ");
//                             valor = scanner.nextDouble();
//                             scanner.nextLine();
//                             partidaProcurada.atualizaInfo(data, local, valor);
//                         } else {
//                             System.out.println("Partida não localizada!");
//                         }
//                     } else {
//                         System.out.println("Você precisa primeiro cadastrar uma partida!");
//                     }
//                     break;
//                 case 8:
//                     if (partidas.size() > 0) {
//                         System.out.print("Informe o nome da partida para exibir os ingressos restantes: ");
//                         nome = scanner.nextLine();
//                         partidaProcurada = procuraPartida(partidas, nome);
//                         if (partidaProcurada != null) {
//                             System.out.println(partidaProcurada.getNome() + ": " + partidaProcurada.getIngressos() + " restantes.");
//                         } else {
//                             System.out.println("Partida não localizada!");
//                         }
//                     } else {
//                         System.out.println("Você precisa primeiro cadastrar uma partida!");
//                     }
//                     break;
//                 default:
//                     opcao = 0;
//             }

//             if (opcao == 0) {
//                 System.out.println("Saindo do programa...");
//                 break;
//             }
//         }

//         scanner.close();
//     }

//     public static Partida procuraPartida(ArrayList<Partida> partidas, String nomeProcurado) {
//         for (Partida partida : partidas) {
//             if (nomeProcurado.equals(partida.getNome())) {
//                 return partida;
//             }
//         }

//         return null;
//     }

//     public static Ingresso venderIngresso(Scanner scanner, Partida partidaVenda) {
//         String opcaoIngresso;
//         char letraAssento;
//         TipoIngresso tipo;
//         Ingresso ingresso;
//         int numeroAssento;
//         Assento assento;

//         System.out.print("Letra do assento: ");
//         letraAssento = scanner.next().charAt(0);
//         System.out.print("Número do assento: ");
//         numeroAssento = scanner.nextInt();
//         scanner.nextLine();
//         assento = new Assento(numeroAssento, letraAssento);

//         System.out.print("O seu ingresso é meia (s/n)? ");
//         opcaoIngresso = scanner.nextLine();

//         if (opcaoIngresso.equals("s")) {
//             tipo = TipoIngresso.MEIA;

//             ingresso = new IngressoMeia(partidaVenda, assento);
//         } else {
//             tipo = TipoIngresso.INTEIRA;

//             ingresso = new IngressoInteira(partidaVenda, assento);
//         }
//         partidaVenda.venderIngresso(tipo);
//         return ingresso;
//     }