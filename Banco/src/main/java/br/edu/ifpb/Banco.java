package br.edu.ifpb;

import java.util.*;

public class Banco implements Iterable<Conta>, AutoCloseable {
    private String nome;
    private TreeSet<Conta> contas;

    public Banco() { this("--sem nome--", Comparator.naturalOrder()); }
    public Banco(String nome) { this(nome, Comparator.naturalOrder()); }
    public Banco(Comparator<Conta> comparator) { this("--sem nome--", comparator); }
    public Banco(String nome, Comparator<Conta> comparator) {
        this.nome = Objects.requireNonNullElse(nome, "--sem nome--");
        Comparator<Conta> comp = Objects.requireNonNullElse(comparator, Comparator.naturalOrder());
        contas = new TreeSet<>(comp);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = Objects.requireNonNullElse(nome, "--sem nome--"); }

    public boolean adicionar_Conta(int numero, String titular, double saldo) {
        return contas.add(new Conta(numero, titular, saldo));
    }
    public boolean adicionar_Conta(Conta conta) {
        return contas.add(conta);
    }
    public boolean remover_Conta(int numero) throws ContaException { return contas.remove(buscar_Conta(numero)); }
    public boolean remover_Conta(Conta c) { return contas.remove(c); }
    public Conta buscar_Conta(int numero) throws ContaException {
        return contas.stream()
                .filter(conta -> conta.getNumero() == numero).findFirst().orElseThrow(() -> new ContaException("A conta não existe"));
    }
    public String filtrar_Conta(int numero, int numero1) throws ContaException {
        TreeSet<Conta> contas_FIL = (TreeSet<Conta>) contas
                .subSet(buscar_Conta(numero), true, buscar_Conta(numero1), true);
        StringBuilder string = new StringBuilder(" ");
        for (Conta c : contas) {
            string.append(c);
        }
        return string.toString();
    }
    public String exibir_Conta(int numero) throws ContaException { return buscar_Conta(numero).toString(); }
    public String exibir_Contas() {
        StringBuilder string = new StringBuilder();
        for (Conta c : contas) { string.append(c); }
        return string.toString();
    }
    public int qtde_Contas() { return contas.size(); }

    @Override
    public Iterator<Conta> iterator() { return contas.iterator(); }

    @Override
    public void close() throws BancoException { System.out.println("Fechando o banco! Adeus"); }

    @Override
    public String toString() {
        return String.format(
                "Nome do banco: %s\n" +
                "Qtde. de contas: %i\n" +
                "Contas:\n\n %s", getNome(), qtde_Contas(), exibir_Contas());
    }
}