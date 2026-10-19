package tiocsAndrpls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Driver {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/tioc&rplsDB";
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url, "root", "");
			
			if (conn != null) {
				JOptionPane.showMessageDialog(null, "Database Connected", "Database Responding", JOptionPane.INFORMATION_MESSAGE);
			} 
			
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}


