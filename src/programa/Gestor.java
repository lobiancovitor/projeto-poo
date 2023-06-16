package programa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        sb.append("09 - Lista todos os ingressos vendidos de uma partida;\n");
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
                if (this.partidaDAO.listar().isEmpty()) {
                    System.out.println("Cadastre uma partida antes.");
                } else {
                    System.out.print("Digite o nome da partida a ser removida: ");
                    String nomeRemover = this.leitora.lerTexto();
                    if (partidaDAO.consultar(nomeRemover) == null) {
                        System.out.println("Partida não encontrada");
                    } else {
                        partidaDAO.excluir(nomeRemover);
                        System.out.println("Partida removida com sucesso");
                    }
                }
                break;
            case "3":
                if (this.partidaDAO.listar().isEmpty()) {
                        System.out.println("Cadastre uma partida antes.");
                    } else {
                        System.out.println(partidaDAO);
                    }
                break;
            case "4":
                if (this.partidaDAO.listar().isEmpty()) {
                    System.out.println("Cadastre uma partida antes.");
                } else {
                    System.out.print("Digite o nome da partida que deseja atualizar: ");
                    String partidaAtualizar = this.leitora.lerTexto();
                    if (partidaDAO.consultar(partidaAtualizar) == null) {
                        System.out.println("Partida não encontrada");
                    } else {
                        partidaDAO.alterar(this.leitora.atualizarPartida(partidaAtualizar));
                    }
                }    
                break;
            case "5":
                if (this.partidaDAO.listar().isEmpty()) {
                    System.out.println("Cadastre uma partida antes.");
                } else {
                    System.out.print("Digite o nome da partida a ser consultada: ");
                    String partidaPesquisada = this.leitora.lerTexto();
                    if (partidaDAO.consultar(partidaPesquisada) == null) {
                        System.out.println("Partida não encontrada");
                    } else {
                        System.out.println(partidaDAO.consultar(partidaPesquisada));
                    }
                }    
                break;
            case "6":
                System.out.print("Digite o nome da Partida: ");
                Partida partidaProcurada = partidaDAO.consultar(this.leitora.lerTexto());
                if (partidaProcurada == null) {
                    System.out.println("Partida não encontrada");
                    break;
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
                System.out.print("Digite o nome da Partida: ");
                Partida partidaIngresso = partidaDAO.consultar(this.leitora.lerTexto());
                if (partidaIngresso == null) {
                    System.out.println("Partida não encontrada");
                } else {
                    System.out.println(partidaIngresso.getIngressos());
                }
                break;
            case "9":
                System.out.print("Digite o nome da Partida: ");
                Partida partidaIngressos = partidaDAO.consultar(this.leitora.lerTexto());
                if (partidaIngressos == null) {
                    System.out.println("Partida não encontrada");
                } else {
                    List<Ingresso> listaIngressos = ingressoDAO.listar();

                    List<Ingresso> ingressosPartida = new ArrayList<>();
                    for (Ingresso ingresso : listaIngressos) {
                        if (ingresso.getPartida().equals(partidaIngressos)) {
                            ingressosPartida.add(ingresso);
                        }
                    }

                    if (ingressosPartida.isEmpty()) {
                        System.out.println("Não há ingressos vendidos para estar partida");
                    } else {
                        for (Ingresso ingresso : ingressosPartida) {
                            System.out.println(ingresso + "\n");
                        }
                    }
                }
                break;
            case "10":
                List<Ingresso> listarIngressos = ingressoDAO.listar();
                if (listarIngressos.isEmpty()) {
                    System.out.println("Um ingresso ainda não foi vendido.");
                } else {
                    Ingresso ultimoIngresso = listarIngressos.get(ingressoDAO.listar().size() -1);
                    System.out.println(ultimoIngresso);
                }
                break;
            default:
                ingressoDAO.exportar();
                partidaDAO.exportar();
                System.out.println("Volte sempre!");
                opcao = ""; 
        }
        return opcao;
    }
}