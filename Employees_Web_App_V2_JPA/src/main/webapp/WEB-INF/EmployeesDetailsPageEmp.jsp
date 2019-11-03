<%-- 
    Document   : welcome
    Created on : 20 sept. 2019, 10:33:43
    Author     : JAA
--%>

<%@page import="se.m1.utils.Constants"%>
<!-- Valable uniquement pour la V1 du rendu (car pas le droit au code dans les JSPs) -->

<%@page import="se.m1.model.Employees"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="se.m1.model.DBActionsMySql"%>
<%@page import="java.util.ArrayList"%>
<%@page import="se.m1.model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                    <jsp:include page='Navbar.jsp'/>
                    <form action="EmployeesDetailsPageController" method="POST">
                                <h2>Details of Employee  ${emp.getFirstname()}   ${emp.getName()} </h2>
                                <h3 style='display:inline-block; margin-right:10px'>Name</h3><input name='empName' maxlength='10' style='display:inline-block' type='text' value="${emp.getName()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>First Name</h3><input name='empFirstname' maxlength='25' style='display:inline-block' type='text' value="${emp.getFirstname()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Home Phone</h3><input  name='empTelhome' maxlength='25' style='display:inline-block' type='text' value="${emp.getTelhome()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Mobile Phone</h3><input  name='empTelMob' maxlength='10' style='display:inline-block' type='text' value="${emp.getTelmob()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Work Phone</h3><input  name='empTelPro' maxlength='10' style='display:inline-block' type='text' value="${emp.getTelpro()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Address</h3><input  name='empAdress' maxlength='10' style='display:inline-block' type='text' value="${emp.getAdress()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Postal Code</h3><input  name='empPostalcode' maxlength='10' style='display:inline-block' type='text' value="${emp.getPostalcode()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>City</h3><input  name='empCity' maxlength='10' style='display:inline-block' type='text' value="${emp.getCity()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Email</h3><input  name='empEmail' maxlength='10'  style='display:inline-block' type='text' value="${emp.getEmail()}"/>
                            <br/>
                            <input type='submit' value='Go Back' name="<%=Constants.CANCEL_EMP_DETAILS_BUT_NAME %>"/>
        </form>
       
    </body>
</html>