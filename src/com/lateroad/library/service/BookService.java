package com.lateroad.library.service;

import com.lateroad.library.database.dao.BookDAO;
import com.lateroad.library.entity.Book;
import com.lateroad.library.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class BookService {
    private static BookService instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static BookService getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new BookService();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private BookDAO bookDAO;
    private List<Book> wantedBooks;

    private BookService() {
        bookDAO = BookDAO.getInstance();
        wantedBooks = new ArrayList<>();
    }

    public List<Book> getBookList() throws ItemNotFoundException {
        List<Book> books = null;
        if ((books = bookDAO.findAll()) != null) {
            return books;
        }
        throw new ItemNotFoundException();
    }

    public List<Book> getWantedBookList() {
       return wantedBooks;
    }

    public void addWantedBook(String bookID, String login) {
        Book book = bookDAO.find(bookID);
        book.setLogin(login);
        wantedBooks.add(book);
    }

    public void acceptBeBorrowed(String bookID, String login) {
        Book book = bookDAO.find(bookID);
        book.setLogin(login);
        bookDAO.update(book);
        wantedBooks.remove(book);
    }

    public void unborrow(String bookID) {
        Book book = bookDAO.find(bookID);
        book.setLogin(null);
        bookDAO.update(book);
    }

    public List<Book> getBookListByLogin(String login) {
        List<Book> books = null;
        if ((books = bookDAO.findByLogin(login)) != null) {
            return books;
        }
        return books;
    }
}
