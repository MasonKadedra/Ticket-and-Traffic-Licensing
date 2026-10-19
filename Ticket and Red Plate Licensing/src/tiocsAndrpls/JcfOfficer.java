package tiocsAndrpls;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JcfOfficer extends User implements Serializable {
	
	private String name;
	private String assignedStation;
	private int badgeNo;
	private String role;
	private PreparedStatement ps;
	private static Connection conn;
	
	public JcfOfficer() {
		
		name = "";
		assignedStation = "";
		badgeNo = 0;
	}
	
	public JcfOfficer(String email, String password, String name, String assignedStation, int badgeNo, String role) {
		
		super(email, password);
		this.name = name;
		this.assignedStation = assignedStation;
		this.badgeNo = badgeNo;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAssignedStation() {
		return assignedStation;
	}

	public void setAssignedStation(String assignedStation) {
		this.assignedStation = assignedStation;
	}

	public int getBadgeNo() {
		return badgeNo;
	}

	public void setBadgeNo(int badgeNo) {
		this.badgeNo = badgeNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "JcfOfficer [name=" + name + ", assignedStation=" + assignedStation + ", badgeNo=" + badgeNo + ", role="
				+ role + ", ps=" + ps + ", email=" + email + ", password=" + password + "]";
	}

	public static void addOfficer(JcfOfficer officer) {
		
		try (Connection conn = Login.getDatabaseConn()) {
			
			String sql = "INSERT INTO jcfofficer(email, password, name, assignedStation, badgeNo, role) VALUES (?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,  officer.getEmail());
			ps.setString(2, officer.getPassword());
			ps.setString(3,  officer.getName());
			ps.setString(4, officer.getAssignedStation());
			ps.setInt(5, officer.getBadgeNo());
			ps.setString(6, officer.getRole());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static JcfOfficer selectOfficer(String email) {
		
		try (Connection conn = DBConnection.getConnection()) {
			
			String sql = "SELECT * FROM jcfofficer WHERE email = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				JcfOfficer officer = new JcfOfficer();
				officer.setEmail(rs.getString("email"));
				officer.setPassword(rs.getString("password"));
				officer.setRole(rs.getString("role"));
				
				return officer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
	
