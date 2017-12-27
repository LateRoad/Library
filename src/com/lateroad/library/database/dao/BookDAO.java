package com.lateroad.library.database.dao;

import com.lateroad.library.entity.Book;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class BookDAO extends AbstractDAO<Book> {
    public static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM library.book";
    public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM library.book WHERE login=?";

    private static BookDAO instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    public static BookDAO getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new BookDAO();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public Book find(Book book) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void insert(Book entity) {

    }

    @Override
    public Book delete(Book entity) {
        return null;
    }

    @Override
    public Book delete(int id) {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }
}
