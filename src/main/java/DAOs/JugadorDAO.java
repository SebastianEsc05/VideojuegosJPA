package DAOs;

import entities.Jugador;
import interfaces.IJugadorDAO;

public class JugadorDAO implements IJugadorDAO {
    @Override
    public boolean insertar(Jugador jugador) {
        return true;
    }

    @Override
    public boolean actualizar(Jugador jugador) {
        return true;
    }

    @Override
    public boolean eliminar(long id) {
        return true;
    }

    @Override
    public Jugador leer(long id) {
        return null;
    }
}
