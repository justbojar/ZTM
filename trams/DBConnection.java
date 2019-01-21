package trams;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Klasa sluzaca do utworzenia polaczenia JDBC z baza Oracle
 * @author Justyna
 */

public class DBConnection {
	
	private static final String DB_URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
	
	public static Connection getConnection(){
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {

		}

		try{
		//Utworzenie polaczenia
		conn = DriverManager.getConnection(DB_URL, "pkulig", "pkulig");
		if (conn != null){
		//	JOptionPane.showMessageDialog(null, "Utworzono polaczenie z baza danych.");
		}
		}catch(SQLException exc){
			JOptionPane.showMessageDialog(null, "Problem z nawiazaniem polaczenia z baza danych. " + exc.getMessage(), "Errors", JOptionPane.ERROR_MESSAGE);
		}
		return conn;
	}
}
