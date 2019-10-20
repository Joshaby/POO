package br.edu.ifpb;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

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
    public void setNome(String nome) {
        this.nome = Objects.requireNonNullElse(nome, "--sem nome--");
    }
    
    @Override
    public Iterator<Conta> iterator() { return contas.iterator(); }

}