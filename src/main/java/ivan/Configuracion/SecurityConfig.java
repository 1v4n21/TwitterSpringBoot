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

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.formLogin(form -> form
                        .loginPage ("/login")
                        .permitAll ())
                .authorizeHttpRequests (req->req
                        .requestMatchers ("/registro", "/estilos/**", "/images/**", "/javascript/**").permitAll ()
                        .anyRequest ().authenticated ())
                .userDetailsService ((UserDetailsService) userService).build ();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
