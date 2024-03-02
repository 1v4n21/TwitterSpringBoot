package ivan.Servicios;

import ivan.Constructores.Guardado;
import ivan.Repositorio.GuardadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioGuardado {

    @Autowired
    private GuardadoRepositorio guardadoDAO;

    //Agregar, Actualizar Guardado
    public boolean agregarGuardado(Guardado guardado) {
        guardadoDAO.save(guardado);
        return true;
    }

    //Eliminar Guardado
    public boolean eliminarGuardado(long idGuardado) {
        guardadoDAO.deleteById (idGuardado);
        return true;
    }

    //Obtener Guardado por id
    public Guardado obtenerGuardadoPorId(long idGuardado) { return guardadoDAO.getReferenceById (idGuardado); }

    //Obtener todos los Guardados
    public List<Guardado> obtenerTodosLosGuardados() {
        return guardadoDAO.findAll ();
    }

    //Consultas via query
    public List<Guardado> obtenerGuardadosPorIdUsuario(long idUsuario) {
        return guardadoDAO.obtenerGuardadosPorIdUsuario(idUsuario);
    }

    public List<Guardado> obtenerGuardadosPorIdPublicacion(long idPublicacion) {
        return guardadoDAO.obtenerGuardadosPorIdPublicacion(idPublicacion);
    }

    public Guardado obtenerGuardadoPorIdUsuarioYIdPublicacion(long idUsuario, long idPublicacion) {
        return guardadoDAO.obtenerGuardadoPorIdUsuarioYIdPublicacion(idUsuario, idPublicacion);
    }


}

