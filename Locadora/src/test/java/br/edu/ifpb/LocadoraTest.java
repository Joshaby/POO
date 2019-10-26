package br.edu.ifpb;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class LocadoraTest {
    Locadora l = new Locadora();
    Cliente c = new Cliente("José", 0);
    Cliente c1 = new Cliente("José", 1);
    Cliente c2 = new Cliente("José", 2);
    Cliente c3 = new Cliente("José", 3);
    Dvd d = new Dvd("FF3", 0, "Ação");
    Dvd d1 = new Dvd("FF3", 1, "Ação");
    Dvd d2 = new Dvd("FF3", 2, "Ação");
    Dvd d3 = new Dvd("FF3", 3, "Ação");

    @Test
    public void printarMap() {
        try {
            l.adicionar_Historico_Emprestimo(c, d);
            System.out.println(l.historico);
            l.adicionar_Historico_Emprestimo(c, d);
            System.out.println(l.historico);
        } catch (DvdException e) {
            System.out.println(e.getMessage());
        }
    }

    @Before
    public void cadastrar_Clientes_Dvds() {
        l.adicionar_Cliente(c);
        l.adicionar_Cliente(c1);
        l.adicionar_Cliente(c2);
        l.adicionar_Cliente(c3);
        l.adicionar_Dvd(d);
        l.adicionar_Dvd(d1);
        l.adicionar_Dvd(d2);
        l.adicionar_Dvd(d3);
    }
}
