package br.edu.ifpb;

public class ContaException extends Exception {
    ContaException() { super("A conta não existe"); }
    ContaException(String message) { super(message); }
}
