package com.lateroad.library.database.dao;

import com.lateroad.library.database.DBPool;
import com.lateroad.library.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class BookDAO implements AbstractDAO<Book> {
    public static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM library.book";
    public static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM library.book WHERE login=?";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM library.book WHERE id=?";
    public static final String SQL_UPDATE_LOGIN = "UPDATE library.book SET login=? WHERE id=?";

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

    public Book find(String id) {
        DBPool dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        Book book = null;
        try (PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ID)) {
            st.setString(1, id);
            try(   ResultSet resultSet = st.executeQuery()){
                while (resultSet.next()) {
                    book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setName(resultSet.getString("name"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setLogin(resultSet.getString("login"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            dbPool.putConnection(cn);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        DBPool dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (Statement st = cn.createStatement();
             ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_BOOKS)) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setLogin(resultSet.getString("login"));

                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            dbPool.putConnection(cn);
        }
        return books;
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
    public void update(Book book) {
        DBPool dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (PreparedStatement st = cn.prepareStatement(SQL_UPDATE_LOGIN)) {
            st.setString(1, book.getLogin());
            st.setInt(2, book.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            dbPool.putConnection(cn);
        }
    }

    public List<Book> findByLogin(String login) {
        List<Book> books = new ArrayList<>();
        DBPool dbPool = DBPool.getInstance();
        Connection cn = dbPool.getConnection();
        try (PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_LOGIN)){
            st.setString(1, login);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setLogin(resultSet.getString("login"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            dbPool.putConnection(cn);
        }
        return books;
    }
}
