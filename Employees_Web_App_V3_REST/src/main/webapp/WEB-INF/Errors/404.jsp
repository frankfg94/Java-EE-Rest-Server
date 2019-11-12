<html>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- On utilise les fonctions via le préfixe fn de jstl pour obtenir l'url exacte du projet -->
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/404style.css" media="screen">
        <title>Page not found</title>
        <c:set var="req" value="${pageContext.request}" />
        <c:set var="url">${req.requestURL}</c:set>
        <c:set var="uri" value="${req.requestURI}" />
    </head>
    <body>
        <div class="wrapper">
            <div class="row d-flex justify-content-center">
                <div class="col-md-13">
                    <form class="form-signin" method="POST" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/Controller">
                        <h1 class="form-signin-heading text-center">Ouch!</h1>
                        <h2 class="form-signin-heading text-center">Sadly, we couldn't find your page</h2>
                        <img class="center-image" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6P_UKEGpg3VuBGvJb1XQ8juGwlT4X7YwEyn-4pcGGmI3nhDIpIA&s%22%3E">
                        <input class="btn btn-primary btn-block"  type="submit" name="action" value="Go to the Login Page"/>
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>