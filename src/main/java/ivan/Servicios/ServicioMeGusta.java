package ivan.Servicios;

import ivan.Constructores.MeGusta;
import ivan.Modelos.MeGustaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicioMeGusta {

    @Autowired
    private MeGustaDAO meGustaDAO;

    public ServicioMeGusta() {
    }

    public ServicioMeGusta(MeGustaDAO meGustaDAO) {
        this.meGustaDAO = meGustaDAO;
    }

    public MeGustaDAO getMeGustaDAO() {
        return meGustaDAO;
    }

    public void setMeGustaDAO(MeGustaDAO meGustaDAO) {
        this.meGustaDAO = meGustaDAO;
    }

    public void agregarMeGusta(MeGusta meGusta) {
        meGustaDAO.agregarMeGusta(meGusta);
    }

    public MeGusta obtenerMeGustaPorId(int idMG) {
        return meGustaDAO.obtenerMeGustaPorId(idMG);
    }

    public List<MeGusta> obtenerTodosLosMeGustas() {
        return meGustaDAO.obtenerTodosLosMeGustas();
    }

    public List<MeGusta> obtenerMeGustasPorIdUsuario(int idUsuario) {
        return meGustaDAO.obtenerMeGustasPorIdUsuario(idUsuario);
    }

    public List<MeGusta> obtenerMeGustasPorIdPublicacion(int idPublicacion) {
        return meGustaDAO.obtenerMeGustasPorIdPublicacion(idPublicacion);
    }

    public MeGusta obtenerMeGustaPorIdUsuarioYIdPublicacion(int idUsuario, int idPublicacion) {
        return meGustaDAO.obtenerMeGustaPorIdUsuarioYIdPublicacion(idUsuario, idPublicacion);
    }

    public void actualizarMeGusta(MeGusta meGusta) {
        meGustaDAO.actualizarMeGusta(meGusta);
    }

    public void eliminarMeGusta(long idMG) {
        meGustaDAO.eliminarMeGusta(idMG);
    }
}
