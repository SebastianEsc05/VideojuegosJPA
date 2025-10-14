package DAOs;

import entities.Direccion;
import interfaces.IDireccionDAO;
import jakarta.persistence.EntityManager;

public class DireccionDAO implements IDireccionDAO {

    EntityManager em;
    public DireccionDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public boolean insertar(Direccion direccion) {
        try{
            em.getTransaction().begin();
            em.persist(direccion);
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
    public boolean actualizar(Direccion direccion) {
        try{
            em.getTransaction().begin();
            em.merge(direccion);
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
           Direccion direccion = em.find(Direccion.class,id);
           if(direccion != null){
               em.remove(direccion);
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
    public Direccion leer(long id) {
        return em.find(Direccion.class,id);
    }
}
