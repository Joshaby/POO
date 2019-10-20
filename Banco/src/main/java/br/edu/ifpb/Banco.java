package br.edu.ifpb;

import java.util.*;

public class Banco implements Iterable<Conta> {
    private String nome;
    private int qtde_Contas;
    private NavigableSet<Conta> contas;
    
    public Banco(String nome, Comparator<Conta> comparator) {
        this.nome = Objects.requireNonNullElse(nome, "--sem nome'--");
        Comparator<Conta> comp = Objects.requireNonNullElse(comparator, Comparator.naturalOrder());
        contas = new TreeSet(comp);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = Objects.requireNonNullElse(nome, "--sem nome--"); }

    public boolean adicionar_Conta(int numero, String titular, double saldo) {
        return contas.add(new Conta(numero, titular, saldo));
    }
    public boolean remover_Conta(int numero) { return contas.remove(buscar_Conta(numero)); }
    public Optional<Conta> buscar_Conta(int numero) {
        return contas.stream().filter(conta -> conta.getNumero() == numero).findFirst();
    }
    
    @Override
    public Iterator<Conta> iterator() { return contas.iterator(); }
}