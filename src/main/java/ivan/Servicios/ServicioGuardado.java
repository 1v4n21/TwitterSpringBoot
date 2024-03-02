package ivan.Servicios;

import ivan.Constructores.Guardado;
import ivan.Modelos.GuardadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicioGuardado {

    @Autowired
    private GuardadoDAO guardadoDAO;

    public ServicioGuardado() {
    }

    public ServicioGuardado(GuardadoDAO guardadoDAO) {
        this.guardadoDAO = guardadoDAO;
    }

    public GuardadoDAO getGuardadoDAO() {
        return guardadoDAO;
    }

    public void setGuardadoDAO(GuardadoDAO guardadoDAO) {
        this.guardadoDAO = guardadoDAO;
    }

    public void agregarGuardado(Guardado guardado) {
        guardadoDAO.agregarGuardado(guardado);
    }

    public Guardado obtenerGuardadoPorId(int idGuardado) {
        return guardadoDAO.obtenerGuardadoPorId(idGuardado);
    }

    public List<Guardado> obtenerTodosLosGuardados() {
        return guardadoDAO.obtenerTodosLosGuardados();
    }

    public List<Guardado> obtenerGuardadosPorIdUsuario(int idUsuario) {
        return guardadoDAO.obtenerGuardadosPorIdUsuario(idUsuario);
    }

    public List<Guardado> obtenerGuardadosPorIdPublicacion(int idPublicacion) {
        return guardadoDAO.obtenerGuardadosPorIdPublicacion(idPublicacion);
    }

    public Guardado obtenerGuardadoPorIdUsuarioYIdPublicacion(int idUsuario, int idPublicacion) {
        return guardadoDAO.obtenerGuardadoPorIdUsuarioYIdPublicacion(idUsuario, idPublicacion);
    }

    public void actualizarGuardado(Guardado guardado) {
        guardadoDAO.actualizarGuardado(guardado);
    }

    public void eliminarGuardado(long idGuardado) {
        guardadoDAO.eliminarGuardado(idGuardado);
    }
}

