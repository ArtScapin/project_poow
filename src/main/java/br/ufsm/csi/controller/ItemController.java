package br.ufsm.csi.controller;

import br.ufsm.csi.dao.ItemDAO;
import br.ufsm.csi.dao.ListDAO;
import br.ufsm.csi.dao.WorkspaceDAO;
import br.ufsm.csi.model.Item;
import br.ufsm.csi.model.List;
import br.ufsm.csi.model.User;
import br.ufsm.csi.model.Workspace;
import br.ufsm.csi.service.ItemService;
import br.ufsm.csi.service.ListService;
import br.ufsm.csi.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register-item")
class RegisterItem extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new UserService().getUser(req.getCookies());
        if(user != null){
            String name = req.getParameter("name");
            Workspace workspace = new WorkspaceDAO().getWorkspace(Integer.parseInt(req.getParameter("workspace")), user);
            List list = new ListDAO().getList(Integer.parseInt(req.getParameter("list")), workspace);
            if(workspace != null && list != null){
                new ItemService().register(name, list);
                resp.sendRedirect("./item?workspace="+workspace.getId()+"&list="+list.getId());

            } else {
                resp.sendRedirect("./");
            }
            resp.sendRedirect("./");
        }
    }
}
@WebServlet("/change-status-item")
class ChangeStatusItem extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new UserService().getUser(req.getCookies());
        if(user != null){
            Workspace workspace = new WorkspaceDAO().getWorkspace(Integer.parseInt(req.getParameter("workspace")), user);
            List list = new ListDAO().getList(Integer.parseInt(req.getParameter("list")), workspace);
            Item item = new ItemDAO().getItem(Integer.parseInt(req.getParameter("item")), list);
            if(workspace != null && list != null && item != null){
                new ItemService().changeStatus(item);
                resp.sendRedirect("./item?workspace="+workspace.getId()+"&list="+list.getId());
            } else {
                resp.sendRedirect("./");
            }
            resp.sendRedirect("./");
        }
    }
}