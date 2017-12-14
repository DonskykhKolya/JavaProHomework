package ua.kiev.prog.service;

import ua.kiev.prog.dao.UserDAO;
import ua.kiev.prog.dao.UserDAOImpl;
import ua.kiev.prog.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public class DataServiceImpl implements DataService {

    private final EntityManager em;
    private final UserDAO userDAO;

    public DataServiceImpl(EntityManager em) {
        this.em = em;
        userDAO = new UserDAOImpl(em);
    }

    @Override
    public void addUser(User user) {

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            userDAO.add(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public void deleteUser(int id) {

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            userDAO.delete(id);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }
}
