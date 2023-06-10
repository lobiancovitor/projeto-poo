package dao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entidades.Partida;
import util.Arquivo;

public class PartidaDAO {

    private List<Partida> partidas = new ArrayList<>();
    private String caminho;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PartidaDAO(String caminho) throws IOException {
        this.caminho = caminho;
        this.importar();
    }

    public void adicionar(String[] dadosPartida) {
        Partida partida = new Partida(dadosPartida[0], LocalDate.parse(dadosPartida[1], dtf), dadosPartida[2], Integer.parseInt(dadosPartida[3]), Integer.parseInt(dadosPartida[4]), Double.parseDouble(dadosPartida[5]));
        if(!this.partidas.contains(partida)) {
            this.partidas.add(partida);
        }
    }

    public void alterar(String[] dadosPartida) {
        Partida partida = new Partida(dadosPartida[0], LocalDate.parse(dadosPartida[1], dtf), dadosPartida[2], Integer.parseInt(dadosPartida[3]), Integer.parseInt(dadosPartida[4]), Double.parseDouble(dadosPartida[5]));
        int posicao = this.partidas.indexOf(partida);
        if (posicao >= 0) {
            this.partidas.set(posicao, partida);
        }
    }

    public void excluir(String nome) {
        Partida partida = new Partida(nome);
        if (this.partidas.contains(partida)) {
            this.partidas.remove(partida);
        }
    }

    public Partida consultar(String nome) {
        Partida partidaPesquisada = new Partida(nome);
        for (Partida partida : this.partidas) {
            if (partida.equals(partidaPesquisada)) {
                return partida;
            }
        }
        return null;
    }

    public List<Partida> listar() {
        return Collections.unmodifiableList(this.partidas);
    }

    public void exportar() throws IOException {
        List<String> linhas = new ArrayList<>();

        for (Partida partida : this.partidas) {
            linhas.add(partida.toString());
        }
        Arquivo.escrever(this.caminho, linhas);
    }

    public void importar() throws IOException {
        List<String> linhas = Arquivo.ler(this.caminho);

        for (String linha : linhas) {
            String[] dadosPartida = linha.split(" - ");
            this.adicionar(dadosPartida);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Partida partida : this.partidas) {
            sb.append(partida);
            sb.append("\n\n");
        }
        return sb.toString();
    }
}