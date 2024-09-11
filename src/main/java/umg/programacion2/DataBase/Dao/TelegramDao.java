package umg.programacion2.DataBase.Dao;

import umg.programacion2.DataBase.DbConnection.DatabaseConnection;
import umg.programacion2.DataBase.Model.TelegramModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelegramDao {

    private final Connection connection;

    public TelegramDao() throws SQLException {
        this.connection = DatabaseConnection.getConnection(); // Asume que tienes una clase DatabaseConnection que maneja las conexiones
    }

    // Método para crear un nuevo usuario
    public void addUsuario(TelegramModel usuario) throws SQLException {
        String query = "INSERT INTO tb_usuarios (nombre, carne, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCarne());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getSeccion());
            statement.setObject(5, usuario.getTelegramid()); // Usa setObject para permitir null
            statement.setString(6, usuario.getActivo());

            statement.executeUpdate();
        }
    }

    // Método para obtener un usuario por ID
    public TelegramModel getUsuarioById(int idusuario) throws SQLException {
        String sql = "SELECT * FROM tb_usuarios WHERE idusuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idusuario);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                TelegramModel usuario = new TelegramModel();
                usuario.setIdusuario(resultSet.getInt("idusuario"));
                usuario.setCarne(resultSet.getString("carne"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setSeccion(resultSet.getString("seccion"));
                usuario.setTelegramid(resultSet.getLong("telegramid"));
                usuario.setActivo(resultSet.getString("activo"));
                return usuario;
            }
        }
        return null;
    }

    // Método para obtener todos los usuarios
    public List<TelegramModel> getAllUsuarios() throws SQLException {
        List<TelegramModel> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM tb_usuarios";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TelegramModel usuario = new TelegramModel();
                usuario.setIdusuario(resultSet.getInt("idusuario"));
                usuario.setCarne(resultSet.getString("carne"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setSeccion(resultSet.getString("seccion"));
                usuario.setTelegramid(resultSet.getLong("telegramid"));
                usuario.setActivo(resultSet.getString("activo"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    // Método para actualizar un usuario
    public void updateUsuario(TelegramModel usuario) throws SQLException {
        String sql = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getCarne());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getSeccion());
            statement.setObject(5, usuario.getTelegramid());
            statement.setString(6, usuario.getActivo());
            statement.setInt(7, usuario.getIdusuario());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un usuario
    public void deleteUsuario(int idusuario) throws SQLException {
        String sql = "DELETE FROM tb_usuarios WHERE idusuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idusuario);
            statement.executeUpdate();
        }
    }

    //Metodo para verificar la existencia del ususario

    public boolean existeCorreo(String correo) throws SQLException {
        String query = "SELECT COUNT(*) FROM tb_usuarios WHERE correo = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Si el conteo es mayor a 0, el correo existe
            }
        }
        return false; // El correo no existe
    }

}

