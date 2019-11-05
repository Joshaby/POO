package br.edu.ifpb;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class AppTest {
    Banco banco = new Banco();
    Conta c1 = new Conta(0, "José", 6.90);
    Conta c2 = new Conta(1, "v5u6vu7j", 3456.90);
    Conta c3 = new Conta(2, "tfxrt34", 56.90);
    Conta c4 = new Conta(3, "Arthur", 456.90);
    Conta c5 = new Conta(4, "Arthur", 3456.90);
    Conta c6 = new Conta(5, "Arthu", 3456.90);
    Conta c4Clone = new Conta(3, "Arthur", 3456.90);

    @Before
    public void adiciona_Contas() throws IOException, DadosException {
        banco.start_Contas();
    }

    @Test
    public void adicionou_Contas() {
        assertThat(banco, contains(c1, c2, c3, c4, c5));
    }

    @Test
    public void adiconou_Conta() throws IOException {
        banco.adicionar_Conta(c6);

        assertThat(banco, hasItem(c6));
    }

    @Test
    public void removeu_Conta() {
        banco.remover_Conta(c2);

        assertThat(banco, not(hasItem(c2)));
    }

    @Test
    public void hashSet_To_TXT() throws IOException {
        banco.write_Contas();
    }

    @Test
    public void filtrar_Contas() throws ContaException {
        Set<Conta> AUX = banco.filtrar_Conta(1, 1000, "Saldo");

        System.out.println(AUX);
    }
}