package umg.programacion2.DataBase.Service;

import umg.programacion2.DataBase.Dao.TelegramDao;
import umg.programacion2.DataBase.Model.TelegramModel;

import java.sql.SQLException;
import java.util.List;

public class TelegramService {

    private final TelegramDao telegramDao;

    public TelegramService() throws SQLException {
        this.telegramDao = new TelegramDao();
    }

    // Método para agregar un nuevo usuario
    public void addUsuario(TelegramModel usuario) throws SQLException {
        telegramDao.addUsuario(usuario);
    }

    // Método para obtener un usuario por ID
    public TelegramModel getUsuarioById(int idusuario) throws SQLException {
        return telegramDao.getUsuarioById(idusuario);
    }

    // Método para obtener todos los usuarios
    public List<TelegramModel> getAllUsuarios() throws SQLException {
        return telegramDao.getAllUsuarios();
    }

    // Método para actualizar un usuario
    public void updateUsuario(TelegramModel usuario) throws SQLException {
        telegramDao.updateUsuario(usuario);
    }

    // Método para eliminar un usuario
    public void deleteUsuario(int idusuario) throws SQLException {
        telegramDao.deleteUsuario(idusuario);
    }

    //Metodo para verificar si existe el usuario
    public boolean verificarCorreo(String correo) throws SQLException {
        return telegramDao.existeCorreo(correo);
    }

    // Obtener el correo actual del usuario por su ID

    public String getCorreoByIdUsuario(int idUsuario) throws SQLException {
        return telegramDao.getCorreoByIdUsuario(idUsuario); // Llama al DAO
    }
}

