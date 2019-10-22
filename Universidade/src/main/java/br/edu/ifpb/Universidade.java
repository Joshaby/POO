package br.edu.ifpb;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Universidade implements Iterable<Funcionario> {
    private String nome;
    private int qtde_Funcionarios;
    private TreeSet<Funcionario> funcionarios;
    private Object ArrayList;

    public Set<Funcionario> so_Professor() {
        return funcionarios.stream()
                .filter(funcionarios -> funcionarios instanceof Professor).collect(Collectors.toSet());
    }
    @Override
    public Iterator<Funcionario> iterator() { return funcionarios.iterator(); }
}
