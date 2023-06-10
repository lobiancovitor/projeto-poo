package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entidades.Assento;
import entidades.Partida;
import entidades.ingresso.Ingresso;
import entidades.ingresso.IngressoFactory;
import entidades.ingresso.TipoIngresso;
import util.Arquivo;

public class IngressoDAO {
    private List<Ingresso> ingressos = new ArrayList<>();
    private String caminho;
    Ingresso ingresso;
    IngressoFactory ingressoFactory;

    public IngressoDAO(String caminho) throws IOException {
        this.caminho = caminho;
        this.importar();
        this.ingressoFactory = new IngressoFactory();
    }

    public void adicionar(String[] dadosIngresso) { // ?????????????????????????? Como String -> Partida/ Assento?
        Partida partida = Partida.valueOf(dadosIngresso[0]);
        TipoIngresso tipoIngresso = TipoIngresso.valueOf(dadosIngresso[1]);
        Assento assento = Assento.valueOf(dadosIngresso[2]);

        ingresso = ingressoFactory.novoIngresso(partida, tipoIngresso, assento);
        if(!this.ingressos.contains(ingresso)) {
            this.ingressos.add(ingresso);
        }
    }

    public void alterar(String[] dadosIngresso) { // ??????????????????????????
        Partida partida = Partida.valueOf(dadosIngresso[0]);
        TipoIngresso tipoIngresso = TipoIngresso.valueOf(dadosIngresso[1]);
        Assento assento = Assento.valueOf(dadosIngresso[2]);

        Ingresso ingresso = ingressoFactory.novoIngresso(partida, tipoIngresso, assento);
        int posicao = this.ingressos.indexOf(ingresso);
        if (posicao >= 0) {
            this.ingressos.set(posicao, ingresso);
        }
    }

    public void excluir(Partida partida, TipoIngresso tipoIngresso) {
        Ingresso ingresso = ingressoFactory.novoIngresso(partida, tipoIngresso);
        if (this.ingressos.contains(ingresso)) {
            this.ingressos.remove(ingresso);
        }
    }

    public Ingresso consultar(Partida partida, TipoIngresso tipoIngresso) {
        Ingresso ingressoPesquisado = ingressoFactory.novoIngresso(partida, tipoIngresso);
        for (Ingresso ingresso : this.ingressos) {
            if (ingresso.equals(ingressoPesquisado)) {
                return ingresso;
            }
        }
        return null;
    }

    public List<Ingresso> listar() {
        return Collections.unmodifiableList(this.ingressos);
    }

    public void exportar() throws IOException {
        List<String> linhas = new ArrayList<>();

        for (Ingresso ingresso : this.ingressos) {
            linhas.add(ingresso.toString());
        }
        Arquivo.escrever(this.caminho, linhas);
    }

    public void importar() throws IOException {
        List<String> linhas = Arquivo.ler(this.caminho);

        for (String linha : linhas) {
            String[] dadosIngresso = linha.split(" - ");
            this.adicionar(dadosIngresso);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Ingresso ingresso : this.ingressos) {
            sb.append(ingresso);
            sb.append("\n");
        }
        return sb.toString();
    }
}
