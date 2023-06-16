package br.ufsm.csi.controller;

import br.ufsm.csi.dao.WorkspaceDAO;
import br.ufsm.csi.model.User;
import br.ufsm.csi.model.Workspace;
import br.ufsm.csi.service.ListService;
import br.ufsm.csi.service.UserService;
import br.ufsm.csi.service.WorkspaceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register-list")
class RegisterList extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new UserService().getUser(req.getCookies());
        if(user != null){
            String name = req.getParameter("name");
            Workspace workspace = new WorkspaceDAO().getWorkspace(Integer.parseInt(req.getParameter("workspace")), user);
            if(workspace != null){
                new ListService().register(name, workspace);
                resp.sendRedirect("./list?workspace="+workspace.getId());

            } else {
                resp.sendRedirect("./");
            }
            resp.sendRedirect("./");
        }
    }
}