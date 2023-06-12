package br.ufsm.csi.model;

import java.util.List;

public class Item {
    private int id;
    private String name;
    private Boolean status;
    private List list;

    public Item(int id, String name, Boolean status, List list) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.list = list;
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
