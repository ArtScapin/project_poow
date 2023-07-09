package br.ufsm.csi.service;

import br.ufsm.csi.dao.ItemDAO;
import br.ufsm.csi.model.Item;
import br.ufsm.csi.model.List;

public class ItemService {
    public boolean register(String name, List list){
        Item item = new Item(name, list);
        return new ItemDAO().create(item);
    }
    public boolean changeStatus(Item item){
        return new ItemDAO().changeStatus(item);
    }
}
