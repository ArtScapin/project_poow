package br.com.ufsm.todolist.controller;

import br.com.ufsm.todolist.model.List;
import br.com.ufsm.todolist.model.User;
import br.com.ufsm.todolist.model.Workspace;
import br.com.ufsm.todolist.repositories.ListRepository;
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

@Controller
public class ListController {
    @Autowired
    private ListRepository listRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String index(HttpServletRequest request, Model model){
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                    if(workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        java.util.List<List> lists = this.listRepository.findAllByWorkspaceId(workspace.getId());
                        model.addAttribute("user", user);
                        model.addAttribute("workspace", workspace);
                        model.addAttribute("lists", lists);
                        return "app/list";
                    }
                }
            }
        }

        return "redirect:/";
    }

    @PostMapping("/register-list")
    public String createList(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        String name = request.getParameter("name");
                        List list = new List(name, workspace);
                        this.listRepository.save(list);
                    }
                    return "redirect:/list?workspace=" + workspace.getId();
                }
            }
        }

        return "redirect:/";
    }

    @PostMapping("/update-list")
    public String updateList(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("editWorkspace"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        List list = this.listRepository.findById(Long.parseLong(request.getParameter("editList"))).orElse(null);
                        if(list != null && list.getWorkspace().getId().equals(workspace.getId())) {
                            list.setName(request.getParameter("editName"));
                            this.listRepository.save(list);
                            return "redirect:/item?workspace="+workspace.getId()+"&list="+list.getId();
                        }
                    }
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping("/delete-list")
    public String deleteList(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        List list = this.listRepository.findById(Long.parseLong(request.getParameter("list"))).orElse(null);
                        if(list != null && list.getWorkspace().getId().equals(workspace.getId())) {
                            this.listRepository.delete(list);
                            return "redirect:/list?workspace="+workspace.getId();
                        }
                    }
                }
            }
        }

        return "redirect:/";
    }
}
