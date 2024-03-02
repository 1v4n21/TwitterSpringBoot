package ivan.Servicios;

import ivan.Constructores.Usuario;
import ivan.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {

    @Autowired
    private UsuarioRepositorio usuarioDAO;

    //Agregar, Actualizar Usuario
    public boolean agregarUsuario(Usuario u) {
        usuarioDAO.save (u);
        return true;
    }

    //Obtener Usuario por id
    public Usuario obtenerUsuarioPorId(long idUsuario) {
        return usuarioDAO.getReferenceById (idUsuario);
    }

    //Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.findAll ();
    }

    //Eliminar Usuario
    public boolean eliminarUsuario(long idUsuario) {
        usuarioDAO.deleteById (idUsuario);
        return true;
    }

    public Usuario verificarUsuario(String nombreUsuario, String password){ return usuarioDAO.verificarUsuario (nombreUsuario, password); }
    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario){return usuarioDAO.obtenerUsuarioPorNombreUsuario (nombreUsuario); }

}
