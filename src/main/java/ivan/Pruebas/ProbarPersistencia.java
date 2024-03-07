package ivan.Pruebas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProbarPersistencia {
    public static void main(String[] args) {
        EntityManagerFactory emf   = Persistence.createEntityManagerFactory("JPAIvan");
        EntityManager em = emf.createEntityManager();

        System.out.println("Se ha creado la persistencia...");


        em.close();

        emf.close();
    }
}
