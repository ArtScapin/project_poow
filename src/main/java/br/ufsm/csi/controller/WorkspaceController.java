package br.ufsm.csi.controller;

import br.ufsm.csi.dao.UserDAO;
import br.ufsm.csi.model.User;
import br.ufsm.csi.service.WorkspaceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register-workspace")
public class WorkspaceController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        if("/register-workspace".equals(servletPath)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            User user = new UserDAO().getUser(email);
            System.out.println("email");
            System.out.println("user"+ user);
            new WorkspaceService().register(name, user);
            resp.sendRedirect("/");
        }
    }
}
