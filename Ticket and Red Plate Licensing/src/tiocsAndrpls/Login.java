package tiocsAndrpls;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	
	public JLabel email;
	public JLabel password;
	
	public JTextField emailTxt;
	public JPasswordField passwordTxt;
	
	private static Connection conn = null;
	
	private JButton Login;
	
	public Login() {
		
		getDatabaseConn();
		
		setTitle("Login");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(null);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		getContentPane().setBackground(Color.WHITE);
		
		ImageIcon logoIcon = new ImageIcon("Image/Logo.png");
		Image image = logoIcon.getImage().getScaledInstance(170, 160, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(image));

		
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(Color.WHITE);
		logoPanel.add(logoLabel);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		add(logoPanel, gbc);


		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		
		
		email = new JLabel("Email");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(email, gbc);
		
		emailTxt = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(emailTxt, gbc);
		
		password = new JLabel("Password");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		add(password, gbc);
		
		passwordTxt = new JPasswordField(25);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		add(passwordTxt, gbc);
		
		Login = new JButton("Login");
		Login.setBackground(Color.BLUE);
		Login.setForeground(Color.WHITE);
		Login.setFont(new Font("Arial", Font.BOLD, 14));
		
		Login.setFocusPainted(false);
		Login.setBorderPainted(false);
		Login.setContentAreaFilled(true);
		
		JPanel panel = new JPanel();
		panel.add(Login);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		add(panel, gbc);
		
		Login.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String email = emailTxt.getText().trim();
				String password = new String(passwordTxt.getPassword()).trim();
				
				if(email.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Empty Field", "Blank Fields", JOptionPane.ERROR_MESSAGE);
				}
				
				JcfOfficer officer = JcfOfficer.selectOfficer(email);
				
				if(officer == null) {
					
					JOptionPane.showMessageDialog(null, "Incorrect Login", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!password.equals(officer.getPassword()) || !email.equals(officer.getEmail())) {
					
					JOptionPane.showMessageDialog(null, "Incorrect Login", "Error", JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				
				if("JCF Officer".equals(officer.getRole())) {
					
					JcfOfficer loggedInOfficer = JcfOfficer.selectOfficer(email);
					new JcfDashboard(loggedInOfficer).setVisible(true);
					dispose();
					
					JOptionPane.showMessageDialog(null, "Login: Officer has loggedin", "Login", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
			
		});

	}
	
	
	public static Connection getDatabaseConn() {
		
		if (conn == null) {
			
			final String url = "jdbc:mysql://localhost:3306/tioc&rplsDB";
			
			try {
				
				conn = DriverManager.getConnection(url, "root", "");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	public static void main(String[] args) {
		
		//JcfOfficer officer = new JcfOfficer("gregPaul@gmail.com", "gregp1", "Greg Paul", "DownTown Police Station", 1001, "JCF Officer");
		//JcfOfficer.addOfficer(officer);
		
		new Login().setVisible(true);
	}

}
