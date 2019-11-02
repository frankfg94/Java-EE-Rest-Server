<%-- 
    Document   : index
    Created on : 20 sept. 2019, 09:31:27
    Author     : JAA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
          <link rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

            <!-- Reinitialisation des paramètres, ugly pour l'instant , plus tard il faudra regroupper toutes les données dans une Java Bean et la mettre à null -->
            <c:set var="isAdmin" value="${false}" scope="session" />
            <c:if test="${not empty requestScope.errKey}">
                <p style="color:red">${requestScope.errKey}</p>
            </c:if> 
                <form  name ='myform' action='LoginPageController' method="POST">
            Login :<input type='text' placeholder="Login"  name='loginField'><br/>
            Password :<input type='password' placeholder="Password" name='pwdField'><br/>
            <input type='submit' name='action' value='ok'>
        </form>
    </body>
</html>
