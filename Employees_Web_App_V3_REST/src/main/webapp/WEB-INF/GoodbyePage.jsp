<%@page import="se.m1.utils.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
    function redirect(elem) {
        elem.setAttribute("action", "LoginPage.jsp");
        elem.submit();
    }
</script>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/goodbyepagestyle.css" media="screen">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Goodbye</title>
    </head>
    <body>
        <div class="wrapper">
            <form class="form-signin" action="Controller" method="POST">
                <h1 class="form-signin-heading text-center">Goodbye!</h1>
                <h2 class="form-signin-heading text-center">Your disconnection is sucessful</h2>
                <input class="btn btn-primary btn-block" type="submit" name="action" value="Go to the Login Page"/>
            </form>
        </div>
    </body>
</html>

