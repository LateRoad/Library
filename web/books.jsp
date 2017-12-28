<%@ page import="com.lateroad.library.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Roula
  Date: 27.12.2017
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
                <li><a href="/books.html">Books</a></li>
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
                <li><a>${username}</a></li>
                <li><a href=${inOrOutAdress}><span class="glyphicon glyphicon-log-${inOrOut}"></span> ${inOrOutLabel}
                </a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="pull-right">
        <c:forEach var="book" items="${bookList.rows}">
            <br>
            Name: <c:out value="${book.name}"/>
            <br>
            Author: <c:out value="${book.author}"/>
            <br>
            <c:if test="${book.login != null}">
                Borrowed by: <c:out value="${book.login}"/>
            </c:if>
            <c:if test="${book.login == null}">
                <button formmethod="post" type="submit" name="action" value="borrow_${book.id}" class="btn btn-primary">Borrow ${book.name}</button>
            </c:if>
            <br>
        </c:forEach>
    </div>
</div>
</body>
</html>
