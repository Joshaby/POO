package br.edu.ifpb;

import java.util.Iterator;

public class Cliente implements Iterable<Cliente> {
    private String nome;
    private int id;

    public Cliente() {
        this("--sem nome--", 0);
    }

    public Cliente(String nome, int id) {
        setNome(nome);
        setId(id);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return String.format(
                "Nome: %s " +
                "Id: %d ", getNome(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }

    @Override
    public Iterator<Cliente> iterator() {
        return null;
    }
}
