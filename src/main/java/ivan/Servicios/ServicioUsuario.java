package ivan.Servicios;

import ivan.Constructores.Usuario;
import ivan.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioDAO;

    //Agregar, Actualizar Usuario
    public boolean agregarUsuario(Usuario u) {
        usuarioDAO.save (u);
        return true;
    }

    //Eliminar Usuario
    public boolean eliminarUsuario(long idUsuario) {
        usuarioDAO.deleteById (idUsuario);
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


    //Consultas via query
    public Usuario verificarUsuario(String nombreUsuario, String password){ return usuarioDAO.verificarUsuario (nombreUsuario, password); }
    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario){return usuarioDAO.obtenerUsuarioPorNombreUsuario (nombreUsuario); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando cargar el usuario con nombre: " + username);
        Usuario usuario = usuarioDAO.obtenerUsuarioPorNombreUsuario(username);

        if (usuario == null) {
            System.out.println("Usuario no encontrado para nombre de usuario: " + username);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        System.out.println("Usuario encontrado: " + usuario.getNombreUsuario());

        return User
                .withUsername(usuario.getNombreUsuario())
                .password(usuario.getPassword())
                .roles(usuario.getRol())
                .build();
    }

}
