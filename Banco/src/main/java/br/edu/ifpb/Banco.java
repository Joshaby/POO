package br.edu.ifpb;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Banco implements Iterable<Conta>, AutoCloseable {
    public static final String CONTAS_TXT = "contas.txt";
    private String nome;
    private HashSet<Conta> contas;

    public Banco() { this("--sem nome--"); }
    public Banco(String nome) {
        this.nome = Objects.requireNonNullElse(nome, "--sem nome--");
        contas = new HashSet<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = Objects.requireNonNullElse(nome, "--sem nome--"); }

    public Set<Conta> getContas() { return Collections.unmodifiableSet(contas); }

    public void start_Contas() throws IOException, DadosException {
        if (contas.isEmpty()) {
            File file = new File(CONTAS_TXT);
            if (file.exists()) {
                if (file.length() < 1) {
                    throw new DadosException();
                }
                Set<String> AUX = new HashSet<>(Files.readAllLines(Path.of(CONTAS_TXT)));
                for (String c : AUX) {
                    String[] AUX1 = c.split(" ");
                    String AUX2 = AUX1[2].replace(',', '.');
                    contas.add(new Conta(Integer.parseInt(AUX1[0]), AUX1[1], Double.parseDouble(AUX2)));
                }
            }
            else throw new DadosException("O banco de dados não existe!");
        }
    }
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
    public Set<Conta> filtrar_Conta(int numero, int numero1, String mod) throws ContaException {
        Set<Conta> AUX = null;
        if (mod.equalsIgnoreCase("Saldo")) AUX = contas.stream()
                .filter(Conta -> Conta.getSaldo() >= numero && Conta.getSaldo() <= numero1).collect(Collectors.toSet());
        if(mod.equalsIgnoreCase("Número")) AUX = contas.stream()
                .filter(Conta -> Conta.getNumero() >= numero && Conta.getNumero() <= numero1).collect(Collectors.toSet());
        return AUX;
    }
    public String exibir_Conta(int numero) throws ContaException { return buscar_Conta(numero).toString(); }
    public String exibir_Conta() {
        StringBuilder string = new StringBuilder();
        for (Conta c : contas) { string.append(c); }
        return string.toString();
    }
    public int qtde_Contas() { return contas.size(); }
    public void write_Contas() throws IOException {
        Files.write(Path.of(CONTAS_TXT), contas.stream().map(Conta::toString).collect(Collectors.toSet())
                , Charset.defaultCharset()
                , StandardOpenOption.CREATE
                , StandardOpenOption.TRUNCATE_EXISTING
                , StandardOpenOption.WRITE);
    }

    @Override
    public Iterator<Conta> iterator() { return contas.iterator(); }

    @Override
    public void close() throws BancoException { System.out.println("Fechando o banco! Adeus"); }

    @Override
    public String toString() {
        return String.format(
                "Nome do banco: %s\n" +
                "Qtde. de contas: %i\n" +
                "Contas:\n\n %s", getNome(), qtde_Contas(), exibir_Conta());
    }
}