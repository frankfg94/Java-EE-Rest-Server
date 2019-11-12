<%@page import="se.m1.utils.Constants"%>
<!DOCTYPE html>

<nav class="navbar navbar-light bg-light fixed-top">
    <%
        String s = (String) request.getSession().getAttribute("username");
        out.print("<a>Hello " + s + " your session is Active! </a>");
    %> 
    <form action="Controller" method="GET">
        <input type="submit" class="btn btn-outline-secondary" name="action" value="Disconnect">
    </form>
</nav>