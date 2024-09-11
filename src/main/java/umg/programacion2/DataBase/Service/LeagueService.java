package umg.programacion2.DataBase.Service;

import umg.programacion2.DataBase.Dao.LeagueDao;
import umg.programacion2.DataBase.Model.LeagueModel;

import java.sql.SQLException;
import java.util.List;

public class LeagueService {
    private LeagueDao leagueDao;

    public LeagueService() {
        this.leagueDao = new LeagueDao();
    }

    public void addLeague(LeagueModel league) {
        try {
            leagueDao.addLeague(league);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    public LeagueModel getLeague(int id) {
        try {
            return leagueDao.getLeague(id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return null;
    }

    public List<LeagueModel> getAllLeagues() {
        try {
            return leagueDao.getAllLeagues();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return null;
    }

    public void updateLeague(LeagueModel league) {
        try {
            leagueDao.updateLeague(league);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    public void deleteLeague(int id) {
        try {
            leagueDao.deleteLeague(id);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
