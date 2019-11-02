<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <link rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            // Reinitialisation des paramètres
            request.setAttribute("isAdmin", false);
            request.setAttribute("state", "loginAttempt");

            if (request.getAttribute("errKey") != null) {
                out.print((String) request.getAttribute("errKey"));
            }
        %>
        <form  name ='myform' action='Controller' method="POST">
            Login :<input type='text' placeholder="Login"  name='loginField'><br/>
            Password :<input type='password' placeholder="Password" name='pwdField'><br/>
            <input type='submit' name='action' value='Connect'>
        </form>


    </body>
</html>
