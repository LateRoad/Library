package com.lateroad.library.entity;

import java.util.Objects;

public class Book extends Entity {
    private int id;
    private String name;
    private String author;
    private String login;

    public Book(){}

    public Book(int id, String name, String author, String login) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(name, book.name) &&
                Objects.equals(author, book.author) &&
                Objects.equals(login, book.login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, author, login);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
