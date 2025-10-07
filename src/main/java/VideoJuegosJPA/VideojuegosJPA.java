package VideoJuegosJPA;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class VideojuegosJPA {
    public static void main(String [] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegosJPA");
        EntityManager em = emf.createEntityManager();

    }
}
