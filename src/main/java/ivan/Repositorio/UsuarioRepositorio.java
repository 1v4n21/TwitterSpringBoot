package ivan.Repositorio;

import ivan.Constructores.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    //Consultas via query
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.password = :password")
    public Usuario verificarUsuario(String nombreUsuario, String password);

    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario);
}
