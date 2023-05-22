package com.vsu.controller.book;

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

import java.io.IOException;

@WebServlet("/book/delete")
public class DeleteBookServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init(ServletConfig config) {
        bookService = new BookService(new BookRepository(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            bookService.deleteBook(Long.parseLong(id));
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/book_form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/book/all");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

