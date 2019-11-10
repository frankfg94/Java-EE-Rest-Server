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
<html lang="en-GB">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Pour le moment je triche un peu, j'utilise une pince de scriplet afin de pouvoir accéder à une variable statique, faute de moyens en JSTL -->
        <c:set var="radioName" value="<%=Constants.RADIO_EMPLOYEES_LIST_NAME  %>" />
        <jsp:include page='Navbar.jsp'/>
        <form action="Controller" method="POST">
             <h1> Employees List</h1>
             <c:set var="employees" value="${sessionScope.empList}"  />
             <c:set var="empKeys" value ="${sessionScope.empKeys}" />
             <c:choose>
                 <c:when test="${not empty employees && employees.size() != 0}">
                     <jsp:include page="EmployeesListTable.jsp" />
                     
                    <!-- Ajout des boutons pour un accès administrateur -->
                   <input type='submit' value='Delete' name='action'/>
                   <input type='submit' value='Details' name='action'/>

                 </c:when>
                 <c:otherwise>
                    <h2 style='color:red;'>The company doesn't have employees!</h2>
                 </c:otherwise>
             </c:choose>
             

                   
                <input type='submit' value='Add' name='action'/>
        </form>
       
    </body>
</html>
