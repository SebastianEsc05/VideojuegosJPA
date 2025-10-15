package DAOs;

import entities.Direccion;
import entities.Jugador;
import interfaces.IJugadorDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JugadorDAO implements IJugadorDAO {

    EntityManager em;
    public JugadorDAO(EntityManager em){
        this.em = em;
    }
    @Override
    public boolean insertar(Jugador jugador) {
        try{
            em.getTransaction().begin();
            em.persist(jugador);
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
    public boolean actualizar(Jugador jugador) {
        try{
            em.getTransaction().begin();
            em.merge(jugador);
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
            Jugador jugador = em.find(Jugador.class,id);
            if(jugador != null){
                em.remove(jugador);
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
    public Jugador leer(long id) {
        return em.find(Jugador.class,id);
    }

    @Override
    public List<Object[]> listarJugadoresConMasVideojuegos() {
        return em.createNamedQuery("Jugador.listarConMasVideojuegos", Object[].class)
                .getResultList();
    }

    @Override
    public List<Jugador> buscarPorPuntajeTotalMayorA(long puntajeMinimo) {
        TypedQuery<Jugador> query = em.createNamedQuery("Jugador.buscarPorPuntajeTotalMayorA", Jugador.class);
        query.setParameter("puntajeMinimo", puntajeMinimo);
        return query.getResultList();
    }

    @Override
    public List<Jugador> buscarPorColoniaYConVariosVideojuegos(String colonia) {
        return em.createNamedQuery("Jugador.buscarPorColoniaYConVariosVideojuegos", Jugador.class)
                .setParameter("colonia", colonia)
                .getResultList();
    }

    @Override
    public List<Jugador> ordenarPorEdadDesc() {
        return em.createNamedQuery("Jugador.ordenarPorEdadDesc", Jugador.class)
                .getResultList();
    }
}
