package com.vsu.controller.book;

import com.vsu.entity.Book;
import com.vsu.entity.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.BookRepository;
import com.vsu.service.BookService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/book/add")
public class AddBookServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init(ServletConfig config) {
        bookService = new BookService(new BookRepository(new ConnectionFactory()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        String year = req.getParameter("year");
        User user = (User) session.getAttribute("user");
        try {
            bookService.insertBook(new Book(author, title, Integer.parseInt(year), user.getId()));
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/book/form");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/user/home");
    }
}
