
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="se.m1.utils.Constants"%>
<!DOCTYPE html>
<nav class="navbar navbar-light bg-light fixed-top">
    <a>Hello ${sessionScope.userBean.login} your session is Active!</a>
    <form action="Controller" method="GET">
        <input type="submit" class="btn btn-outline-secondary" name="action" value="Disconnect">
    </form>
</nav>



