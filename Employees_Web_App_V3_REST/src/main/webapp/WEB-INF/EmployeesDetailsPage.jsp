<%-- 
    Document   : welcome
    Created on : 20 sept. 2019, 10:33:43
    Author     : JAA
--%>

<%@page import="se.m1.utils.Constants"%>
<!-- Valable uniquement pour la V1 du rendu (car pas le droit au code dans les JSPs) -->

<%@page import="se.m1.model.Employees"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="se.m1.model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="emp" type="se.m1.model.Employees" scope="session"/>

<!DOCTYPE html>
<html lang="en-GB">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Details</title>
    </head>
    <body>
                    <jsp:include page='Navbar.jsp'/>
                    <form action="Controller" method="POST">
                

                <c:choose>
                    <c:when test="${empty emp}">
                        <h2>Creation of a new Employee </h2>
                            <h3 style='display:inline-block; margin-right:10px'>Name</h3><input name='empName' maxlength='25' style='display:inline-block' type='text'/>
                            <br/><h3 style='display:inline-block; margin-right:10px'>First Name</h3><input name='empFirstname' maxlength='25' style='display:inline-block' type='text' />
                            <br/><h3 style='display:inline-block; margin-right:10px'>Home Phone</h3><input  name='empTelhome' maxlength='25' style='display:inline-block' type='text' />
                            <br/><h3 style='display:inline-block; margin-right:10px'>Mobile Phone</h3><input  name='empTelmob' maxlength='10' style='display:inline-block' type='text' />
                            <br/><h3 style='display:inline-block; margin-right:10px'>Work Phone</h3><input  name='empTelpro' maxlength='10' style='display:inline-block' type='text' />
                            <br/><h3 style='display:inline-block; margin-right:10px'>Address</h3><input  name='empAdress' maxlength='150' style='display:inline-block' type='text' />
                            <br/><h3 style='display:inline-block; margin-right:10px'>Postal Code</h3><input  name='empPostalcode' maxlength='5' style='display:inline-block' type='text' />
                            <br/><h3 style='display:inline-block; margin-right:10px'>City</h3><input  name='empCity' maxlength='25' style='display:inline-block' type='text' />
                            <br/><h3 style='display:inline-block; margin-right:10px'>Email</h3><input  name='empEmail' maxlength='25'  style='display:inline-block' type='text' />
                        <br/>
                        <input type='submit' value='Create' name="action"/>
                        <input type='submit' value='Cancel' name="action"/>
                    </c:when>

                    <c:otherwise>
                                <h2>Details of Employee  ${emp.getFirstname()}   ${emp.getName()} </h2>
                                <h3 style='display:inline-block; margin-right:10px'>Name</h3><input name='empName' maxlength='25' style='display:inline-block' type='text' value="${emp.getName()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>First Name</h3><input name='empFirstname' maxlength='25' style='display:inline-block' type='text' value="${emp.getFirstname()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Home Phone</h3><input  name='empTelhome' maxlength='25' style='display:inline-block' type='text' value="${emp.getTelhome()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Mobile Phone</h3><input  name='empTelmob' maxlength='10' style='display:inline-block' type='text' value="${emp.getTelmob()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Work Phone</h3><input  name='empTelpro' maxlength='10' style='display:inline-block' type='text' value="${emp.getTelpro()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Address</h3><input  name='empAdress' maxlength='150' style='display:inline-block' type='text' value="${emp.getAdress()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Postal Code</h3><input  name='empPostalcode' maxlength='5' style='display:inline-block' type='text' value="${emp.getPostalcode()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>City</h3><input  name='empCity' maxlength='25' style='display:inline-block' type='text' value="${emp.getCity()}"/>
                                <br/><h3 style='display:inline-block; margin-right:10px'>Email</h3><input  name='empEmail' maxlength='25'  style='display:inline-block' type='text' value="${emp.getEmail()}"/>
                            <br/>
                            <input type='submit' value='Save' name="action"/>
                            <input type='submit' value='Cancel' name="action"/>

                    </c:otherwise>
                </c:choose>
            
        </form>
       
    </body>
</html>
