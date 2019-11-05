package br.edu.ifpb;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class TestBanco {
    public static void main(String[] args) {
        start_Test_Banco();
    }

    public static void show_Gui(String name) {
        System.out.printf("Banco: %s\n", name);
        System.out.println("1 - Adcionar conta\n" +
                        "2 - Remover conta\n" +
                        "3 - Filtrar contas\n" +
                        "4 - Exibir dados da conta\n" +
                        "5 - Exibir todas as contas\n" +
                        "6 - Total de contas\n" +
                        "7 - Gravar contas\n" +
                        "8 - Sair\n" +
                        "  Digite uma opção: ");
    }

    public static void start_Test_Banco() {
        int cond = 0;
        boolean cond1 = true;
        Banco b = new Banco("Banco do Brasil - BB");
        while (cond1) {
            try (b) {
                b.start_Contas();
                Scanner input = new Scanner(System.in);
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
                            System.out.println("Conta adicionada!\n");
                        }
                        else throw new ContaException(String.format("Já existe conta com o número %s\n", numero));
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
                        System.out.print("Primeiro número: ");
                        numero = input.nextInt();
                        System.out.print("Segundo número: ");
                        int numero1 = input.nextInt();
                        for (Conta c : b.filtrar_Conta(numero, numero1, mod)) {
                            System.out.println(c);
                        }
                        break;
                    case 4 :
                        System.out.print("Número: ");
                        numero = input.nextInt();
                        System.out.printf("Conta: %s\n\n", b.exibir_Conta(numero));
                        break;
                    case 5 :
                        System.out.printf("Contas:\n%s\n\n", b.exibir_Conta());
                        break;
                    case 6 :
                        System.out.printf("Total de contas: %d\n\n", b.qtde_Contas());
                        break;
                    case 7 :
                        throw new BancoException();
                    case 8 :
                        b.write_Contas();
                        cond1 = false;
                        break;
                }
            }
            catch (BancoException | ContaException | DadosException | IOException e) {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                System.out.println("Entrada inválida!\n");
            }
        }
    }
}
