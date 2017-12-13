package ua.kiev.prog.dao;

import ua.kiev.prog.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    private final EntityManager em;

    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    public void add(User newUser) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(newUser);
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }

    public List<User> getAll() {

        Query query = em.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList();
    }

    public void delete(int id) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            User user = em.getReference(User.class, id);
            em.remove(user);
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }
}
