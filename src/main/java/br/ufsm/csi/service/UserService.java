package br.ufsm.csi.service;

import br.ufsm.csi.dao.UserDAO;
import br.ufsm.csi.model.User;

import javax.servlet.http.Cookie;

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

    public boolean update(User user){
        return new UserDAO().update(user);
    }

    public User getUser(Cookie[] cookies){
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("user")){
                return new UserDAO().getUser(Integer.parseInt(cookie.getValue()));
            }
        }
        return null;
    }

    public boolean canUseMail(String email){
        User user = new UserDAO().getUser(email);
        if(user != null){
            return false;
        }
        return true;
    }

}
