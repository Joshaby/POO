package br.edu.ifpb;

public class DvdException extends Exception {
    DvdException() { super("O dvd não existe!"); }
    DvdException(String message) { super(message); }
}
