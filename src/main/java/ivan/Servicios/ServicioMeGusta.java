package ivan.Servicios;

import ivan.Constructores.MeGusta;
import ivan.Repositorio.MeGustaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMeGusta {

    @Autowired
    private MeGustaRepositorio meGustaDAO;

    //Agregar, Actualizar MeGusta
    public boolean agregarMeGusta(MeGusta meGusta) {
        meGustaDAO.save (meGusta);
        return true;
    }

    //Eliminar MeGusta
    public boolean eliminarMeGusta(long idMG) {
        meGustaDAO.deleteById (idMG);
        return true;
    }

    //OBtener MeGusta por id
    public MeGusta obtenerMeGustaPorId(long idMG) {
        return meGustaDAO.getReferenceById (idMG);
    }

    //Obtener todos los MeGusta
    public List<MeGusta> obtenerTodosLosMeGustas() {
        return meGustaDAO.findAll ();
    }

    //Consultas via query
    public List<MeGusta> obtenerMeGustasPorIdUsuario(long idUsuario) {
        return meGustaDAO.obtenerMeGustasPorIdUsuario(idUsuario);
    }

    public List<MeGusta> obtenerMeGustasPorIdPublicacion(long idPublicacion) {
        return meGustaDAO.obtenerMeGustasPorIdPublicacion(idPublicacion);
    }

    public MeGusta obtenerMeGustaPorIdUsuarioYIdPublicacion(long idUsuario, long idPublicacion) {
        return meGustaDAO.obtenerMeGustaPorIdUsuarioYIdPublicacion(idUsuario, idPublicacion);
    }
}
