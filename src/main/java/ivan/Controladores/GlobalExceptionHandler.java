package ivan.Controladores;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
Esta anotación marca la clase GlobalExceptionHandler como un controlador global de Spring
que manejará excepciones en toda la aplicación.
*/

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    // Maneja excepciones generales producidas por controladores
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        //model.addAttribute("elError", e.getMessage()); // Puedes personalizar el mensaje de error según tus necesidades
        return "error-500"; // Redirige a una página de error 500
    }

    // Maneja excepciones de acceso denegado producidas por Spring Security
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException e, Model model) {
        //model.addAttribute("elError", e.getMessage()); // Puedes personalizar el mensaje de error según tus necesidades
        return "error-403"; // Redirige a una página de error 403 (Acceso denegado)
    }


}
