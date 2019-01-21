package trams;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel{

	private List<Employee> employeeList;
	String[] columnName = {"ID","Nazwisko","Imiê","P³aca","Data zatrudnienia","Ulica","Numer budynku","Miasto","Kod pocztowy","Stanowisko"};
	
	public EmployeeTableModel(List<Employee> employeeList){
		this.employeeList = employeeList;
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		return employeeList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee employee = new Employee();
		employee = employeeList.get(rowIndex);
		switch (columnIndex){
		case 0:
			return employee.getEmployeeId();
		case 1:
			return employee.getEmployeeSurname();
		case 2:
			return employee.getEmployeeName();
		case 3:			
			return employee.getEmployeeWage();
		case 4:
			return employee.getEmployeeDate();
		case 5:
			return employee.getEmployeeStreet();
		case 6:
			return employee.getEmployeeNum();
		case 7:
			return employee.getEmployeeCity();
		case 8:
			return employee.getEmployeePost();
		case 9:
			return employee.getEmployeePosition();
		default:
			return 0;
		}
	}
	
	@Override
	public String getColumnName(int col) {
	    return columnName[col];
	}
}
