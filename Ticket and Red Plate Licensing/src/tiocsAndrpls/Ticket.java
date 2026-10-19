package tiocsAndrpls;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.util.Date;

public class Ticket implements Serializable {
	
	private static int ticketCounter = 1000;
	private int ticketNo;
	private Date issueDate;
	private int offenceCode;
	private String description;
	private float fineAmount;
	private String licensePlate;
	private String driverTRN;
	private String driverName;
	private String driverDOB;
	private String address;
	private String parish;
	private String contactNo;
	private JcfOfficer officer;
	private JcfOfficer badgeNum;
	private JcfOfficer name;
	private JcfOfficer assignedStation;
	private String paymentStatus;
	
	public Ticket() {
		
		this.ticketNo = ticketCounter++;
		issueDate = new Date(System.currentTimeMillis());
		offenceCode = 0;
		description = "";
		fineAmount = 0f;
		licensePlate = "";
		driverTRN = "";
		driverName = "";
		driverDOB = "";
		address = "";
		parish = "";
		contactNo = "";
		paymentStatus = "";
	}
	
	public Ticket(int ticketNo, Date issueDate, int offenceCode, String description, float fineAmount,
			String licensePlate, String driverTRN, String driverName, String driverDOB, String address, String parish,
			String contactNo, JcfOfficer badgeNum, JcfOfficer name, JcfOfficer assignedStation) {
		
		this.ticketNo = ticketCounter++;
		this.issueDate = issueDate;
		this.offenceCode = offenceCode;
		this.description = description;
		this.fineAmount = fineAmount;
		this.licensePlate = licensePlate;
		this.driverTRN = driverTRN;
		this.driverName = driverName;
		this.driverDOB = driverDOB;
		this.address = address;
		this.parish= parish;
		this.contactNo = contactNo;
		this.badgeNum = badgeNum;
		this.name = name;
		this.assignedStation = assignedStation;
		//this.officer = officer;
	}
	
	public Ticket(int offenceCode, String description,
			String licensePlate, String driverTRN, String driverName, String driverDOB, String address, String parish,
			String contactNo, JcfOfficer officer) {
		
		this.ticketNo = ticketCounter++;;
		this.issueDate = new Date(System.currentTimeMillis());
		this.offenceCode = offenceCode;
		this.description = description;
		this.licensePlate = licensePlate;
		this.driverTRN = driverTRN;
		this.driverName = driverName;
		this.driverDOB = driverDOB;
		this.address = address;
		this.parish = parish;
		this.contactNo = contactNo;
		this.officer = officer;
		this.badgeNum = officer;
		this.name = officer;
		this.assignedStation = officer;
		this.paymentStatus = "Unpaid";
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public int getOffenceCode() {
		return offenceCode;
	}

	public void setOffenceCode(int offenceCode) {
		this.offenceCode = offenceCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(float fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getDriverTRN() {
		return driverTRN;
	}

	public void setDriverTRN(String driverTRN) {
		this.driverTRN = driverTRN;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverDOB() {
		return driverDOB;
	}

	public void setDriverDOB(String driverDOB) {
		this.driverDOB = driverDOB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getParish() {
		return parish;
	}

	public void setParish(String parish) {
		this.parish = parish;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public JcfOfficer getBadgeNum() {
		return badgeNum;
	}

	public void setBadgeNum(JcfOfficer badgeNum) {
		this.badgeNum = badgeNum;
	}

	public JcfOfficer getName() {
		return name;
	}

	public void setName(JcfOfficer name) {
		this.name = name;
	}

	public JcfOfficer getAssignedStation() {
		return assignedStation;
	}

	public void setAssignedStation(JcfOfficer assignedStation) {
		this.assignedStation = assignedStation;
	}
	
	public JcfOfficer getOfficer() {
		return officer;
	}

	public void setOfficer(JcfOfficer officer) {
		this.officer = officer;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public float generateFine() {
		
		switch (offenceCode) {
		
		case 1:
			fineAmount = 2500f;
			break;
			
		case 2:
			fineAmount = 5000f;
			break;
			
		case 3:
			fineAmount = 10000f;
			break;
			
		default:
			System.out.println("Incorrect code");
		}
		
		return fineAmount;
	}

	@Override
	public String toString() {
		return "Ticket [ticketNo=" + ticketNo + ", issueDate=" + issueDate + ", offenceCode=" + offenceCode
				+ ", description=" + description + ", fineAmount=" + fineAmount + ", licensePlate=" + licensePlate
				+ ", driverTRN=" + driverTRN + ", driverName=" + driverName + ", driverDOB=" + driverDOB + ", address="
				+ address + ", contactNo=" + contactNo + ", badgeNum=" + badgeNum + ", name=" + name
				+ ", assignedStation=" + assignedStation + "]";
	}
	
	public static void createTicket(Ticket ticket) {
		
		try(Connection conn = DBConnection.getConnection()) {
			
			final String sql = "INSERT into ticket(ticketNo, issueDate, offenceCode, description, fineAmount, licensePlate, driverTRN, driverName, driverDOB, address, parish, contactNo, badgeNum, name, assignedStation, paymentStatus) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  ticket.getTicketNo());
			ps.setDate(2, ticket.getIssueDate());
			ps.setInt(3, ticket.getOffenceCode());
			ps.setString(4, ticket.getDescription());
			ps.setFloat(5, ticket.generateFine());
			ps.setString(6, ticket.getLicensePlate());
			ps.setString(7, ticket.getDriverTRN());
			ps.setString(8, ticket.getDriverName());
			ps.setString(9, ticket.getDriverDOB());
			ps.setString(10, ticket.getAddress());
			ps.setString(11, ticket.getParish());
			ps.setString(12, ticket.getContactNo());
			ps.setInt(13, ticket.getOfficer().getBadgeNo());
			ps.setString(14, ticket.getOfficer().getName());
			ps.setString(15, ticket.getOfficer().getAssignedStation());
			ps.setString(16, ticket.getPaymentStatus());
			
			//System.out.println("Parish = " + ticket.getParish()); debugging statement
			ps.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	

}
