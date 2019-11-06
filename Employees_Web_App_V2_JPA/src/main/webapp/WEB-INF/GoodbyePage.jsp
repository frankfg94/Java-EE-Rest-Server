<%-- 
    Document   : GoodbyePage
    Created on : 18 oct. 2019, 16:24:24
    Author     : franc
--%>

<%@page import="se.m1.utils.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
        function redirect(elem){
        elem.setAttribute("action","LoginPage.jsp");
        elem.submit();
}
</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disconnected</title>
    </head>
    <body>
        <form action="Controller" method="POST">
            <h1>Goodbye!</h1>
            <h2>Your disconnection is sucessful</h2>
            <input type="submit" name="action" value="Go to the Login Page"/>
        </form>
    </body>
</html>

