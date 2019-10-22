package br.edu.ifpb;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public abstract class Funcionario implements Comparable<Funcionario> {
    private String nome;
    private String sobrenome;
    private int matricula;
    private double salario;

    public static final Comparator<Funcionario> COMPARADOR_NOME = Comparator.comparing(Funcionario::getNome);
    public static final Comparator<Funcionario> COMPARADOR_SALARIO = Comparator.comparing(Funcionario::getSalario);

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = Objects.requireNonNullElse(nome, "--sem nome--"); }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = Objects.requireNonNullElse(sobrenome, "--sem sobrenome--");
    }

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = (matricula < 0) ? 0 : matricula; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = (salario < 0) ? 0 : salario; }

    @Override
    public int compareTo(Funcionario outro) { return Integer.compare(getMatricula(), outro.getMatricula()); }
}
