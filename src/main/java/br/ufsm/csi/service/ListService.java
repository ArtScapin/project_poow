package br.ufsm.csi.service;

import br.ufsm.csi.dao.ListDAO;
import br.ufsm.csi.dao.WorkspaceDAO;
import br.ufsm.csi.model.List;
import br.ufsm.csi.model.User;
import br.ufsm.csi.model.Workspace;

public class ListService {
    public boolean register(String name, Workspace workspace){
        List list = new List(name, workspace);
        return new ListDAO().create(list);
    }
}
