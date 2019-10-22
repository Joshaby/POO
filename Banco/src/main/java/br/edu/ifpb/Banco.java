package br.edu.ifpb;

import java.util.*;

public class Banco implements Iterable<Conta> {
    private String nome;
    private int qtde_Contas;
    private TreeSet<Conta> contas;

    public Banco() { this("--sem nome--", Comparator.naturalOrder()); }
    public Banco(String nome) { this(nome, Comparator.naturalOrder()); }
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
    public boolean remover_Conta(int numero) { return contas.remove(buscar_Conta(numero)); }
    public Conta buscar_Conta(int numero) {
        return contas.stream()
                .filter(conta -> conta.getNumero() == numero)
                .findFirst().orElseThrow();
    }
    public TreeSet<Conta> filtrar_Conta(int numero, int numero1) {
        return (TreeSet<Conta>) contas.subSet(buscar_Conta(numero), true, contas.ceiling(buscar_Conta(numero1)), true);
    }
    public String exibir_Conta(int numero) { return buscar_Conta(numero).toString(); }

    @Override
    public Iterator<Conta> iterator() { return contas.iterator(); }
}
