package entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "videojuego")
@NamedQueries(
        {
                @NamedQuery(name = "Videojuego.findAll",query = "SELECT v FROM Videojuego v"),
                @NamedQuery(name = "Videojuego.listarConMayorNumeroDeLogros", query = "SELECT v, COUNT(l.id) AS total_logros FROM Videojuego v JOIN v.logros l GROUP BY v ORDER BY total_logros DESC"),
                @NamedQuery(name = "Videojuego.buscarSinLogros", query = "SELECT v FROM Videojuego v WHERE v.logros IS EMPTY"),
                @NamedQuery(name = "Videojuego.buscarConSumaPuntosLogrosMayorA", query = "SELECT v FROM Videojuego v JOIN v.logros l GROUP BY v HAVING SUM(l.puntaje) > :puntajeTotalMinimo"),
                @NamedQuery(name = "Videojuego.buscarConLogroMasAlto", query = "SELECT v FROM Videojuego v JOIN v.logros l WHERE l.puntaje = (SELECT MAX(l2.puntaje) FROM Logro l2)")
        }
)


public class Videojuego implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private int puntaje;
    private String desarrolladora;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
    private Set<Logro> logros;

    @ManyToMany
    @JoinTable(
        name = "videojuego_jugador",
        joinColumns = @JoinColumn(name = "videojuego_id"),
        inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private Set<Jugador> jugadores;

    public Videojuego(){}

    public Videojuego(String nombre, int puntaje, String desarrolladora, Set<Logro> logros, Set<Jugador> jugadores) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.desarrolladora = desarrolladora;
        this.logros = logros;
        this.jugadores = jugadores;
    }

    public Videojuego(long id, String nombre, int puntaje, String desarrolladora, Set<Logro> logros, Set<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.desarrolladora = desarrolladora;
        this.logros = logros;
        this.jugadores = jugadores;
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

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public Set<Logro> getLogros() {
        return logros;
    }

    public void setLogros(Set<Logro> logros) {
        this.logros = logros;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

}
