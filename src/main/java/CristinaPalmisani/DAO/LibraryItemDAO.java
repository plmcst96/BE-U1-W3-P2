package CristinaPalmisani.DAO;

import CristinaPalmisani.entities.LibraryItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
}
