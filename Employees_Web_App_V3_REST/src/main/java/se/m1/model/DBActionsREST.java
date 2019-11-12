package se.m1.model;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.http.HTTPException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import se.m1.beans.EmployeesSB;

/**
 *
 * @author JAA
 */
public class DBActionsREST {

    public final static String REST_SERVICE_URL = "http://localhost:8080/Employees_Web_App_V3_REST/webresources";
    public final static String EMPLOYEES_REL_URL = "/se.m1.model.employees";

    // Active le mode rest a true, et le mode jpa a false pour le systeme CRUD (les appels URL fonctionneront dans tous les cas)
    public final static boolean REST_ONLY = true;
    private static final Logger LOGGER = Logger.getLogger(DBActionsREST.class.getName());

    Connection conn;
    Statement stmt;
    ResultSet rs;
    ArrayList<Users> listUsers;
    TreeMap<Integer, Employees> listEmployees;
    private final EmployeesSB empSB;

    public DBActionsREST(String url, String user, String pwd) throws NamingException {
        LOGGER.log(Level.INFO, "Starting in mode REST : {0}", REST_ONLY);
        empSB = (EmployeesSB)new InitialContext().lookup("java:global/Employees_Web_App_V3_REST/EmployeesSB!se.m1.beans.EmployeesSB");
    }

    public Statement getStatement() {
       LOGGER.log(Level.INFO,"Get Statement");
        try {
            stmt = conn.createStatement();
        } catch (SQLException sqle) {
            LOGGER.log(Level.INFO,sqle.getMessage());
        }
        return stmt;

    }

    public ResultSet getResultSet(String query) {
        LOGGER.log(Level.INFO,"Get Result Set");
        stmt = getStatement();
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException sqle) {
            LOGGER.log(Level.INFO,sqle.getMessage());
        }
        return rs;
    }

    private TreeMap<Integer, Employees> getTreeMapFromList(List<Employees> emps) {
        TreeMap<Integer, Employees> tm = new TreeMap<>();
        emps.forEach((e) -> {
            tm.put(e.getId(), e);
        });
        return tm;
    }
    
    private void setXmlHeader(HttpRequestBase request)
    {
       request.setHeader("content-type", "application/xml");
    }

    public void editEmployeeREST(int id, Employees e) {
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpPut post = new HttpPut(REST_SERVICE_URL + EMPLOYEES_REL_URL + "/" + id);
                LOGGER.log(Level.INFO, "Using post for the following url : {0}", post.getURI());
                setXmlHeader(post);
                String xml = "<employees>\n"
                        + "    <adress>" + e.getAdress() + "</adress>\n"
                        + "    <city>" + e.getCity() + "</city>\n"
                        + "    <email>" + e.getEmail() + "</email>\n"
                        + "    <firstname>" + e.getFirstname() + "</firstname>\n"
                        + "    <id>" + id + "</id>\n"
                        + "    <name>" + e.getName() + "</name>\n"
                        + "    <postalcode>" + e.getPostalcode() + "</postalcode>\n"
                        + "    <telhome>" + e.getTelhome() + "</telhome>\n"
                        + "    <telmob>" + e.getTelmob() + "</telmob>\n"
                        + "    <telpro>" + e.getTelpro() + "</telpro>\n"
                        + "</employees>";
                StringEntity entity = new StringEntity(xml);
                post.setEntity(entity);
                HttpResponse resp = client.execute(post);
                LOGGER.log(Level.INFO, xml);
                if (resp.getStatusLine().getStatusCode() < 200 || resp.getStatusLine().getStatusCode() >= 400) {
                    throw new HTTPException(resp.getStatusLine().getStatusCode());
                }
        } catch (IOException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertEmployeeREST(Employees e) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(REST_SERVICE_URL + EMPLOYEES_REL_URL);
            LOGGER.log(Level.INFO, "Using post for the following url : {0}", post.getURI());
            setXmlHeader(post);
            String xml
                    = "<employees>\n"
                    + "    <adress>" + e.getAdress() + "</adress>\n"
                    + "    <city>" + e.getCity() + "</city>\n"
                    + "    <email>" + e.getEmail() + "</email>\n"
                    + "    <firstname>" + e.getFirstname() + "</firstname>\n"
                    + "    <name>" + e.getName() + "</name>\n"
                    + "    <postalcode>" + e.getPostalcode() + "</postalcode>\n"
                    + "    <telhome>" + e.getTelhome() + "</telhome>\n"
                    + "    <telmob>" + e.getTelmob() + "</telmob>\n"
                    + "    <telpro>" + e.getTelpro() + "</telpro>\n"
                    + "</employees>";

            StringEntity entity = new StringEntity(xml);
            post.setEntity(entity);

            HttpResponse resp = client.execute(post);
        } catch (IOException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TreeMap<Integer, Employees> getAllEmployeesREST() {
        EmployeesContainer cont;
        TreeMap<Integer, Employees> emps = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(REST_SERVICE_URL + EMPLOYEES_REL_URL);
            LOGGER.log(Level.INFO, "Using get for the following url : {0}", get.getURI());
            setXmlHeader(get);

            HttpResponse resp = client.execute(get);

            String xml = EntityUtils.toString(resp.getEntity());

            // Conversion du string en l'objet souhait�
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeesContainer.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            cont = (EmployeesContainer) jaxbUnmarshaller.unmarshal(new StringReader(xml));

            emps = getTreeMapFromList(cont.employees);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emps;
    }

    public TreeMap<Integer, Employees> getAllEmployees() {
        if (REST_ONLY) {
            return getAllEmployeesREST();
        }
        return empSB.getAllEmployeesDict();

    }

    public void insertEmployee(Employees emp) {
        if (REST_ONLY) {
            insertEmployeeREST(emp);
        } else {
            empSB.addEmployee(emp);
        }

        LOGGER.log(Level.INFO, "Employee Added to the Database");
    }

    public void editEmployee(Employees e) {
        if (REST_ONLY) {
            editEmployeeREST(e.getId(), e);
        } else {
            empSB.EditEmployee(e);
        }
    }   

    public void deleteEmployee(Employees e) {
        if (e == null) {
            throw new NullPointerException("Deletion not possible because Employee e is null");
        } else {
            if (REST_ONLY) {
                deleteEmployeeREST(e.getId());
            } else {
                empSB.RemoveEmployee(e);
            }
        }

    }

    public void deleteEmployeeREST(int id) {
        try (CloseableHttpClient client = HttpClients.createDefault())
        {
            HttpDelete del = new HttpDelete(REST_SERVICE_URL + EMPLOYEES_REL_URL + "/" + id);
            client.execute(del);
        } catch (IOException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
