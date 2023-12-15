package CristinaPalmisani;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
 static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("archiviolibreria");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println(em);
    }
}
