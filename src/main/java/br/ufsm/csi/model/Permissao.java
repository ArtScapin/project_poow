package br.ufsm.csi.model;

public class Permissao {
    public Permissao() {
    }
    public Permissao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
