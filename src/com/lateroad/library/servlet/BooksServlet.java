package com.lateroad.library.servlet;

import com.lateroad.library.entity.Book;
import com.lateroad.library.exception.UserNotFoundException;
import com.lateroad.library.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BooksServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            List<Book> bookList = bookService.getBookList();
            session.setAttribute("books", bookList);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/books.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getParameter("action");
        System.out.println(s);
    }
}
