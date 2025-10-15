package DAOs;

import entities.Jugador;
import entities.Logro;
import entities.Videojuego;
import interfaces.ILogroDAO;
import jakarta.persistence.EntityManager;

public class LogroDAO implements ILogroDAO {
    EntityManager em;
    public LogroDAO(EntityManager em){
        this.em = em;
    }
    @Override
    public boolean insertar(Logro logro) {
        try{
            em.getTransaction().begin();
            em.persist(logro);
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean actualizar(Logro logro) {
        try{
            em.getTransaction().begin();
            em.merge(logro);
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean eliminar(long id) {
        try{
            em.getTransaction().begin();
            Logro logro = em.find(Logro.class,id);
            if(logro != null){
                em.remove(logro);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
        return true;
    }

    @Override
    public Logro leer(long id) {
        return em.find(Logro.class,id);
    }
}
