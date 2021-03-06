

<%@page import="se.m1.utils.Constants"%>
<!-- Valable uniquement pour la V1 du rendu (car pas le droit au code dans les JSPs) -->
<%@page import="se.m1.utils.Constants"%>
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
                <c:set var="emp" value="${sessionScope.selEmployee}"/>

                <h1>Details of Employee  ${emp.getFirstname()}   ${emp.getName()}</h1><br/>
                <h3>Name</h3><input class='form-control' name='empName' maxlength='10' type='text' value="${emp.getName()}" required />
                <h3>First Name</h3><input class='form-control' name='empFirstname' maxlength='25' type='text' value="${emp.getFirstname()}" required />
                <h3>Home Phone</h3><input class='form-control' name='empTelhome' maxlength='25' value="${emp.getTelhome()}" type='tel' pattern="01[0-9]{8}" placeholder="ex: 0145863902"/>
                <h3>Mobile Phone</h3><input class='form-control' name='empTelmob' maxlength='10' value="${emp.getTelmob()}" type='tel' pattern="0[6|7][0-9]{8}" placeholder="ex: 0638012990"/>
                <h3>Work Phone</h3><input class='form-control' name='empTelpro' maxlength='10' value="${emp.getTelpro()}" type='tel' pattern="[0-9]{10}" placeholder="ex: 0729548104"/>
                <h3>Address</h3><input class='form-control' name='empAdress' maxlength='150' type='text' value="${emp.getAdress()}"/>
                <h3>Postal Code</h3><input class='form-control' name='empPostalcode' maxlength='5' type='number' pattern="[1-7][0-9]{3}" value="${emp.getPostalcode()}"/>
                <h3>City</h3><input class='form-control' name='empCity' maxlength='25' type='text' value="${emp.getCity()}"/>
                <h3>Email</h3><input class='form-control' name='empEmail' maxlength='25' type='email' placeholder="address@example.com" value="${emp.getEmail()}"/>
                <br/>
                <input type='submit' class='btn btn-primary' value='Go Back' name="action"/>
            </form>
        </div>
    </body>
</html>


