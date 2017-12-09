package ua.kiev.prog.dao;

import ua.kiev.prog.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;


public class PersonDAOImpl implements PersonDAO {

    private final EntityManager em;

    public PersonDAOImpl(EntityManager em) {
        this.em = em;
    }

    public void add(Person newPerson) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(newPerson);
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }

    public List<Person> getAll() {

        Query query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }

    public void delete(int id) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Person person = em.getReference(Person.class, id);
            em.remove(person);
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }
}
