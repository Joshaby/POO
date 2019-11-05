package br.edu.ifpb;

public class Dvd {
    private String nome;
    private int id;
    private String genero;

    public Dvd() { this(0, "--sem nome--", "--sem genero--"); }
    public Dvd(int id, String nome, String genero) {
        setNome(nome);
        setId(id);
        setGenero(genero);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public String toString() {
        return String.format("%d %s %s", getId(), getNome(), getGenero());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return id == dvd.id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }
}