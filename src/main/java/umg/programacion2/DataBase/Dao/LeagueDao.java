package umg.programacion2.DataBase.Dao;

import umg.programacion2.DataBase.DbConnection.DatabaseConnection;
import umg.programacion2.DataBase.Model.LeagueModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeagueDao {
    private Connection connection;

    public LeagueDao() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLeague(LeagueModel league) throws SQLException {
        String query = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, league.getNombre());
            stmt.setString(2, league.getPais());
            stmt.setString(3, league.getCiudad());
            stmt.setString(4, league.getEstadio());
            stmt.setInt(5, league.getFundacion());
            stmt.setString(6, league.getEntrenador());
            stmt.setString(7, league.getWebOficial());
            stmt.setString(8, league.getFacebook());
            stmt.setString(9, league.getTwitter());
            stmt.setString(10, league.getInstagram());
            stmt.setString(11, league.getPatrocinadorPrincipal());
            stmt.executeUpdate();
        }
    }

    public LeagueModel getLeague(int id) throws SQLException {
        String query = "SELECT * FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LeagueModel league = new LeagueModel();
                league.setIdEquipo(rs.getInt("id_equipo"));
                league.setNombre(rs.getString("nombre"));
                league.setPais(rs.getString("pais"));
                league.setCiudad(rs.getString("ciudad"));
                league.setEstadio(rs.getString("estadio"));
                league.setFundacion(rs.getInt("fundacion"));
                league.setEntrenador(rs.getString("entrenador"));
                league.setWebOficial(rs.getString("web_oficial"));
                league.setFacebook(rs.getString("facebook"));
                league.setTwitter(rs.getString("twitter"));
                league.setInstagram(rs.getString("instagram"));
                league.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
                league.setCreadoEn(rs.getTimestamp("creado_en"));
                return league;
            }
        }
        return null;
    }

    public List<LeagueModel> getAllLeagues() throws SQLException {
        List<LeagueModel> leagues = new ArrayList<>();
        String query = "SELECT * FROM equipos_champions";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                LeagueModel league = new LeagueModel();
                league.setIdEquipo(rs.getInt("id_equipo"));
                league.setNombre(rs.getString("nombre"));
                league.setPais(rs.getString("pais"));
                league.setCiudad(rs.getString("ciudad"));
                league.setEstadio(rs.getString("estadio"));
                league.setFundacion(rs.getInt("fundacion"));
                league.setEntrenador(rs.getString("entrenador"));
                league.setWebOficial(rs.getString("web_oficial"));
                league.setFacebook(rs.getString("facebook"));
                league.setTwitter(rs.getString("twitter"));
                league.setInstagram(rs.getString("instagram"));
                league.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
                league.setCreadoEn(rs.getTimestamp("creado_en"));
                leagues.add(league);
            }
        }
        return leagues;
    }

    public void updateLeague(LeagueModel league) throws SQLException {
        String query = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, league.getNombre());
            stmt.setString(2, league.getPais());
            stmt.setString(3, league.getCiudad());
            stmt.setString(4, league.getEstadio());
            stmt.setInt(5, league.getFundacion());
            stmt.setString(6, league.getEntrenador());
            stmt.setString(7, league.getWebOficial());
            stmt.setString(8, league.getFacebook());
            stmt.setString(9, league.getTwitter());
            stmt.setString(10, league.getInstagram());
            stmt.setString(11, league.getPatrocinadorPrincipal());
            stmt.setInt(12, league.getIdEquipo());
            stmt.executeUpdate();
        }
    }

    public void deleteLeague(int id) throws SQLException {
        String query = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
