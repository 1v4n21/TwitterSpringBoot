package ivan.Repositorio;

import ivan.Constructores.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {
    //Consultas via query
    @Query("from Publicacion p where p.usuario.idUsuario = :idUsuario")
    public List<Publicacion> obtenerPublicacionesPorIdUsuario(long idUsuario);

    @Query("SELECT p FROM Publicacion p WHERE p.usuario.nombreUsuario LIKE :nombreUsuarioPrefix")
    public List<Publicacion> buscarPublicacionesPorNombreUsuario(String nombreUsuarioPrefix);

    @Query("FROM Publicacion p ORDER BY p.fecha DESC")
    public List<Publicacion> obtenerTodasLasPublicaciones();
}
