package br.edu.ifpb;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LocadoraTest {
    Locadora l = new Locadora();
    Cliente c = new Cliente(0, "José");
    Cliente c1 = new Cliente(1, "Henrique");
    Dvd d = new Dvd(0,"FF3", "Ação");
    Dvd d1 = new Dvd(1, "FF3", "Ação");

    @Test
    public void printarMap() {
        try {
            l.adicionar_Historico_Emprestimo(c, d);
            l.adicionar_Historico_Emprestimo(c1, d);

        } catch (DvdException e) {
            System.out.println(e.getMessage());
        }
    }

    @Before
    public void cadastrar_Clientes_Dvds() {
        l.adicionar_Cliente(c);
        l.adicionar_Cliente(c1);
        l.adicionar_Dvd(d);
        l.adicionar_Dvd(d1);
    }

    @Test
    public void test() throws DadosException, IOException {
        l.start_Collections(l.CLIENTES_TXT, l.CLIENTES);
        System.out.println(l.getClientes());

        l.start_Collections(l.HISTORICO_TXT, l.HISTORICO);
        System.out.println(l.getHistorico());

        l.start_Collections(l.DVDS_TXT, l.DVDS);
        System.out.println(l.getDvds());
    }
}
