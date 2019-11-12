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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/404.css" media="screen">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Employee Details</title>
    </head>
    <body>
        <jsp:include page='Navbar.jsp'/>
        <div class="wrapper">
            <form class="form-signin" action="Controller" method="POST">

                <c:set var="empOld" value="${sessionScope.selEmployee}" />
                <c:set var="emp" value="${empOld}" scope="request" />


                <c:choose>
                    <c:when test="${empty emp}">
                        <h1>Creation of a new Employee</h1><br/>
                        <h3 style='display:inline-block; margin-right:10px'>Name</h3><input class='form-control' name='empName' maxlength='10' style='display:inline-block' type='text'/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>First Name</h3><input class='form-control' name='empFirstname' maxlength='25' style='display:inline-block' type='text' />
                        <br/><h3 style='display:inline-block; margin-right:10px'>Home Phone</h3><input class='form-control' name='empTelhome' maxlength='25' style='display:inline-block' type='tel' pattern="01[0-9]{8}" placeholder="ex: 0145863902"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Mobile Phone</h3><input class='form-control' name='empTelmob' maxlength='10' style='display:inline-block' type='tel' pattern="0[6|7][0-9]{8}" placeholder="ex: 0638012990"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Work Phone</h3><input class='form-control' name='empTelpro' maxlength='10' style='display:inline-block' type='tel' pattern="[0-9]{10}" placeholder="ex: 0729548104"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Address</h3><input class='form-control' name='empAdress' maxlength='150' style='display:inline-block' type='text' />
                        <br/><h3 style='display:inline-block; margin-right:10px'>Postal Code</h3><input class='form-control' name='empPostalcode' maxlength='5' style='display:inline-block' type='number' pattern="[1-7][0-9]{3}" />
                        <br/><h3 style='display:inline-block; margin-right:10px'>City</h3><input class='form-control' name='empCity' maxlength='25' style='display:inline-block' type='text' />
                        <br/><h3 style='display:inline-block; margin-right:10px'>Email</h3><input class='form-control' name='empEmail' maxlength='25'  style='display:inline-block' type='email' placeholder="address@example.com"/>
                        <br/>
                        <input type='submit' class='btn btn-primary' value='Create' name="action"/>
                        <input type='submit' class='btn btn-primary' value='Cancel' name="action"/>
                    </c:when>

                    <c:otherwise>
                        <h1>Details of Employee  ${emp.getFirstname()}   ${emp.getName()}</h1><br/>
                        <h3 style='display:inline-block; margin-right:10px'>Name</h3><input class='form-control' name='empName' maxlength='10' style='display:inline-block' type='text' value="${emp.getName()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>First Name</h3><input class='form-control' name='empFirstname' maxlength='25' style='display:inline-block' type='text' value="${emp.getFirstname()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Home Phone</h3><input class='form-control' name='empTelhome' maxlength='25' style='display:inline-block' type='text' value="${emp.getTelhome()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Mobile Phone</h3><input class='form-control' name='empTelmob' maxlength='10' style='display:inline-block' type='text' value="${emp.getTelmob()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Work Phone</h3><input class='form-control' name='empTelpro' maxlength='10' style='display:inline-block' type='text' value="${emp.getTelpro()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Address</h3><input class='form-control' name='empAdress' maxlength='10' style='display:inline-block' type='text' value="${emp.getAdress()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Postal Code</h3><input class='form-control' name='empPostalcode' maxlength='10' style='display:inline-block' type='text' value="${emp.getPostalcode()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>City</h3><input class='form-control' name='empCity' maxlength='10' style='display:inline-block' type='text' value="${emp.getCity()}"/>
                        <br/><h3 style='display:inline-block; margin-right:10px'>Email</h3><input class='form-control' name='empEmail' maxlength='10'  style='display:inline-block' type='text' value="${emp.getEmail()}"/>
                        <br/>
                        <input class='btn btn-primary' type='submit' value='Save' name="action"/>
                        <input class='btn btn-primary' type='submit' value='Cancel' name="action"/>

                    </c:otherwise>
                </c:choose>
            </form>
        </div>

    </body>
</html>
