package umg.programacion2.DataBase.Dao;

import umg.programacion2.DataBase.DbConnection.DatabaseConnection;
import umg.programacion2.DataBase.Model.DatosModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatosDao {

    private Connection connection;

    // Constructor que inicializa la conexión
    public DatosDao() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(DatosModel datos) throws SQLException {
        String query = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, datos.getNombre());
            stmt.setString(2, datos.getApellido());
            stmt.setString(3, datos.getDepartamento());
            stmt.setTimestamp(4, datos.getFechaNacimiento());
            stmt.executeUpdate();
        }
    }

    public DatosModel getById(int id) throws SQLException {
        String query = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DatosModel(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento"),
                        rs.getTimestamp("fecha_nacimiento")
                );
            }
        }
        return null;
    }

    public List<DatosModel> getAll() throws SQLException {
        List<DatosModel> datosList = new ArrayList<>();
        String query = "SELECT * FROM tb_datos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                DatosModel datos = new DatosModel(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento"),
                        rs.getTimestamp("fecha_nacimiento")
                );
                datosList.add(datos);
            }
        }
        return datosList;
    }

    public void update(DatosModel datos) throws SQLException {
        String query = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, datos.getNombre());
            stmt.setString(2, datos.getApellido());
            stmt.setString(3, datos.getDepartamento());
            stmt.setTimestamp(4, datos.getFechaNacimiento());
            stmt.setInt(5, datos.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM tb_datos WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
