package interfaces;

import entities.Direccion;

public interface IDireccionDAO {
    boolean insertar(Direccion direccion);
    boolean actualizar(Direccion direccion);
    boolean eliminar(long id);
    Direccion leer(long id);
}
