package br.ufsm.csi.controller;

import br.ufsm.csi.dao.UserDAO;
import br.ufsm.csi.dao.WorkspaceDAO;
import br.ufsm.csi.model.User;
import br.ufsm.csi.model.Workspace;
import br.ufsm.csi.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login-user")
class LoginUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (new UserService().authenticate(email, password)) {
            User user = new UserDAO().getUser(email);
            resp.addCookie(new Cookie("user", user.getId()+""));
            resp.sendRedirect("./workspaces");
        } else {
            resp.sendRedirect("./");
        }
    }
}

@WebServlet("/register-user")
class RegisterUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if(password.equals(confirmPassword)){
            if(new UserService().register(name, email, password)){
                resp.sendRedirect("./");
            }else{
                resp.sendRedirect("./register");
            }
        }else{
            resp.sendRedirect("./register");
        }
    }
}
