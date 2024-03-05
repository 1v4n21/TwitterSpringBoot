package ivan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ivan.Constructores.Publicacion;
import ivan.Constructores.Usuario;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;

import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ServicioUsuario servicioU;
    private final ServicioPublicacion servicioP;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(ServicioUsuario servicioU, ServicioPublicacion servicioP, PasswordEncoder passwordEncoder) {
        this.servicioU = servicioU;
        this.servicioP = servicioP;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existe un usuario admin
        if (servicioU.obtenerUsuarioPorNombreUsuario("admin") == null) {
            // Si no existe, creamos el usuario admin
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setNombreUsuario("admin");
            admin.setPassword(passwordEncoder.encode("root1234"));
            admin.setRol("admin");

            // Guardamos el usuario admin
            servicioU.agregarUsuario(admin);

            // Creamos tres usuarios adicionales con dos publicaciones cada uno si no existen
            crearUsuarioYPublicaciones("user1", "user1@gmail.com", "password1", "user1");
            crearUsuarioYPublicaciones("user2", "user2@gmail.com", "password2", "user2");
            crearUsuarioYPublicaciones("user3", "user3@gmail.com", "password3", "user3");
        }
    }

    private void crearUsuarioYPublicaciones(String nombreUsuario, String email, String password, String nombre) {
        // Verificar si el usuario ya existe
        Usuario usuarioExistente = servicioU.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        if (usuarioExistente == null) {
            // Crear usuario
            usuarioExistente = new Usuario();
            usuarioExistente.setNombreUsuario(nombreUsuario);
            usuarioExistente.setEmail(email);
            usuarioExistente.setPassword(passwordEncoder.encode(password));
            usuarioExistente.setNombre(nombre);
            usuarioExistente.setRol("normal");

            // Guardar el usuario
            servicioU.agregarUsuario(usuarioExistente);
        }
    }
}


