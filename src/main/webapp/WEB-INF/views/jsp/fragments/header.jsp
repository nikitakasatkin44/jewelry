<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
    <title>Jewelry site</title>
    <script src="/resources/jquery/jquery-3.2.1.js"></script>
    <script src="/resources/js/script.js"></script>

    <spring:url value="/resources/css/style.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.min.css"
                var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/users/add" var="urlRegister" />
<spring:url value="/login" var="urlLogin" />

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">

        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${urlHome}">Домашняя страница</a></li>
                <li><a href="#">О нас</a></li>
                <li><a href="#">Галерея</a></li>
                <li><a href="#">Контакты</a></li>
                <li><a href="/users">Пользователи</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href=${urlLogin}>Логин</a></li>
                <li><a href=${urlRegister}>Регистрация</a></li>
            </ul>
        </div>
    </div>
</nav>