package br.ufsm.csi.service;

import br.ufsm.csi.dao.UserDAO;
import br.ufsm.csi.model.User;

public class UserService {
    public boolean authenticate(String email, String password){
        User user = new UserDAO().getUser(email);
        if(user != null){
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public boolean register(String name, String email, String password){
        User user = new User(name, email, password);
        return new UserDAO().create(user);
    }

}
