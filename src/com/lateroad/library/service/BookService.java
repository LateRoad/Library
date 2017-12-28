package com.lateroad.library.service;

import com.lateroad.library.database.dao.BookDAO;
import com.lateroad.library.entity.Book;
import com.lateroad.library.exception.UserNotFoundException;

import java.util.List;

public class BookService {
    private BookDAO bookDAO = BookDAO.getInstance();
    public List<Book> getBookList() throws UserNotFoundException {
        List<Book> books = null;
        if ((books = bookDAO.findAll()) != null){
            return books;
        }
        throw new UserNotFoundException();
    }
}
