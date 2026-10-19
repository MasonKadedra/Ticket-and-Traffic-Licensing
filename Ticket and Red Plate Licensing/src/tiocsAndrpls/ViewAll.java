package tiocsAndrpls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.Document;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ViewAll extends JInternalFrame implements ActionListener {
	
	private DefaultTableModel model;
	private JTable table;
	
	private JButton generateReports;
	private Ticket ticket;
	
	
	public ViewAll() {
		
		super("Reports", true, true, true, true);
		
		setTitle("Reports");
		setSize(800,800);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
		
		 model = new DefaultTableModel(new String[] {"Driver Name", "Driver Address", "TRN", "Contact Number", "Ticket Number" , "Date Issued", "Status"}, 0) 		
		 {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // read-only table
	            }
		   };
		    
		    table = new JTable(model);
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
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
	        header.setBackground(new Color(244,164,96));
	        header.setForeground(Color.white);
	        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
	        header.setReorderingAllowed(false);

	        add(new JScrollPane(table), BorderLayout.CENTER);
	        
	        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
	        
	        generateReports = new JButton("Generate Reports");
	        generateReports.setFont(new Font("Segoe UI", Font.BOLD, 14));
	        generateReports.setBackground(new Color(244,164,96));
	        generateReports.setForeground(Color.white);
	        generateReports.setFocusPainted(false);
	        generateReports.setPreferredSize(new Dimension(180, 35));
	        bottomPanel.add(generateReports);
	        
	        
	        add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void generateReports() {
		
		try {
			
			int selectedRow = table.getSelectedRow();
			
			if(selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Select a report to view.");
			}
			
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save Report as PDF");
	        chooser.setSelectedFile(new java.io.File("Ticket Information_" + ticket.getTicketNo() + ".pdf"));
	        int userSelection = chooser.showSaveDialog(this);
	        if (userSelection != JFileChooser.APPROVE_OPTION) return;
			
	        PDDocument document = new PDDocument();
			
			PDPage page = new PDPage();
			document.addPage(page);
			
			PDPageContentStream content = new PDPageContentStream(document, page);

			content.beginText();
            content.setFont(PDType1Font.SERIF_BOLD, 16);
            content.setLeading(20f);
            content.newLineAtOffset(50, 750);
            
            content.showText("");

			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
