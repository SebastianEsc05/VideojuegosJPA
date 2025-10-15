package DAOs;

import entities.Videojuego;
import interfaces.IVideojuegoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideojuegoDAO implements IVideojuegoDAO {

    EntityManager em;
    public VideojuegoDAO(EntityManager em){
        this.em = em;
    }

    @Override
    public boolean insertar(Videojuego videojuego) {
        try{
            em.getTransaction().begin();
            em.persist(videojuego);
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
    public boolean actualizar(Videojuego videojuego) {
        try{
            em.getTransaction().begin();
            em.merge(videojuego);
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
            Videojuego videojuego = em.find(Videojuego.class,id);
            if(videojuego != null){
                em.remove(videojuego);
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
    public Videojuego leer(long id) {
        return em.find(Videojuego.class,id);
    }

    @Override
    public List<Videojuego> obtenerTodos() {
        TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v", Videojuego.class);
        return query.getResultList();
    }

    @Override
    public List<Videojuego> buscarPorNombre(String nombre) {
        TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v WHERE v.nombre LIKE :nombre", Videojuego.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    @Override
    public List<Videojuego> buscarPorDesarrolladora(String desarrolladora) {
        TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v WHERE v.desarrolladora = :desarrolladora", Videojuego.class);
        query.setParameter("desarrolladora", desarrolladora);
        return query.getResultList();
    }

    @Override
    public List<Videojuego> filtrarPorPuntajeMayorA(int puntajeMinimo) {
        TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v WHERE v.puntaje > :puntajeMinimo", Videojuego.class);
        query.setParameter("puntajeMinimo", puntajeMinimo);
        return query.getResultList();
    }

    @Override
    public List<Videojuego> ordenarPorNombre() {
        TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v ORDER BY v.nombre ASC", Videojuego.class);
        return query.getResultList();
    }

    @Override
    public List<Videojuego> ordenarPorPuntajeDesc() {
        TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v ORDER BY v.puntaje DESC", Videojuego.class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> contarVideojuegosPorDesarrolladora() {
        TypedQuery<Object[]> query = em.createQuery("SELECT v.desarrolladora, COUNT(v) FROM Videojuego v GROUP BY v.desarrolladora", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Videojuego> buscarSinJugadores() {
        TypedQuery<Videojuego> query = em.createQuery("SELECT v FROM Videojuego v WHERE v.jugadores IS EMPTY", Videojuego.class);
        return query.getResultList();
    }

    @Override
    public List<Videojuego> buscarConLogrosMayorA(int puntosMinimos) {
        TypedQuery<Videojuego> query = em.createQuery("SELECT DISTINCT v FROM Videojuego v JOIN v.logros l WHERE l.puntaje > :puntosMinimos", Videojuego.class);
        query.setParameter("puntosMinimos", puntosMinimos);
        return query.getResultList();
    }

    @Override
    public int actualizarPuntajePorNombre(String nombre, int nuevoPuntaje) {
        em.getTransaction().begin();
        int updatedCount = em.createQuery("UPDATE Videojuego v SET v.puntaje = :nuevoPuntaje WHERE v.nombre = :nombre")
                .setParameter("nuevoPuntaje", nuevoPuntaje)
                .setParameter("nombre", nombre)
                .executeUpdate();
        em.getTransaction().commit();
        return updatedCount;
    }

    @Override
    public int eliminarPorNombre(String nombre) {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM Videojuego v WHERE v.nombre = :nombre")
                .setParameter("nombre", nombre)
                .executeUpdate();
        em.getTransaction().commit();
        return deletedCount;
    }
}
