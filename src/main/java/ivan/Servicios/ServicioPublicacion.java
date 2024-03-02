package ivan.Servicios;

import ivan.Constructores.Guardado;
import ivan.Constructores.Publicacion;
import ivan.Modelos.PublicacionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ServicioPublicacion {
    @Autowired
    private Publicacion publicacion;

    @Autowired
    private PublicacionDAO publicacionDAO;

    @Autowired
    private ServicioGuardado servicioG;

    public ServicioPublicacion() {
    }

    public ServicioPublicacion(PublicacionDAO publicacionDAO, Publicacion publicacion) {
        this.publicacionDAO = publicacionDAO;
        this.publicacion = publicacion;
    }

    public Publicacion getPublicacion() { return publicacion; }
    public PublicacionDAO getPublicacionDAO() {
        return publicacionDAO;
    }

    public void setPublicacionDAO(PublicacionDAO publicacionDAO) {
        this.publicacionDAO = publicacionDAO;
    }

    public void agregarPublicacion(Publicacion publicacion) {
        publicacionDAO.agregarPublicacion(publicacion);
    }

    public Publicacion obtenerPublicacionPorId(long idPublicacion) {
        return publicacionDAO.obtenerPublicacionPorId(idPublicacion);
    }

    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionDAO.obtenerTodasLasPublicaciones();
    }

    public List<Publicacion> obtenerPublicacionesPorIdUsuario(long idUsuario) {
        return publicacionDAO.obtenerPublicacionesPorIdUsuario(idUsuario);
    }

    public void actualizarPublicacion(Publicacion publicacion) {
        publicacionDAO.actualizarPublicacion(publicacion);
    }

    public void eliminarPublicacion(int idPublicacion) {
        publicacionDAO.eliminarPublicacion(idPublicacion);
    }

    public List<Publicacion> obtenerPublicacionesGuardadasPorUsuario(int idUsuario) {
        // Suponiendo que tienes un método en tu ServicioGuardado para obtener publicaciones guardadas por un usuario
        List<Guardado> guardados = servicioG.obtenerGuardadosPorIdUsuario(idUsuario);

        // Extraer los objetos Publicacion de las publicaciones guardadas
        List<Publicacion> publicacionesGuardadas = new ArrayList<>();
        for (Guardado guardado : guardados) {
            publicacionesGuardadas.add(guardado.getPublicacion());
        }

        // Ordenar la lista de publicaciones guardadas por fecha (de más reciente a más antiguo)
        publicacionesGuardadas.sort(Comparator.comparing(Publicacion::getFecha).reversed());

        return publicacionesGuardadas;
    }

    public List<Publicacion> buscarPublicacionesPorNombreUsuario(String nombreUsuario) {
        return publicacionDAO.buscarPublicacionesPorNombreUsuario (nombreUsuario);
    }
}

