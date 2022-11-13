/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import myservlet.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author kanya
 */
public class StudentTable {
    public static void insertStudent(Student stud) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentApp");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(stud);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void updateStudent(Student stud) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentApp");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, stud.getId());
        fromDb.setName(stud.getName());
        fromDb.setGpa(stud.getGpa());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static Student findStudentById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentApp");
        EntityManager em = emf.createEntityManager();
        Student emp = em.find(Student.class, id);
        em.close();
        return emp;
    }
    
    public static List<Student> findAllStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentApp");
        EntityManager em = emf.createEntityManager();
        List<Student> stuList = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        em.close();
        return stuList;
    }
    
    public static List<Student> findStudentByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentApp");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Student.findByName");
        query.setParameter("name", name);
        List<Student> stuList = (List<Student>) query.getResultList();
        em.close();
        return stuList;
    }
    
     public static void removeStudent(Student stu) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentApp");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, stu.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
     }
}