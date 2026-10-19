package tiocsAndrpls;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JcfDashboard extends JFrame implements ActionListener {
	
	private JDesktopPane desktop;
	private JMenuBar menuBar;
	private JMenu menu1;
	private JMenu menu2;
	private JMenu menu3;
	private JMenuItem createTicket;
	private JMenuItem checkDriverStatus;
	private JMenuItem viewAll;
	private JButton logout;
	private JcfOfficer officer;
	private Ticket ticket;
	
	public JcfDashboard(JcfOfficer officer) {
		this.officer = officer;
		setTitle("JCF Officer Dashboard");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		getContentPane().setBackground(Color.WHITE);
		
		desktop = new JDesktopPane();
		setContentPane(desktop);
		
		menuBar = new JMenuBar();
		menu1 = new JMenu("Create Ticket");
		menu2 = new JMenu("Check Driver Status");
		menu3 = new JMenu("View All");
		
		createTicket = new JMenuItem("Create Ticket");
		checkDriverStatus = new JMenuItem("Check Status");
		viewAll = new JMenuItem("View All");
		
		menu1.add(createTicket);
		menu2.add(checkDriverStatus);
		menu3.add(viewAll);
		
        menuBar.add(menu1);
        menuBar.add(Box.createHorizontalGlue()); 
        
        menuBar.add(menu2);
        menuBar.add(Box.createHorizontalGlue()); 
        
        menuBar.add(menu3);
        menuBar.add(Box.createHorizontalGlue()); 
        
        setJMenuBar(menuBar);
		
		logout = new JButton("Logout");
	    logout.setBorderPainted(false);
	    logout.setOpaque(true);
	    logout.setFocusPainted(false);
	    logout.setBackground(Color.WHITE);
	    
	    
		menuBar.add(logout);
		
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int option = JOptionPane.showConfirmDialog(null,"Do you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				
				if(option == JOptionPane.YES_OPTION) {
					
					new Login().setVisible(true);
					dispose();
				}
				
			}
			
		});
		
		createTicket.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
	
				openTicketForm(officer);
			}
			
			
		});
		
		checkDriverStatus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				openDriverStatus(ticket);
			}
		});
		
		viewAll.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				openViewAll();
			}
			
		});
	}
	
	private void openTicketForm(JcfOfficer loggedIn) {
		
		CreateTicketForm form = new CreateTicketForm(loggedIn);
		form.setVisible(true);
		form.setLocation(30, 30);
		desktop.add(form);
		
        try {
        	
            form.setSelected(true);
            
        } catch (java.beans.PropertyVetoException ex) {
        	
            ex.printStackTrace();
        }
	}
	
	private void openDriverStatus(Ticket ticket) {
		
		CheckDriverStatus form = new CheckDriverStatus(ticket);
		form.setVisible(true);
		form.setLocation(30, 30);
		desktop.add(form);
		
		try {
			
			form.setSelected(true);
			
		} catch (java.beans.PropertyVetoException e) {
			
			e.printStackTrace();
		}
	}
	
	private void openViewAll() {
		
		ViewAll form1 = new ViewAll();
		form1.setVisible(true);
		form1.setLocation(30, 30);
		desktop.add(form1);
		
		try {
			
			form1.setSelected(true);
			
		} catch (java.beans.PropertyVetoException e) {
			
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
