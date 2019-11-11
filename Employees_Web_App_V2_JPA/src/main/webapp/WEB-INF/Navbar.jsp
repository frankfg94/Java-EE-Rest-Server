
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="se.m1.utils.Constants"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <h2>Hello ${sessionScope.userBean.login} your session is Active! </h2>
    <div class="collapse navbar-collapse">
        <form action="Controller" method="GET">
            <input type="submit" class="btn btn-outline-secondary" name="action" value="Disconnect">
        </form>
    </div>
</nav>



