package tiocsAndrpls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CheckDriverStatus extends JInternalFrame implements ActionListener {

	private JTable table;
	private DefaultTableModel model;
	
	private JButton viewOutstandingTickets;
	private JButton pastTickets;
	private JButton viewParish;
	
	private JComboBox<String> parishes;
	
	private JTextField response;
	private Ticket ticket;
	
	
	
	public CheckDriverStatus(Ticket ticket) {
		
		super("Driver Status", true, true, true, true);
		
		this.ticket = ticket;
		
		setTitle("Driver Status");
		setSize(800,800);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        
		model = new DefaultTableModel(new String[] { "Ticket Number", "Driver Name", "Driver TRN", "Address", "Parish", "Ticket Total", "Ticket Status"}, 0) 
				
		{
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // read-only table
            }
	    };
	    
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionBackground(new Color(51, 153, 255));
        table.setSelectionForeground(Color.white);
		
        // Center align certain columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        // Table header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(135,206,235));
        header.setForeground(Color.white);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setReorderingAllowed(false);

        add(new JScrollPane(table), BorderLayout.CENTER);
		
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        
        parishes = new JComboBox<>(new String[] {"Kingston", "St.Ann", "St. Andrew", "St. Catherine", "Clarendon", "Manchester", "St. Elizabeth", "Westmoreland", "Hanover", "St. James", "Trelawny", "St. Mary", "Portland", "St. Thomas"});
        parishes.setPreferredSize(new Dimension(180, 30));
        bottomPanel.add(parishes);
        
        viewParish = new JButton("View Parish");
        viewParish.setFont(new Font("Segoe UI", Font.BOLD, 14));
        viewParish.setBackground(new Color(244,164,96));
        viewParish.setForeground(Color.white);
        viewParish.setFocusPainted(false);
        viewParish.setPreferredSize(new Dimension(130, 35));
        bottomPanel.add(viewParish);
        
        viewOutstandingTickets = new JButton("View");
        viewOutstandingTickets.setFont(new Font("Segoe UI", Font.BOLD, 14));
        viewOutstandingTickets.setBackground(new Color(144,238,144));
        viewOutstandingTickets.setForeground(Color.white);
        viewOutstandingTickets.setFocusPainted(false);
        viewOutstandingTickets.setPreferredSize(new Dimension(130, 35));
        bottomPanel.add(viewOutstandingTickets);
        
        response = new JTextField(10);
        response.setPreferredSize(new Dimension(130, 35));
        bottomPanel.add(response);
        
        pastTickets = new JButton("Past Tickets (Over 21 days)");
        pastTickets.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pastTickets.setBackground(new Color(255,215,0));
        pastTickets.setForeground(Color.white);
        pastTickets.setFocusPainted(false);
        pastTickets.setPreferredSize(new Dimension(130, 35));
        bottomPanel.add(pastTickets);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        viewParish.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		String parish = parishes.getSelectedItem().toString();
				
        		parishTickets(parish);
        		
        	}
        	
        });
        
        viewOutstandingTickets.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		outstandingTickets();
        	}
        	
        });
        
        pastTickets.addActionListener(new ActionListener() {
        	
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		String answer = response.getText().trim();
        		pastTickets(answer);
        	}
        });
	}
	
	private void parishTickets(String parish) {
		
	    model.setRowCount(0); // clear table
		
		try (Connection conn = DBConnection.getConnection()) {
			
			final String sql = "SELECT * FROM ticket WHERE parish = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, parish);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				model.addRow(new Object[] {
						
						rs.getInt("ticketNo"),
						rs.getString("driverName"),
						rs.getString("driverTRN"),
						rs.getString("address"),
						rs.getString("parish"),
						rs.getFloat("fineAmount"),
						rs.getString("paymentStatus")
						
				});
	
			}
			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
		}
	}
	
	private void outstandingTickets() {
		
		model.setRowCount(0);
		
		try(Connection conn = DBConnection.getConnection()) {
			
			final String sql = "SELECT * WHERE paymentStatus = 'Unpaid' ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				model.addRow(new Object[] {
						
						rs.getInt("ticketNo"),
						rs.getString("driverName"),
						rs.getString("driverTRN"),
						rs.getString("address"),
						rs.getString("parish"),
						rs.getFloat("fineAmount"),
						rs.getString("paymentStatus")
						
				});
	
			}
			
		} catch (SQLException err) {
			
			err.printStackTrace();
		}
		
	}
	
	private void pastTickets(String driverTRN) {
		
		model.setRowCount(0);
		
		try(Connection conn = DBConnection.getConnection()) {
			
			final String sql = "SELECT * FROM ticket WHERE paymentStatus = 'Unpaid' AND driverTRN = ? AND DATEDIFF(CURDATE(), issueDate) > 21";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  driverTRN);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				model.addRow(new Object[] {
						
						rs.getInt("ticketNo"),
						rs.getString("driverName"),
						rs.getString("driverTRN"),
						rs.getString("address"),
						rs.getString("parish"),
						rs.getFloat("fineAmount"),
						rs.getString("paymentStatus")
						
				});
			}
			
		} catch (SQLSyntaxErrorException sql) {
			sql.printStackTrace();
			
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
