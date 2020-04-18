package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import dbconnection.DBConnection;
import javax.ws.rs.FormParam;

public class Patient {
	// A common method to connect to the DB
	
	public String insertPatient(String Name, String age, String nic, String phoneNo) {
		String output = "";
		try {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into patients(`userID`,`patientName`,`age`,`nic`,`phoneNo`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Name);
			preparedStmt.setInt(3, Integer.parseInt(age));
			preparedStmt.setString(4, nic);
			preparedStmt.setInt(5, Integer.parseInt(phoneNo));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPatients() {
		String output = "";
		try {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><th>Patient Name</th><th>Age</th><th>NIC</th><th>Contact No</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from patients";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String UserID = Integer.toString(rs.getInt("UserID"));
				String patientName = rs.getString("PatientName");
				String age = Integer.toString(rs.getInt("Age"));
				String nic = rs.getString("NIC");
				String phoneNo = Integer.toString(rs.getInt("PhoneNo"));
				// Add into the html table
				output += "<tr><td>" + patientName + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + phoneNo + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"patients.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"UserID\" type=\"hidden\" value=\"" + UserID + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the patient details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePatient(String ID, String name, String age, String nic, String pNo) {
		String output = "";
		try {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE patients SET patientName=?,age=?,nic=?,phoneNo=? WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, Integer.parseInt(age));
			preparedStmt.setString(3, nic);
			preparedStmt.setInt(4, Integer.parseInt(pNo));
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the patient details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePatients(String userID) {
		String output = "";
		try {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from patients where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}