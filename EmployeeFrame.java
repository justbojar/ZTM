package trams;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.text.DateFormat;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeFrame extends JFrame {
	
	Connection conn;
	List<Employee> employeeList;
	Employee employee;
	EmployeeTableModel employeeTable;

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNazw;
	private JTextField textFieldImie;
	private JTextField textFieldPlaca;
	private JTextField textFieldData;
	private JTextField textFieldUlica;
	private JTextField textFieldNumer;
	private JTextField textFieldKod;
	private JTextField textFieldMiasto;
	private JTextField textFieldStan;
	private JScrollPane scrollPane;
	private JTextField textFieldId;
	
	//Pobieranie wszystkich pracownikow z bazy
	public void getEmployeeTableData(){
		employee = new Employee();
		employeeList = employee.getEmployees(conn);
		employeeTable = new EmployeeTableModel(employeeList);
		table = new JTable(employeeTable);
		table.setRowMargin(2);
		table.setRowHeight(18);
		table.setBorder(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 139, 859, 120);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
	}

	//Pobieranie pracownikow wedlug nazwiska
	public void getSearchedEmployeeTableData(String employeeSurname){
		employee = new Employee();
		employeeList = employee.getSearchedEmployees(conn, employeeSurname);
		employeeTable = new EmployeeTableModel(employeeList);
		table = new JTable(employeeTable);
		table.setRowMargin(2);
		table.setRowHeight(18);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBorder(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 139, 859, 120);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
	}
	
	//Odblokowanie/zablokowanie textFields do dodawania i modyfikacji pracownikow
	public void setFieldsEnabled(boolean flag){
		textFieldImie.setEnabled(flag);
		textFieldPlaca.setEnabled(flag);
		textFieldData.setEnabled(flag);
		textFieldUlica.setEnabled(flag);
		textFieldNumer.setEnabled(flag);
		textFieldKod.setEnabled(flag);
		textFieldMiasto.setEnabled(flag);
		textFieldStan.setEnabled(flag);
		textFieldId.setEnabled(flag);
	}
	
	//Czyszczenie TextFields
	public void clearFields(){
		textFieldId.setText(null);
		textFieldNazw.setText(null);
		textFieldImie.setText(null);
		textFieldPlaca.setText(null);
		textFieldData.setText(null);
		textFieldUlica.setText(null);
		textFieldNumer.setText(null);
		textFieldMiasto.setText(null);
		textFieldKod.setText(null);
		textFieldStan.setText(null);
	}
	
	//Kopiowanie danych pracownika wybranego z tablicy do textFieldow w celu modyfikacji
	public void copyChosenEmployee(){
		int i = table.getSelectedRow();
		String employeeId = employeeTable.getValueAt(i, 0).toString();
		String employeeSurname = employeeTable.getValueAt(i, 1).toString();
		String employeeName = employeeTable.getValueAt(i, 2).toString();
		String employeeWage = employeeTable.getValueAt(i, 3).toString();
		String employeeDate = employeeTable.getValueAt(i, 4).toString();
		String employeeStreet = employeeTable.getValueAt(i, 5).toString(); 
		String employeeNum = employeeTable.getValueAt(i, 6).toString();
		String employeeCity = employeeTable.getValueAt(i, 7).toString();
		String employeePost = employeeTable.getValueAt(i, 8).toString();
		String employeePosition = employeeTable.getValueAt(i, 9).toString();
		
		textFieldId.setText(employeeId);
		textFieldNazw.setText(employeeSurname);
		textFieldImie.setText(employeeName);
		textFieldPlaca.setText(employeeWage);
		textFieldData.setText(employeeDate);
		textFieldUlica.setText(employeeStreet);
		textFieldNumer.setText(employeeNum);
		textFieldMiasto.setText(employeeCity);
		textFieldKod.setText(employeePost);
		textFieldStan.setText(employeePosition);
	}
	
	//Modyfikacja wybranego pracownika
	public void updateChosenEmployee(){
		Integer id = Integer.parseInt(textFieldId.getText());
		String surname = textFieldNazw.getText();
		String name = textFieldImie.getText();
		Double wage = Double.parseDouble(textFieldPlaca.getText());
		String date = textFieldData.getText();
		String street = textFieldUlica.getText();
		String number = textFieldNumer.getText();
		String city = textFieldMiasto.getText();
		String post = textFieldKod.getText();
		String position = textFieldStan.getText().toUpperCase();

		employee = new Employee();
		employee.updateEmployee(conn, id, surname, name, wage, date, street, number, city, post, position);
	}
	
	//Dodanie nowego pracownika
	public void addEmployee(){
		Integer id = Integer.parseInt(textFieldId.getText());
		String surname = textFieldNazw.getText();
		String name = textFieldImie.getText();
		Double wage = Double.parseDouble(textFieldPlaca.getText());
		String date = textFieldData.getText();
		String street = textFieldUlica.getText();
		String number = textFieldNumer.getText();
		String city = textFieldMiasto.getText();
		String post = textFieldKod.getText();
		String position = textFieldStan.getText().toUpperCase();

		employee = new Employee();
		employee.addEmployee(conn, id, surname, name, wage, date, street, number, city, post, position);
	}
	
	//Usuwanie wybranego pracownika
	public void deleteChosenEmployee(){
		Integer id = Integer.parseInt(textFieldId.getText());
		employee = new Employee();
		employee.deleteEmployee(conn, id);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeFrame frame = new EmployeeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public EmployeeFrame() {
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setTitle("ZTM - Pracownicy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getEmployeeTableData();		//Dla pierwszego wywo³ania tabeli
		
		JLabel lblImie = new JLabel("Imi\u0119");
		lblImie.setBounds(155, 11, 43, 14);
		contentPane.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 11, 58, 14);
		contentPane.add(lblNazwisko);
		
		textFieldNazw = new JTextField();
		textFieldNazw.setBounds(67, 8, 80, 20);
		contentPane.add(textFieldNazw);
		textFieldNazw.setColumns(10);
		
		textFieldImie = new JTextField();
		textFieldImie.setEnabled(false);
		textFieldImie.setBounds(181, 8, 80, 20);
		contentPane.add(textFieldImie);
		textFieldImie.setColumns(10);
		
		JLabel lblPaca = new JLabel("P\u0142aca");
		lblPaca.setBounds(270, 11, 46, 14);
		contentPane.add(lblPaca);
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.setEnabled(false);
		textFieldPlaca.setBounds(304, 8, 80, 20);
		contentPane.add(textFieldPlaca);
		textFieldPlaca.setColumns(10);
		
		JLabel lblDataZatrudnienia = new JLabel("Data zatrudnienia");
		lblDataZatrudnienia.setBounds(394, 11, 104, 14);
		contentPane.add(lblDataZatrudnienia);
		
		textFieldData = new JTextField();
		textFieldData.setEnabled(false);
		textFieldData.setColumns(10);
		textFieldData.setBounds(497, 8, 80, 20);
		contentPane.add(textFieldData);
		
		JLabel lblUlica = new JLabel("Ulica");
		lblUlica.setBounds(588, 11, 46, 14);
		contentPane.add(lblUlica);
		
		textFieldUlica = new JTextField();
		textFieldUlica.setEnabled(false);
		textFieldUlica.setBounds(621, 8, 80, 20);
		contentPane.add(textFieldUlica);
		textFieldUlica.setColumns(10);
		
		JLabel lblNrBudynku = new JLabel("Nr. budynku");
		lblNrBudynku.setBounds(718, 11, 80, 14);
		contentPane.add(lblNrBudynku);
		
		textFieldNumer = new JTextField();
		textFieldNumer.setEnabled(false);
		textFieldNumer.setColumns(10);
		textFieldNumer.setBounds(789, 8, 80, 20);
		contentPane.add(textFieldNumer);
		
		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		lblKodPocztowy.setBounds(412, 51, 90, 14);
		contentPane.add(lblKodPocztowy);
		
		textFieldKod = new JTextField();
		textFieldKod.setEnabled(false);
		textFieldKod.setBounds(497, 48, 70, 20);
		contentPane.add(textFieldKod);
		textFieldKod.setColumns(10);
		
		JLabel lblMiasto = new JLabel("Miasto");
		lblMiasto.setBounds(577, 51, 46, 14);
		contentPane.add(lblMiasto);
		
		textFieldMiasto = new JTextField();
		textFieldMiasto.setEnabled(false);
		textFieldMiasto.setColumns(10);
		textFieldMiasto.setBounds(621, 48, 80, 20);
		contentPane.add(textFieldMiasto);
		
		JLabel lblStanowisko = new JLabel("Stanowisko");
		lblStanowisko.setBounds(718, 51, 70, 14);
		contentPane.add(lblStanowisko);
		
		textFieldStan = new JTextField();
		textFieldStan.setEnabled(false);
		textFieldStan.setColumns(10);
		textFieldStan.setBounds(789, 48, 80, 20);
		contentPane.add(textFieldStan);
		
		JLabel lblKliknijAbyWybra = new JLabel("Kliknij, aby wybra\u0107 pracownika:");
		lblKliknijAbyWybra.setBounds(10, 114, 229, 14);
		contentPane.add(lblKliknijAbyWybra);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(280, 51, 46, 14);
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(304, 48, 80, 20);
		contentPane.add(textFieldId);
		
		JButton btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.setBounds(63, 46, 90, 25);
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String surname = textFieldNazw.getText();
				if (surname.trim().length() > 0){
					getSearchedEmployeeTableData(surname);
				}else{
					getEmployeeTableData();
				}
				//JOptionPane.showMessageDialog(null, "Otrzymane dane: " + employeeList.size());
			}
		});
		contentPane.add(btnWyszukaj);
		
		JButton btnModyfikuj = new JButton("Modyfikuj");
		btnModyfikuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null, "Wybierz z tablicy pracownika do modyfikacji.", "Warning", JOptionPane.WARNING_MESSAGE);
				}else{
					updateChosenEmployee();
					clearFields();
					setFieldsEnabled(false);
					getEmployeeTableData(); //wyswietlenie zmodyfikowanych danych
				}
				
			}
		});
		btnModyfikuj.setBounds(304, 290, 90, 25);
		contentPane.add(btnModyfikuj);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldNazw.getText().trim().length() <= 0 || textFieldId.getText().trim().length() <= 0 || textFieldImie.getText().trim().length() <= 0 || 
						textFieldPlaca.getText().trim().length() <= 0 || textFieldKod.getText().trim().length() <= 0 || textFieldMiasto.getText().trim().length() <= 0 || 
						textFieldUlica.getText().trim().length() <= 0 || textFieldNumer.getText().trim().length() <= 0 || textFieldStan.getText().trim().length() <= 0 || textFieldData.getText().trim().length() <=0){
					JOptionPane.showMessageDialog(null, "Wpisz wszystkie dane nowego pracownika.", "Warning", JOptionPane.WARNING_MESSAGE);
				}else{
					addEmployee();
					clearFields();
					setFieldsEnabled(false);
					getEmployeeTableData(); //wyswietlenie zmodyfikowanych danych
				}
			}
		});
		btnDodaj.setBounds(734, 93, 90, 25);
		contentPane.add(btnDodaj);
		
		JButton btnUsu = new JButton("Usu\u0144");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null, "Wybierz z tablicy pracownika do usuniêcia.", "Warning", JOptionPane.WARNING_MESSAGE);
				}else{
					deleteChosenEmployee();
					clearFields();
					setFieldsEnabled(false);
					getEmployeeTableData(); //wyswietlenie zmodyfikowanych danych
				}
			}
		});
		btnUsu.setBounds(435, 290, 90, 25);
		contentPane.add(btnUsu);
		
		JButton btnNowy = new JButton("Nowy pracownik");
		btnNowy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFieldsEnabled(true);
			}
		});
		btnNowy.setBounds(575, 93, 126, 25);
		contentPane.add(btnNowy);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setFieldsEnabled(true);
				copyChosenEmployee();
			}
		});

	}
}
