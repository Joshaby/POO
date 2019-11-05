package br.edu.ifpb;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class TestBanco {
    public static void main(String[] args) {

    }

    public static void show_Gui(String name) {
        System.out.printf("Banco: %s\n", name);
        System.out.println("1 - Adcionar conta\n" +
                        "2 - Remover conta\n" +
                        "3 - Filtrar contas\n" +
                        "4 - Buscar conta\n" +
                        "5 - Exibir dados da conta\n" +
                        "6 - Exibir todas as contas\n" +
                        "7 - Total de contas\n" +
                        "8 - Gravar contas\n" +
                        "8 - Sair\n" +
                        "  Digite uma opção: ");
    }

    public static void start_Test_Banco() {
        try (Banco b = new Banco("Banco do Brasil - BB");) {
            int cond = 0;
            Scanner input = new Scanner(System.in);
            while (cond != 8) {
                show_Gui("Banco do Brasil - BB");
                cond = input.nextInt();
                switch (cond) {
                    case 1 :
                        System.out.print("Nome do cliente: ");
                        String nome = input.next();
                        System.out.print("Número para a conta: ");
                        int numero = input.nextInt();
                        System.out.print("Saldo: ");
                        double saldo = input.nextDouble();
                        if (b.adicionar_Conta(numero, nome, saldo)) {
                            System.out.println("Conta adicionada!");
                        }
                        else throw new ContaException(String.format("Já existe conta com o número %s", numero));
                        break;
                    case 2 :
                        System.out.print("Número : ");
                        numero = input.nextInt();
                        if (b.remover_Conta(numero)) {
                            System.out.println("Conta removida!");
                        }
                        else throw new ContaException();
                        break;
                    case 3 :
                        System.out.print("Filtral por número ou saldo? ");
                        String mod = input.next();
                        System.out.print("Primeiro número : ");
                        numero = input.nextInt();
                        System.out.print("Segundo número : ");
                        int numero1 = input.nextInt();
                        for (Conta c : b.filtrar_Conta(numero, numero1, mod)) {
                            System.out.println(c);
                        }
                }
            }
        }
        catch (BancoException | ContaException | DadosException e) {
            System.out.println(e);
        }
    }
}
