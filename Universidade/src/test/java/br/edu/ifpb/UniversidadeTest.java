package br.edu.ifpb;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UniversidadeTest {
    Universidade uni = new Universidade("IFPB");
    Funcionario f1 = new Professor("José", "Henrique", 0, 1273.90);
    Funcionario f2 = new Professor("try", "catch", 1, 2273.90);
    Funcionario f3 = new Gerente("MUX", "DEMUX", 2, 573.90, 5000.90, 0.1);
    Funcionario f4 = new Gerente("Cesar", ".", 2, 573.90, 6000.90, 0.17);
    Funcionario f4Clone = new Gerente("Cesar", ".", 2, 573.90, 6000.90, 0.17);

    @Before
    public void adicionar_Funcionarios() {
        uni.adicionar_Funcionario(f1);
        uni.adicionar_Funcionario(f2);
        uni.adicionar_Funcionario(f3);
        uni.adicionar_Funcionario(f4);
    }

    @Test
    public void adicionou_Funcionario() {
        uni.adicionar_Funcionario(f4Clone);

        assertThat(uni, not(contains(f4Clone)));
        assertThat(uni, contains(f1,f2,f3,f4));
    }

    @Test
    public void buscar_Professores() {
        assertThat(uni.so_Professores(), contains(f1,f2));
    }

    @Test
    public void buscar_Gerentes() {
        assertThat(uni.so_Professores(), contains(f3,f4));
    }
}
