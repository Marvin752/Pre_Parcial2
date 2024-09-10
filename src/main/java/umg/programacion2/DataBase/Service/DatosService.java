package umg.programacion2.DataBase.Service;

import umg.programacion2.DataBase.Dao.DatosDao;
import umg.programacion2.DataBase.Model.DatosModel;

import java.sql.SQLException;
import java.util.List;

public class DatosService {

    private final DatosDao datosDao;

    // Constructor que inicializa DatosDao
    public DatosService() {
        this.datosDao = new DatosDao(); // Asegúrate de que DatosDao esté correctamente inicializado
    }

    public void createDatos(DatosModel datos) throws SQLException {
        datosDao.create(datos);
    }

    public DatosModel getDatosById(int id) throws SQLException {
        return datosDao.getById(id);
    }

    public List<DatosModel> getAllDatos() throws SQLException {
        return datosDao.getAll();
    }

    public void updateDatos(DatosModel datos) throws SQLException {
        datosDao.update(datos);
    }

    public void deleteDatos(int id) throws SQLException {
        datosDao.delete(id);
    }
}
