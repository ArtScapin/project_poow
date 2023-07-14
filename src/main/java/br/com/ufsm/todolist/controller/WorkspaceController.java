package br.com.ufsm.todolist.controller;

import br.com.ufsm.todolist.model.User;
import br.com.ufsm.todolist.model.Workspace;
import br.com.ufsm.todolist.repositories.UserRepository;
import br.com.ufsm.todolist.repositories.WorkspaceRepository;
import br.com.ufsm.todolist.util.EncryptionUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WorkspaceController {
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/workspaces")
    public String index(HttpServletRequest request, Model model){
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    List<Workspace> workspaces = this.workspaceRepository.findAllByUserId(user.getId());
                    model.addAttribute("user", user);
                    model.addAttribute("workspaces", workspaces);
                    return "app/workspaces";
                }
            }
        }

        return "redirect:/";
    }

    @PostMapping("/register-workspace")
    public String createWorkspace(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    String name = request.getParameter("name");
                    Workspace workspace = new Workspace(name, user);
                    this.workspaceRepository.save(workspace);
                    return "redirect:/workspaces";
                }
            }
        }

        return "redirect:/";
    }

    @PostMapping("/update-workspace")
    public String updateWorkspace(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("editWorkspace"))).orElse(null);
                if (user != null && workspace != null) {
                    if (workspace.getUser().getId().equals(user.getId())) {
                        String name = request.getParameter("editName");
                        workspace.setName(name);
                        this.workspaceRepository.save(workspace);
                        return "redirect:/list?workspace="+workspace.getId();
                    }
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping("/delete-workspace")
    public String deleteWorkspace(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                if (user != null && workspace != null) {
                    if (workspace.getUser().getId().equals(user.getId())) {
                        this.workspaceRepository.delete(workspace);
                        return "redirect:/workspaces";
                    }
                }
            }
        }

        return "redirect:/";
    }
}
