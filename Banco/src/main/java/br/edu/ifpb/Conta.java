package br.edu.ifpb;

import java.util.*;

public class Conta implements Comparable<Conta>{
    private int numero;
    private String titular;
    private double saldo;
    private String extrato = "\tExtrato:\n\n";
    
    public Conta() { this(0, null, 900.00); }
    public Conta(int numero, String titular, double saldo) {
        setNumero(numero);
        setTitular(titular);
        setSaldo(saldo);
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = (numero < 0) ? 0 : numero; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = (saldo < 0) ? 0 : saldo; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { 
        this.titular = Objects.requireNonNullElse(titular, "--sem nome--");
    }
    
    public String extrato() { return this.extrato; }

    public boolean depositar(double quantia) {
        if (quantia >= 0) {
            saldo += quantia;
            extrato += String.format("\t\tDepósito feito de R$ %.2f\n", quantia);
            return true;
        }
        return false;
    }
    public boolean sacar(double quantia) {
        if (quantia <= saldo && saldo != 0) {
            double nova_Quantia = descontar_CPMF(quantia);
            saldo -= nova_Quantia;
            extrato += String.format("\t\tSaque feito de R$ %.2f (Com desconto de CPMF)\n", nova_Quantia);
            return true;
        }
        return false;
    }

    private double calcular_CPMF(double quantia) { return quantia * 0.01; }
    private double descontar_CPMF(double quantia) { return quantia - calcular_CPMF(quantia); }

    @Override
    public String toString() {
        return String.format("%d %s %.2f %s", numero, titular, saldo, extrato);
    }

    @Override
    public int hashCode() { return Objects.hashCode(getNumero()); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Conta other = (Conta) obj;
        return numero == other.numero;
    }

    @Override
    public int compareTo(Conta arg0) { return Integer.compare(getNumero(), arg0.getNumero()); }
}