package br.edu.ifpb;

public class Teste {
    public static void main(String[] args) {
        Conta conta = new Conta(0, "José", 23456.90);
        Banco banco = new Banco("Banco do Brasil", Conta.COMPARAR_NUM);
        banco.adicionar_Conta(conta);
        System.out.println(banco.exibir_Conta(0));
    }
}
