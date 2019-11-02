package se.m1.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import se.m1.beans.EmployeesSB;
import se.m1.ctrl.LoginPageController;
import static se.m1.utils.Constants.*;

/**
 *
 * @author JAA
 */
public class DBActionsREST {
    
    public final String REST_SERVICE_URL = "http://localhost:8080/Employees_Web_App_V3_REST/webresources";
    public final String EMPLOYEES_REL_URL = "/se.m1.model.employees";
    
    // Active le mode rest à true, et le mode jpa à false pour le système CRUD (les appels URL fonctionneront dans tous les cas)
    public boolean REST_ONLY = true;
    
    
    Connection conn;
    Statement stmt;
    ResultSet rs;
    ArrayList<Users> listUsers;
    TreeMap<Integer,Employees> listEmployees;
    private final EmployeesSB empSB;
    public DBActionsREST(String url, String user, String pwd, EmployeesSB empSB) {
        this.empSB = empSB;
//        try {
//            System.out.println("New DB action");
//            conn = DriverManager.getConnection(url, user, pwd);
//        } catch (SQLException sqle) {
//            System.out.println(sqle.getMessage());
//        } 
    }

    public Statement getStatement() {
        System.out.println("Get Statement"); 
        try {
            stmt = conn.createStatement();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return stmt;

    }

    public ResultSet getResultSet(String query) {
        System.out.println("Get Result Set"); 
        stmt = getStatement();
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return rs;
    }
    
    private TreeMap<Integer,Employees> getTreeMapFromList(List<Employees> emps)
    {
        TreeMap<Integer,Employees> tm= new TreeMap<>();
        for(Employees e : emps)
        {
            tm.put(e.getId(), e);
        }
        return tm;
    }
    

    public void editEmployeeREST(int id, Employees e)
    {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut post = new HttpPut(REST_SERVICE_URL + EMPLOYEES_REL_URL+ "/" + id);
            System.out.println("Using post for the following url : " + post.getURI());
            post.setHeader("content-type","application/xml");
            
            String xml = "<employees>\n" +
                    "    <adress>"+e.getAdress()+"</adress>\n" +
                    "    <city>"+e.getCity()+"</city>\n" +
                    "    <email>"+e.getEmail()+"</email>\n" +
                    "    <firstname>"+e.getFirstname()+"</firstname>\n" +
                    "    <id>"+id+"</id>\n"+
                    "    <name>"+e.getName()+"</name>\n" +
                    "    <postalcode>"+e.getPostalcode()+"</postalcode>\n" +
                    "    <telhome>"+e.getTelhome()+"</telhome>\n" +
                    "    <telmob>"+e.getTelmob()+"</telmob>\n" +
                    "    <telpro>"+e.getTelpro()+"</telpro>\n" +
                    "</employees>";
            StringEntity entity = new StringEntity(xml);
            post.setEntity(entity);
            HttpResponse resp =client.execute(post);
            System.out.println(xml);
		if (resp.getStatusLine().getStatusCode() < 200 || resp.getStatusLine().getStatusCode() >= 400) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + resp.getStatusLine().getStatusCode());
		}
                
        } catch (IOException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertEmployeeREST(Employees e)
    {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(REST_SERVICE_URL + EMPLOYEES_REL_URL);
            System.out.println("Using post for the following url : " + post.getURI());
            
            post.setHeader("content-type","application/xml");
            
            String xml = 
                    "<employees>\n" +
                    "    <adress>"+e.getAdress()+"</adress>\n" +
                    "    <city>"+e.getCity()+"</city>\n" +
                    "    <email>"+e.getEmail()+"</email>\n" +
                    "    <firstname>"+e.getFirstname()+"</firstname>\n" +
                    "    <name>"+e.getName()+"</name>\n" +
                    "    <postalcode>"+e.getPostalcode()+"</postalcode>\n" +
                    "    <telhome>"+e.getTelhome()+"</telhome>\n" +
                    "    <telmob>"+e.getTelmob()+"</telmob>\n" +
                    "    <telpro>"+e.getTelpro()+"</telpro>\n" +
                    "</employees>";
            
            StringEntity entity = new StringEntity(xml);
            post.setEntity(entity);
            
            HttpResponse resp = client.execute(post);
            
        } catch (IOException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public TreeMap<Integer,Employees> getAllEmployeesREST()
    {
        EmployeesContainer cont;
        TreeMap<Integer,Employees> emps = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet(REST_SERVICE_URL + EMPLOYEES_REL_URL);
            System.out.println("Using get for the following url : " + get.getURI());
            get.setHeader("content-type","application/xml");
            
            HttpResponse resp = client.execute(get);
            
            String xml = EntityUtils.toString(resp.getEntity());
            
            // Conversion du string en l'objet souhaité
           JAXBContext  jaxbContext = JAXBContext.newInstance(EmployeesContainer.class);              
           Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
           cont = (EmployeesContainer) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            
            emps = getTreeMapFromList(cont.employees);
             
        } catch (IOException ex) {
            System.out.println("GET ALL EMPLOYEES REQUEST FAILED");
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emps;
    }

    public TreeMap<Integer, Employees> getAllEmployees() 
    {
        if(REST_ONLY)
            return getAllEmployeesREST();
        return empSB.getAllEmployeesDict();
        
    }
    
    public void insertEmployee(Employees emp)
    {
      if(REST_ONLY)
           insertEmployeeREST(emp);
      else
          empSB.AddEmployee(emp);
      
      System.out.println("Employee Added to the Database");

    }
    
    public void editEmployee(Employees e)
    {
      if(REST_ONLY)
          editEmployeeREST(e.getId(), e);
      else 
          empSB.EditEmployee(e);
    }
    
    public void deleteEmployee(Employees e)
    {
        if(REST_ONLY)
            deleteEmployeeREST(e.getId());
        else 
            empSB.RemoveEmployee(e);
    }

        public void deleteEmployeeREST(int id)
    {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpDelete del = new HttpDelete(REST_SERVICE_URL + EMPLOYEES_REL_URL + "/" + id);
            HttpResponse resp =  client.execute(del);
            System.out.println(EntityUtils.toString(resp.getEntity()));
            
        } catch (IOException ex) {
            Logger.getLogger(DBActionsREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


}