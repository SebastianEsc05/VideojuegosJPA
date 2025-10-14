package interfaces;

import entities.Jugador;

public interface IJugadorDAO {
    boolean insertar(Jugador jugador);
    boolean actualizar(Jugador jugador);
    boolean eliminar(long id);
    Jugador leer(long id);
}
