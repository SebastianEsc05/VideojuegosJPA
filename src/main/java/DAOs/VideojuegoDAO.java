package DAOs;

import entities.Videojuego;
import interfaces.IVideojuegoDAO;
import jakarta.persistence.EntityManager;

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
        return List.of();
    }

    @Override
    public List<Videojuego> buscarPorNombre(String nombre) {
        return List.of();
    }

    @Override
    public List<Videojuego> buscarPorDesarrolladora(String desarrolladora) {
        return List.of();
    }

    @Override
    public List<Videojuego> filtrarPorPuntajeMayorA(int puntajeMinimo) {
        return List.of();
    }

    @Override
    public List<Videojuego> ordenarPorNombre() {
        return List.of();
    }

    @Override
    public List<Videojuego> ordenarPorPuntajeDesc() {
        return List.of();
    }

    @Override
    public List<Object[]> contarVideojuegosPorDesarrolladora() {
        return List.of();
    }

    @Override
    public List<Videojuego> buscarSinJugadores() {
        return List.of();
    }

    @Override
    public List<Videojuego> buscarConLogrosMayorA(int puntosMinimos) {
        return List.of();
    }

    @Override
    public int actualizarPuntajePorNombre(String nombre, int nuevoPuntaje) {
        return 0;
    }

    @Override
    public int eliminarPorNombre(String nombre) {
        return 0;
    }
}
