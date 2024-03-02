package ivan.Repositorio;

import ivan.Constructores.MeGusta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeGustaRepositorio extends JpaRepository<MeGusta, Long> {
    //Consultas via query
    @Query("from MeGusta mg where mg.usuario.idUsuario = :idUsuario")
    public List<MeGusta> obtenerMeGustasPorIdUsuario(long idUsuario);

    @Query("from MeGusta mg where mg.publicacion.idPublicacion = :idPublicacion")
    public List<MeGusta> obtenerMeGustasPorIdPublicacion(long idPublicacion);

    @Query("from MeGusta mg where mg.usuario.idUsuario = :idUsuario and mg.publicacion.idPublicacion = :idPublicacion")
    public MeGusta obtenerMeGustaPorIdUsuarioYIdPublicacion(long idUsuario, long idPublicacion);
}
