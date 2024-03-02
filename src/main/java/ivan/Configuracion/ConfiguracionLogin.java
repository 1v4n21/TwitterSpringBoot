package ivan.Configuracion;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class ConfiguracionLogin implements WebMvcConfigurer {
    public void addViewControllers (ViewControllerRegistry registry){
        registry.addViewController("/login");
    }
}
