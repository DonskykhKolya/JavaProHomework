package ua.kiev.prog.dao;


import ua.kiev.prog.entity.Address;
import ua.kiev.prog.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class AddressDAOImpl  implements AddressDAO {

    private final EntityManager em;

    public AddressDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Address newAddres) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(newAddres);
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public Address getAddress(int userId) {

        Query query = em.createQuery("SELECT a FROM Address a WHERE a.user=:user", Address.class);
        query.setParameter("user", userId);
        return (Address)query.getSingleResult();
    }

    @Override
    public void delete(int id) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Address address = em.getReference(Address.class, id);
            User user = address.getUser();
            user.setAddress(null);
            em.merge(user);
            em.remove(address);
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
    }
}
