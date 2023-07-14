package br.com.ufsm.todolist.controller;

import br.com.ufsm.todolist.model.User;
import br.com.ufsm.todolist.repositories.ItemRepository;
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



@Controller
public class DashboardController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ListRepository listRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/dashboard")
    public String index(HttpServletRequest request, Model model) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    model.addAttribute("user", user);
                    model.addAttribute("totalWorkspaces", this.workspaceRepository.countByUser_Id(user.getId()));
                    model.addAttribute("totalLists", this.listRepository.countByWorkspace_User_Id(user.getId()));
                    model.addAttribute("totalItems", this.itemRepository.countByList_Workspace_User_Id(user.getId()));
                    model.addAttribute("completedItems", this.itemRepository.countByList_Workspace_User_IdAndStatusTrue(user.getId()));
                    model.addAttribute("inProgressItems", this.itemRepository.countByList_Workspace_User_IdAndStatusFalse(user.getId()));
                    return "app/dashboard";
                }
            }
        }

        return "redirect:/";
    }
}
