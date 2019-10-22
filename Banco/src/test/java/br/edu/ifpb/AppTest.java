package br.edu.ifpb;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class AppTest {
    Banco banco = new Banco();
    Conta c1 = new Conta(0, "José", 3456.90);
    Conta c2 = new Conta(1, "v5u6vu7j", 3456.90);
    Conta c3 = new Conta(2, "tfx rt34", 3456.90);
    Conta c4 = new Conta(3, "Arthur", 3456.90);
    Conta c4Clone = new Conta(3, "Arthur", 3456.90);

    @Before
    public void adiciona_Contas() {
        banco.adicionar_Conta(c1);
        banco.adicionar_Conta(c2);
        banco.adicionar_Conta(c3);
        banco.adicionar_Conta(c4);
    }

    @Test
    public void adicionou_Conta() {
        banco.adicionar_Conta(c4Clone);

        assertThat(banco, contains(c1, c2, c3, c4));
    }

    @Test
    public void removeu_Conta() {
        banco.remover_Conta(c2);

        assertThat(banco, not(contains(c2)));
    }
}