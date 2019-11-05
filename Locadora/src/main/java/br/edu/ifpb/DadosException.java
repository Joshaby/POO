package br.edu.ifpb;

public class DadosException extends Exception {
    public DadosException() { super("O banco de dados está vazio!"); }
    public DadosException(String message) { super(message); }
}
