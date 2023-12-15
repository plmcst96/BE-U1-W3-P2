package CristinaPalmisani.DAO;

import CristinaPalmisani.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {
    private final EntityManager em;

    public  UserDAO(EntityManager em){
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        System.out.println("User " + user.getName() + user.getSurname() + " saved");
    }

    public User getById(int id) {
        return em.find(User.class, id);
    }

    public void delete(User user) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(user);
        tx.commit();
        System.out.println("User " + user.getName() + user.getSurname() + " he was deleted");
    }
}
