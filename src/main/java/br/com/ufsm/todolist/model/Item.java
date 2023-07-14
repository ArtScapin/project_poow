package br.com.ufsm.todolist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_list", nullable = false)
    private List list;

    public Item() {
    }

    public Item(String name,List list) {
        this.name = name;
        this.status = false;
        this.list = list;
    }

    public Item(Long id, String name, Boolean status, List list) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.list = list;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
