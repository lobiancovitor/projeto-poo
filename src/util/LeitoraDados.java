package util;

import java.util.Scanner;

import entidades.Assento;
import entidades.Partida;
import entidades.ingresso.Ingresso;
import entidades.ingresso.IngressoFactory;
import entidades.ingresso.TipoIngresso;

public class LeitoraDados {
    private Scanner scanner;

    public LeitoraDados() {
        this.scanner = new Scanner(System.in);
    }

    public String lerTexto() {
        return scanner.nextLine();
    }

    public String coletaData() {

        StringBuilder dataSB = new StringBuilder();

        System.out.print("Insira o dia da partida (dd): ");
        String dia = scanner.nextLine();

        while (!validaDataDia(dia)) {
            System.out.print("Dia inválido.\nInsira um dia válido: ");
            dia = scanner.nextLine();
        }

        System.out.print("Insira o mês da partida (mm): ");
        String mes = scanner.nextLine();

        while (!validaDataMes(mes)) {
            System.out.print("Mês inválido.\nInsira um mês válido: ");
            mes = scanner.nextLine();
        }

        System.out.print("Insira o ano da partida (yyyy): ");
        String ano = scanner.nextLine();

        while (!validaDataAno(ano)) {
            System.out.print("Ano inválido.\nInsira um ano válido: ");
            ano = scanner.nextLine();
        }

        dataSB.append(String.format("%02d", Integer.parseInt(dia))).append("/");
        dataSB.append(String.format("%02d", Integer.parseInt(mes))).append("/");
        dataSB.append(ano);

        return dataSB.toString();
    }

    public String[] lerNovaPartida() {

        String nome;
        String local;
        String data;
        int ingressosInt;
        int ingressosMeia;
        double valor;

        System.out.println("Insira as informações da partida:");
        System.out.print("Nome da partida: ");
        nome = scanner.nextLine();

        data = coletaData(); 

        System.out.print("Local da partida: ");
        local = scanner.nextLine();

        System.out.print("Número de ingressos tipo inteira: ");
        ingressosInt = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de ingressos tipo meia: ");
        ingressosMeia = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Valor do ingresso: ");
        valor = scanner.nextDouble();
        scanner.nextLine();

        return new String[] {nome, data, local, Integer.toString(ingressosInt), Integer.toString(ingressosMeia), Double.toString(valor)};
    
    }

    public String[] atualizarPartida(Partida partida) {

        String local;
        String data;
        String nome;
        int ingressosInt;
        int ingressosMeia;
        double valor;

        System.out.println("Insira as novas informações para a partida:");

        data = coletaData(); 

        System.out.print("Local da partida: ");
        local = scanner.nextLine();

        System.out.print("Número de ingressos tipo inteira: ");
        ingressosInt = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de ingressos tipo meia: ");
        ingressosMeia = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Valor do ingresso: ");
        valor = scanner.nextDouble();
        scanner.nextLine();

        return new String[] {partida.getNome(), data, local, Integer.toString(ingressosInt), Integer.toString(ingressosMeia), Double.toString(valor)};
    
    }

    public String[] lerNovoIngresso(Partida partidaVenda) {

        String opcaoIngresso;
        char letraAssento;
        TipoIngresso tipo;
        int numeroAssento;
        Assento assento;

        while (true) {
            System.out.print("Letra do assento: ");
            letraAssento = scanner.next().charAt(0);
            System.out.print("Número do assento: ");
            numeroAssento = scanner.nextInt();
            scanner.nextLine();
            assento = new Assento(numeroAssento, letraAssento);

            if (partidaVenda.assentoOcupado(assento)) {
                System.out.println("Assento já ocupado, informe outro.");
                continue;
            } else {
                break;
            }

        }

        System.out.print("O seu ingresso é meia (s/n)? ");
        opcaoIngresso = scanner.nextLine();

        if (opcaoIngresso.equals("s")) {
            tipo = TipoIngresso.MEIA;
        } else {
            tipo = TipoIngresso.INTEIRA;
        }

        if (partidaVenda.venderIngresso(tipo)) {
            Ingresso ingresso = IngressoFactory.novoIngresso(partidaVenda, tipo, assento);
        
            partidaVenda.addIngresso(ingresso);
        }

        return new String[] {partidaVenda.toString(), tipo.toString(), assento.toString()};
    }

    public boolean validaDataDia(String dia) {
        int diaInt;
        try {
            diaInt = Integer.parseInt(dia);
        } catch (NumberFormatException e) {
            return false;
        }
        return diaInt >= 1 && diaInt <= 31;
    }

    public boolean validaDataMes(String mes) {
        int mesInt;
        try {
            mesInt = Integer.parseInt(mes);
        } catch (NumberFormatException e) {
            return false;
        }
        return mesInt >= 1 && mesInt <= 12;
    }

    public boolean validaDataAno(String ano) {
        int anoInt;
        try {
            anoInt = Integer.parseInt(ano);
        } catch (NumberFormatException e) {
            return false;
        }
        return anoInt >= 999;
    }

}