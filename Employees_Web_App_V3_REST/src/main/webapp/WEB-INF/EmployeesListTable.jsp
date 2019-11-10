<%-- 
    Document   : EmployeesListTemplate
    Created on : 9 nov. 2019, 18:40:18
    Author     : franc
--%>



                 <table border='1'>
                    <caption>The list of all the employees that are registered in the company</caption>
                    <tr>
                        <th scope="row">Selection</th>
                        <th scope="row">NAME</th>
                        <th scope="row">FIRST NAME</th>
                        <th scope="row">HOME PHONE</th>
                        <th scope="row">MOBILE PHONE</th>
                        <th scope="row">WORK PHONE</th>
                        <th scope="row">ADDRESS</th>
                        <th scope="row">POSTAL CODE</th>
                        <th scope="row">CITY</th>
                        <th scope="row">EMAIL</th>
                    </tr>
               
                            <c:forEach items="${empList}" var="emp" varStatus="status">
                                    <tr>
                                    <c:choose>
                                         <c:when test="${firstLineChecked eq false}">
                                             <td><input type='radio' value="${empKeys[status.index]}" checked='checked'  name="${radioName}"></td>
                                             <c:set var="firstLineChecked" value="true"  />
                                         </c:when>
                                         <c:otherwise>
                                             <td><input type='radio' value="${empKeys[status.index]}"  name="${radioName}"></td>
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
