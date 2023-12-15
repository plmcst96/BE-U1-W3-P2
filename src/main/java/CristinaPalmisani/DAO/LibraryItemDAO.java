package CristinaPalmisani.DAO;

import CristinaPalmisani.entities.LibraryItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LibraryItemDAO {
    private final EntityManager em;

    public LibraryItemDAO(EntityManager em){
        this.em = em;
    }

    public  void save(LibraryItem item){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(item);
        tx.commit();
        System.out.println("Item " + item.getTitle() + " it was saved");
    }

    public LibraryItem getById(UUID id){
        return em.find(LibraryItem.class, id);
    }

    public void findByIdAndDelete(UUID id){
        LibraryItem found = em.find(LibraryItem.class, id);

        if (found != null){
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(found);
            tx.commit();
            System.out.println("Item it was correctly delete");
        } else {
            System.out.println("The Item by ID " + id + " It was not found");
        }
    }

    public List<LibraryItem> findByYear (LocalDate year){
        TypedQuery<LibraryItem> getItemsByYear = em.createNamedQuery("findByYear", LibraryItem.class);
        getItemsByYear.setParameter("yearPublication", year);
        return getItemsByYear.getResultList();
    }

    public List<LibraryItem> findByAuthor(String author){
        TypedQuery<LibraryItem> query = em.createQuery("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))", LibraryItem.class);
        query.setParameter("author", author);
        return  query.getResultList();
    }

    public List<LibraryItem> findByTitle(String title){
        TypedQuery<LibraryItem> query = em.createQuery("SELECT it FROM LibraryItem it WHERE LOWER(it.title) LIKE LOWER(CONCAT('%', :title, '%'))", LibraryItem.class);
        query.setParameter("title", title);
        return  query.getResultList();
    }
}
