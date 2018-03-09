package com.lateroad.library.servlet;

import com.lateroad.library.bundle.Bundle;
import com.lateroad.library.entity.Book;
import com.lateroad.library.entity.User;
import com.lateroad.library.exception.ItemNotFoundException;
import com.lateroad.library.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BooksServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BooksServlet.class);
    private BookService bookService = BookService.getInstance();

    public BooksServlet(){
        Bundle.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            List<Book> bookList = bookService.getBookList();
            session.setAttribute("books", bookList);
        } catch (ItemNotFoundException e) {
            LOGGER.error(e);
        }
        if (session.getAttribute("user") != null && "admin".equals(((User) session.getAttribute("user")).getRole())) {
            session.setAttribute("wantedBooks", bookService.getWantedBookList());
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/books.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String reqParameter = req.getParameter("action");
        String action = reqParameter.split("_")[0];
        String bookID = reqParameter.split("_")[1];
        String currentUser = ((User) session.getAttribute("user")).getLogin();
        switch (action) {
            case "borrow":
                bookService.addWantedBook(bookID, currentUser);
                break;
            case "accept":
                String userWhoBorrow = reqParameter.split("_")[2];
                bookService.acceptBeBorrowed(bookID, userWhoBorrow);
                break;
            case "unborrow":
                bookService.unborrow(bookID);
                break;
            default:
                LOGGER.error("Undefined action");
                break;
        }
    }
}
