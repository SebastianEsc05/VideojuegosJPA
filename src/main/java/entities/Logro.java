package entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NamedQueries(
        {
        @NamedQuery(name = "Logro.buscarConPuntajeMayorAlPromedio",query = "SELECT l FROM Logro l WHERE l.puntaje > (SELECT AVG(l2.puntaje) FROM Logro l2)")
        }
)
public class Logro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private int puntaje;

    @ManyToOne
    @JoinColumn(name = "videojuego")
    private Videojuego videojuego;

    public Logro() {
    }
    public Logro(String nombre, int puntaje, Videojuego videojuego) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.videojuego = videojuego;
    }

    public Logro(long id, String nombre, int puntaje, Videojuego videojuego) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.videojuego = videojuego;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntaje;
    }

    public void setPuntos(int puntaje) {
        this.puntaje = puntaje;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }

    @Override
    public String toString() {
        return "Logro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", puntos=" + puntaje +
                ", videojuego=" + videojuego +
                '}';
    }
}
