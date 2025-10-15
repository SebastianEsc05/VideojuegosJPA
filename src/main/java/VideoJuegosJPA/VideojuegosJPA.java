package VideoJuegosJPA;

import DAOs.DireccionDAO;
import DAOs.JugadorDAO;
import DAOs.LogroDAO;
import DAOs.VideojuegoDAO;
import entities.Direccion;
import entities.Jugador;
import entities.Logro;
import entities.Videojuego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.HashSet;

public class VideojuegosJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegosJPA");
        EntityManager em = null;

        try {

            System.out.println("-------------Creando Datos de Prueba-------------");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Direccion d1 = new Direccion("Calle Falsa", "123", "Centro");
            Direccion d2 = new Direccion("Avenida Siempre Viva", "742", "Norte");
            Jugador j1 = new Jugador("GamerPro", "M", LocalDate.of(1995, 5, 20), d1, new HashSet<>());
            Jugador j2 = new Jugador("LadyPlayer", "F", LocalDate.of(2002, 11, 10), d2, new HashSet<>());
            Videojuego v1 = new Videojuego("Cyberpunk 2077", 90, "CD Projekt RED", new HashSet<>(), new HashSet<>());
            Videojuego v2 = new Videojuego("The Witcher 3", 95, "CD Projekt RED", new HashSet<>(), new HashSet<>());
            Videojuego v3 = new Videojuego("Stardew Valley", 98, "ConcernedApe", new HashSet<>(), new HashSet<>());
            Videojuego v4 = new Videojuego("Juego Vacio", 50, "Dev X", new HashSet<>(), new HashSet<>());
            Logro l1 = new Logro("Primer Paso", 50, v1);
            Logro l2 = new Logro("Maestro de Night City", 100, v1);
            Logro l3 = new Logro("Rey de las Bestias", 80, v2);

            v1.getLogros().add(l1);
            v1.getLogros().add(l2);
            v2.getLogros().add(l3);
            j1.getVideojuegos().add(v1);
            j1.getVideojuegos().add(v2);
            j2.getVideojuegos().add(v1);
            v1.getJugadores().add(j1);
            v1.getJugadores().add(j2);
            v2.getJugadores().add(j1);

            em.persist(j1);
            em.persist(j2);
            em.persist(v3);
            em.persist(v4);

            em.getTransaction().commit();
            em.close();
            System.out.println("-------------Datos de Prueba Creados -------------\n");

            System.out.println("-------------PROBANDO VIDEOJUEGODAO-------------");

            VideojuegoDAO videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());

            System.out.println("\n-------------Probando: leer(1L)-------------");
            Videojuego vLeido = videojuegoDAO.leer(1L);
            System.out.println(vLeido != null ? "Encontrado: " + vLeido.getNombre() : "No encontrado");

            System.out.println("\n-------------Probando: obtenerTodos()-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.obtenerTodos().forEach(videojuego -> System.out.println("- " + videojuego.getNombre()));

            System.out.println("\n-------------Probando: buscarPorNombre('Witcher')-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.buscarPorNombre("Witcher").forEach(videojuego -> System.out.println("- " + videojuego.getNombre()));

            System.out.println("\n-------------Probando: buscarPorDesarrolladora('CD Projekt RED')-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.buscarPorDesarrolladora("CD Projekt RED").forEach(videojuego -> System.out.println("- " + videojuego.getNombre()));

            System.out.println("\n-------------Probando: filtrarPorPuntajeMayorA(92)-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.filtrarPorPuntajeMayorA(92).forEach(videojuego -> System.out.println("- " + videojuego.getNombre() + " | Puntaje: " + videojuego.getPuntaje()));

            System.out.println("\n------------- Probando: ordenarPorNombre() -------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.ordenarPorNombre().forEach(videojuego -> System.out.println("- " + videojuego.getNombre()));

            System.out.println("\n-------------Probando: ordenarPorPuntajeDesc()-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.ordenarPorPuntajeDesc().forEach(videojuego -> System.out.println("- " + videojuego.getNombre() + " | Puntaje: " + videojuego.getPuntaje()));

            System.out.println("\n-------------Probando: contarVideojuegosPorDesarrolladora() -------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.contarVideojuegosPorDesarrolladora().forEach(obj -> System.out.println("- Desarrolladora: " + obj[0] + ", Cantidad: " + obj[1]));

            System.out.println("\n-------------Probando: buscarSinJugadores()-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.buscarSinJugadores().forEach(videojuego -> System.out.println("- " + videojuego.getNombre()));

            System.out.println("\n-------------Probando: buscarConLogrosMayorA(75)-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            videojuegoDAO.buscarConLogrosMayorA(75).forEach(videojuego -> System.out.println("- " + videojuego.getNombre()));

            System.out.println("\n-------------Probando: actualizarPuntajePorNombre('Stardew Valley', 100)-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            System.out.println("Filas actualizadas: " + videojuegoDAO.actualizarPuntajePorNombre("Stardew Valley", 100));

            System.out.println("\n-------------Probando: eliminarPorNombre('Juego Vacio')-------------");
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            System.out.println("Filas eliminadas: " + videojuegoDAO.eliminarPorNombre("Juego Vacio"));

            System.out.println("\n\n-------------PROBANDO JUGADORDAO-------------");
            JugadorDAO jugadorDAO = new JugadorDAO(emf.createEntityManager());

            System.out.println("\n-------------Probando: listarJugadoresConMasVideojuegos()-------------");
            jugadorDAO.listarJugadoresConMasVideojuegos().forEach(obj -> {
                Jugador j = (Jugador) obj[0];
                System.out.println("- Jugador: " + j.getPseudonimo() + ", Cantidad de juegos: " + obj[1]);
            });

            System.out.println("\n-------------Probando: buscarPorPuntajeTotalMayorA(100) -------------");
            jugadorDAO = new JugadorDAO(emf.createEntityManager());
            jugadorDAO.buscarPorPuntajeTotalMayorA(100).forEach(j -> System.out.println("- " + j.getPseudonimo()));

            System.out.println("\n-------------Probando: buscarPorColoniaYConVariosVideojuegos('Centro') -------------");
            jugadorDAO = new JugadorDAO(emf.createEntityManager());
            jugadorDAO.buscarPorColoniaYConVariosVideojuegos("Centro").forEach(j -> System.out.println("- " + j.getPseudonimo()));

            System.out.println("\n------------- Probando: ordenarPorEdadDesc()-------------");
            jugadorDAO = new JugadorDAO(emf.createEntityManager());
            jugadorDAO.ordenarPorEdadDesc().forEach(j -> System.out.println("- " + j.getPseudonimo() + " | Nacimiento: " + j.getFechaNacimiento()));

            System.out.println("\n\n-------------PROBANDO CRUDs ADICIONALES-------------");

            System.out.println("\n-------------Probando CRUD de Direccion -------------");
            DireccionDAO direccionDAO = new DireccionDAO(emf.createEntityManager());
            Direccion nuevaDir = new Direccion("Calle Test", "789", "Este");
            direccionDAO.insertar(nuevaDir);
            System.out.println("Insertada Dirección: " + nuevaDir.getCalle());

            nuevaDir.setColonia("Oeste");
            direccionDAO = new DireccionDAO(emf.createEntityManager());
            direccionDAO.actualizar(nuevaDir);
            System.out.println("Actualizada colonia a: " + nuevaDir.getColonia());

            System.out.println("\n------------- Probando CRUD de Logro-------------");
            LogroDAO logroDAO = new LogroDAO(emf.createEntityManager());
            videojuegoDAO = new VideojuegoDAO(emf.createEntityManager());
            Videojuego stardew = videojuegoDAO.buscarPorNombre("Stardew Valley").get(0);

            Logro nuevoLogro = new Logro("Logro de Prueba", 10, stardew);
            logroDAO.insertar(nuevoLogro);
            System.out.println("Insertado Logro: " + nuevoLogro.getNombre());

            logroDAO = new LogroDAO(emf.createEntityManager());
            logroDAO.eliminar(nuevoLogro.getId());
            System.out.println("Eliminado Logro: " + nuevoLogro.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
                System.out.println("\n-------------Conexión a la base de datos cerrada -------------");
            }
        }
    }


}
