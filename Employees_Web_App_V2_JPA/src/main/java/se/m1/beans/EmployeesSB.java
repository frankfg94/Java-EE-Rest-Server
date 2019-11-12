package se.m1.beans;

import java.util.List;
import java.util.TreeMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import se.m1.model.Employees;

@Stateless
public class EmployeesSB {

    @PersistenceContext
    EntityManager em;

    /**
     * Obtain all the employees from the Mysql Database
     * @return 
     */
    public List getAllEmployees() {
        return em.createNamedQuery("Employees.findAll").getResultList();
    }

    /**
     * Obtain all the employees but with a dictionnary, the keys are the employees ids
     * @return 
     */
    public TreeMap<Integer, Employees> getAllEmployeesDict() {
        TreeMap<Integer, Employees> tMap = new TreeMap<>();
        List<Employees> emps = em.createNamedQuery("Employees.findAll").getResultList();
        for (Employees emp : emps) {
            tMap.put(emp.getId(), emp);
        }
        return tMap;
    }

    /**
     * Add a new employee depending on the parameter emp (Employees)
     * @param emp The employee to add
     */
    public void AddEmployee(Employees emp) throws ConstraintViolationException {
        try {
            if (emp != null) {
                em.persist(emp);
            } else {
                throw new NullPointerException("ERROR | We wanted to insert selEmployee into the DB but its value is NULL");
            }
        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> ex : e.getConstraintViolations()) {
                System.out.println(ex.getRootBeanClass().getName() + "." + ex.getPropertyPath() + " " + ex.getMessage());
            }
            throw e;
        }
    }

    /**
     * Edit an employee depending on the parameter emp (Employees)
     * @param selEmployee  the employee to edit
     */
    public void EditEmployee(Employees selEmployee) throws ConstraintViolationException {
        System.out.println("Start of edit");
        try {
            em.merge(selEmployee);
        } catch (ConstraintViolationException ex) {
            for (ConstraintViolation<?> e : ex.getConstraintViolations()) {
                System.out.println(e.getRootBeanClass().getName() + "." + e.getPropertyPath() + " " + e.getMessage());
            }
            throw ex;
        }
        System.out.println("Edit Successful");
    }

    /**
     * Remove the employee
     * @param emp  the employee to delete from the database
     */
    public void RemoveEmployee(Employees emp) throws ConstraintViolationException{

                try {
        if (!em.contains(emp)) {
            emp = em.merge(emp);
            em.remove(emp);
        }}catch (ConstraintViolationException ex) {
            for (ConstraintViolation<?> e : ex.getConstraintViolations()) {
                System.out.println(e.getRootBeanClass().getName() + "." + e.getPropertyPath() + " " + e.getMessage());
            }
            throw ex;
        }

    }
}
