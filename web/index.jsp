<%--
  Created by IntelliJ IDEA.
  com.lateroad.library.entity.User: Roula
  Date: 24.12.2017
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
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
                <li><a href=${inOrOutAdress}><span class="glyphicon glyphicon-log-${inOrOut}"></span> ${inOrOutLabel}</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="pull-right">
        <h2>Register</h2>
        <form method="post" class="" style="width: 500px">
            <div class="form-group">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <input type="text" class="form-control" placeholder="Имя" name="name">
                    </div>
                    <div class="col-md-6 mb-3">
                        <input type="text" class="form-control" placeholder="Фамилия" name="surname">
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-default">Регистрация</button>
        </form>
    </div>
</div>

</body>
</html>
