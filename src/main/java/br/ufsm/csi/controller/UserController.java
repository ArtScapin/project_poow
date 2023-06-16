package br.ufsm.csi.controller;

import br.ufsm.csi.dao.UserDAO;
import br.ufsm.csi.model.User;
import br.ufsm.csi.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-user")
class LoginUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (new UserService().authenticate(email, password)) {
            User user = new UserDAO().getUser(email);
            Cookie cookie = new Cookie("user", user.getId()+"");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
            resp.sendRedirect("./workspaces");
        } else {
            resp.sendRedirect("./");
        }
    }
}

@WebServlet("/register-user")
class RegisterUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

@WebServlet("/update-user")
class UpdateUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new UserService().getUser(req.getCookies());

        if(user != null) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String newPassword = req.getParameter("newPassword");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");

            if (password.equals(confirmPassword)) {
                if(name != null)
                    user.setName(name);
                if(email != null)
                    user.setEmail(email);
                if(newPassword != null)
                    user.setPassword(newPassword);
                new UserService().update(user);
            }

            resp.sendRedirect("./user");
        }
    }
}

@WebServlet("/logout-user")
class LogoutUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie cookie = new Cookie("user", null);
        cookie.setMaxAge(1);
        resp.addCookie(cookie);
        resp.sendRedirect("./");
    }
}
