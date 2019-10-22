package br.edu.ifpb;

import java.io.File;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        try(Banco banco = new Banco("Banco do Brasil");) {
            banco.adicionar_Conta(new Conta(0, "José", 23456.90));
            banco.adicionar_Conta(new Conta(1, "José", 23456.90));
            banco.adicionar_Conta(new Conta(2, "José", 23456.90));
            System.out.println(banco.buscar_Conta(2).toString());
        }
        catch (ContaException | BancoException e) { System.out.println(e.getMessage()); }
    }
}
