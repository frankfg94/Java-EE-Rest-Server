package se.m1.model;

import java.io.InputStream;
import java.util.TreeMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;

public class EmployeesDetailsPage extends HttpServlet {

    private InputStream input;
    private String dbUrl = "";
    private String dbUser = "";
    private String dbPwd = "";
    private Employees selEmployee = null;

    public void saveEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        selEmployee = (Employees) request.getSession().getAttribute("selEmployee");
        if (selEmployee == null) {
            System.out.println("Employee is NULL, can't save it");
            return;
            // METTRE UNE EXCEPTION SINON PB APRES CAR SELEMPLOYEE EST UTILISE
        }

        int tempId = selEmployee.getId();
        selEmployee = GetDataFromDetailsForm(request);
        selEmployee.setId(tempId);

        if (LoginPage.dba == null) {
            System.out.println("null");
        }
        LoginPage.dba.editEmployee(selEmployee);
        request.getSession().setAttribute("empList", LoginPage.dba.getAllEmployees());
        System.out.println("Employee Update Done");

        request.getSession().setAttribute("selEmployee", null);
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
    }

    public void cancelEmployeesCreation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
    }

    public void goBackToEmpList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
    }

    public void createNewEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Employees employeeFromForm = GetDataFromDetailsForm(request);
        TreeMap<Integer, Employees> employees = LoginPage.dba.getAllEmployees();

        for (int id : employees.keySet()) {
            Employees e = employees.get(id);
            if (employeeFromForm.equals(e)) {
                request.getSession().setAttribute("empList", employees);
                request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
            }
        }

        LoginPage.dba.insertEmployee(employeeFromForm);

        // Actualise la liste
        request.getSession().setAttribute("empList", LoginPage.dba.getAllEmployees());
        System.out.println("Employee Added to the Database");

        // On active le mode création
        request.getSession().setAttribute("selEmployee", null);
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
    }

    Employees GetDataFromDetailsForm(HttpServletRequest request) {
        Employees emp = new Employees();
        emp.setName(request.getParameter("empName"));
        emp.setFirstname(request.getParameter("empFirstname"));
        emp.setTelhome(request.getParameter("empTelhome"));
        emp.setTelmob(request.getParameter("empTelmob"));
        emp.setTelpro(request.getParameter("empTelpro"));
        emp.setAdress(request.getParameter("empAdress"));
        emp.setPostalcode(request.getParameter("empPostalcode"));
        emp.setCity(request.getParameter("empCity"));
        emp.setEmail(request.getParameter("empEmail"));

        return emp;
    }
}
