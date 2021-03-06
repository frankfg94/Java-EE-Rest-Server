<%@page import="se.m1.utils.Constants"%>
<!-- Valable uniquement pour la V1 du rendu (car pas le droit au code dans les JSPs) -->

<%@page import="se.m1.model.Employees"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="se.m1.model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/employeesdetailpagestyle.css" media="screen">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Employee Details</title>
    </head>
    <body>
        <jsp:include page='Navbar.jsp'/>
        <div class="wrapper">
            <form class="form-signin" action="Controller" method="POST">

                <c:set var="emp" value="${sessionScope.selEmployee}" />


                <c:choose>
                    <c:when test="${empty emp}">
                        <h1>Creation of a new Employee</h1><br/>
                        <h3>Home Phone</h3><input class='form-control' name='empTelhome' maxlength='25' type='tel' pattern="01[0-9]{8}" placeholder="ex: 0145863902" required/>
                        <h3>Address</h3><input class='form-control' name='empAdress' maxlength='150' type='text' />
                        <h3>Postal Code</h3><input class='form-control' name='empPostalcode' type='text' maxlength="5" pattern="[1-9]{2}[0-9]{3}" />
                        <h3>City</h3><input class='form-control' name='empCity' maxlength='25' type='text' />
                        <h3>Email</h3><input class='form-control' name='empEmail' maxlength='25' type='email' placeholder="address@example.com"/>
                        <br/>
                        <input type='submit' class='btn btn-primary' value='Create' name="action"/>
                        <input type='submit' formnovalidate class='btn btn-primary' value='Cancel' name="action"/>
                    </c:when>

                    <c:otherwise>
                        <h1>Details of Employee  ${emp.getFirstname()}   ${emp.getName()}</h1><br/>
                        <h3>Home Phone</h3><input class='form-control' name='empTelhome' maxlength='25' value="${emp.getTelhome()}" type='tel' pattern="01[0-9]{8}" placeholder="ex: 0145863902" required/>
                        <h3>Address</h3><input class='form-control' name='empAdress' maxlength='150' type='text' value="${emp.getAdress()}"/>
                        <h3>Postal Code</h3><input class='form-control' name='empPostalcode' type='text' maxlength='5' pattern="[1-9]{2}[0-9]{3}" value="${emp.getPostalcode()}"/>
                        <h3>City</h3><input class='form-control' name='empCity' maxlength='25' type='text' value="${emp.getCity()}"/>
                        <h3>Email</h3><input class='form-control' name='empEmail' maxlength='25' type='email' placeholder="address@example.com" value="${emp.getEmail()}"/>
                        <br/>
                        <input class='btn btn-primary' type='submit' value='Save' name="action"/>
                        <input class='btn btn-primary' formnovalidate type='submit' value='Cancel' name="action"/>

                    </c:otherwise>
                </c:choose>
            </form>
        </div>

    </body>
</html>
