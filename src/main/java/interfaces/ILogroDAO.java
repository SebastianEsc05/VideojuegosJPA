package interfaces;

import entities.Logro;

public interface ILogroDAO {
    boolean insertar(Logro logro);
    boolean actualizar(Logro logro);
    boolean eliminar(long id);
    Logro leer(long id);


}
