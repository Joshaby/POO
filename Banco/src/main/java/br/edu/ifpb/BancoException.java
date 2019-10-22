package br.edu.ifpb;

public class BancoException extends Exception {
    BancoException() { super("Erro ao fechar o banco!"); }
    BancoException(String message) { super(message); }
}
