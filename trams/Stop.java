package trams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Kulig on 21.01.2019.
 */
public class Stop {

    public static final String GET_ALL_STOPS = "SELECT " +
            "    id_przystanku, " +
            "    nazwa_przystanku " +
            "FROM " +
            "    przystanki";

    private Integer ID;

    private String description;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<Stop> getAllStops() {
        List<Stop> rows = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(GET_ALL_STOPS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Stop stop = new Stop();
                stop.setID(rs.getInt(1));
                stop.setDescription(rs.getString(2));
                rows.add(stop);
            }

        } catch (SQLException e) {

        } finally {
            try {
                stmt.close();
                conn.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rows;
    }

    @Override
    public String toString() {
        return description;
    }
}
