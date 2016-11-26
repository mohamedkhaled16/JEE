/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author mohamedk
 */
@Stateless
public class studentService {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;
    public void addStduent (studentEntity student) {
        em.persist(student);
        
    }
    public List<studentEntity> listStudent(){
    
       TypedQuery<studentEntity> sQuery = em.createQuery("select s from studentEntity s ",studentEntity.class);
         List<studentEntity>  sList =sQuery.getResultList();
         return sList;
    }
    public void updateStudent(studentEntity s){
    TypedQuery<studentEntity> sQuery = em.createNamedQuery("studentEntity.updateById",studentEntity.class);
    sQuery.setParameter("fname", s.getFname());
    sQuery.setParameter("lname", s.getLname());
    sQuery.setParameter("email", s.getEmail());
    sQuery.setParameter("age", s.getAge());
    sQuery.setParameter("dob", s.getDob());
    sQuery.setParameter("file", s.getFile());
    sQuery.setParameter("gender", s.getGender());
    sQuery.setParameter("interest", s.getInterest());
    sQuery.setParameter("id", s.getId());
    sQuery.executeUpdate();
    }
    public studentEntity getStudent(int id){
    TypedQuery<studentEntity> sQuery = em.createNamedQuery("studentEntity.getById",studentEntity.class);
    sQuery.setParameter("id", id);
    return  sQuery.getSingleResult();
        
    }

    public void deleteStudent(int id){
    studentEntity s = em.find(studentEntity.class, id);
    em.remove(s);    
    }
    
}
