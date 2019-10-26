package br.edu.ifpb;

public class ClienteException extends Exception {
    ClienteException() { super("O cliente não existe!"); }
    ClienteException(String message) { super(message); }
}

