package br.edu.ifpb;

import java.util.*;

public class Locadora implements Iterable<Map.Entry<Cliente, HashSet<Dvd>>> {
    private String nome;
    private HashSet<Cliente> clientes;
    private HashSet<Dvd> dvds;
    public HashMap<Cliente, HashSet<Dvd>> historico;

    public Locadora() { this("--sem nome--"); }
    public Locadora(String nome) {
        setNome(nome);
        clientes = new HashSet<>();
        dvds = new HashSet<>();
        historico = new HashMap<Cliente, HashSet<Dvd>>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean adicionar_Cliente(Cliente c) { return clientes.add(c); }
    public boolean remover_Cliente(int id) throws ClienteException {
        return clientes.remove(clientes.stream()
                .filter(clientes -> clientes.getId() == id).findFirst().orElseThrow(ClienteException::new));
    }

    public boolean adicionar_Dvd(Dvd d) { return dvds.add(d); }
    public boolean remover_Dvd(int id) throws DvdException {
        return clientes.remove(clientes.stream()
                .filter(dvds -> dvds.getId() == id).findFirst().orElseThrow(DvdException::new));
    }

    public void adicionar_Historico_Emprestimo(Cliente c, Dvd d) throws DvdException {
        if (!historico.containsKey(c)) { historico.put(c, new HashSet<Dvd>(Arrays.asList(d))); }
        else {
            HashSet<Dvd> aux = historico.get(c);
            if (aux.contains(d)) throw new DvdException("O dvd já foi locado pelo cliente");
            else { aux.add(d); }
        }
    }
    public void remover_Historico_Emprestimo(Cliente c) throws ClienteException {
        if (!historico.containsKey(c)) throw new ClienteException("O cliente não possui emprêstimo!");
        else {
            HashSet<Dvd> aux = historico.get(c);
            aux.clear();
        }
    }
    public void remover_Historico_Emprestimo(Cliente c, Dvd d) throws ClienteException, DvdException {
        if (!historico.containsKey(c)) throw new ClienteException("O cliente não possui emprêstimo!");
        else {
            HashSet<Dvd> aux = historico.get(c);
            if (!aux.contains(d)) throw new DvdException("O dvd não foi locado pelo cliente");
            else { aux.remove(d); }
        }
    }

    @Override
    public Iterator<Map.Entry<Cliente, HashSet<Dvd>>> iterator() {
        return historico.entrySet().iterator();
    }
}