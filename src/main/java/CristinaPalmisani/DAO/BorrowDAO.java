package CristinaPalmisani.DAO;

import CristinaPalmisani.entities.Borrow;
import CristinaPalmisani.entities.LibraryItem;
import CristinaPalmisani.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class BorrowDAO {
    private final EntityManager em;

    public  BorrowDAO(EntityManager em){
        this.em = em;
    }

    public void save(Borrow borrow) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(borrow);
        tx.commit();
        System.out.println("User " + borrow.getLoanedItem().getTitle() + " saved");
    }

    public Borrow getById(int id) {
        return em.find(Borrow.class, id);
    }

    public void delete(Borrow borrow) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(borrow);
        tx.commit();
        System.out.println("User "+ borrow.getId() + " he was deleted");
    }

    public List<Borrow> findBorrowByCardNumUser(int cardNum){
        TypedQuery<Borrow> borrow = em.createNamedQuery("findBorrowUser", Borrow.class);
        borrow.setParameter("cardNumber", cardNum);
        return borrow.getResultList();
    }

    public List<Borrow> showUnreturnedItems() {
        TypedQuery<Borrow> query = em.createQuery("SELECT b FROM Borrow b WHERE b.effectiveReturnDate IS NULL AND b.returnDate < :today", Borrow.class);
        return query.getResultList();
    }
    public boolean isItemAvaiable(LibraryItem item) {
        TypedQuery<Borrow> query = em.createQuery("SELECT b FROM Borrow b WHERE b.loanedItem = :loanedItem " +
                "AND b.effectiveReturnDate = null", Borrow.class);
        query.setParameter("item", item);
        return query.getResultList().isEmpty();
    }

    private List<Borrow> findAll() {
        TypedQuery<Borrow> query = em.createQuery("SELECT b FROM Borrow b", Borrow.class);
        return query.getResultList();
    }
}
