<%--
  Created by IntelliJ IDEA.
  User: Roula
  Date: 27.12.2017
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.html">Library</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/books.html">Книги</a></li>
                <c:if test="${user.role == \"admin\"}">
                    <li><a href="/users.html">Пользователи</a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">русский
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">русский</a></li>
                        <li><a href="#">беларускі</a></li>
                        <li><a href="#">english</a></li>
                    </ul>
                </li>
                <c:if test="${user != null }">
                    <li><a href="/home.html">${user.login}</a></li>
                    <li><a href="/logout.html"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
                </c:if>
                <c:if test="${user == null }">
                    <li><a href="/signin.html"><span class="glyphicon glyphicon-log-in"></span> Войти</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <c:if test="${user.role == \"admin\"}">
        <h1>Книги, ожидающие одобрения на выдачу:</h1>
        <c:forEach var="wantedBook" items="${wantedBooks}">
            <form method="POST">
                <div class="form-group">
                    <br>
                    Название: <c:out value="${wantedBook.name}"/>
                    <br>
                    Автор: <c:out value="${wantedBook.author}"/>
                    <br>
                    <c:out value="${wantedBook.login}"/> подал заявку.
                    <button name="action" value="accept_${wantedBook.id}_${wantedBook.login}" type="submit" class="btn btn-primary">Одобрить
                    </button>
                    <br>
                </div>
            </form>
        </c:forEach>
    </c:if>

    <h1>Все книги:</h1>
    <c:forEach var="book" items="${books}">
        <form method="POST">
            <div class="form-group">
                <br>
                Название: <c:out value="${book.name}"/>
                <br>
                Автор: <c:out value="${book.author}"/>
                <br>
                <c:if test="${book.login != null}">
                    Взято: <c:out value="${book.login}"/>
                </c:if>
                <c:if test="${book.login == null && user.role == \"user\"}">
                    <button name="action" value="borrow_${book.id}" type="submit" class="btn btn-primary">Взять
                    </button>
                </c:if>
                <c:if test="${book.login != null && user.role == \"admin\"}">
                    <button name="action" value="unborrow_${book.id}" type="submit" class="btn btn-primary">Забрать
                    </button>
                </c:if>
                <br>
            </div>
        </form>
    </c:forEach>

</div>
</body>
</html>
