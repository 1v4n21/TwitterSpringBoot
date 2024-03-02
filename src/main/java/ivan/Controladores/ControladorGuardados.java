package ivan.Controladores;

import ivan.Constructores.Guardado;
import ivan.Constructores.Publicacion;
import ivan.Constructores.Usuario;
import ivan.Servicios.ServicioGuardado;
import ivan.Servicios.ServicioMeGusta;
import ivan.Servicios.ServicioPublicacion;
import ivan.Servicios.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControladorGuardados {

    @Autowired
    private ServicioUsuario servicioU;

    @Autowired
    private ServicioPublicacion servicioP;

    @Autowired
    private ServicioGuardado servicioG;

    @Autowired
    private ServicioMeGusta servicioM;

    public ControladorGuardados () {
    }

    @RequestMapping("/guardados")
    public String guardados(Model modelo, HttpSession session) {
        // Verificar si la sesión ha sido iniciada
        if (session.getAttribute("usuarioLogueado") == null) {
            // Si no se ha iniciado sesión, redirigir a la página de login
            return "redirect:/login";
        }

        // Obtener el usuario logueado
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        // Obtener las publicaciones guardadas por el usuario logueado
        List<Publicacion> publicacionesGuardadas = servicioP.obtenerPublicacionesGuardadasPorUsuario(usuarioLogueado.getIdUsuario());

        // Agregar las publicaciones y el usuario logueado al modelo
        modelo.addAttribute("lasPublicaciones", publicacionesGuardadas);
        modelo.addAttribute("usuarioLogueado", usuarioLogueado);

        // Retornar la vista de inicio
        return "inicio";
    }

    @PostMapping("/guardarPost")
    @ResponseBody
    public ResponseEntity<String> guardarPost(@RequestParam int postId, @RequestParam int userId) {
        // Obtener la publicación y el usuario
        Publicacion publicacion = servicioP.obtenerPublicacionPorId(postId);
        Usuario usuario = servicioU.obtenerUsuarioPorId(userId);

        // Verificar si la publicación y el usuario existen
        if (publicacion != null && usuario != null) {

            // Obtener el "me gusta" para la publicación y el usuario
            Guardado guardado = servicioG.obtenerGuardadoPorIdUsuarioYIdPublicacion (userId, postId);

            // Realizar la acción de guardar o desguardar según la situación actual
            if (guardado != null) {
                // Si el usuario ya ha guardado el post, desguardarlo
                servicioG.eliminarGuardado(guardado.getIdGuardado ());
            } else {
                // Si el usuario no ha guardado el post, guardarlo
                servicioG.agregarGuardado(new Guardado(usuario, publicacion));
            }

            // Obtener el nuevo recuento de guardados para la publicación
            int nuevoRecuentoGuardados = publicacion.getGuardados().size();

            // Devolver el nuevo recuento de guardados como JSON
            return ResponseEntity.ok("{\"saved\":" + (guardado == null) + ", \"saveCount\":" + nuevoRecuentoGuardados + "}");
        } else {
            // Si la publicación o el usuario no existen, devolver un error 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/borrarGuardadoAdmin")
    public String borrarGuardadoAdmin(@RequestParam int guardadoId, HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si el usuario de la sesión es admin
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioSesion == null || !usuarioSesion.getRol().equals("admin")) {
            // Si el usuario no es admin, redirigir y mostrar un mensaje de error
            redirectAttributes.addFlashAttribute("error", "Acceso no autorizado");
            return "redirect:/login"; // Puedes redirigir a donde consideres apropiado
        }

        // Lógica para borrar "Guardado" según el guardadoId
        Guardado guardado = servicioG.obtenerGuardadoPorId(guardadoId);
        if (guardado != null) {
            // Borrar el "Guardado"
            servicioG.eliminarGuardado(guardado.getIdGuardado ());
            return "redirect:/admin?accion=guardados"; // Puedes redirigir a donde consideres apropiado
        } else {
            // Si no se encuentra el "Guardado", devolver un error
            return "redirect:/admin?accion=guardados"; // Puedes redirigir a donde consideres apropiado
        }
    }

}
