package br.com.ufsm.todolist.controller;

import br.com.ufsm.todolist.dto.UserRequest;
import br.com.ufsm.todolist.model.User;
import br.com.ufsm.todolist.repositories.UserRepository;
import br.com.ufsm.todolist.util.EncryptionUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        return "user/singIn";
    }

    @GetMapping("/register")
    public String register() {
        return "user/singUp";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    model.addAttribute("user", user);
                    return "user/user";
                }
            }
        }

        return "redirect:/";
    }

    @PostMapping("/login-user")
    public String loginUser(HttpServletResponse response, UserRequest userRequest) throws IOException {
        User user = this.userRepository.findByEmail(userRequest.getEmail());
        if(user != null && userRequest.getPassword().equals(user.getPassword())){
            Cookie cookie = new Cookie("user", EncryptionUtils.encrypt(user.getId().toString()));
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return "redirect:/workspaces";
        }

        return "redirect:/";
    }

    @PostMapping("/register-user")
    public String createUser(UserRequest userRequest) {
        if(userRequest.getPassword().equals(userRequest.getConfirmPassword()) && this.userRepository.findByEmail(userRequest.getEmail()) == null){
            User user =  userRequest.toUser();
            this.userRepository.save(user);
            return "redirect:/?msg=1";
        }

        return "redirect:/register";
    }

    @PostMapping("/update-user")
    public String updateUser(HttpServletRequest request, UserRequest userRequest) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    if(userRequest.getPassword().equals(userRequest.getConfirmPassword()) && userRequest.getPassword().equals(user.getPassword())){
                        if(user.getEmail().equals(userRequest.getEmail()) || this.userRepository.findByEmail(userRequest.getEmail()) == null) {
                            user = userRequest.toUser(user.getId());
                            this.userRepository.save(user);
                        }
                    }
                }
            }
        }

        return "redirect:/user";
    }

    @GetMapping("/logout-user")
    public String logoutUser(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", null);
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
