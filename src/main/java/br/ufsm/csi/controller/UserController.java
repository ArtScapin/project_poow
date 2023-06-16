package br.ufsm.csi.controller;

import br.ufsm.csi.dao.UserDAO;
import br.ufsm.csi.dao.WorkspaceDAO;
import br.ufsm.csi.model.User;
import br.ufsm.csi.model.Workspace;
import br.ufsm.csi.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/login", "/register"})
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if("/login".equals(servletPath)){
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            RequestDispatcher dispatcher;
            if(new UserService().authenticate(email, password)){
                User user = new UserDAO().getUser(email);
                req.setAttribute("user", user);

                ArrayList<Workspace> workspaces = new WorkspaceDAO().getWorkspaces(user);
                req.setAttribute("workspaces", workspaces);

                dispatcher = req.getRequestDispatcher("/WEB-INF/index.jsp");
            }else{
                dispatcher = req.getRequestDispatcher("/index.jsp");
            }

            dispatcher.forward(req, resp);
        } else if ("/register".equals(servletPath)){
            System.out.println("register");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");

            RequestDispatcher dispatcher;
            if(password.equals(confirmPassword)){
                if(new UserService().register(name, email, password)){
                    dispatcher = req.getRequestDispatcher("/index.jsp");
                }else{
                    dispatcher = req.getRequestDispatcher("/register.jsp");
                }
            }else{
                dispatcher = req.getRequestDispatcher("/register.jsp");
            }

            dispatcher.forward(req, resp);
        }
    }

}
