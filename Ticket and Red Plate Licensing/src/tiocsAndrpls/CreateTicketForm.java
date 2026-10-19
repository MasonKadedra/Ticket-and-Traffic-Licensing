package tiocsAndrpls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateTicketForm extends JInternalFrame implements ActionListener {
	
	private JLabel ticketNum;
	private JTextField ticketNumField;
	
	private JLabel issueDate;
	private JTextField issueDateField;
	
	private JLabel offenceCode;
	private JComboBox<Integer> code;
	
	private JLabel description;
	private JTextField descriptionField;
	
	private JLabel fineAmount;
	private JTextField amountField;
	
	private JLabel licensePlate;
	private JTextField licensePlateField;
	
	private JLabel driverTRN;
	private JTextField driverTRNField;
	
	private JLabel driverName;
	private JTextField driverNameField;
	
	private JLabel driverDOB;
	private JTextField dobField;
	
	private JLabel address;
	private JTextField addressField;
	
	private JLabel parish;
	private JTextField parishField;
	
	private JLabel contactNo;
	private JTextField contactField;
	
	private JLabel badgeNum;
	private JTextField badgeNumField;
	
	private JLabel name;
	private JTextField nameField;
	
	private JLabel assignedStation;
	private JComboBox<String> assignedStationField;
	
	private JButton submit;
	private JcfOfficer officer;
	
	Date now = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Connection conn;
	
	public CreateTicketForm(JcfOfficer officer) {
		
		
		super("Create Ticket", true, true, true, true);

		this.officer = officer;
		
		setTitle("Create Ticket");
		setSize(800,800);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10, 10, 10);
		getContentPane().setBackground(Color.WHITE);
		
		ticketNum = new JLabel("Ticket Number");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		add(ticketNum, gbc);
		
		ticketNumField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		ticketNumField.setEditable(false);
		add(ticketNumField, gbc);
		
		issueDate = new JLabel("Issue Date");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(issueDate, gbc);
		
		issueDateField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		issueDateField.setEditable(false);
		issueDateField.setText(sdf.format(now));
		add(issueDateField, gbc);
		
		offenceCode = new JLabel("Offence Code");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		add(offenceCode, gbc);
		
		code = new JComboBox<>(new Integer[] {1, 2, 3});
		code.setPreferredSize(new Dimension(180, 25));
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		add(code, gbc);
		
		description = new JLabel("Description");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		add(description, gbc);
		
		descriptionField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		add(descriptionField, gbc);
		
		fineAmount = new JLabel("Fine Amount");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		add(fineAmount, gbc);
		
		amountField = new JTextField(25);
		amountField.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		add(amountField, gbc);
		
		licensePlate = new JLabel("License Plate");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		add(licensePlate, gbc);
		
		licensePlateField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		add(licensePlateField, gbc);
		
		driverTRN = new JLabel("Driver's TRN");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		add(driverTRN, gbc);
		
		driverTRNField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		add(driverTRNField, gbc);
		
		driverName = new JLabel("Driver's Name");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		add(driverName, gbc);
		
		driverNameField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		add(driverNameField, gbc);
		
		driverDOB = new JLabel("Driver's D.O.B");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		add(driverDOB, gbc);
		
		dobField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		add(dobField, gbc);
		
		address = new JLabel("Address");
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.anchor = GridBagConstraints.WEST;
		add(address, gbc);
		
		addressField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.anchor = GridBagConstraints.WEST;
		add(addressField, gbc);
		
		parish = new JLabel("Parish");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.anchor = GridBagConstraints.WEST;
		add(parish, gbc);
		
		parishField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.anchor = GridBagConstraints.WEST;
		add(parishField, gbc);
		
		contactNo = new JLabel("Contact Number");
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.anchor = GridBagConstraints.WEST;
		add(contactNo, gbc);
		
		contactField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.anchor = GridBagConstraints.WEST;
		add(contactField, gbc);
		
		badgeNum = new JLabel("Badge Number");
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.anchor = GridBagConstraints.WEST;
		add(badgeNum, gbc);
		
		badgeNumField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 12;
		gbc.anchor = GridBagConstraints.WEST;
		add(badgeNumField, gbc);
		
		name = new JLabel("Officer Name");
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.anchor = GridBagConstraints.WEST;
		add(name, gbc);
		
		nameField = new JTextField(25);
		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.anchor = GridBagConstraints.WEST;
		add(nameField, gbc);
		
		assignedStation = new JLabel("Assigned Station");
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.anchor = GridBagConstraints.WEST;
		add(assignedStation, gbc);
		
		assignedStationField = new JComboBox<>(new String[] {"DownTown Police Station", "Hunts Bay Station", "MayPen Plolice Station"});
		code.setPreferredSize(new Dimension(180, 25));
		gbc.gridx = 1;
		gbc.gridy = 14;
		gbc.anchor = GridBagConstraints.WEST;
		add(assignedStationField, gbc);
		
		JPanel btnPanel = new JPanel();
		submit = new JButton("Submit");
		submit.setBackground(Color.yellow);
		submit.setForeground(Color.WHITE);
		submit.setFont(new Font("Arial", Font.BOLD, 14));
		submit.setBorderPainted(false);
		submit.setOpaque(true);
		submit.setFocusPainted(false);
		btnPanel.add(submit);
		
		gbc.gridx = 0;
		gbc.gridy = 17;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		add(btnPanel, gbc);
		
		
		submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					
					Date issueD = sdf.parse(issueDateField.getText().trim());
					
				} catch (ParseException ex) {
					
					ex.printStackTrace();
				}
			
				int offence = (Integer) code.getSelectedItem();
				String description = descriptionField.getText().trim();
				String license = licensePlateField.getText().trim();
				String trn = driverTRNField.getText().trim();
				String name = driverNameField.getText().trim();
				String dob = dobField.getText().trim();
				String address = addressField.getText().trim();
				String parish = parishField.getText().trim();
				String phone = contactField.getText().trim();
				officer.setBadgeNo(Integer.parseInt(badgeNumField.getText()));
				officer.setName(nameField.getText());
				officer.setAssignedStation(assignedStationField.getSelectedItem().toString());
				
				Ticket ticket = new Ticket(offence, description, license, trn, name, dob, address, parish, phone, officer);
				
				ticketNumField.setText(String.valueOf(ticket.getTicketNo()));
				amountField.setText(String.valueOf(ticket.generateFine()));
				

			    Ticket.createTicket(ticket);
			    
				JOptionPane.showMessageDialog(null, "Ticket has been created!", "Ticket", JOptionPane.INFORMATION_MESSAGE);

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

}
