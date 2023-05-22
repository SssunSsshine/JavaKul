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
import java.util.List;

@WebServlet("/book/all")
public class ShowBooksServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookService = new BookService(new BookRepository(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Book> bookList = bookService.selectAllBooksByUser(user.getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/book_list.jsp");
        req.setAttribute("books", bookList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

