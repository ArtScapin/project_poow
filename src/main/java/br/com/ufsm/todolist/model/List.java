package br.com.ufsm.todolist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lists")
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_workspace", nullable = false)
    private Workspace workspace;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
    private java.util.List<Item> items;

    public List() {
    }

    public List(String name, Workspace workspace) {
        this.name = name;
        this.workspace = workspace;
    }

    public List(Long id, String name, Workspace workspace) {
        this.id = id;
        this.name = name;
        this.workspace = workspace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workspace=" + workspace +
                ", items=" + items +
                '}';
    }
}

