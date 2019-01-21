package trams;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Font;

public class AdminFrame {

	JFrame frmZtmAdministracja;
	JPanel adminPane;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame window = new AdminFrame();
					window.frmZtmAdministracja.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	/**
	 * Create the application.
	 */
	public AdminFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZtmAdministracja = new JFrame();
		frmZtmAdministracja.setTitle("ZTM - Administracja");
		frmZtmAdministracja.setBounds(100, 100, 905, 400);
		frmZtmAdministracja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZtmAdministracja.getContentPane().setLayout(null);
		
		adminPane = new JPanel();
		adminPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 895, 365);
		frmZtmAdministracja.getContentPane().add(tabbedPane);
		
		tabbedPane.add(adminPane);
		tabbedPane.setTitleAt(0, "Panel g��wny");
		
		JLabel lblWybierzObszarDo = new JLabel("Wybierz obszar do administrowania:");
		lblWybierzObszarDo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWybierzObszarDo.setBounds(10, 21, 306, 14);
		adminPane.add(lblWybierzObszarDo);
		
		JButton btnPracownicyButton = new JButton("Pracownicy");
		btnPracownicyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeFrame emplFrame = new EmployeeFrame();
				//emplFrame.setVisible(true);
				tabbedPane.add(emplFrame.getContentPane());
				tabbedPane.setTitleAt(1, "Pracownicy");
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnPracownicyButton.setBounds(20, 46, 115, 115);
		adminPane.add(btnPracownicyButton);
		
		JButton btnLinieButton = new JButton("Linie");
		btnLinieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLinieButton.setBounds(165, 46, 115, 115);
		adminPane.add(btnLinieButton);
		

	}

}
