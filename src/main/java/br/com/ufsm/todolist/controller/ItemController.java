package br.com.ufsm.todolist.controller;

import br.com.ufsm.todolist.model.Item;
import br.com.ufsm.todolist.model.List;
import br.com.ufsm.todolist.model.User;
import br.com.ufsm.todolist.model.Workspace;
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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ListRepository listRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/item")
    public String index(HttpServletRequest request, Model model){
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        List list = this.listRepository.findById(Long.parseLong(request.getParameter("list"))).orElse(null);;
                        if (list != null && list.getWorkspace().getId().equals(workspace.getId())) {
                            java.util.List<Item> items = this.itemRepository.findAllByListIdOrderById(list.getId());
                            model.addAttribute("user", user);
                            model.addAttribute("workspace", workspace);
                            model.addAttribute("list", list);
                            model.addAttribute("items", items);
                            return "app/item";
                        }
                    }
                }
            }
        }

        return "redirect:/";
    }


    @PostMapping("/register-item")
    public String createList(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        List list = this.listRepository.findById(Long.parseLong(request.getParameter("list"))).orElse(null);
                        if (list != null && list.getWorkspace().getId().equals(workspace.getId())) {
                            String name = request.getParameter("name");
                            Item item = new Item(name, list);
                            this.itemRepository.save(item);
                            return "redirect:/item?workspace="+workspace.getId()+"&list="+list.getId();
                        }
                    }
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping("/change-status-item")
    public String changeStatusItem(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        List list = this.listRepository.findById(Long.parseLong(request.getParameter("list"))).orElse(null);
                        if (list != null && list.getWorkspace().getId().equals(workspace.getId())) {
                            System.out.println("ok");
                            Item item = this.itemRepository.findById(Long.parseLong(request.getParameter("item"))).orElse(null);
                            if(item != null && item.getList().getId().equals(list.getId())) {
                                System.out.println("ok");
                                item.setStatus(!item.getStatus());
                                this.itemRepository.save(item);
                                return "redirect:/item?workspace="+workspace.getId()+"&list="+list.getId();
                            }
                        }
                    }
                }
            }
        }

        return "redirect:/";
    }

    @PostMapping("/update-item")
    public String updateItem(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("editWorkspace2"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        List list = this.listRepository.findById(Long.parseLong(request.getParameter("editList2"))).orElse(null);
                        if (list != null && list.getWorkspace().getId().equals(workspace.getId())) {
                            Item item = this.itemRepository.findById(Long.parseLong(request.getParameter("editItem2"))).orElse(null);
                            if (item != null && item.getList().getId().equals(list.getId())) {
                                item.setName(request.getParameter("editNameItem"));
                                this.itemRepository.save(item);
                                return "redirect:/item?workspace="+workspace.getId()+"&list="+list.getId();
                            }
                        }
                    }
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping("/delete-item")
    public String deleteItem(HttpServletRequest request) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("user")) {
                User user = userRepository.findById(Long.parseLong(EncryptionUtils.decrypt(cookie.getValue()))).orElse(null);
                if (user != null) {
                    Workspace workspace = this.workspaceRepository.findById(Long.parseLong(request.getParameter("workspace"))).orElse(null);
                    if (workspace != null && workspace.getUser().getId().equals(user.getId())) {
                        List list = this.listRepository.findById(Long.parseLong(request.getParameter("list"))).orElse(null);
                        if (list != null && list.getWorkspace().getId().equals(workspace.getId())) {
                            Item item = this.itemRepository.findById(Long.parseLong(request.getParameter("item"))).orElse(null);
                            if (item != null && item.getList().getId().equals(list.getId())) {
                                this.itemRepository.delete(item);
                                return "redirect:/item?workspace="+workspace.getId()+"&list="+list.getId();
                            }
                        }
                    }
                }
            }
        }

        return "redirect:/";
    }
}
