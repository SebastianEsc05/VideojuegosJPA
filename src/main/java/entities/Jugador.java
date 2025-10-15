package entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity

@NamedQueries(
        {
            @NamedQuery(name = "Jugador.listarConMasVideojuegos",query = "SELECT j, COUNT(v.id) AS total_videojuegos FROM Jugador j JOIN j.videojuegos v GROUP BY j ORDER BY total_videojuegos DESC"),
            @NamedQuery(name = "Jugador.buscarPorPuntajeTotalMayorA",query = "SELECT j FROM Jugador j JOIN j.videojuegos v JOIN v.logros l GROUP BY j HAVING SUM(l.puntaje) > :puntajeMinimo"),
            @NamedQuery(name = "Jugador.buscarPorColoniaYConVariosVideojuegos",query = "SELECT j FROM Jugador j WHERE j.direccion.colonia = :colonia AND SIZE(j.videojuegos) > 1"),
            @NamedQuery(name = "Jugador.ordenarPorEdadDesc",query = "SELECT j FROM Jugador j ORDER BY j.fechaNacimiento ASC"),
            @NamedQuery(name = "Jugador.listarConDireccionOrdenadosPorColonia",query = "SELECT j, j.direccion.calle, j.direccion.colonia FROM Jugador j ORDER BY j.direccion.colonia ASC")
        }
)
public class Jugador implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String pseudonimo;
    private String sexo;
    private LocalDate fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private Direccion direccion;

    @ManyToMany(mappedBy = "jugadores")
    private Set<Videojuego> videojuegos;

    public Jugador(){}

    public Jugador(String pseudonimo, String sexo, LocalDate fechaNacimiento, Direccion direccion, Set<Videojuego> videojuegos) {
        this.pseudonimo = pseudonimo;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.videojuegos = videojuegos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Set<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(Set<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public Jugador(long id, String pseudonimo, String sexo, LocalDate fechaNacimiento, Direccion direccion, Set<Videojuego> videojuegos) {
        this.id = id;
        this.pseudonimo = pseudonimo;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.videojuegos = videojuegos;

    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", pseudonimo='" + pseudonimo + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion=" + direccion +
                ", videojuegos=" + videojuegos +
                '}';
    }
}

