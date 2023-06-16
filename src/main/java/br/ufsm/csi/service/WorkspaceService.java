package br.ufsm.csi.service;


import br.ufsm.csi.dao.WorkspaceDAO;
import br.ufsm.csi.model.User;
import br.ufsm.csi.model.Workspace;

public class WorkspaceService {
    public boolean register(String name, User user){
        Workspace workspace = new Workspace(name, user);
        return new WorkspaceDAO().create(workspace);
    }
}
