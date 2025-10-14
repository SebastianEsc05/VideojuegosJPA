package DAOs;

import entities.Logro;
import interfaces.ILogroDAO;

public class LogroDAO implements ILogroDAO {
    @Override
    public boolean insertar(Logro logro) {
        return true;
    }

    @Override
    public boolean actualizar(Logro logro) {
        return true;
    }

    @Override
    public boolean eliminar(long id) {
        return true;
    }

    @Override
    public Logro leer(long id) {
        return null;
    }
}
