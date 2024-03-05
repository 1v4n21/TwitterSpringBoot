package ivan.Servicios;

import ivan.Constructores.Guardado;
import ivan.Constructores.Publicacion;
import ivan.Repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ServicioPublicacion {
    @Autowired
    private PublicacionRepositorio publicacionDAO;

    @Autowired
    private ServicioGuardado servicioG;

    //Agregar, Actualizar Publicacion
    public boolean agregarPublicacion(Publicacion publicacion) {
        publicacionDAO.save (publicacion);
        return true;
    }

    //Eliminar Publicacion
    public boolean eliminarPublicacion(long idPublicacion) {
        publicacionDAO.deleteById (idPublicacion);
        return true;
    }

    //Obtener Publicacion por id
    public Publicacion obtenerPublicacionPorId(long idPublicacion) {
        return publicacionDAO.getReferenceById (idPublicacion);
    }

    //Obtener todas las publicaciones
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionDAO.obtenerTodasLasPublicaciones ();
    }

    //Consultas via query
    public List<Publicacion> obtenerPublicacionesPorIdUsuario(long idUsuario) {
        return publicacionDAO.obtenerPublicacionesPorIdUsuario(idUsuario);
    }

    public List<Publicacion> buscarPublicacionesPorNombreUsuario(String nombreUsuario) {
        return publicacionDAO.buscarPublicacionesPorNombreUsuario (nombreUsuario);
    }

    public List<Publicacion> obtenerPublicacionesGuardadasPorUsuario(long idUsuario) {
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

}

