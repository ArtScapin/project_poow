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

@WebServlet("/")
class IndexPage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }
}

@WebServlet("/register")
class RegisterPage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("/register.jsp");
        dispatcher.forward(req, resp);
    }
}
@WebServlet("/workspaces")
class WorkspacesPage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new UserService().getUser(req.getCookies());

        if(user != null){
            req.setAttribute("user", user);
            ArrayList<Workspace> workspaces = new WorkspaceDAO().getWorkspaces(user);
            req.setAttribute("workspaces", workspaces);
            RequestDispatcher dispatcher;
            dispatcher = req.getRequestDispatcher("/WEB-INF/workspace.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("./");
        }

    }
}
@WebServlet("/list")
class ListsPage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new UserService().getUser(req.getCookies());
        System.out.println("ok");

        if(user != null){
            req.setAttribute("user", user);
            Workspace workspace = new WorkspaceDAO().getWorkspace(Integer.parseInt(req.getParameter("workspace")), user);
            req.setAttribute("workspace", workspace);
            ArrayList<String> lists = new ArrayList<>();
            lists.add("id: "+workspace.getId());
            lists.add("name: "+workspace.getName());
            lists.add("created_by: "+workspace.getUser().getName());

            req.setAttribute("lists", lists);
            RequestDispatcher dispatcher;
            dispatcher = req.getRequestDispatcher("/WEB-INF/list.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("./");
        }

    }
}

