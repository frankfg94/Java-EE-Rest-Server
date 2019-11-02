<%-- 
    Document   : welcome
    Created on : 20 sept. 2019, 10:33:43
    Author     : JAA
--%>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <jsp:include page='Navbar.jsp'/>
        <form action="EmployeesListPageController">
             <h1> Employees List</h1>
            <%
                ArrayList<Employee> employees = (ArrayList<Employee>)session.getAttribute("empList");
                if(employees != null && employees.size() != 0)
                {
                    out.print("<table border='1'>");
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
                    int radioButId = 0;
                    boolean firstLineChecked = false;
                    for(Employee emp : employees)
                   {

                       out.print("<tr>");
                       if(!firstLineChecked)
                       {
                         out.print("<td>"+"<input type='radio' value="+radioButId+" checked='checked'  name="+Constants.RADIO_EMPLOYEES_LIST_NAME+">"+"</td>");
                         firstLineChecked = true;
                       }
                       else
                        out.print("<td>"+"<input type='radio' value="+radioButId+"  name="+Constants.RADIO_EMPLOYEES_LIST_NAME+">"+"</td>");
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
                       radioButId++;
                   }
                    out.print("</table>");
                    out.print("<br/>");
                    out.print("<input type='submit' value='Delete' name='delEmpButton'/>");
                    out.print("<input type='submit' value='Details' name='detailsEmpButton'/>");
                }
                else 
                {
                    out.print("<h2 style='color:red;'>The company doesn't have employees!</h2>");
                }

                out.print("<input type='submit' value='Add' name='addEmpButton'/>");

            %>
        </form>
       
    </body>
</html>
