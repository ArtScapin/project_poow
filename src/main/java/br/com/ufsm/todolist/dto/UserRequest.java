package br.com.ufsm.todolist.dto;


import br.com.ufsm.todolist.model.User;
import br.com.ufsm.todolist.util.EncryptionUtils;

public class UserRequest {
    private String name;
    private String email;
    private String newPassword;
    private String password;
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public User toUser() {
        User user = new User();
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        return user;
    }
    public User toUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        if(this.getNewPassword() != null && !this.getNewPassword().equals("")){
            user.setPassword(this.getNewPassword());
        } else {
            user.setPassword(this.getPassword());
        }
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = EncryptionUtils.encrypt(password);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = EncryptionUtils.encrypt(confirmPassword);
    }
}
