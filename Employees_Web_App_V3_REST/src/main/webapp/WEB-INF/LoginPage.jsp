<%@page import="se.m1.utils.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="previousPageUrl" value="<%=Constants.JSP_GOODBYE_PAGE%>" scope="request" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/loginstyle.css" media="screen">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion page</title>
    </head>
    <body>

        <c:if test="${not empty requestScope.errKey}">
            <p style="color:red">${requestScope.errKey}</p>
        </c:if> 
        <div class="wrapper">
            <form class="form-signin" name ='myform' action='Controller' method="POST">
                <h2 class="form-signin-heading text-center">Login</h2>
                <input type='text' class="form-control" name='loginField' placeholder="Login" autofocus="">
                <input type='password' class="form-control" name='pwdField' placeholder="Password" autofocus="">
                <input type='submit' class="btn btn-primary btn-block" name='action' value='Connect'>
            </form>
        </div>
    </body>
</html>
