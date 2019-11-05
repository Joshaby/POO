package br.edu.ifpb;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Locadora {
    private String nome;
    private HashSet<Cliente> clientes;
    private HashSet<Dvd> dvds;
    private HashMap<Cliente, HashSet<Dvd>> historico;

    public static final String CLIENTES_TXT = "clientes.txt";
    public static final String DVDS_TXT = "dvds.txt";
    public static final String HISTORICO_TXT = "historico.txt";
    public static final String CLIENTES = "clientes";
    public static final String DVDS = "dvds";
    public static final String HISTORICO = "historico";

    public Locadora() { this("--sem nome--"); }
    public Locadora(String nome) {
        setNome(nome);
        clientes = new HashSet<>();
        dvds = new HashSet<>();
        historico = new HashMap<Cliente, HashSet<Dvd>>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public HashMap<Cliente, HashSet<Dvd>> getHistorico() {
        return historico;
    }

    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    public HashSet<Dvd> getDvds() {
        return dvds;
    }

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
        if (!historico.containsKey(c)) { historico.put(c, new HashSet<Dvd>(Arrays.asList(d))); } //Pq esse Arrays.asList() funcionou em um HashSet?? NEM EU SEI AHSHAUHSAUHSUAHSU
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
    public void start_Collections(String BD, String mod) throws DadosException, IOException {
        if (clientes.isEmpty() || dvds.isEmpty() || historico.isEmpty()) {
            File file = new File(BD);
            if (BD.isEmpty()) {
                throw new DadosException();
            }
            for (String s : Files.readAllLines(Path.of(BD))) {
                String[] AUX = s.split(" ");
                if (mod.equalsIgnoreCase("historico")) {
                    List<String> AUX1 = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(AUX, 2, AUX.length)));
                    HashSet<Dvd> AUX2 = new HashSet<>();
                    for (int i = 0; i < AUX1.size(); i += 3) {
                        AUX2.add(new Dvd(Integer.parseInt(AUX1.get(i)), AUX1.get(i + 1), AUX1.get(i + 2)));
                    }
                    historico.put(new Cliente(Integer.parseInt(AUX[0]), AUX[1]), AUX2);
                }
                if (mod.equalsIgnoreCase("clientes")) {
                    clientes.add(new Cliente(Integer.parseInt(AUX[0]), AUX[1]));
                }
                if (mod.equalsIgnoreCase("dvds")) {
                    dvds.add(new Dvd(Integer.parseInt(AUX[0]), AUX[1], AUX[2]));
                }
            }
        }
    }
    public void write_Collections(String BD, String mod) throws IOException {
        List<String> AUX = new ArrayList<>();
        if (mod.equalsIgnoreCase("historico")) {
            for (Cliente c : historico.keySet()) {
                AUX.add(c + historico.get(c).stream().map(Dvd::toString).collect(Collectors.joining(" ")));
            }
        }
        if (mod.equalsIgnoreCase("clientes")) {
            AUX = clientes.stream().map(Cliente::toString).collect(Collectors.toList());
        }
        if (mod.equalsIgnoreCase("dvds")) {
            AUX = dvds.stream().map(Dvd::toString).collect(Collectors.toList());
        }
        Files.write(Path.of(BD), AUX, Charset.defaultCharset()
                , StandardOpenOption.CREATE
                , StandardOpenOption.TRUNCATE_EXISTING
                , StandardOpenOption.WRITE);
    }
}