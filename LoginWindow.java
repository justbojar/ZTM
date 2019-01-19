package trams;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	private JFrame frmZtmLogowanie;
	private JTextField loginField;
	private JTextField passField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmZtmLogowanie.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZtmLogowanie = new JFrame();
		frmZtmLogowanie.setTitle("ZTM - Logowanie");
		frmZtmLogowanie.setBounds(100, 100, 370, 240);
		frmZtmLogowanie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZtmLogowanie.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogin.setBounds(89, 50, 46, 14);
		frmZtmLogowanie.getContentPane().add(lblLogin);
		
		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHaso.setBounds(89, 100, 46, 14);
		frmZtmLogowanie.getContentPane().add(lblHaso);
		
		loginField = new JTextField();
		loginField.setBounds(129, 48, 110, 20);
		frmZtmLogowanie.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		passField = new JTextField();
		passField.setBounds(129, 98, 110, 20);
		frmZtmLogowanie.getContentPane().add(passField);
		passField.setColumns(10);
		
		JButton btnZaloguj = new JButton("Zaloguj");
		btnZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login = loginField.getText();
				String pass = passField.getText();
				if(login.equals("admin") && pass.equals("admin")){
					AdminFrame adminFrame = new AdminFrame();
					adminFrame.frmZtmAdministracja.setVisible(true);
					frmZtmLogowanie.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "Nieprawid³owy login lub has³o, spróbuj ponownie. ", "Error", JOptionPane.ERROR_MESSAGE);
					loginField.setText(null);
					passField.setText(null);
				}
			}
		});
		btnZaloguj.setBounds(139, 137, 89, 23);
		frmZtmLogowanie.getContentPane().add(btnZaloguj);
	}
}
