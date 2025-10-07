package entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class Logro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private int puntos;

    @ManyToOne
    @JoinColumn(name = "videojuego")
    private Videojuego videojuego;

    public Logro() {
    }

    public Logro(long id, String nombre, int puntos, Videojuego videojuego) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
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
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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
                ", puntos=" + puntos +
                ", videojuego=" + videojuego +
                '}';
    }
}
