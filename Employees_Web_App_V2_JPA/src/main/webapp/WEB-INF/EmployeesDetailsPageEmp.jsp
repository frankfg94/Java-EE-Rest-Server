

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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='Navbar.jsp'/>
        <form action="Controller" method="POST">
            <c:set var="emp" value="${sessionScope.selEmployee}" />

            <h2>Details of Employee  ${emp.getFirstname()}   ${emp.getName()} </h2>
            <h3 style='display:inline-block; margin-right:10px'>Name</h3><input name='empName' style='display:inline-block' type='text' value="${emp.getName()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>First Name</h3><input name='empFirstname' style='display:inline-block' type='text' value="${emp.getFirstname()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>Home Phone</h3><input  name='empTelhome' style='display:inline-block' type='text' value="${emp.getTelhome()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>Mobile Phone</h3><input  name='empTelMob' style='display:inline-block' type='text' value="${emp.getTelmob()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>Work Phone</h3><input  name='empTelPro' style='display:inline-block' type='text' value="${emp.getTelpro()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>Address</h3><input  name='empAdress' style='display:inline-block' type='text' value="${emp.getAdress()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>Postal Code</h3><input  name='empPostalcode' style='display:inline-block' type='text' value="${emp.getPostalcode()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>City</h3><input  name='empCity' style='display:inline-block' type='text' value="${emp.getCity()}" readonly/>
            <br/><h3 style='display:inline-block; margin-right:10px'>Email</h3><input  name='empEmail'  style='display:inline-block' type='text' value="${emp.getEmail()}" readonly/>
            <br/>
            <input type='submit' value='Go Back' name="action"/>
        </form>

    </body>
</html>
