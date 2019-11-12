<%-- 
    Document   : welcome
    Created on : 20 sept. 2019, 10:33:43
    Author     : JAA
--%>

<%@page import="java.util.TreeMap"%>
<%@page import="se.m1.utils.Constants"%>
<!-- Valable uniquement pour la V1 du rendu (car pas le droit au code dans les JSPs) -->

<%@page import="se.m1.model.Employee"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="se.m1.model.DBActions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="se.m1.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/employeeslistpagestyle.css" media="screen">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
            <jsp:include page='Navbar.jsp'/>
            <div class="wrapper">
            <form class="form-signin" action="Controller" method="POST">
             <h1>Employees List</h1><br/>
            <%
                request.setAttribute("state", "AdminListPage");
                TreeMap<Integer,Employee> employees = (TreeMap<Integer,Employee>)session.getAttribute("empList");
              //  ArrayList<Employee> employees = (ArrayList<Employee>)session.getAttribute("empList");
                if(employees != null && employees.size() != 0)
                {
                    out.print("<table class='table table-striped table bordered'>");
                    out.print("<tr>");
                        out.print("<td>Sél</td>");
                        out.print("<td>NAME</td>");
                        out.print("<td>FIRST NAME</td>");
                        out.print("<td>HOME PHONE</td>");
                        out.print("<td>MOBILE PHONE</td>");
                        out.print("<td>WORK PHONE</td>");
                        out.print("<td>ADDRESS</td>");
                        out.print("<td>POSTAL CODE</td>");
                        out.print("<td>CITY</td>");
                        out.print("<td>EMAIL</td>");

                    out.print("</tr>");
                    boolean firstLineChecked = false;
                    for(int key : employees.keySet())
                   {
                       Employee emp = employees.get(key);
                       out.print("<tr>");
                       if(!firstLineChecked)
                       {
                         out.print("<td>"+"<input type='radio' value="+key+" checked='checked'  name="+Constants.RADIO_EMPLOYEES_LIST_NAME+">"+"</td>");
                         firstLineChecked = true;
                       }
                       else
                        out.print("<td>"+"<input type='radio' value="+key+"  name="+Constants.RADIO_EMPLOYEES_LIST_NAME+">"+"</td>");
                        out.print("<td>"+emp.getName()+"</td>");
                        out.print("<td>"+emp.getFirstname()+"</td>");  
                        out.print("<td>"+emp.getHomePhone()+"</td>");   
                        out.print("<td>"+emp.getMobilePhone()+"</td>");
                        out.print("<td>"+emp.getProPhone()+"</td>");
                        out.print("<td>"+emp.getAddress()+"</td>");
                        out.print("<td>"+emp.getPostalCode()+"</td>");
                        out.print("<td>"+emp.getCity()+"</td>");
                        out.print("<td>"+emp.getMail()+"</td>");
                       out.print("</tr>");
                   }
                    out.print("</table>");
                    out.print("<br/>");
                    out.print("<input class='btn btn-primary' type='submit' value='Delete' name='action'/>");
                    out.print("<input class='btn btn-primary' type='submit' value='Details' name='action'/>");
                }
                else 
                {
                    out.print("<h2 style='color:red;'>The company doesn't have employees!</h2>");
                }

                out.print("<input class='btn btn-primary' type='submit' value='Add' name='action'/>");

            %>
        </form>
       </div>
    </body>
</html>
