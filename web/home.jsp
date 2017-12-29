<%--
  Created by IntelliJ IDEA.
  com.lateroad.library.entity.User: Roula
  Date: 24.12.2017
  Time: 20:09
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
                <li><a href="/books.html">Книги</a></li>
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
    <h1>Мой кабинет</h1>
    <div class="col-md-2 col-md-offset-5">
        <h2>Мои книги</h2>
        <c:forEach var="book" items="${myBooks}">
            <form method="POST" style="background-color: #CCCCCC">
                <div class="form-group">
                    <h4>Название: <c:out value="${book.name}"/></h4>
                    <h4>Автор: <c:out value="${book.author}"/></h4>
                </div>
            </form>
        </c:forEach>
    </div>
</div>
</body>
</html>
