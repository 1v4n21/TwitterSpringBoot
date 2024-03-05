package ivan.Repositorio;

import ivan.Constructores.Guardado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuardadoRepositorio extends JpaRepository<Guardado, Long> {
    //Consultas via query
    @Query("from Guardado g where g.usuario.idUsuario = :idUsuario")
    public List<Guardado> obtenerGuardadosPorIdUsuario(long idUsuario);

    @Query("from Guardado g where g.publicacion.idPublicacion = :idPublicacion")
    public List<Guardado> obtenerGuardadosPorIdPublicacion(long idPublicacion);

    @Query("from Guardado g where g.usuario.idUsuario = :idUsuario and g.publicacion.idPublicacion = :idPublicacion")
    public Guardado obtenerGuardadoPorIdUsuarioYIdPublicacion(long idUsuario, long idPublicacion);
}
