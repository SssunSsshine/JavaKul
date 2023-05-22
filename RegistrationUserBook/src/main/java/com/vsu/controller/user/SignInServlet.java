package com.vsu.controller.user;

import com.vsu.entity.User;
import com.vsu.exception.ValidationException;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepository;
import com.vsu.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = new UserService(new UserRepository(new ConnectionFactory()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = userService.getByLoginAndPassword(login, password);
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/user/home");
        } catch (ValidationException e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
            req.setAttribute("error", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }
}