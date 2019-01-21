package trams;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Kulig on 21.01.2019.
 */
public class TimetableRow {


    public static final String TIMETABLE_QUERY = "WITH distances AS ( " +
            "    SELECT " +
            "        l1.id_przystanku, " +
            "        l1.id_linii, " +
            "        SUM(l2.odleglosc_do_kolejnego) AS odl " +
            "    FROM " +
            "        linia_przystanek l1 " +
            "        JOIN linia_przystanek l2 ON l2.kolejnosc_przystanku <= l1.kolejnosc_przystanku " +
            "                                    AND l1.kierunek_linii = l2.kierunek_linii " +
            "    GROUP BY " +
            "        l1.id_przystanku, " +
            "        l1.id_linii " +
            ") " +
            "SELECT " +
            "    TO_CHAR(moment_odjazdu + distances.odl / 1440, 'DD.MM.YYYY HH24:MI:SS') as czas, " +
            "    k.id_linii as linia " +
            "FROM " +
            "    kursy k " +
            "    JOIN ( " +
            "        SELECT " +
            "            l1.id_przystanku   AS idp1, " +
            "            l1.id_linii, " +
            "            l1.kierunek_linii, " +
            "            l2.id_przystanku   AS idp2 " +
            "        FROM " +
            "            linia_przystanek l1 " +
            "            JOIN linia_przystanek l2 ON l1.id_linii = l2.id_linii " +
            "                                        AND l1.kierunek_linii = l2.kierunek_linii " +
            "        WHERE " +
            "            l1.kolejnosc_przystanku = '1' " +
            "    ) lp ON lp.idp1 = k.id_przystanku " +
            "    JOIN distances ON k.id_linii = distances.id_linii " +
            "                      AND lp.idp2 = distances.id_przystanku " +
            "                      AND k.moment_odjazdu BETWEEN sysdate and sysdate + 1" +
            "                      AND idp2 = ? " +
            "ORDER BY " +
            "    moment_odjazdu";

    private String time;

    private String line;

    public static List<TimetableRow> getTimetableRows(Integer stopID) {
        List<TimetableRow> rows = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(TIMETABLE_QUERY);
            stmt.setInt(1, stopID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                TimetableRow row = new TimetableRow();
                row.setTime(rs.getString(1));
                row.setLine(rs.getString(2));

                rows.add(row);
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
