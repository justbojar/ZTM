package trams;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Employee {
	
	private Integer employeeId;
	private String employeeSurname;
	private String employeeName;
	private Double employeeWage;
	private Date employeeDate;
	private String employeeStreet; 
	private String employeeNum;
	private String employeeCity;
	private String employeePost;
	private String employeePosition;
	
	//Zwraca wszystkich pracownikow
	public List<Employee> getEmployees(Connection conn){
		List<Employee> employeeList = new ArrayList<>();
		Statement stmt = null;
		String sql = "select * from pracownicy";
		ResultSet rs = null;
		
		try{
			//pobranie polaczenia
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt(1));
				employee.setEmployeeSurname(rs.getString(2));
				employee.setEmployeeName(rs.getString(3));
				employee.setEmployeeWage(rs.getDouble(4));
				employee.setEmployeeDate(rs.getDate(5));
				employee.setEmployeeStreet(rs.getString(6));
				employee.setEmployeeNum(rs.getString(7));
				employee.setEmployeeCity(rs.getString(8));
				employee.setEmployeePost(rs.getString(9));
				employee.setEmployeePosition(rs.getString(10));
				employeeList.add(employee);
			}
			//zamykanie polaczenia
			stmt.close();
			conn.close();
			rs.close();
			
		}catch(SQLException exc){
			JOptionPane.showMessageDialog(null, "Problem z pobieraniem danych." + exc.getMessage(), "Errors", JOptionPane.ERROR_MESSAGE);
		}
		return employeeList;
	}
	
	//Zwraca pracownikow o wpisanym nazwisku
	public List<Employee> getSearchedEmployees(Connection conn, String employeeSurname){
		List<Employee> employeeList = new ArrayList<>();
		PreparedStatement stmt = null;
		String sql = "select id_pracownika, nazwisko, imie, wynagrodzenie, data_zatrudnienia, ulica, numer_budynku, miasto, kod_pocztowy, stanowisko from pracownicy where upper(nazwisko) like ? order by id_pracownika";
		ResultSet rs = null;
		
		try{
			//pobranie polaczenia
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employeeSurname.toUpperCase() + "%");
			rs = stmt.executeQuery();
			
			while (rs.next()){
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt(1));
				employee.setEmployeeSurname(rs.getString(2));
				employee.setEmployeeName(rs.getString(3));
				employee.setEmployeeWage(rs.getDouble(4));
				employee.setEmployeeDate(rs.getDate(5));
				employee.setEmployeeStreet(rs.getString(6));
				employee.setEmployeeNum(rs.getString(7));
				employee.setEmployeeCity(rs.getString(8));
				employee.setEmployeePost(rs.getString(9));
				employee.setEmployeePosition(rs.getString(10));
				employeeList.add(employee);
			}
			//zamykanie polaczenia
			stmt.close();
			conn.close();
			rs.close();
			
		}catch(SQLException exc){
			JOptionPane.showMessageDialog(null, "Problem z pobieraniem danych." + exc.getMessage(), "Errors", JOptionPane.ERROR_MESSAGE);
		}
		return employeeList;
	}
	
	//Modyfikuje pracownika
	public void updateEmployee(Connection conn, Integer employeeId, String employeeSurname, String employeeName, Double employeeWage, String employeeDate, String employeeStreet, String employeeNum, String employeeCity, String employeePost, String employeePosition){
		PreparedStatement stmt = null;
		String sql = "update pracownicy set nazwisko = ?, imie = ?, wynagrodzenie = ?, data_zatrudnienia = ?, ulica = ?, numer_budynku = ?, miasto = ?, kod_pocztowy = ?, stanowisko = ? where id_pracownika = ?";
		int result;
		
		try{
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employeeSurname);
			stmt.setString(2, employeeName);
			stmt.setDouble(3, employeeWage);
			stmt.setString(4, employeeDate);
			stmt.setString(5, employeeStreet);
			stmt.setString(6, employeeNum);
			stmt.setString(7, employeeCity);
			stmt.setString(8, employeePost);
			stmt.setString(9, employeePosition);
			stmt.setInt(10, employeeId);
			
			result = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Zmodyfikowano dane pracownika.");
		}
		catch(SQLException exc){
			JOptionPane.showMessageDialog(null, "Problem z modyfikacj¹ danych." + exc.getMessage(), "Errors", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Dodawanie pracownika
	public void addEmployee(Connection conn, Integer employeeId, String employeeSurname, String employeeName, Double employeeWage, String employeeDate, String employeeStreet, String employeeNum, String employeeCity, String employeePost, String employeePosition){
		PreparedStatement stmt = null;
		String sql = "insert into pracownicy values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result;
		
		try{
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			stmt.setString(2, employeeSurname);
			stmt.setString(3, employeeName);
			stmt.setDouble(4, employeeWage);
			stmt.setString(5, employeeDate);
			stmt.setString(6, employeeStreet);
			stmt.setString(7, employeeNum);
			stmt.setString(8, employeeCity);
			stmt.setString(9, employeePost);
			stmt.setString(10, employeePosition);
			
			result = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dodano nowego pracownika.");
		}
		catch(SQLException exc){
			JOptionPane.showMessageDialog(null, "Problem z dodaniem danych do bazy." + exc.getMessage(), "Errors", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	//Usuwanie pracownika
	public void deleteEmployee(Connection conn, Integer employeeId){
		PreparedStatement stmt = null;
		String sql = "delete from pracownicy where id_pracownika = ?";
		int result;
		
		try{
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			
			result = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuniêto wybranego pracownika.");
		}
		catch(SQLException exc){
			JOptionPane.showMessageDialog(null, "Problem z usuwaniem pracownika." + exc.getMessage(), "Errors", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeSurname() {
		return employeeSurname;
	}

	public void setEmployeeSurname(String employeeSurname) {
		this.employeeSurname = employeeSurname;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Double getEmployeeWage() {
		return employeeWage;
	}

	public void setEmployeeWage(Double employeeWage) {
		this.employeeWage = employeeWage;
	}

	public Date getEmployeeDate() {
		return employeeDate;
	}

	public void setEmployeeDate(Date employeeDate) {
		this.employeeDate = employeeDate;
	}

	public String getEmployeeStreet() {
		return employeeStreet;
	}

	public void setEmployeeStreet(String employeeStreet) {
		this.employeeStreet = employeeStreet;
	}

	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getEmployeeCity() {
		return employeeCity;
	}

	public void setEmployeeCity(String employeeCity) {
		this.employeeCity = employeeCity;
	}

	public String getEmployeePost() {
		return employeePost;
	}

	public void setEmployeePost(String employeePost) {
		this.employeePost = employeePost;
	}	
	
	public String getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}
}
