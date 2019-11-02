/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.beans;

import java.util.List;
import java.util.TreeMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import se.m1.model.Employees;

/**
 *
 * @author franc
 */
@Stateless
public class EmployeesSB {

    @PersistenceContext
    EntityManager em;
    
    public List getAllEmployees()
    {
        return em.createNamedQuery("Employees.findAll").getResultList();
    }
    
    public TreeMap<Integer,Employees> getAllEmployeesDict()
    {
        TreeMap<Integer,Employees> tMap = new TreeMap<>();
        List<Employees> emps = em.createNamedQuery("Employees.findAll").getResultList();
        for(Employees emp : emps)
        {
            tMap.put(emp.getId(), emp);
        }
        return tMap;
    }

    public void AddEmployee(Employees emp) {
            try{
            if(emp != null)
            em.persist(emp);
        else
            throw new NullPointerException("ERROR | We wanted to insert selEmployee into the DB but its value is NULL"); 
            }catch (ConstraintViolationException e) {
       for(ConstraintViolation<?> ex : e.getConstraintViolations())
       {
           System.out.println(ex.getRootBeanClass().getName() +"."+ex.getPropertyPath()+ " " + ex.getMessage());   
       }
       
    }
    }
    
    public void EditEmployee(Employees selEmployee) {
        System.out.println("Start of edit");
        em.merge(selEmployee);
        System.out.println("Edit Successful");
    }


    public void RemoveEmployee(Employees emp) {
        
        // Pour ne pas avoir l'exception : java.lang.IllegalArgumentException: Entity must be managed to call remove: se.m1.beans.Employees
        if(!em.contains(emp))
            emp = em.merge(emp);
        
        em.remove(emp);
    }
}
