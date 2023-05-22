package com.vsu.controller.user;

import com.vsu.entity.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepository;
import com.vsu.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/edit")
public class EditUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = new UserService(new UserRepository(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        User existingUser;
        try {
            existingUser = userService.selectByIdUser(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher( "/jsp/edit_user.jsp");
            req.setAttribute("user", existingUser);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}

