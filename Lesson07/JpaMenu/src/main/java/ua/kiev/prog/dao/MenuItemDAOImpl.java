package ua.kiev.prog.dao;

import ua.kiev.prog.entity.MenuItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class MenuItemDAOImpl implements MenuItemDAO {

    private static final String DB_UNIT_NAME = "JPAMenu";

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public MenuItemDAOImpl() {
        init();
    }

    private void init() {

        entityManagerFactory = Persistence.createEntityManagerFactory(DB_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public boolean add(MenuItem newItem) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(newItem);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
        return true;
      }

    @Override
    public List<MenuItem> getByPrice(double min, double max) {

        TypedQuery<MenuItem> query = entityManager.createQuery("SELECT m FROM MenuItem m WHERE m.price >= :min AND m.price <= :max", MenuItem.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    @Override
    public List<MenuItem> getWithDiscount() {

        TypedQuery<MenuItem> query = entityManager.createQuery("SELECT m FROM MenuItem m WHERE m.hasDiscount = TRUE", MenuItem.class);
        return query.getResultList();
    }

    @Override
    public List<MenuItem> getByWeight(int maxWeight) {

        List<MenuItem> result = new ArrayList<>();

        TypedQuery<MenuItem> query = entityManager.createQuery("SELECT m FROM MenuItem m", MenuItem.class);
        List<MenuItem> menuList = query.getResultList();

        int sumWeight = 0;
        for(MenuItem item : menuList) {
            sumWeight += item.getWeight();
            if(sumWeight <= maxWeight) {
                result.add(item);
            }
        }

        return result;
    }

    @Override
    public void close() {

        entityManager.close();
        entityManagerFactory.close();
    }
}
