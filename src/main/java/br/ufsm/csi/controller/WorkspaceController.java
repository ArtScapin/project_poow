package br.ufsm.csi.controller;

import br.ufsm.csi.dao.UserDAO;
import br.ufsm.csi.model.User;
import br.ufsm.csi.service.UserService;
import br.ufsm.csi.service.WorkspaceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register-workspace")
class RegisterWorkspace extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new UserService().getUser(req.getCookies());
        if(user != null){
            String name = req.getParameter("name");
            new WorkspaceService().register(name, user);
            resp.sendRedirect("./workspaces");
        }
        resp.sendRedirect("./");
    }
}
