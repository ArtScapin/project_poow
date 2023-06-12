package br.ufsm.csi.model;

public class Lists {
    private int id;
    private String name;
    private  Workspace workspace;

    public Lists(int id, String name, Workspace workspace) {
        this.id = id;
        this.name = name;
        this.workspace = workspace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }
}
