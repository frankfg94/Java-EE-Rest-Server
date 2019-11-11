<%-- 
    Document   : welcome
    Created on : 20 sept. 2019, 10:33:43
    Author     : JAA
--%>

<%@page import="java.util.TreeMap"%>
<%@page import="se.m1.utils.Constants"%>
<!-- Valable uniquement pour la V1 du rendu (car pas le droit au code dans les JSPs) -->

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
        <c:if test="${not empty requestScope.errRadioButton}">
            <p style="color:red">${requestScope.errRadioButton}</p>
        </c:if> 
        <!-- Pour le moment je triche un peu, j'utilise une pince de scriplet afin de pouvoir accéder à une variable statique, faute de moyens en JSTL -->
        <c:set var="radioName" value="<%=Constants.RADIO_EMPLOYEES_LIST_NAME%>" />
        <jsp:include page='Navbar.jsp'/>
        <form action="Controller" method="POST">
            <h1> Employees List</h1>
            <c:set var="employees" value="${sessionScope.empList}"  />
            <c:choose>
                <c:when test="${not empty employees && employees.size() != 0}">
                    <table border='1'>
                        <tr>
                            <td>Selection</td>
                            <td>NAME</td>
                            <td>FIRST NAME</td>
                            <td>HOME PHONE</td>
                            <td>MOBILE PHONE</td>
                            <td>WORK PHONE</td>
                            <td>ADDRESS</td>
                            <td>POSTAL CODE</td>
                            <td>CITY</td>
                            <td>EMAIL</td>
                        </tr>

                        <c:forEach items="${empList}" var="emp" varStatus="status">
                            <tr>
                                <c:choose>
                                    <c:when test="${firstLineChecked eq false}">
                                        <td><input type='radio' value="${emp.value.getId()}" checked='checked'  name="${radioName}"></td>
                                            <c:set var="firstLineChecked" value="true"  />
                                        </c:when>
                                        <c:otherwise>
                                        <td><input type='radio' value="${emp.value.getId()}"  name="${radioName}"></td>
                                        </c:otherwise>
                                    </c:choose>
                                <td>${emp.value.getName()}</td>
                                <td>${emp.value.getFirstname()}</td> 
                                <td>${emp.value.getTelhome()}</td>
                                <td>${emp.value.getTelmob()}</td>
                                <td>${emp.value.getTelpro()}</td>
                                <td>${emp.value.getAdress()}</td>
                                <td>${emp.value.getPostalcode()}</td>
                                <td>${emp.value.getCity()}</td>
                                <td>${emp.value.getEmail()}</td>
                            </tr>

                        </c:forEach>

                    </table>
                    <br/>
                    <input type='submit' value='Delete'  name='action'/>
                    <input type='submit' value='Details' name='action'/>

                </c:when>
                <c:otherwise>
                    <h2 style='color:red;'>The company doesn't have employees!</h2>
                </c:otherwise>
            </c:choose>



            <input type='submit' value='Add' formmethod="GET" name='action'/>
        </form>

    </body>
</html>
