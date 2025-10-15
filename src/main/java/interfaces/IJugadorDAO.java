package interfaces;

import entities.Jugador;

import java.util.List;

public interface IJugadorDAO {
    boolean insertar(Jugador jugador);
    boolean actualizar(Jugador jugador);
    boolean eliminar(long id);
    Jugador leer(long id);

    List<Object[]> listarJugadoresConMasVideojuegos();

    List<Jugador> buscarPorPuntajeTotalMayorA(long puntajeMinimo);

    List<Jugador> buscarPorColoniaYConVariosVideojuegos(String colonia);

    List<Jugador> ordenarPorEdadDesc();
}
