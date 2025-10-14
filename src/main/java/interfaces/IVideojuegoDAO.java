package interfaces;

import entities.Videojuego;

import java.util.List;

public interface IVideojuegoDAO {
    boolean insertar(Videojuego videojuego);
    boolean actualizar(Videojuego videojuego);
    boolean eliminar(long id);
    Videojuego leer(long id);

    List<Videojuego> obtenerTodos();
    List<Videojuego> buscarPorNombre(String nombre);
    List<Videojuego> buscarPorDesarrolladora(String desarrolladora);
    List<Videojuego> filtrarPorPuntajeMayorA(int puntajeMinimo);
    List<Videojuego> ordenarPorNombre();
    List<Videojuego> ordenarPorPuntajeDesc();
    List<Object[]> contarVideojuegosPorDesarrolladora();
    List<Videojuego> buscarSinJugadores();
    List<Videojuego> buscarConLogrosMayorA(int puntosMinimos);
    int actualizarPuntajePorNombre(String nombre, int nuevoPuntaje);
    int eliminarPorNombre(String nombre);

}
