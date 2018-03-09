package com.lateroad.library.servlet;

import com.lateroad.library.bundle.Bundle;
import com.lateroad.library.entity.Book;
import com.lateroad.library.entity.User;
import com.lateroad.library.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    private BookService bookService = BookService.getInstance();

    public HomeServlet(){
        Bundle.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Book> bookList = bookService.getBookListByLogin(((User) session.getAttribute("user")).getLogin());
        session.setAttribute("myBooks", bookList);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
