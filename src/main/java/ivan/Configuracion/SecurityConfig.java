package ivan.Configuracion;

import ivan.Servicios.ServicioUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final ServicioUsuario userService;

    public SecurityConfig (ServicioUsuario userService) {
        this.userService = userService;
    }

    //Configuracion de Security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //Para que deje utilizar AJAX con Spring Security csrf
                .csrf (csrf -> csrf.ignoringRequestMatchers ("/buscarPublicaciones", "/darLike", "/borrarPost", "/guardarPost"))
                .formLogin(form -> form
                        //Formulario por defecto creado por mi
                        .loginPage ("/login")
                        .permitAll ())
                .authorizeHttpRequests (req->req
                        //Apartados a los que se esta permitido entrar sin autorizacion para cargar el registro
                        .requestMatchers ("/registro", "/estilos/**", "/images/**", "/javascript/**").permitAll ()
                        .anyRequest ().authenticated ())
                .userDetailsService (userService).build ();
    }

    //Encriptamiento de contraseña
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
